package com.capital.one.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.dao.AwardDaoImpl;
import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCredit;
import com.capital.one.datamodelbeans.TeamCredit;
import com.capital.one.service.RedeemService;

@CrossOrigin(origins = "*")
@Controller
public class RedemptionController {

	@Autowired
	RedeemService rs;

	@RequestMapping("/creditType")
	public @ResponseBody List<Credit> validate() {
		List<Credit> creditTypes = rs.creditType();
		return creditTypes;
	}

//	@RequestMapping("/awardsList")
//	public @ResponseBody List<Award> awardsList() {
//		List<Award> awardsList = rs.awardsList();
//		System.out.println("awards" + awardsList);
//		return awardsList;
//	}
	
	@RequestMapping(value="/awardsList",  method=RequestMethod.GET)
	public @ResponseBody List<Award> awardsList(@RequestParam(value="selectedCreditId", required=true) int selectedCreditId) {
		List<Award> awardsList = rs.awardsList(selectedCreditId);
		System.out.println("awards" + awardsList);
		return awardsList;
	}

	@RequestMapping(value="/personalCredits", method=RequestMethod.GET)
	public @ResponseBody List<EmployeeCredit> empCredits(@RequestParam(value="empId", required=true) int empId) {
		List<EmployeeCredit> empCredits = rs.empCreditList(empId);	
		return empCredits;
	}

	@RequestMapping(value="/teamCredits", method=RequestMethod.GET)
	public @ResponseBody List<TeamCredit> teamCredits(@RequestParam(value="teamId", required=true) int teamId) {
		System.out.println("in teamCredits" + teamId);
		List<TeamCredit> teamCredits = rs.teamCreditList(teamId);
		return teamCredits;
	}
}
