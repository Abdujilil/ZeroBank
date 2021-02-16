package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.When;

public class AccountActivityNavigationStepDefs {

    @When("the user clicks on {string} link on the home page")
    public void the_user_clicks_on_link_on_the_home_page(String link) {
        new AccountSummaryPage().navigateToLink(link);
    }
}
