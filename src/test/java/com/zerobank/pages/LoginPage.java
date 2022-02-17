package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "signin_button")
    public WebElement signIn;

    @FindBy(id = "user_login")
    public WebElement usernameBox;

    @FindBy(id = "user_password")
    public WebElement passwordBox;

    @FindBy(name = "submit")
    public WebElement submitButton;

    @FindBy(xpath = "//form[@id='login_form']/div[1]")
    public WebElement InvalidCredentials;

    @FindBy(xpath = "//div[@class='page-header']/h3")
    public WebElement InvalidCredentials2;


    public void login(String username, String password){
        BrowserUtils.waitForVisibility(usernameBox,5);
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        submitButton.click();
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
    }

    public void loginWithValidCredentials(){
        BrowserUtils.waitForVisibility(usernameBox,5);
        usernameBox.sendKeys(ConfigurationReader.get("username"));
        passwordBox.sendKeys(ConfigurationReader.get("password"));
        submitButton.click();
        Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
    }


}
