@regression
Feature: Navigating to specific accounts in Accounts Activity

  Background:
    Given user is logged in

  Scenario Outline: Account redirect
    When the user clicks on "<account>" link on the home page
    Then the page title should be "Zero - Account Activity"
    And the Account dropdown selected option should be "<option>"
    Examples:
      | account     | option      |
      | Savings     | Savings     |
      | Brokerage   | Brokerage   |
      | Checking    | Checking    |
      | Credit Card | Credit Card |
      | Loan        | Loan        |