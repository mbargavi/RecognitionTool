package com.capital.one.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capital.one.dao.RedemptionDao;
import com.capital.one.dao.RedemptionDaoImpl;
import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCredit;
import com.capital.one.datamodelbeans.TeamCredit;

@Service
public class RedeemService {
	
	@Autowired
	RedemptionDao redeemDao;
	private static Logger log = Logger.getRootLogger();
	
	public List<Credit> creditType() {		
		log.info("Credit Types requested");
		return redeemDao.getCreditType();
	}
	
	public List<Award> awardsList(int creditId) {		
		System.out.println("In Service");
		log.info("Awards List GET Call");
		return redeemDao.getAwardsList(creditId);	
	}

	public List<EmployeeCredit> empCreditList(int empId) {
		System.out.println("In Service");
		log.info("Emp Credit List GET Call");
		return redeemDao.getempCredits(empId);
	}

	public List<TeamCredit> teamCreditList(int teamId) {
		System.out.println("In Service");
		log.info("Team Credit List GET Call");
		return redeemDao.getteamCredits(teamId);
	}

}
