package com.capital.one.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capital.one.dao.DAOUtilities;
import com.capital.one.dao.EmployeeDao;
import com.capital.one.dao.RecognitionDao;
import com.capital.one.datamodelbeans.Employee;
import com.capital.one.datamodelbeans.Recognition;
@Service
public class HistoryService {
	
	@Autowired
	RecognitionDao recDao;
	
	// Employee employee;
      
//	RecognitionDao recDao = DAOUtilities.getRecognitionDao();
	EmployeeDao empDao = DAOUtilities.getEmployeeDao();
	
	public List<Recognition> getRecognitionsGiven(int empId) {
		List<Recognition> givenList = new ArrayList<Recognition>();
		givenList = recDao.getHistoricalGiven(empId).stream()
				.filter(element -> element.getEmpNominatorId()==empId)
				.collect(Collectors.toList());
		
		return givenList;
	}

	public List<Recognition> getRecognitionsEarned(int empId, int teamId) {
		List<Recognition> earnedList = new ArrayList<Recognition>();
		earnedList = recDao.getRecognitionHistory().stream()
				.filter(element -> (element.getEmpNomineeId()==empId || element.getTeamNomineeId()==teamId))
				.collect(Collectors.toList());
		
		return earnedList;
	}

	
}
