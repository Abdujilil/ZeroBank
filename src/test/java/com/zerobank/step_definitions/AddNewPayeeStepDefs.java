package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static com.zerobank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class AddNewPayeeStepDefs {

    @When("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> payeeInfo) {
        new PayBillsPage().enterPayeeInfo(payeeInfo);
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedMessage) {
        PayBillsPage payBillsPage = new PayBillsPage();
        waitUntilVisible(payBillsPage.alertMessage);
        String actualMessage = payBillsPage.alertMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
