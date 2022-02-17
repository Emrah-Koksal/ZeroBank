package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSummaryDefs {


    @Then("Verify Accounts Summary SubTitles")
    public void verifyAccountsSummarySubTitles() {
        new AccountSummaryPage().comparison();
    }

    @And("Verify Credit Accounts columns")
    public void verifyCreditAccountsColumns() {
        new AccountSummaryPage().creditAccountColumns();
    }

    @When("the user clicks on {string} link on the {string} page")
    public void theUserClicksOnLinkOnThePage(String linkName, String moduleName) {
        BrowserUtils.waitFor(2);
        new AccountSummaryPage().navigateTo(moduleName);
        new AccountSummaryPage().clickOnLink(linkName);
    }
}
