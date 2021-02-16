@regression
Feature: Purchase Foreign Currency

  Background:
    Given user is logged in
    And user navigates to "Pay Bills"
    When user accesses the "Purchase Foreign Currency" tab

  @smoke
  Scenario: Available currencies
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |

  Scenario: Error message for not selecting currency
    And user tries to calculate cost without selecting a currency
    Then error message should be displayed
  @smoke
  Scenario: Error message for not entering amount
    And user tries to calculate cost without entering an amount
    Then error message should be displayed