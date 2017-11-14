package com.capital.one.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCreditName;
import com.capital.one.datamodelbeans.Redemption;
import com.capital.one.datamodelbeans.TeamCreditWithName;
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

	// @RequestMapping("/awardsList")
	// public @ResponseBody List<Award> awardsList() {
	// List<Award> awardsList = rs.awardsList();
	// System.out.println("awards" + awardsList);
	// return awardsList;
	// }

	@RequestMapping(value = "/awardsList", method = RequestMethod.GET)
	public @ResponseBody List<Award> awardsList(
			@RequestParam(value = "selectedCreditId", required = true) int selectedCreditId) {
		List<Award> awardsList = rs.awardsList(selectedCreditId);
		System.out.println("awards" + awardsList);
		return awardsList;
	}

	@RequestMapping(value = "/personalCredits", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeCreditName> empCredits(
			@RequestParam(value = "empId", required = true) int empId) {
		List<EmployeeCreditName> empCredits = rs.empCreditList(empId);
		return empCredits;
	}

	@RequestMapping(value = "/teamCredits", method = RequestMethod.GET)
	public @ResponseBody List<TeamCreditWithName> teamCredits(
			@RequestParam(value = "teamId", required = true) int teamId) {
		System.out.println("in teamCredits" + teamId);
		List<TeamCreditWithName> teamCredits = rs.teamCreditList(teamId);
		return teamCredits;
	}

	@RequestMapping(value = "/updateRedemptionRequest", method = RequestMethod.POST)
	public @ResponseBody boolean updateRedemption(@RequestBody Redemption redemption) {
		System.out.println("in insert redemption" + redemption.getCreditTypeToUse());
		if (redemption.getCreditTypeToUse() == "Team") {
			return rs.insertTeamRedeem(redemption);
		} else
			return rs.insertEmpRedeem(redemption);
	}

	@RequestMapping(value = "/insertTeamRedemptionRequest", method = RequestMethod.POST)
	public @ResponseBody boolean insertTeamRedemption(@RequestBody Redemption redemption) {
		System.out.println("in insert Team redemption" + redemption.getTeamRedemptionId());
		return rs.insertTeamRedeem(redemption);
	}
}
