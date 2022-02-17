package com.zerobank.stepdefinitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PayBillsDefs {
    @Then("Fill the payment form with the amount of {string} dollars, the date of {string} and description of {string}")
    public void fillThePaymentFormWithTheAmountOfDollarsTheDateOfAndDescriptionOf(String amount, String date, String description) {
        new PayBillsPage().fillForm(amount,date,description);
    }

    @And("Verify successful payment message")
    public void verifySuccessfulPaymentMessage() {
        new PayBillsPage().checkAlertMessage();
    }

    @And("Verify -Fill out the field- message is displayed empty amount")
    public void verifyFillOutTheFieldMessageIsDisplayedEmptyAmount() {
        new PayBillsPage().checkAmountErrorMessage();
    }

    @And("Verify -Fill out the field- message is displayed empty date")
    public void verifyFillOutTheFieldMessageIsDisplayedEmptyDate() {
        new PayBillsPage().checkDateErrorMessage();
    }
}
