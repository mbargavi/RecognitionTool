package com.capital.one.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeCreditDao;
import com.capital.one.dao.RecognitionDao;

@Service
public class RecognitionService {
	
	@Autowired
	RecognitionDao recDao;
	
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

}
