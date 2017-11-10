package com.capital.one.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.capital.one.service.RecognitionService;

@CrossOrigin(origins="*")
@Controller
public class RecognitionController {
	
	@Autowired
	RecognitionService rs;
	Logger log = RootLogger.getLogger("RecognitionController");
	

	@RequestMapping("/getHistMetrics/{empId}")
	public  @ResponseBody List<Integer> getHistMetrics(@PathVariable("empId") int empId){
		log.debug("Got to getHistMetrics method in Recognition Controller");
		List<Integer> myList =  rs.getHistoricalMetrics(empId);
		return myList;
	}

	

}
