package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static com.zerobank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class PaySavedPayeeStepDefs {

    @And("the user enters {string} {string} {string} {string}")
    public void the_user_enters(String payee, String account, String amount, String date) {
        waitUntilTitleIs("Zero - Pay Bills");
        new PayBillsPage().enterPaymentDetails(payee, account, amount, date);
    }

    @Then("{string} alert message should be displayed")
    public void alert_message_should_be_displayed(String expectedMessage) {
        String actualMessage = new PayBillsPage().alertMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        String actualMessage = new PayBillsPage().alertMessage();
        assertTrue(actualMessage.contains("Please"));
    }
}
