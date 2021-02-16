package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;

public class AccountActivityPage extends BasePage {

    @FindBy(xpath = "//select[@name='accountId']")
    public WebElement accountDropdown;

    @FindBy(xpath = "//div[@id='all_transactions_for_account']//th")
    public List<WebElement> transactionsTableHeaders;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]")
    public WebElement searchResult;

    @FindBy(xpath = "//input[@name='description']")
    public WebElement descriptionInput;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[1]")
    public List<WebElement> dates;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[2]")
    public List<WebElement> descriptions;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[3]")
    public List<WebElement> deposits;

    @FindBy(xpath = "//div[contains(@id,'filtered_transactions')]//td[4]")
    public List<WebElement> withdrawals;

    @FindBy(name = "type")
    public WebElement typeDropdown;

    public String selectedAccount() {
        return getDropdown(accountDropdown).getFirstSelectedOption().getText().trim();
    }

    public List<String> allAccountOptions() {
        return getTextOfElements(getDropdown(accountDropdown).getOptions());
    }

    public List<String> transactionsTableHeaders() {
        return getTextOfElements(transactionsTableHeaders);
    }

    public void navigateTo_(String tab) {
        clickElement("//a[.='%s']", tab);
    }

    public void selectTransactionType(String type) {
        getDropdown(typeDropdown).selectByVisibleText(type);
    }

    public void enterDate(String dateType, String date) {
        getElement("//input[@name='%sDate']", dateType).clear();
        getElement("//input[@name='%sDate']", dateType).sendKeys(date);
    }

    public void enterDescription(String description) {
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
    }

    public void submitFind() {
        waitUntilClickable(findButton).click();
    }

    public boolean verifyDisplayedDates(String fromDate, String toDate) {
        List<String> dateTexts = getTextOfElements(dates);
        for (String eachDate : dateTexts) {
            if (convertToLocalDate(eachDate).isBefore(convertToLocalDate(fromDate))) {
                return false;
            } else if (convertToLocalDate(eachDate).isAfter(convertToLocalDate(toDate))) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyDateOrder() {
        List<String> dateTexts = getTextOfElements(dates);
        for (int i = 1; i < dates.size(); i++) {
            LocalDate earlierDate = convertToLocalDate(dateTexts.get(i - 1));
            LocalDate recentDate = convertToLocalDate(dateTexts.get(i));
            if (recentDate.isAfter(earlierDate)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyDateOutOfRange(String outOfRange) {
        String startDate = getElement("//input[@name='fromDate']").getAttribute("value");
        String endDate = getElement("//input[@name='toDate']").getAttribute("value");
        LocalDate fromDate = convertToLocalDate(startDate);
        LocalDate toDate = convertToLocalDate(endDate);
        LocalDate outOfRangeDate = convertToLocalDate(outOfRange);
        return !outOfRangeDate.isBefore(fromDate) && !outOfRangeDate.isAfter(toDate);
    }

    public LocalDate convertToLocalDate(String date) {
        String[] dateArray = date.split("-");
        int[] dates = new int[dateArray.length];
        for (int i = 0; i < dateArray.length; i++) {
            String each = dateArray[i];
            dates[i] = Integer.parseInt(each);
        }
        return LocalDate.of(dates[0], dates[1], dates[2]);
    }

    public boolean verifyResultsContainDescription(String description) {
        List<String> descriptionTexts = getTextOfElements(descriptions);
        for (String each : descriptionTexts) {
            if (!each.contains(description)) {
                return false;
            }
        }
        return true;
    }

    public int numberOfResult(String resultType) {
        List<String> resultTexts = new ArrayList<>();
        if (resultType.equalsIgnoreCase("deposit")) {
            resultTexts = getTextOfElements(deposits);
        } else if (resultType.equalsIgnoreCase("withdrawal")) {
            resultTexts = getTextOfElements(withdrawals);
        }
        for (String each : resultTexts) {
            if (!each.isEmpty()) {
                return 1;
            }
        }
        return 0;
    }

}
