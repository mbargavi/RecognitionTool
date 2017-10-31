Feature: Assigning credits or Cap One dollars to an associate or team

As an employee, I can assign credits to an associate or team

Scenario: I have selected an employee or team and am able to assign them credits or cap one dollars
	Given I have selected an associate or team to recognize
	When I choose a credit or Cap One dollars to be given
	Then that credit or dollars are assigned to that employee or team.