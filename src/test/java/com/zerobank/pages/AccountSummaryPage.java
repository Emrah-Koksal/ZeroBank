package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountSummaryPage extends BasePage {



    public void comparison() {
        List<WebElement> summarySubTitles = Driver.get().findElements(By.xpath("//div[@class='offset2 span8']/h2"));
        String[] expectedSubTitles = new String[]{"Cash Accounts", "Investment Accounts", "Credit Accounts", "Loan Accounts"};
        int i = 0;
        for (WebElement each : summarySubTitles) {
            Assert.assertEquals("Subtitles doesn't match", expectedSubTitles[i], each);
            i++;
        }
    }

    public void creditAccountColumns() {
        BrowserUtils.waitFor(2);
        List<WebElement> actualCreditAccountsColumnsList=Driver.get().findElements(By.xpath("//div[3]/div/table/thead/tr/th"));
        List<String> actualCreditAccountsColumns= BrowserUtils.getElementsText(actualCreditAccountsColumnsList);
        List<String> expectedCreditAccountsColumns = new ArrayList<>();
        expectedCreditAccountsColumns.add("Account");
        expectedCreditAccountsColumns.add("Credit Card");
        expectedCreditAccountsColumns.add("Balance");
        for (int i = 0; i < expectedCreditAccountsColumns.size(); i++) {
            Assert.assertEquals("Column names don't match", expectedCreditAccountsColumns.get(i), actualCreditAccountsColumns.get(i));
        }
    }

}