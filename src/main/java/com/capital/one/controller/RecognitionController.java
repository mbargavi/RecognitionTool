package com.capital.one.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Recognition;
import com.capital.one.service.RecognitionService;

@CrossOrigin(origins="*")
@Controller
public class RecognitionController {
	
	@Autowired
	RecognitionService rs;
	Logger log = RootLogger.getLogger("RecognitionController");
	
	@Autowired
	RecognitionService recognitionService;
	
	@RequestMapping("/getHistMetrics/{empId}")
	public  @ResponseBody List<Integer> getHistMetrics(@PathVariable("empId") int empId){
		log.debug("Got to getHistMetrics method in Recognition Controller");
		List<Integer> myList =  rs.getHistoricalMetrics(empId);
		return myList;
	}
	
	@RequestMapping(value = "addRecognition", method=RequestMethod.POST)
	public ResponseEntity<Object> addRecognition(@RequestBody Recognition recognition){
		log.debug("Got To addRecognition method in Recognition Controller");
		try {
			recognitionService.addRecognitionService(recognition);
		} catch (Exception e) {
		 	return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
		}
		return null;
		
	}
	

}
