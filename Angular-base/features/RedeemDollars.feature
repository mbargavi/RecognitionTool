Feature: I will be able to choose items for redeeming Cap One dollars earned.

The employee is able to navigate to a page to redeem items/gifts for Cap One Dollars earned.

Scenario: An employee has been given a certain amount of Cap One Dollars and is now able to navigate to a redemption page where they can choose items/gifts/services to redeem their Cap One Dollars.
	Given an employee is logged in who has un-redeemed Cap One Dollars accrued.
	When the employee clicks on a link to redeem the Cap One Dollars.
	Then they are taken to a page where they can select items
	And their Cap One Dollars are redeemed.
	And their balance is updated.