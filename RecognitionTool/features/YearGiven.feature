Feature: An associate can see how many credits they have given over the course of a year

As an employee, I can see my credits given over the course of the year

Scenario: I have logged on and can see the credits I have given for the year.
	Given I have logged into the system
	When my information displays on the screen
	Then I can also see how many credits I have given for the year.