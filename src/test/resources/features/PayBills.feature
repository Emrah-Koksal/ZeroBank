Feature: Pay Bills Module Functions

  Background:
    Given the user is on the login page
    When the user logs in with "username" "password"


   @smoke
  Scenario: Pay Bills Module functions properly
    When Navigate to "Account Activity" page
    Then the "Account Activity" should be displayed


  Scenario Outline: Successful payment message verified
    When Navigate to <module> page
    Then Fill the payment form with the amount of <amount> dollars, the date of <date> and description of <description>
    And Verify successful payment message
    Examples:
      | module      | amount | date         | description        |
      | "Pay Bills" | "100"  | "2022-03-05" | "Electricity Bill" |


  Scenario: Error message is displayed with empty amount
    When Navigate to "Pay Bills" page
    Then Fill the payment form with the amount of "" dollars, the date of "2022-03-05" and description of "Electricity Bill"
    And Verify -Fill out the field- message is displayed empty amount


  Scenario: Error message is displayed with empty date
    When Navigate to "Pay Bills" page
    Then Fill the payment form with the amount of "100" dollars, the date of "" and description of "Electricity Bill"
    And Verify -Fill out the field- message is displayed empty date