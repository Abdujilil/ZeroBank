package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.getTextOfElements;
import static org.junit.Assert.assertEquals;

public class CreditAccountsTableStepDefs {

    @Then("the user should see following columns in credit accounts table")
    public void the_user_should_see_following_columns_in_credit_accounts_table(List<String> expectedHeaders) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualHeaders = getTextOfElements(accountSummaryPage.creditAccountHeaders);
        assertEquals(expectedHeaders, actualHeaders);
    }
}
