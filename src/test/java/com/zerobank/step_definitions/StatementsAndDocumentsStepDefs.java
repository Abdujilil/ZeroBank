package com.zerobank.step_definitions;

import com.zerobank.pages.OnlineStatementsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StatementsAndDocumentsStepDefs {

    @When("the user selects the Recent Statements Year {string}")
    public void the_user_selects_the_Recent_Statements_Year(String year) {
        new OnlineStatementsPage().selectRecentStatement(year);
    }

    @Then("{string} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(String expectedCount) {
        String actualCount = new OnlineStatementsPage().numberOfStatements();
        assertEquals(expectedCount, actualCount);
    }

    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String statement) {
        new OnlineStatementsPage().selectFile(statement);
    }

    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String fileName) {
        assertTrue(new OnlineStatementsPage().isDownloaded(fileName));
    }

    @Then("the fle type should be pdf")
    public void the_fle_type_should_be_pdf() {
        OnlineStatementsPage onlineStatementsPage = new OnlineStatementsPage();
        assertTrue(onlineStatementsPage.isDownloaded(".pdf"));
        onlineStatementsPage.deleteDownloadedFile();
    }
}
