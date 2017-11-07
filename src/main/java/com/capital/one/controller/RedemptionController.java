package com.capital.one.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capital.one.dao.AwardDaoImpl;
import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.service.RedeemService;

@CrossOrigin(origins="*")
@Controller
public class RedemptionController {
	
	@Autowired
	RedeemService rs;
	
	
	  @RequestMapping("/creditType")
	  public @ResponseBody List<Credit> validate() {
			List<Credit> creditTypes = rs.creditType();
			return creditTypes;
		}
	  
	  @RequestMapping("/awardsList")
	  public @ResponseBody List<Award> awardsList() {
		    List<Award> awardsList = rs.awardsList();
			System.out.println("awards" + awardsList);
			return awardsList;
		}
}
