package com.capital.one.dao;

public interface ImageDao {
	
	/***
	 * This method is used to insert an Image Name associated with an Employee ID, so we can look up the image name later
	 * to retrieve from the server
	 * @param empId - need the employee ID to associate the image name with
	 * @param name - the name of the image file
	 */
	void insertImage(int empId, String name);
	
	/***
	 * This method is used to retrieve the Image Name for an employee ID, so we can pull it off the server
	 * @param empId - need emp ID to do the lookup
	 * @return  String - the name of the file to pull from the server
	 */
	String retrieveImageName(int empId);

}
