package com.capital.one.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capital.one.dao.RedemptionDaoImpl;
import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;

@Service
public class RedeemService {
	
	@Autowired
	RedemptionDaoImpl redeemDao;
	private static Logger log = Logger.getRootLogger();
	
	public List<Credit> creditType() {		
		log.info("Credit Types requested");
		return redeemDao.getCreditType();
	}
	
	public List<Award> awardsList() {		
		System.out.println("In Service");
		log.info("Awards List GET Call");
		return redeemDao.getAwardsList();	
	}

}
