@smoke @login
Feature: Login Functionality

  Background:
    Given user is on the login page

  Scenario: Successful login with valid credentials
    When user enters username: "student" and password: "Password123"
    And user clicks the submit button
    Then user should be logged in successfully

  Scenario Outline: Unsuccessful login with invalid credentials
    When user enters username: "<username>" and password: "<password>"
    And user clicks the submit button
    Then login should fail with error message: The username or password is invalid

    Examples:
      | username | password    |
      | invalid1 | wrongPass   |
      | student  | wrongPass   |
      | invalid2 | Password123 |
