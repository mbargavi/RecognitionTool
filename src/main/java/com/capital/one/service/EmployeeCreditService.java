package com.capital.one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capital.one.dao.EmployeeCreditDao;

@Service
public class EmployeeCreditService {

	@Autowired
	EmployeeCreditDao employeeCreditDaoImpl;
	
	  public int getCreditsToGive(int empId) {
		  int credits=  employeeCreditDaoImpl.retrieveEmpCreditToGiveBalance(empId);
		  return credits;
		}

	public int getCreditsEarned(int empId) {
		 int credits=  employeeCreditDaoImpl.retrieveEmpCreditEarnedBalance(empId);
		  return credits;
	}
}
