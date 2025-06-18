Feature: Login Page

  Scenario Outline: Login with invalid credentials
    Given the user is on the Login Page
    When the user enters invalid "<username>" and "<password>"
    And the user clicks on the login button
    Then the user should not be logged in successfully
    And error message is displayed: "The username and password could not be verified."

    Examples:
      | username | password |
      | pablodev9 | 123456  |
      | chay      | 2345234 |
      | april     | 9867987 |
      | mercator  |         |
