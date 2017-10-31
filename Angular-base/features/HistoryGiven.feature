Feature: An associate can see how many credits they have given since the start of the program

As an employee, I can see my credits given since the start of the program

Scenario: I have logged on and can see the credits I have given historically.
	Given I have logged into the system
	When my information displays on the screen
	Then I can also see how many credits I have given since program inception.