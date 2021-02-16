package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class AccountDropdownStepDefs {

    @When("user navigates to {string}")
    public void user_navigates_to(String tab) {
        new AccountActivityPage().navigateTo(tab);
    }

    @Then("the Account dropdown selected option should be {string}")
    public void the_Account_dropdown_selected_option_should_be(String expected) {
        waitUntilTitleIs("Zero - Account Activity");
        String actual = new AccountActivityPage().selectedAccount();
        assertEquals(expected, actual);
    }

    @Then("the Account dropdown should have following options")
    public void the_Account_dropdown_should_have_following_options(List<String> expectedAccounts) {
        waitUntilTitleIs("Zero - Account Activity");
        List<String> actualAccounts = new AccountActivityPage().allAccountOptions();
        assertEquals(expectedAccounts, actualAccounts);
    }
}
