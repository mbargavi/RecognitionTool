Feature: The nominator can choose whether or not to copy his or her own manager

As an employee, I want to be able to choose whether to copy my own manager. 

Scenario: I have selected an employee to recognize and want to choose whether to copy my manager before I submit the recognition.
	Given I have selected an associate to recognize
	When I select not to have my manager copied
	Then when I submit, my manager is not copied on my recognition submission