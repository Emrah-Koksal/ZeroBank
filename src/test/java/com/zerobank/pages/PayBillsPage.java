package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PayBillsPage extends BasePage{
    @FindBy(css="#sp_amount")
    public WebElement amountBox;

    @FindBy(css="#sp_date")
    public WebElement dateBox;

    @FindBy(css="#sp_description")
    public WebElement descriptionBox;

    @FindBy(css="#pay_saved_payees")
    public WebElement payButton;

    @FindBy(css="#alert_content>span")
    public WebElement alertMessage;

public void fillForm(String amount, String date, String description){
    Select select1=new Select(Driver.get().findElement(By.id("sp_payee")));
    select1.getFirstSelectedOption();
    Select select2=new Select(Driver.get().findElement(By.id("sp_account")));
    select2.getFirstSelectedOption();
    amountBox.sendKeys(amount);
    dateBox.sendKeys(date);
    descriptionBox.sendKeys(description);
    payButton.click();
    BrowserUtils.waitFor(2);
}

public void checkAlertMessage(){
    BrowserUtils.waitForVisibility(alertMessage,2);
    Assert.assertEquals("Alert message doesn't match ","The payment was successfully submitted.",alertMessage.getText());
}

public void checkAmountErrorMessage(){
    BrowserUtils.waitFor(2);
    String validationMessage = amountBox.getAttribute("validationMessage");
    Assert.assertEquals("Alert message doesn't fit","Fill out this field",validationMessage);
}

    public void checkDateErrorMessage(){
        BrowserUtils.waitFor(2);
        String validationMessage = dateBox.getAttribute("validationMessage");
        Assert.assertEquals("Alert message doesn't fit","Fill out this field",validationMessage);
    }
}
