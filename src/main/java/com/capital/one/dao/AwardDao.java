package com.capital.one.dao;

public interface AwardDao {
	
	/***
	 * This function exists if we want to allow some superuser or manager to enter new awards to add to the award list.
	 * The function might need to be updated later to pass in an object if we are allowing the superuser to upload a photo
	 * to be displayed for the item.  We will likely not use this method
	 * @param awardName  - The name of the award item
	 * @param awardType  - The type of item, current types: gift card, gift, service, swag
	 * @param creditCost - The cost of the item in credits
	 */
	void insertAward(String awardName, String awardType, int creditCost);

}
