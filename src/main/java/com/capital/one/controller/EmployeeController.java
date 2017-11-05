package com.capital.one.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.Employee;
//import com.capital.one.model.User;
import com.capital.one.service.EmployeeService;
import com.capital.one.service.LoginService;
import com.capital.one.service.RedeemService;


@CrossOrigin(origins="*")
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	RedeemService rs;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getSearchList", method=RequestMethod.GET)
	public @ResponseBody List<String[]> getListToSearch(){
		List<String[]> myList =  es.getSearchList();
		
		//Commented out real line above and filling in dummy data to test return
//		List<String[]> myList = new ArrayList<String[]>();
//		String[] listOne = {"Employee","1","dcrites","David","Crites","dcrites@place.com"};
//		String[] listTwo = {"Team","1","mastermind",null,null,"teammastermind@capitalone.com"};
//		myList.add(listOne);
//		myList.add(listTwo);
		return myList;
	}
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value="/login", method=RequestMethod.GET)
	  public @ResponseBody ResponseEntity<Object> getUsernamepassword(@RequestParam(value="UserId") String username,@RequestParam(value="Password") String password){
	    //public @ResponseBody ResponseEntity<Object> getUsernamepassword(@RequestParam String username,@RequestParam String password){
		System.out.println("here!!");
		  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");		
		  Employee employee = (Employee) context.getBean("employee");
		  LoginService loginService = (LoginService)context.getBean("loginService");
		  employee.setUserName(username);
		  employee.setPassword(password);
	       
	        employee = loginService.authenticateUser(employee.getUserName(), employee.getPassword());
			return ResponseEntity.ok(employee);
	    }	
	  
	  @GetMapping("/creditType")
	  public @ResponseBody List<Credit> validate() {
			List<Credit> creditTypes = rs.creditType();
			return creditTypes;
		}
	  
	  @GetMapping("/awardsList")
	  public @ResponseBody List<Award> awardsList() {
			List<Award> awardsList = rs.awardsList();
			System.out.println("awards" + awardsList);
			return awardsList;
		}
	  
}
