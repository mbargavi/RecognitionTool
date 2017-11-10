package com.capital.one.dao;

import java.util.List;

import com.capital.one.datamodelbeans.Recognition;

public interface RecognitionDao {
	
	/***
	 * This method will be used to insert a recognition record...the method should also kick off an email to the the nominator, the nominee,
	 * the nominee's manager, and optionally the nominator's manager.
	 * @param TEAMorEMPLOYEE - should be "TEAM" or "EMPLOYEE" to express what time of recognition is being done
	 * @param nominatorId - the Employee Id of the nominator
	 * @param nomineeId - this will be a Team ID or an Employee ID depending on the first parameter
	 * @param creditTypeId - which type of credit is being given to the nominee (ex. normal Credits or CapitalOneBucks)
	 * @param creditAmount - the amount of credits given to the nominee
	 */
	void insertRecognitionRecord(String TEAMorEMPLOYEE, int nominatorId, int nomineeId, int creditTypeId, int creditAmount);
	
	/***
	 * This method will return the full Recognition List that can be used for Recognition History.  Once retrieved it can be filtered
	 * to display just the recognitions desired.
	 * @return - a List of all recognitions
	 */
	List<Recognition> getRecognitionHistory();

	List<Recognition> getHistoricalGiven(int empId);


	List<Recognition> getHistoricalEarned(int empId, int teamId);
	
	int getTotalHistoricalGiven(int empId);
	
	int getTotalHistoricalEarned(int empId);

}
