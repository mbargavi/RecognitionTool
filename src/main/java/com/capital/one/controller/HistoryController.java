package com.capital.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Recognition;
import com.capital.one.service.HistoryService;

@Controller
public class HistoryController {

	@Autowired
	HistoryService hs;

	@RequestMapping(value = "/HistoricalGiven/{empId}", method = RequestMethod.GET)
	public @ResponseBody List<Recognition> getRecognitionsgivenhistory(@PathVariable("empId") int empID) {
		System.out.println(" im in historucalGiven");
		List<Recognition> myrecog = hs.getRecognitionsGiven(empID);
		return myrecog;

	}

	@RequestMapping(value = "/HistoricalEarned", method = RequestMethod.GET)
	// public @ResponseBody List<Recognition> getRecognitionshistory(int empid)
	// {
	public @ResponseBody List<Recognition> getRecognitionshistory(int empId, int teamId) {
		System.out.println("In History controller");
		List<Recognition> myrecog = hs.getRecognitionsEarned(empId, teamId);
		return myrecog;
	}

}
