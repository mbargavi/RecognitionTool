package com.capital.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.dao.EmployeeCreditDao;
import com.capital.one.datamodelbeans.Employee;
import com.capital.one.service.EmployeeCreditService;

@CrossOrigin(origins="*")
@Controller
public class EmployeeCreditController {

	
	@Autowired
	Employee employee;
	
	@Autowired
	EmployeeCreditDao employeeCreditDaoImpl;
	
	@Autowired
	EmployeeCreditService employeeCreditService;
	
	@RequestMapping("/creditsToGive/{empId}")
	  public @ResponseBody int creditsToGive(@PathVariable("empId") int empId) {
		System.out.println("!!!+ empId");
		  int credits=  employeeCreditService.getCreditsToGive(empId);
		  return credits;
		}
	
	@RequestMapping("/creditsEarned/{empId}")
	  public @ResponseBody int creditsEarned(@PathVariable("empId") int empId) {
		System.out.println("!!!+ empId");
		  int credits=  employeeCreditService.getCreditsEarned(empId);
		  return credits;
		}
	
	
}
