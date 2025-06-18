@smoke @login
Feature: Login Page

  # Use background feature to remove Given duplicates
  Background:
    Given the user is on the Login Page

  Scenario: Login successful with valid credentials
    When the user enters valid username and password
    And the user clicks on the login button
    Then the user should be logged in successfully

  Scenario: Login with invalid credentials
    When the user enters invalid username and password
    And the user clicks on the login button
    Then the user should not be logged in successfully
    And error message is displayed: "The username and password could not be verified."


