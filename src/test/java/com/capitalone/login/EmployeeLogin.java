package com.capitalone.login;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeLogin {
	
	@Test
	public void employeeLogin() {
		System.out.println("Employee login method test");
		Assert.assertTrue(true);
	}
	
	@Test
	public void ManagerLogin() {
		System.out.println("Manager login method test");
		Assert.assertTrue(true);
	}
	
	@Test
	public void employeeCreditsEarned() {
		System.out.println("Employee credits earned method test");
		Assert.assertTrue(true);
	}
	
	@Test
	public void employeeCreditsGivenToIndividual() {
		System.out.println("Employee credits given to individual method test");
		Assert.assertTrue(true);
	}
	
	@Test
	public void employeeCreditsGivenToTeam() {
		System.out.println("Employee credits given to Team method test");
		Assert.assertTrue(true);
	}

}
