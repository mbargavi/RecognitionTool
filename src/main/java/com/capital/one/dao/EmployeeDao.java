package com.capital.one.dao;

import java.util.List;

import com.capital.one.datamodelbeans.Employee;

public interface EmployeeDao {
	
	/***
	 * Used to authenticate the Employee trying to log on
	 * @param username
	 * @param password
	 * @return is an Employee if validated; should return null if not validated
	 */
	Employee authenticateUser(String username, String password);
	
	/***
	 * The assumption is that we have an employee object now and want to know the title name; we can pass in his/her title id to get the title name
	 * @param titleId is a attribute on the Employee object
	 * @return will be the Title Name, a string
	 */
	String getEmployeeTitle(int titleId);
	
	/***
	 * We want to do a combination query and pull all Employees and all Teams, but we will put the results into a list of String Arrays
	 * where we declare as a "Team" or an "Employee" with the first String, and then include ID, username/name, firstname [null for team],
	 * lastname [null for team], and email.  It will be this list that the search result box will search on in the Angular page.
	 * @return will be a custom list of Employee and Team information
	 * in the form ["(Team/Employee)", "(ID)", "(username/name)", "(firstname/null)", "(lastname/null)", "(email)"]
	 */
	List<String[]> getEmployeesAndTeams();
	
	/***
	 * After a recognition has been done, this method should be called to update an Employee's title if needed, and return the current title.
	 * This method should check Recognition Total, Title Threshold, and Employee Title Id to determine if a change is needed.
	 * @param empId - this is the only parameter needed to make the determination
	 * @return
	 */
	String updateEmployeeTitle(int empId);

}
