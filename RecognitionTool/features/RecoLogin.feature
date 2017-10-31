Feature: Logging into Recognition Tool

As an employee I want to login and get to the main page

Scenario: Employee logging into the Recognition Tool
    Given I have an employee username and password
    When I input my username and password on the login page
    And I am a validated employee
    Then I will be redirected to the main page