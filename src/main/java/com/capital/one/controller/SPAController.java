package com.capital.one.controller;


import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capital.one.dao.DAOUtilities;

@Controller
@RequestMapping("page")
public class SPAController {
	
	Connection con;
	
	@RequestMapping
	public String getSPA() {
		System.out.println("Got to getSPA() method");
		try {
			con = DAOUtilities.getConnection();
			System.out.println("Database product name = " + con.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/static/dist/index.html";
	}
	
}
