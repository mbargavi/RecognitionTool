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
					employee.setTitleId(prs.getInt("titleid"));
					employee.setTitle(new Title());
					employee.getTitle().setTitleId(prs.getInt("titleid"));
					employee.getTitle().setTitleName(this.getEmployeeTitle(prs.getInt("titleid")));
					
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
	public List<String[]> getEmployeesAndTeams() {
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
				
				combinedList.add(listItem);
			}
			
			return combinedList;
        }catch (SQLException e) {
			e.printStackTrace();
		} 
        return null;
	}

	@Override
	public String updateEmployeeTitle(int empId) {
		
		// 1) Pull Employee record and get current Title ID
		// 2) Check Recognition History and get Sum of all Credits the given Employee has recognized others with
		// 3) Pull All Titles and get Title with Highest Threshold where Employee Credits Given is higher than threshold
		// 4) For that Title get Title ID and if same as current Title ID, no update but return Title Name
		// 5) If different Title ID than current Title ID, update Employee with new Title ID and return Title Name
		
		// Set up needed variables
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = null;
        
        int creditsGivenOverTime;
        RecognitionDao recDao = DAOUtilities.getRecognitionDao();
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
            
			log.debug("populate employee for checking/updating title");
			employee.setEmployeeId(empId);
			employee.setFirstName(rs.getString("firstname"));
			employee.setLastName(rs.getString("lastname"));
			employee.setEmail(rs.getString("email"));
			employee.setUserName(rs.getString("username"));
			employee.setPassword(rs.getString("password"));
			employee.setRoleId(rs.getInt("roleid"));
			employee.setTeamId(rs.getInt("teamid"));
			employee.setTitleId(rs.getInt("titleid"));
			
			log.info("Finished populating the employee...now have current titleid");
			
			// STEP 2
			log.info("Using streams and lambdas to filter the recognitions to just my guy and then sum the credits he has given");
			creditsGivenOverTime = recDao.getRecognitionHistory().stream().filter(element -> element.getEmpNominatorId()==empId)
																		.mapToInt(element -> element.getCreditAmount()).sum();
			
			
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
			
			// STEP 4
			if (topQualifyingTitleId != employee.getTitleId()) {  //Only update the database if the title has changed
				employee.setTitleId(topQualifyingTitleId);
	            sql = ("UPDATE employee SET titleid = " + topQualifyingTitleId + ";");

	            stmt.executeUpdate(sql);

			}
			// STEP 5
            return topQualifyingTitle;

        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}

}
