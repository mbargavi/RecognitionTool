package com.capital.one.dao;

public interface EmployeeCreditDao {
	
	/***
	 * Pass in the Employer Id and the Credit Type Id to determine the Credit balance for giving to others
	 * @param empId
	 * @param credTypeId
	 * @return
	 */
	int retrieveEmpCreditToGiveBalance(int empId);
	
	/***
	 * Once an employee recognition is made, this needs to be called with the amount of credits given to decrease the credits to give balance.
	 * @param empId - Id of the employee making the recognition
	 * @param credTypeId - the type of credit that is being given
	 * @param credAmount - the amount of credits of said type that are being given.
	 * @throws Exception 
	 */
	void updateEmpCreditsToGiveBalance(int empId, int credTypeId) throws Exception;
	
	/***
	 * Pass in the Employer Id and the Credit Type Id to determine the Credit balance earned to spend on redemptions
	 * @param empId
	 * @param credTypeId
	 * @return
	 */
	int retrieveEmpCreditEarnedBalance(int empId);
	
	/***
	 * Once an employee redemption is made, this needs to be called with the amount of credits used to decrease the credits earned balance.
	 * @param empId - Id of the employee making the redemption
	 * @param credTypeId - the type of credit that is being redeemed
	 * @param credAmount - the amount of credits of said type that are being redeemed.
	 */
	void updateEmpCreditsEarnedBalance(int empId, int credTypeId);

	public int creditBucksAvailable(int empId);
	
	public int capOneBucksAvailable(int empId);

}
