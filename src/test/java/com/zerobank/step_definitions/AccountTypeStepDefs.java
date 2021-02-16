package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.getTextOfElements;
import static org.junit.Assert.assertEquals;

public class AccountTypeStepDefs {

    @Given("user is logged in")
    public void user_is_logged_in() {
        Driver.get().get(ConfigurationReader.get("url"));
        new LoginPage().login();
    }

    @Then("the user should see following account types")
    public void the_user_should_see_following_account_types(List<String> expectedAccountTypes) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualAccountTypes = getTextOfElements(accountSummaryPage.accountTypes);
        assertEquals(actualAccountTypes, expectedAccountTypes);
    }
}
