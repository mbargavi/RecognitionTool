package com.capital.one.service;

import java.util.ArrayList;
import java.util.List;

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

	public List<Integer> getCreditsToGiveByType(int empId) {
		List<Integer> availableCredits = new ArrayList<>(); 
		availableCredits.add(employeeCreditDaoImpl.creditBucksAvailable(empId));
		availableCredits.add(employeeCreditDaoImpl.capOneBucksAvailable(empId));
		return availableCredits;
	}
	
}
