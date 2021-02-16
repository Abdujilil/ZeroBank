@regression
Feature: Pay bills

  Background:
    Given user is logged in
    When user navigates to "Pay Bills"

  Scenario: Pay bills page title
    Then the page title should be "Zero - Pay Bills"

  @smoke
  Scenario:  Valid pay operations
    And the user enters "Apple" "Checking" "100" "2021-02-17"
    Then "The payment was successfully submitted." alert message should be displayed

  Scenario Outline: Invalid pay operations
    And the user enters "<payee>" "<account>" "<amount>" "<date>"
    Then error message should be displayed
    Examples:
      | payee           | account   | amount | date       |
      | Sprint          | Savings   |        | 2021-02-18 |
      | Bank of America | Checking  | 50     |            |
      | Apple           | Brokerage | 60     | date       |