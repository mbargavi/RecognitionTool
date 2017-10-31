Feature: I can input additional feedback that will only go to the associate’s manager

As an employee, I want to to be able to provide additional feedback to their manager only.

Scenario: I have some additional positive feedback or explanation to give about an employee to the person’s manager and want to give that feedback to the manager only.
	Given I have selected an associate to recognize
	When I enter feedback to go to the manager only.
	Then that feedback will be added to the manager’s email copy of the submission.