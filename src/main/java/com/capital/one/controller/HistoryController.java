package com.capital.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Recognition;
import com.capital.one.service.HistoryService;

@Controller
public class HistoryController {

	@Autowired
	HistoryService hs;
	
	@RequestMapping(value = "/creditsEarned", method=RequestMethod.GET)
	public @ResponseBody List<Recognition> getRecognitionshistory(int empid) {
		System.out.println("In History controller");
		List<Recognition> myrecog = hs.getRecognitionsGiven(empid);
		return myrecog;
		
	}
	
	@RequestMapping(value = "/creditsGiven", method=RequestMethod.GET)
	public @ResponseBody List<Recognition> getRecognitionsgivenhistory(int empId, int teamId) {
		List<Recognition> myrecog = hs.getRecognitionsEarned(empId, teamId);
		return myrecog;
		
	}
	
	
}
