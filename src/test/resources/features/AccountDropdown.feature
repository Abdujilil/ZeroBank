@regression
Feature: Account Activity account dropdown

  Background:
    Given user is logged in
    When user navigates to "Account Activity"

  Scenario: Account activity page title
    Then the page title should be "Zero - Account Activity"

  Scenario: Account dropdown default option
    Then the Account dropdown selected option should be "Savings"
  @smoke
  Scenario: Account dropdown options
    Then the Account dropdown should have following options
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |