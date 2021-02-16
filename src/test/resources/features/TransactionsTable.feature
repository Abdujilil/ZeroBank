@regression
Feature: Account activity transactions table

  Scenario: Transactions table headers
    Given user is logged in
    When user navigates to "Account Activity"
    Then the Transactions table should have following headers
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |