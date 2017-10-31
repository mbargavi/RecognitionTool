Feature: Employee search for employee

As an employee, I can conduct an employee search to find the employee I want to recognize

Scenario: I enter an employee name and possible matches are presented to me.
	Given I have entered some text into the search field
	And there is an employee first name or last name matching some portion of that text
	When I select an employee name from options listed
	Then that employee is selected for recognition