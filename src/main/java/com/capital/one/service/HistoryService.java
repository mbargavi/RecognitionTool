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
	
	public List<String[]> getRecognitionsGiven(int empId) {
		List<Recognition> givenList = new ArrayList<Recognition>();
		givenList = recDao.getHistoricalGiven(empId).stream()
				.filter(element -> element.getEmpNominatorId()==empId)
				.collect(Collectors.toList());
		
		List<String[]> recStringList = new ArrayList<>();
		
		for (Recognition item: givenList) {
			String[] recStringArray = new String[5];
			String tempName;
			String recType;
			if (item.getEmpNomineeId() == 0) {
				tempName = empDao.getTeam(item.getTeamNomineeId()).getTeamName();
				recType = "Team";
			}
			else {
				tempName = empDao.getEmployee(item.getEmpNomineeId()).getFirstName() + " " + empDao.getEmployee(item.getEmpNomineeId()).getLastName();
				recType = "Employee";
			}
			recStringArray[0] = tempName;
			recStringArray[1] = recType;
			recStringArray[2] = String.valueOf(item.getCreditAmount());
			
			if (item.getCreditTypeId() == 1) {
				recStringArray[3] = "Credits";
			}
			else {
				recStringArray[3] = "Cap One Bucks";
			}
			recStringArray[4] = String.valueOf(item.getDate());
			
			recStringList.add(recStringArray);
		}
		
		return recStringList;
	}

	public List<String[]> getRecognitionsEarned(int empId, int teamId) {
		System.out.println("recognition earned!!!! serviice" + empId + teamId);
		List<Recognition> earnedList = new ArrayList<Recognition>();
		
		earnedList = recDao.getHistoricalEarned(empId, teamId).stream()
				.filter(element -> (element.getEmpNomineeId()==empId || element.getTeamNomineeId()==teamId))
				.collect(Collectors.toList());
			
		List<String[]> recStringList = new ArrayList<>();
		
		for (Recognition item: earnedList) {
			String[] recStringArray = new String[5];
			String recType;
			if (item.getEmpNomineeId() == 0) {
				recType = "Team";
			}
			else {
				recType = "Employee";
			}
			recStringArray[0] = empDao.getEmployee(item.getEmpNominatorId()).getFirstName() + " " + empDao.getEmployee(item.getEmpNominatorId()).getLastName();
			recStringArray[1] = recType;
			recStringArray[2] = String.valueOf(item.getCreditAmount());
			
			if (item.getCreditTypeId() == 1) {
				recStringArray[3] = "Credits";
			}
			else {
				recStringArray[3] = "Cap One Bucks";
			}
			recStringArray[4] = String.valueOf(item.getDate());
			
			recStringList.add(recStringArray);
		}
		
		return recStringList;
	}

	
}
