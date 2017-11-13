package com.capital.one.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeCreditDao;


@Controller
@RequestMapping("page")
public class SPAController {
	
	Connection con;

	Logger log = Logger.getLogger("SPAController");
	
	@Autowired
	EmployeeCreditDao employeeCreditDaoImpl;


	
	@RequestMapping
	public String getSPA() {
		System.out.println("Got to getSPA() method");
		
		// NOTE: Trying to test DAOUtilities connection to prevent future latency
//		try {
//			con = DAOUtilities.getConnection();
//			String sql1 = "SELECT firstname FROM employee WHERE employee_id=1;";
//			
//			Statement stmt = con.createStatement();
//
//
//			ResultSet rs = stmt.executeQuery(sql1);
//
//			rs.next();
//			String nameEmpOne = rs.getString("firstname");
//			log.info("DAOUtilities says: Employee Name of Employee One is " + nameEmpOne);
//			
//			System.out.println("Database product name = " + con.getMetaData().getDatabaseProductName());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// NOTE: Trying to test JDBCConfig connection to prevent future latency
		
//			int creditTotal = employeeCreditDaoImpl.retrieveEmpCreditEarnedBalance(1);
//			log.info("JDBCConfig says: Total Credits of Employee One is " + creditTotal);
		
		log.debug(System.getProperty("catalina.home")); //testing what this path is for other uses
		
		return "forward:/static/dist/index.html";
	}
	
}
