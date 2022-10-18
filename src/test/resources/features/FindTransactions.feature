@regression
Feature: Find transactions

  Background:
    Given user is logged in
    When user navigates to "Account Activity"
    And user accesses the "Find Transactions" tab

  @smokeTest
  Scenario: Search date range
    When the user enters date range from "2012-09-01" to "2012-09-06"
    And clicks search
    Then results table should only show transaction dates between "2012-09-01" to "2012-09-06"
    And the results should be sorted by most recent date
    When the user enters date range from "2012-09-02" to "2012-09-06"
    And clicks search
    Then results table should only show transaction dates between "2012-09-02" to "2012-09-06"
    And results table should not contain transactions dated "2012-09-01"


  Scenario: Search description
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "OFFICE"
    And clicks search
    Then results table should only show descriptions containing "OFFICE"
    But results table should not show descriptions containing "office"


  Scenario: Type
    And clicks search
    Then results table should show at least one result under Deposit
    Then results table should show at least one result under Withdrawal
    When user selects type "Deposit"
    And clicks search
    Then results table should show at least one result under Deposit
    But results table should show no result under Withdrawal
    When user selects type "Withdrawal"
    And clicks search
    Then results table should show at least one result under Withdrawal
    But results table should show no result under Deposit
