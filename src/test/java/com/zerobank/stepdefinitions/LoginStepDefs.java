package com.zerobank.stepdefinitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        Driver.get().get(ConfigurationReader.get("url"));
        new LoginPage().signIn.click();
        // Assert.assertTrue("Title does not match",Driver.get().getTitle().contains("Zero - Log in"));
    }


    @When("the user logs in with {string} {string}")
    public void theUserLogsInWith(String wrongUsername, String password) {
        new LoginPage().login(wrongUsername, password);
    }

    @Then("the user should not be able to login")
    public void theUserShouldNotBeAbleToLogin() {
        BrowserUtils.waitFor(2);
        String actualErrorMessage = new LoginPage().InvalidCredentials.getAttribute("outerText");
        String expectedErrorMessage = "Login and/or password are wrong.";
        Assert.assertEquals("Error message doesn't match", expectedErrorMessage, actualErrorMessage);
    }

    @Then("the {string} should be displayed")
    public void theShouldBeDisplayed(String expectedTitle) {
        BrowserUtils.waitFor(2);
        expectedTitle="Zero - "+expectedTitle;
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals("Dashboard Title does not match", expectedTitle, actualTitle);
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        new LoginPage().loginWithValidCredentials();
    }
}
