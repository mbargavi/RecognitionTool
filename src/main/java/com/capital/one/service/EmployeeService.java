package com.capital.one.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeDao;
import com.capital.one.datamodelbeans.Title;

@Service
public class EmployeeService {
	
	EmployeeDao empDao = DAOUtilities.getEmployeeDao();
	
	/***
	 * No real business logic to execute...just returning the UNION list of certain employee and team fields for searching.
	 * @return - A list of String Arrays in the form: 
	 * ["(Team/Employee)", "(ID)", "(username/name)", "(firstname/null)", "(lastname/null)", "(email)"]
	 */
	public List<String[]> getSearchList() {
		return empDao.getEmployeesAndTeams();
	}
	
	/***
	 * If the call to updateEmployeeTitle returns a new TitleId (>0) then we will also get the new Title name and put both of them
	 * in a Title object that we send back;
	 * @param empId - the employerID is passed in so we can look up credits given and determine if need to update title
	 * @return Title : TitleId = -1 for error, 0 for no update, else a titleID if title was updated.
	 *                 TitleName = a new Title Name if TitleId(>0) or null if there is no update
	 */
	public Title updateTitle(int empId) {
		
		String newTitleName = null;
		Title newTitle = new Title();
		int newTitleId = empDao.updateEmployeeTitle(empId);
		if(newTitleId > 0) {
			newTitleName = empDao.getEmployeeTitle(newTitleId);
		}
		newTitle.setTitleName(newTitleName);
		newTitle.setTitleId(newTitleId);
		return newTitle;
	}

}
