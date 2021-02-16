package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.waitUntilTitleIs;
import static org.junit.Assert.assertEquals;

public class TransactionsTableStepDefs {

    @Then("the Transactions table should have following headers")
    public void the_Transactions_table_should_have_following_headers(List<String> expectedHeaders) {
        waitUntilTitleIs("Zero - Account Activity");
        List<String> actualHeaders = new AccountActivityPage().transactionsTableHeaders();
        assertEquals(expectedHeaders, actualHeaders);
    }
}
