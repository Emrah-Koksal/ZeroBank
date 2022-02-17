Feature: Accounts Activity Module Functions -Navigating to specific accounts in Accounts Activity

  Background:
    Given the user is on the login page
    When the user logs in with valid credentials

  @smoke
  Scenario: Account Activity page should have the Zero – Account activity
    And Navigate to "Account Activity" page
    Then the "Account Activity" should be displayed
    And Verify drop down option should be as "Savings"
    And Verify Account drop down menu options
    And Verify Transactions table column names
#//mike change 222
# mike branch conflict
  Scenario Outline: Navigating and Verifying <select> accounts
    When the user clicks on <linkName> link on the <moduleName> page
    Then the <expectedTitle> should be displayed
    And Verify drop down option should be as <select>
    Examples:
      | linkName      | moduleName        | expectedTitle      | select        |
      | "Savings"     | "Account Summary" | "Account Activity" | "Savings"     |
      | "Brokerage"   | "Account Summary" | "Account Activity" | "Brokerage"   |
      | "Checking"    | "Account Summary" | "Account Activity" | "Checking"    |
      | "Credit Card" | "Account Summary" | "Account Activity" | "Credit Card" |
      | "Loan"        | "Account Summary" | "Account Activity" | "Loan"        |

  @wip
  Scenario: Search date range
    Given the user clicks on "Find Transactions" link on the "Account Activity" page
    When the user enters date range from "2012-09-01" to "2012-09-06"
    And clicks search
    Then Result table should only show transactions dates between "2012-09-01" to "2012-09-06"
    And the results should be sorted by most recent date
    When the user enters date range from "2012-09-02" to "2012-09-06"
    And clicks search
    Then Result table should only show transactions dates between "2012-09-02" to "2012-09-06"
    And the results table should only not contain transactions dated "2012-09-01"