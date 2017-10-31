Feature: An associate’s recognition “title” will display indicating generally how often they recognize other associates and/or teams.

The threshold and name of the titles are open to the developers but should display on the main page near the user’s name.

Scenario: I am an Employee logged into the system and a recognition “title” is displayed near my name indicating at what level or frequency I recognize other associates.
	Given I have logged into the system
	When my information displays on the screen including my name
	Then my recognition title should also be displayed