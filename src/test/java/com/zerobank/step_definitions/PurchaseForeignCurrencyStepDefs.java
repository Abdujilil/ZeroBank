package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class PurchaseForeignCurrencyStepDefs {

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> expectedCurrencies) {
        PayBillsPage payBillsPage = new PayBillsPage();
        waitUntilTextToBe(payBillsPage.activeTab, "Purchase Foreign Currency");
        List<String> actualCurrencies = payBillsPage.availableCurrencies();
        assertTrue(actualCurrencies.containsAll(expectedCurrencies));
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {
        PayBillsPage payBillsPage = new PayBillsPage();
        waitUntilTextToBe(payBillsPage.activeTab, "Purchase Foreign Currency");
        payBillsPage.enterCurrencyAmount("100");
        payBillsPage.calculateCost();
    }

    @When("user tries to calculate cost without entering an amount")
    public void user_tries_to_calculate_cost_without_entering_an_amount() {
        PayBillsPage payBillsPage = new PayBillsPage();
        waitUntilTextToBe(payBillsPage.activeTab, "Purchase Foreign Currency");
        payBillsPage.selectCurrency();
        payBillsPage.calculateCost();
    }
}
