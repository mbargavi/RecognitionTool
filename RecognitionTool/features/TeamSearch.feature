Feature: Employee search for team

As an employee, I can conduct a search to find the team I want to recognize

Scenario: I enter a team name and possible matches are presented to me.
	Given I have entered some text into the search field
	And there is a team name matching some portion of that text
	When I select a team name from the options listed
	Then that team is selected for recognition