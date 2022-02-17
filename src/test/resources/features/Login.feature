Feature: Login as a user


  Scenario: Only authorized users should be able to login
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the "Account Summary" should be displayed


  Scenario: Users with wrong username or wrong password should not be able to login.
    Given the user is on the login page
    When the user logs in with "wrongusername" "password"
    Then the user should not be able to login
    When the user logs in with "username" "wrongpassword"
    Then the user should not be able to login


  Scenario: Users with blank username or password should also not be able to login
    Given the user is on the login page
    When the user logs in with " " "password"
    Then the user should not be able to login
    When the user logs in with "username" " "
    Then the user should not be able to login