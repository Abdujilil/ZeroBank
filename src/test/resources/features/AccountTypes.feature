@regression
Feature: Account Summary account types

  Background:
    Given user is logged in

  Scenario: Account summary page title
    Then the page title should be "Zero - Account Summary"
@smoke
  Scenario: Displayed account types
    Then the user should see following account types
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |