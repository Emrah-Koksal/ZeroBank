package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AccountActivityDefs {


    @And("Navigate to {string} page")
    public void navigateToPage(String module) {
        new AccountActivityPage().navigateTo(module);
    }

    @And("Verify Transactions table column names")
    public void verifyTransactionsTableColumnNames() {
        new AccountActivityPage().accountActivityColumns();
    }

    @And("Verify Account drop down menu options")
    public void verifyAccountDropDownMenuOptions() {
        new AccountActivityPage().comparisonAccountActivityDropdownOptions();
    }

    @And("Verify drop down option should be as {string}")
    public void verifyDropDownOptionShouldBeAs(String select) {
        new AccountActivityPage().dropDownVerify(select);
    }

    @When("the user enters date range from {string} to {string}")
    public void theUserEntersDateRangeFromTo(String date1, String date2) {
        new AccountActivityPage().enterDate(date1, date2);
    }

    @And("clicks search")
    public void clicksSearch() {
        new AccountActivityPage().find.click();
    }

    @Then("Result table should only show transactions dates between {string} to {string}")
    public void result_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) throws ParseException, ParseException {
        boolean areDatesBetweenToAndFrom = new AccountActivityPage().areDatesBetween(fromDate,toDate);
        Assert.assertTrue(areDatesBetweenToAndFrom);
    }


    @And("the results should be sorted by most recent date")
    public void theResultsShouldBeSortedByMostRecentDate() throws ParseException {
        boolean isSorted = new AccountActivityPage().areDatesSorted();
        Assert.assertTrue(isSorted);
    }

    @And("the results table should only not contain transactions dated {string}")
    public void theResultsTableShouldOnlyNotContainTransactionsDated(String date) throws ParseException{
        boolean doesContain=new AccountActivityPage().dateContains(date);
        Assert.assertFalse(doesContain);
    }
}
