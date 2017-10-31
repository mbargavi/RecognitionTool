Feature: An employee will be given incentives of Credits or Cap One Dollars based on 75% and 100% completion of dispersing points.

As an employee who has given away 75% or 100% of their points for the year, I receive some credits or Dollars into my balance as an incentive, and am notified of such.

Scenario: An employee has reached the 75% or 100% threshold of giving their allotted points for the year and they therefore receive an incentive and are notified.
	Given an employee gives points that take him/her to the 75% or 100% threshold of giving their points
	When the recognition is submitted that takes them to the threshold
	Then they receive some incentive in credits or Cap One Dollars
	And are notified that they have received the incentive.