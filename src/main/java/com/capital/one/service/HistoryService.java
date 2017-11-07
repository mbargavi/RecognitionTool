package com.capital.one.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeDao;
import com.capital.one.dao.RecognitionDao;
import com.capital.one.datamodelbeans.Employee;
import com.capital.one.datamodelbeans.Recognition;

@Service
public class HistoryService {
	
	RecognitionDao recDao = DAOUtilities.getRecognitionDao();
	EmployeeDao empDao = DAOUtilities.getEmployeeDao();
	
	/***
	 * Getting full recognition list and filtering for given
	 * @return - A list of Recognitions given to others 
	 * 
	 */
	public List<Recognition> getRecognitionsGiven(int empId) {
		
		List<Recognition> givenList = new ArrayList<Recognition>();
		givenList = recDao.getRecognitionHistory().stream()
				.filter(element -> element.getEmpNominatorId()==empId)
				.collect(Collectors.toList());
		
		return givenList;
	}
	/***
	 * Getting Full recognition list and filtering for earned
	 * @return - A list of Recognitions earned
	 */
	public List<Recognition> getRecognitionsEarned(int empId, int teamId) {
		
		
		List<Recognition> earnedList = new ArrayList<Recognition>();
		earnedList = recDao.getRecognitionHistory().stream()
				.filter(element -> (element.getEmpNomineeId()==empId || element.getTeamNomineeId()==teamId))
				.collect(Collectors.toList());
		
		return earnedList;
	}

}
