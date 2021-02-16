@regression
Feature: Add new payee under pay bills

  @smoke
  Scenario: Add a new payee
    Given user is logged in
    And user navigates to "Pay Bills"
    When user accesses the "Add New Payee" tab
    And creates new payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then message "The new payee The Law Offices of Hyde, Price & Scharks was successfully created." should be displayed