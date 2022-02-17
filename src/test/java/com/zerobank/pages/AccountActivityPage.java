package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountActivityPage extends BasePage {
    public boolean dateContains(String date) throws ParseException {
        String newDate = date.substring(0, 4).replace(date.substring(0, 2), "") + date.substring(4);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        Date givenDate = dateFormat.parse(newDate);

        List<Date> actualDates = getActualDates();

        for(Date eachDate : actualDates){
            if(eachDate.compareTo(givenDate) == 0){
                System.out.println("Given date exists in the actual date list");
                return true;
            }
        }
        return false;
    }


    @FindBy(id = "aa_fromDate")
    public WebElement dateFirst;

    @FindBy(id = "aa_toDate")
    public WebElement dateSecond;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement find;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//td[1]")
    public List<WebElement> allDates;

    public void dropDownVerify(String dropDownOption) {
        Select select = new Select(Driver.get().findElement(By.id("aa_accountId")));
        //  select.selectByVisibleText(dropDownOption);
        String actualTextOfSelection = select.getFirstSelectedOption().getText();
        Assert.assertEquals("Default is not -Savings-", dropDownOption, actualTextOfSelection);
    }

    public void dropDownSelect(String selectOption) {
        Select select = new Select(Driver.get().findElement(By.id("aa_accountId")));
        select.selectByVisibleText(selectOption);
        //  String actualTextOfSelection=select.getFirstSelectedOption().getText();
        //  Assert.assertEquals("Default is not -Savings-", dropDownOption, actualTextOfSelection);
    }

    public void comparisonAccountActivityDropdownOptions() {
        Select select = new Select(Driver.get().findElement(By.id("aa_accountId")));
        List<WebElement> dropdownOptionsList = select.getOptions();
        List<String> dropdownOptions = BrowserUtils.getElementsText(dropdownOptionsList);
        String[] expectedSubTitles = new String[]{"Savings", "Checking", "Savings", "Loan", "Credit Card", "Brokerage"};
        int i = 0;
        for (String each : dropdownOptions) {
            Assert.assertEquals("Dropdown Options don't match", expectedSubTitles[i], each);
            i++;
        }
    }

    public void accountActivityColumns() {
        BrowserUtils.waitFor(2);
        List<WebElement> actualAccountActivityColumnsList = Driver.get().findElements(By.cssSelector("#all_transactions_for_account>table>thead>tr>th"));
        List<String> actualCreditAccountsColumns = BrowserUtils.getElementsText(actualAccountActivityColumnsList);
        List<String> expectedAccountActivityColumns = new ArrayList<>();
        expectedAccountActivityColumns.add("Date");
        expectedAccountActivityColumns.add("Description");
        expectedAccountActivityColumns.add("Deposit");
        expectedAccountActivityColumns.add("Withdrawal");
        for (int i = 0; i < expectedAccountActivityColumns.size(); i++) {
            Assert.assertEquals("Column names don't match", expectedAccountActivityColumns.get(i), actualCreditAccountsColumns.get(i));
        }
    }

    public void enterDate(String date1, String date2) {
        // BrowserUtils.waitForVisibility(dateFirst,5);
        BrowserUtils.waitFor(2);
        dateFirst.clear();
        dateFirst.sendKeys(date1);

        dateSecond.clear();
        dateSecond.sendKeys(date2);
    }

    public boolean areDatesSorted() throws ParseException { //2012-06-02, 2012-06-09, , 2012-06-01

        List<Date> dateList = new ArrayList<>(getActualDates());

        for (int i = 0; i < dateList.size() - 1; i++) {
            if (dateList.get(i).compareTo(dateList.get(i + 1)) < 0) {
                System.out.println("Dates are not sorted as most recent");
                return false;
            }
        }

        return true;
    }
    public List<Date> getActualDates() throws ParseException {
        List<String> dates = new ArrayList<>();
        for (WebElement each : allDates) {
            String s = each.getText();
            dates.add(s.substring(0, 4).replace(s.substring(0, 2), "") + s.substring(4));//12-09-01
        }

        System.out.println("dates fedfed = " + dates);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

        List<Date> dateList = new ArrayList<>();

        for (String each : dates) {
            Date utilDate = dateFormat.parse(each);
            dateList.add(utilDate);
        }
        return dateList;
    }
    public boolean areDatesBetween(String dateFrom, String dateTo) throws ParseException {
        String dateStartFrom = dateFrom.substring(0, 4).replace(dateFrom.substring(0, 2), "") + dateFrom.substring(4);
        String dateEndTo = dateTo.substring(0, 4).replace(dateTo.substring(0, 2), "") + dateTo.substring(4);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");

        Date givenDateFrom = dateFormat.parse(dateStartFrom);
        Date givenDateTo = dateFormat.parse(dateEndTo);

        if (areDatesSorted()) {

            List<Date> list = new ArrayList<>(getActualDates());

            if (list.get(0).compareTo(givenDateTo) > 0) {
                System.out.println("The actual dates list has dates out of the given dates range");
                return false;
            }else if(list.get(list.size()-1).compareTo(givenDateFrom) < 0){
                System.out.println("The actual dates list has dates out of the given dates range");
                return false;
            }

        }
        return true;
    }
}