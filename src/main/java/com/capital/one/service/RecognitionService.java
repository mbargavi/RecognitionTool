package com.capital.one.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capital.one.dao.EmployeeCreditDao;
import com.capital.one.dao.RecognitionDao;
import com.capital.one.dao.TeamCreditDao;
import com.capital.one.datamodelbeans.Recognition;

@Service
public class RecognitionService {
	
	@Autowired
	RecognitionDao recDao;
	
	@Autowired
	RecognitionDao recognitionDao;
	
	@Autowired
	TeamCreditDao teamCreditDao;
	
	@Autowired
	EmployeeCreditDao employeeCreditDao;
	
	/***
	 * No real business logic to execute...just returning the UNION list of certain employee and team fields for searching.
	 * @return - A list of String Arrays in the form: 
	 * ["(Team/Employee)", "(ID)", "(username/name)", "(firstname/null)", "(lastname/null)", "(email)"]
	 */
	public List<Integer> getHistoricalMetrics(int empId) {
		List<Integer> histMetricArray = new ArrayList<Integer>();
		
		int histGiven = recDao.getTotalHistoricalGiven(empId);
		int histEarned = recDao.getTotalHistoricalEarned(empId);
		
		histMetricArray.add(histGiven);
		histMetricArray.add(histEarned);
		
		return histMetricArray;
	}
	

	
	public void addRecognitionService( Recognition recognition) throws Exception{
		int creditTypeid = recognition.getCreditTypeId();
		int nominatorId = recognition.getEmpNominatorId();
		int nomineeId = recognition.getNomineeId();
		String nominee = recognition.getNominee();
		System.out.println("!!!!!!" + creditTypeid + nominatorId + nomineeId + nominee );
		
		
		if (nominee.equals("Employee")) {
			System.out.println(" im here 1 st place");
			employeeCreditDao.updateEmpCreditsToGiveBalance(nominatorId,creditTypeid);
			employeeCreditDao.updateEmpCreditsEarnedBalance(nomineeId,creditTypeid);
			recognitionDao.insertRecognitionRecord(creditTypeid,nominatorId,nomineeId,nominee);
		}
		else {
			System.out.println(" im here 2nd place");
			employeeCreditDao.updateEmpCreditsToGiveBalance(nominatorId,creditTypeid);
			teamCreditDao.updateTeamCreditsEarnedBalance(nomineeId, creditTypeid);
			recognitionDao.insertRecognitionRecord(creditTypeid,nominatorId,nomineeId,nominee);
		
		}
		}
		
		
	

}
