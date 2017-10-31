Feature: A manager will be able to choose items for redeeming credits earned for a team.

The manager is able to navigate to a page to redeem items/gifts for credits earned for his/her team.

Scenario: A manager has been given a certain amount of credits and is now able to navigate to a redemption page where they can choose items/gifts/services to redeem their credits for his/her team.
	Given a manager is logged in who has un-redeemed credits accrued to his/her team.
	When the employee clicks on a link to redeem the credits.
	Then they are taken to a page where they can select items
	And their credits are redeemed.
	And their balance is updated.