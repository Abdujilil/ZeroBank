@regression @smoke
Feature: Account Summary Credit Accounts table

  Scenario: Credit accounts table
    Given user is logged in
    Then the user should see following columns in credit accounts table
      | Account     |
      | Credit Card |
      | Balance     |