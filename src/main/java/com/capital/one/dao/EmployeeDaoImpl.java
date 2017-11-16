package com.capital.one.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capital.one.datamodelbeans.Employee;
import com.capital.one.datamodelbeans.Team;
import com.capital.one.datamodelbeans.Title;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private Employee employee;
	
	
	Logger log = Logger.getLogger("EmployeeDaoImpl");

	@Override
	public Employee authenticateUser(String username, String password) {
		// Initialize variables
		PreparedStatement preparedStmt = null;
		Connection conn = null;
		ResultSet prs;
		Employee employee = new Employee();

			try {
				conn = DAOUtilities.getConnection();
				

				//String sql = ("SELECT * FROM employee WHERE username = ? AND password = ?;");
				String sql = ("SELECT * FROM employee LEFT JOIN title ON employee.titleid = title.title_id where username=? AND password=?;");				// set up the prepared statement
				preparedStmt = conn.prepareStatement(sql);
				
				System.out.println(sql);
				// add the parameters to replace the question marks
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, password);

				prs = preparedStmt.executeQuery();
				
				prs.next();

				if (prs.getString("username")!=null) {
					log.debug("result set has data - populate authenticatedUser");
					employee.setEmployeeId(prs.getInt("employee_id"));
					employee.setFirstName(prs.getString("firstname"));
					employee.setLastName(prs.getString("lastname"));
					employee.setEmail(prs.getString("email"));
					employee.setUserName(username);
					employee.setPassword(password);
					employee.setRoleId(prs.getInt("roleid"));
					employee.setTeamId(prs.getInt("teamid"));
					
					employee.setTitle(new Title());
					
					employee.getTitle().setTitleId(this.updateEmployeeTitle(prs.getInt("employee_id")));
					employee.getTitle().setTitleName(this.getEmployeeTitle(employee.getTitle().getTitleId()));
					employee.setTitleId(employee.getTitle().getTitleId());
					
					log.info("Finished getting an authenticated user...returning him");
					
					return employee;  //this employee is autowired...hopefully that will work for us :)
				} else {
					return null;
				}
			}
			catch (SQLException sqle) {
				log.error("SQL Exception thrown");
				sqle.printStackTrace();
				return null;
			}

	}

	@Override
	public String getEmployeeTitle(int titleId) {
		// Set up needed variables
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String title = null;
		
		try {
			conn = DAOUtilities.getConnection();
			stmt = conn.createStatement();

			String sql = ("SELECT title_name FROM title WHERE title_id = " + titleId +";");

			rs = stmt.executeQuery(sql);

			rs.next();
			title = rs.getString("title_name");
			
			log.info("Finished getting an employee's title...returning it: " + title);
			return title;
			
		}
		catch (SQLException sqle) {
			log.error("SQL Exception thrown");
			sqle.printStackTrace();
			return null;
		}
				
	}

	@Override
	public List<String[]> getEmployeesAndTeams(int empID) {
		// Need to get a list of employees and teams and the results into a list of String arrays
		// ["(Team/Employee)", "(ID)", "(username/name)", "(firstname/null)", "(lastname/null)", "(email)"]
		
		// Set up needed variables
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<String[]> combinedList = new ArrayList<String[]>();
		
		String sql = "SELECT 'Employee' \"Type\", e.employee_id \"ID\", e.username \"Name\", e.firstname \"First Name\", e.lastname \"Last Name\", e.email \"Email\" \n" +
		   "FROM employee e UNION \n" +
	    "SELECT 'Team' \"Type\", t.team_id \"ID\", t.team_name \"Name\", NULL \"First Name\", NULL \"Last Name\",  t.team_email \"Email\" \n" +
	       "FROM team t;";
		
        try {
			conn = DAOUtilities.getConnection();

			stmt = conn.createStatement();
			
			log.debug("sql for getEmployeesAndTeams() is : " + sql);
			

			rs = stmt.executeQuery(sql);
			
//			Populating each String Array from the result set of Employees and Teams

			while (rs.next()) {
				String[] listItem = new String[6];
				
				listItem[0]=rs.getString("Type");				// Employee or Team
				listItem[1]= Integer.toString(rs.getInt("ID"));  // Employee ID or Team ID
				listItem[2]=rs.getString("Name");                // username or team_name
				listItem[3]=rs.getString("First Name");          // NULL for team records
				listItem[4]=rs.getString("Last Name");           // NULL for team records
				listItem[5]=rs.getString("Email");               // email or team_email

				log.debug(listItem);
				if(!( empID == rs.getInt("ID") && ("Employee".equals(rs.getString("Type"))) ) ) {
					combinedList.add(listItem);
				}
				
			}
			
			return combinedList;
        }catch (SQLException e) {
			e.printStackTrace();
		} 
        return null;
	}
	
	@Override
	public List<Employee> getRecipientList(int nomineeId, int nominatorId){
		// 1) Use nomineeId and nominatorId to pull back and populate first two employees
		// 2) Use teamId on both to look up Employee with that teamId and role of Manager to pull back and populate second two employees.
		// 3) Add Employees to List in correct order: Nominee, Nominator, NomineeManager, NominatorManager
		
		List<Employee> recipList = new ArrayList<Employee>();
		Employee nominee = this.getEmployee(nomineeId);
		Employee nominator = this.getEmployee(nominatorId);
		Employee nomineeManager = this.getManager(nominee.getTeamId());
		Employee nominatorManager = this.getManager(nominator.getTeamId());
		
		recipList.add(nominee);
		recipList.add(nominator);
		recipList.add(nomineeManager);
		recipList.add(nominatorManager);
		
		return recipList;
	}
	
	public Employee getEmployee(int empId) {
		
		// Initialize variables
				PreparedStatement preparedStmt = null;
				Connection conn = null;
				ResultSet prs;
				Employee employee = new Employee();

					try {
						conn = DAOUtilities.getConnection();
						

						String sql = ("SELECT * FROM employee WHERE employee_id=?;");				// set up the prepared statement
						preparedStmt = conn.prepareStatement(sql);
						
						System.out.println(sql);
						// add the parameters to replace the question marks
						preparedStmt.setInt(1, empId);

						prs = preparedStmt.executeQuery();
						
						prs.next();


						log.debug("populating employee");
						employee.setEmployeeId(prs.getInt("employee_id"));
						employee.setFirstName(prs.getString("firstname"));
						employee.setLastName(prs.getString("lastname"));
						employee.setEmail(prs.getString("email"));
						employee.setUserName(prs.getString("username"));
						employee.setRoleId(prs.getInt("roleid"));
						employee.setTeamId(prs.getInt("teamid"));
						
						log.info("Finished populating employee...returning him");
						
						return employee;

					}
					catch (SQLException sqle) {
						log.error("SQL Exception thrown");
							sqle.printStackTrace();
							return null;
					}
	}
	
public Team getTeam(int teamId) {
		
		// Initialize variables
				PreparedStatement preparedStmt = null;
				Connection conn = null;
				ResultSet prs;
				Team team = new Team();

					try {
						conn = DAOUtilities.getConnection();
						

						String sql = ("SELECT * FROM team WHERE team_id=?;");				// set up the prepared statement
						preparedStmt = conn.prepareStatement(sql);
						
						System.out.println(sql);
						// add the parameters to replace the question marks
						preparedStmt.setInt(1, teamId);

						prs = preparedStmt.executeQuery();
						
						prs.next();


						log.debug("populating employee");
						team.setTeamId(prs.getInt("team_id"));
						team.setTeamName(prs.getString("team_name"));
						team.setTeamEmail(prs.getString("team_email"));

						
						log.info("Finished populating team..returning it");
						
						return team;

					}
					catch (SQLException sqle) {
						log.error("SQL Exception thrown");
							sqle.printStackTrace();
							return null;
					}
	}
	
	public int getTeamManagerById(int nomineeId) {
		// Initialize variables
		PreparedStatement preparedStmt = null;
		Connection conn = null;
		ResultSet prs;
		int employeeId = 0;

			try {
				conn = DAOUtilities.getConnection();
				

				String sql = ("SELECT * FROM employee WHERE teamid=? and roleid=3;");				// set up the prepared statement
				preparedStmt = conn.prepareStatement(sql);
				
				System.out.println(sql);
				// add the parameters to replace the question marks
				preparedStmt.setInt(1,nomineeId);

				prs = preparedStmt.executeQuery();
				
				prs.next();


				log.debug("populating manager");
				employeeId = prs.getInt("employee_id");
				
				
				log.info("Found manager...returning his ID");
				
				return employeeId;

			}
			catch (SQLException sqle) {
				log.error("SQL Exception thrown");
					sqle.printStackTrace();
					return 0;
			}
	}
	
	private Employee getManager(int teamId) {
		
		// Initialize variables
				PreparedStatement preparedStmt = null;
				Connection conn = null;
				ResultSet prs;
				Employee employee = new Employee();

					try {
						conn = DAOUtilities.getConnection();
						

						String sql = ("SELECT * FROM employee WHERE teamid=? and roleid=3;");				// set up the prepared statement
						preparedStmt = conn.prepareStatement(sql);
						
						System.out.println(sql);
						// add the parameters to replace the question marks
						preparedStmt.setInt(1, teamId);

						prs = preparedStmt.executeQuery();
						
						prs.next();


						log.debug("populating manager");
						employee.setEmployeeId(prs.getInt("employee_id"));
						employee.setFirstName(prs.getString("firstname"));
						employee.setLastName(prs.getString("lastname"));
						employee.setEmail(prs.getString("email"));
						employee.setUserName(prs.getString("username"));
						employee.setRoleId(prs.getInt("roleid"));
						employee.setTeamId(prs.getInt("teamid"));
						
						log.info("Finished populating manager...returning him");
						
						return employee;

					}
					catch (SQLException sqle) {
						log.error("SQL Exception thrown");
							sqle.printStackTrace();
							return null;
					}
	}

	@Override
	public int updateEmployeeTitle(int empId) {
		
		// 1) Pull Employee record and get current Title ID
		// 2) Check Recognition History and get Sum of all Credits the given Employee has recognized others with
		// 3) Pull All Titles and get Title with Highest Threshold where Employee Credits Given is higher than threshold
		// 4) For that Title get Title ID and if same as current Title ID, no update but return Title Name
		// 5) If different Title ID than current Title ID, update Employee with new Title ID and return Title ID
		
		// Set up needed variables
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = null;
        Employee emp = new Employee();
        
        int creditsGivenOverTime;
        // RecognitionDao recDao = DAOUtilities.getRecognitionDao();
        int topQualifyingTitleId = 100;  // 100 is the title_id for beginner
        int topQualifyingThreshold = 0;
        String topQualifyingTitle = "Beginner";

        try {

            conn = DAOUtilities.getConnection();
            stmt = conn.createStatement();
            
            // STEP 1
            sql = ("SELECT * from employee WHERE employee_id = " + empId + ";");
            
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
			log.debug("populate employee for checking/updating title with empId = " + empId);
			emp.setEmployeeId(empId);
			emp.setFirstName(rs.getString("firstname"));
			emp.setLastName(rs.getString("lastname"));
			emp.setEmail(rs.getString("email"));
			emp.setUserName(rs.getString("username"));
			emp.setPassword(rs.getString("password"));
			emp.setRoleId(rs.getInt("roleid"));
			emp.setTeamId(rs.getInt("teamid"));
			emp.setTitleId(rs.getInt("titleid"));
			
			log.info("Finished populating the employee...now have current titleid: " + rs.getInt("titleid"));
			
			// STEP 2
//			log.info("Using streams and lambdas to filter the recognitions to just my guy and then sum the credits he has given");
//			creditsGivenOverTime = recDao.getRecognitionHistory().stream().filter(element -> element.getEmpNominatorId()==empId)
//																		.mapToInt(element -> element.getCreditAmount()).sum();
			
//			log.info("Trying just calling current RecognitionDao method");
//			creditsGivenOverTime = recDao.getTotalHistoricalGiven(empId);
			
			// Was getting NULL pointer trying to use Recognition Dao methods here even with autowired...going to straight query with
			// connection I already have, because I think the null pointer was the connection.
			
			sql = "SELECT SUM(credit_amount) \"sum_given\" FROM recognition WHERE emp_nominator_id=" + empId;
			
			rs = null; //reset
			rs = stmt.executeQuery(sql);
			rs.next();
			creditsGivenOverTime = rs.getInt("sum_given");
			
			
			// STEP 3
			sql = "SELECT * FROM title;";
			
			rs = null; //reset
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int tempId = rs.getInt("title_id");
				int tempThreshold = rs.getInt("credit_threshold");
				String tempTitle = rs.getString("title_name");
				
				if (creditsGivenOverTime > tempThreshold) {    //we gave more credits than the threshold we are looking at
					if (topQualifyingTitleId == 100) {         //we just started
						topQualifyingTitleId = tempId;
						topQualifyingThreshold = tempThreshold;
						topQualifyingTitle = tempTitle;
					} else if (tempThreshold > topQualifyingThreshold){  //the current threshold we qualify for is better than the last one recorded
						topQualifyingTitleId = tempId;
						topQualifyingThreshold = tempThreshold;
						topQualifyingTitle = tempTitle;
					}
				}
			}
			
			// STEP 4 and 5
			if (topQualifyingTitleId != emp.getTitleId()) {  //Only update the database if the title has changed
				emp.setTitleId(topQualifyingTitleId);
	            sql = ("UPDATE employee SET titleid = " + topQualifyingTitleId + " WHERE employee_id= " + empId + ";");

	            stmt.executeUpdate(sql);
	            return topQualifyingTitleId;
			}else {
				log.info("Was going to return 0 for no update but decided I will return the real title ID");
				return emp.getTitleId();
			}
            

        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.error("Failed to update with SQL so return -1");
        return -1;
	}


}
