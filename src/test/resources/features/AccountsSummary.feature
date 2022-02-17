Feature: Accounts Summary Module Functions

  Scenario: Verify Accounts Summary SubTitles

    Given the user is on the login page
    When the user logs in with "username" "password"
    Then Verify Accounts Summary SubTitles
    And Verify Credit Accounts columns