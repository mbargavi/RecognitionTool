package com.capital.one.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.capital.one.datamodelbeans.Award;
import com.capital.one.datamodelbeans.Credit;
import com.capital.one.datamodelbeans.EmployeeCredit;
import com.capital.one.datamodelbeans.Redemption;
import com.capital.one.datamodelbeans.TeamCredit;

public interface RedemptionDao {
	
	
	
	/***
	 * This function is used to request a redemption, using some of an employee or teams credits to get a gift/service
	 * @param redeemerId - employee Id of the person requesting the redemption
	 * @param teamRedemptionId - this is NULL if the person is redeeming for himself/herself.  It is only populated if this is a team redemption.
	 * @param creditsUsed - the amount of credits used
	 * @param creditTypeId - the type of credit being used
	 * @param awardId - the id of the chosen award
	 */
	void insertRedemptionRequest(int redeemerId, int teamRedemptionId, int creditsUsed, int creditTypeId, int awardId);
	
	/***
	 * This method will return the full Recognition List that can be used for Recognition History.  Once retrieved it can be filtered
	 * to display just the recognitions desired.
	 * @return - a List of all recognitions
	 */
	List<Redemption> getRedemptionHistory();
	
	
	List<Credit> getCreditType();
	
	List<Award> getAwardsList(int creditId);

	List<EmployeeCredit> getempCredits(int empId);

	List<TeamCredit> getteamCredits(int teamId);

}
