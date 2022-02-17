package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateTo(String moduleText) {
        BrowserUtils.waitFor(2);
        WebElement module = Driver.get().findElement(By.linkText(moduleText));
        module.click();

    }

    public void clickOnLink(String linkName) {
        WebElement Link = Driver.get().findElement(By.linkText(linkName));
        BrowserUtils.waitForClickablility(Link, 5);
        Link.click();
        BrowserUtils.waitFor(2);
    }
}
