package com.capital.one.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.capital.one.dao.EmployeeDaoImpl;
import com.capital.one.datamodelbeans.Employee;


@Service
public class LoginService {
	private static Logger log = Logger.getRootLogger();
	public Employee authenticateUser(String username, String password) {
		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
		Employee employee = employeeDaoImpl.authenticateUser(username, password);
				log.info("employee with  " + username + " and password: " + password + " succesfully logged in");
				return employee;
			}
		
	}

	

