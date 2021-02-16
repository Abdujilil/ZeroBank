package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.zerobank.utilities.BrowserUtils.*;
import static org.junit.Assert.*;

public class LoginStepDefs {

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        new LoginPage().login();
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        waitUntilTitleIs(title);
        String actualTitle = getCurrentPageTitle();
        assertEquals(title, actualTitle);
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        new LoginPage().login(username, password);
    }

    @Then("user should not be able to login")
    public void user_should_not_be_able_to_login() {
        LoginPage loginPage = new LoginPage();
        String expectedError = "Login and/or password are wrong.";
        String actualError = loginPage.getErrorMessage();
        assertEquals(expectedError, actualError);
    }
}
