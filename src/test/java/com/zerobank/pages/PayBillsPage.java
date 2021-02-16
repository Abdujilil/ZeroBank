package com.zerobank.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static com.zerobank.utilities.BrowserUtils.*;
import static com.zerobank.utilities.DataUtils.*;

public class PayBillsPage extends BasePage {

    @FindBy(xpath = "//div[@id='alert_content']")
    public WebElement alertMessage;

    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;

    @FindBy(xpath = "//li[contains(@class,'state-active')]/../following-sibling::div[@id='ui-tabs-1']//input[@name='amount']")
    public WebElement payAmount;

    @FindBy(xpath = "//input[@name='date']")
    public WebElement date;

    @FindBy(name = "currency")
    public WebElement currencyDropdown;

    @FindBy(id = "add_new_payee")
    public WebElement addButton;

    @FindBy(xpath = "//li[contains(@class,'state-active')]")
    public WebElement activeTab;

    @FindBy(xpath = "//li[contains(@class,'state-active')]/../following-sibling::div[@id='ui-tabs-3']//input[@name='amount']")
    public WebElement currencyAmount;

    @FindBy(xpath = "//input[@id='pc_calculate_costs']")
    public WebElement calculateCost;

    @FindBy(xpath = "//input[@id='pc_inDollars_true']")
    public WebElement selectedCurrencyRadio;

    public void fillTextBox(String textBox, String value) {
        waitUntilClickable(getElement("//*[contains(@id,'%s')]", textBox)).sendKeys(value);
    }

    public void enterPaymentDetails(String payee, String account, String amount, String date) {
        String payeeAndAccount = "//select[@name='%s']";
        Select payeeDropdown = getDropdown(payeeAndAccount, "payee");
        Select accountDropdown = getDropdown(payeeAndAccount, "account");
        payeeDropdown.selectByVisibleText(payee);
        accountDropdown.selectByVisibleText(account);
        fillTextBox("amount", amount);
        fillTextBox("date", date);
        payButton.click();
    }

    public void enterPayeeInfo(Map<String, String> payeeInfo) {
        fillTextBox("name", payeeInfo.get("Payee Name"));
        fillTextBox("address", payeeInfo.get("Payee Address"));
        jseSendKeys("np_new_payee_account", payeeInfo.get("Account"));
        jseSendKeys("np_new_payee_details", payeeInfo.get("Payee details"));
        addButton.click();
    }

    public String alertMessage() {
        String message = "";
        turnOffImplicitWait();
        try {
            message = getAlertText();
        } catch (NoAlertPresentException e) {
            message = "";
        }
        try {
            if (alertMessage.isDisplayed()) {
                message = alertMessage.getText().trim();
            }
        } catch (NoSuchElementException e) {
            message = "";
        }
        turnOnImplicitWait();
        if (message.isEmpty()) {
            if (!validPayAmount(payAmount.getAttribute("value"))) {
                message = payAmount.getAttribute("validationMessage");
            } else if (!validPayDate(date.getAttribute("value"))) {
                message = date.getAttribute("validationMessage");
            }
        }
        return message;
    }

    public boolean validPayAmount(String amount) {
        if (amount.isEmpty()) {
            return false;
        }
        for (char eachChar : amount.toCharArray()) {
            if (Character.isLetter(eachChar) || !Character.isLetterOrDigit(eachChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean validPayDate(String date) {
        if (date.isEmpty()) {
            return false;
        }
        for (char eachChar : date.toCharArray()) {
            if (Character.isLetter(eachChar)) {
                return false;
            }
        }
        return true;
    }

    public List<String> availableCurrencies() {
        List<WebElement> options = getDropdown(currencyDropdown).getOptions();
        options.remove(0);
        return getTextOfElements(options);
    }

    public void enterCurrencyAmount(String amount) {
        waitUntilClickable(currencyAmount).sendKeys(amount);
    }

    public void calculateCost() {
        clickElement(calculateCost);
    }

    public void selectCurrency() {
        Select currencies = getDropdown(currencyDropdown);
        currencies.selectByIndex(generateSingleNum(1, (currencies.getOptions().size() - 1)));
        clickElement(selectedCurrencyRadio);
    }
}
