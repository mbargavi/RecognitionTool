package com.capital.one.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeDao;

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

}
