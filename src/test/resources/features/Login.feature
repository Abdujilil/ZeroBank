@login @regression @smoke
Feature: User login

  Background:
    Given user is on the login page


  Scenario: Login with valid credentials
    When user logs in with valid credentials
    Then the page title should be "Zero - Account Summary"


  Scenario Outline: Login with invalid credentials
    When user enters "<Username>" and "<Password>"
    Then user should not be able to login
    Examples:
      | Username | Password |
      | password | password |
      | username | username |
      |          | password |
      | username |          |