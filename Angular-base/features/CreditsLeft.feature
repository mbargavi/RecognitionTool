Feature: An associate can see how many credits they have left to give for the year

As an employee, I can see my credits left to give for the year

Scenario: I have logged on and can see the credits I have left to give for the year.
	Given I have logged into the system
	When my information displays on the screen
	Then I can also see how many credits I have left to give for the year.