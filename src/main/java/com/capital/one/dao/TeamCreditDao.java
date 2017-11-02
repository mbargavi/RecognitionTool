package com.capital.one.dao;

public interface TeamCreditDao {
	
	/***
	 * Pass in the Team Id and the Credit Type Id to determine the Credit balance earned to spend on redemptions
	 * @param empId
	 * @param credTypeId
	 * @return
	 */
	int retrieveTeamCreditEarnedBalance(int empId, int credTypeId);
	
	/***
	 * Once a team redemption is made, this needs to be called with the amount of credits used to decrease the credits earned balance.
	 * @param teamId - Id of the team the redemption is being made for
	 * @param credTypeId - the type of credit that is being redeemed
	 * @param credAmount - the amount of credits of said type that are being redeemed.
	 */
	void updateTeamCreditsEarnedBalance(int teamId, int credTypeId, int credAmount);

}
