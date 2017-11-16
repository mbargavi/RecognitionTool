package com.capital.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Recognition;
import com.capital.one.service.HistoryService;

@Controller
@CrossOrigin(origins="*")
public class HistoryController {

	@Autowired
	HistoryService hs;

	@RequestMapping(value = "/HistoricalGiven/{empId}", method = RequestMethod.GET)
	public @ResponseBody List<String[]> getRecognitionsgivenhistory(@PathVariable("empId") int empID) {
		List<String[]> myrecog = hs.getRecognitionsGiven(empID);
		return myrecog;

	}

	@RequestMapping(value = "/HistoricalEarned", method = RequestMethod.GET)
	public @ResponseBody List<String[]> getRecognitionsEarnedhistory(int empId, int teamId) {
		System.out.println("In History controller" + empId + teamId);
		List<String[]> myrecog = hs.getRecognitionsEarned(empId, teamId);
		return myrecog;
	}

}
