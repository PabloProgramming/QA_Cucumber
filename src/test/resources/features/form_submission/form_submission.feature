@wip
Feature: Form Submission

  Background:
    Given I am on the practice form page

  Scenario: Successful form submission with valid data
    When I enter valid data into all required fields
    And I select a gender and a hobby
    And I submit the form
    Then I should see a confirmation message

  Scenario: Form cannot be submitted with missing required fields
    When I leave required fields empty
    And I submit the form
    Then I should not be able to submit the form
