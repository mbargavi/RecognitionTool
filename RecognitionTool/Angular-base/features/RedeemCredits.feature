Feature: I will be able to choose items for redeeming credits earned.

The employee is able to navigate to a page to redeem items/gifts for credits earned.

Scenario: An employee has been given a certain amount of credits and is now able to navigate to a redemption page where they can choose items/gifts/services to redeem their credits.
	Given an employee is logged in who has un-redeemed credits accrued.
	When the employee clicks on a link to redeem the credits.
	Then they are taken to a page where they can select items
	And their credits are redeemed.
	And their balance is updated.