package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;

public class AccountSummaryPage extends BasePage {

    @FindBy(xpath = "//h2[@class='board-header']")
    public List<WebElement> accountTypes;

    @FindBy(xpath = "//h2[.='Credit Accounts']/following-sibling::*[1]//th")
    public List<WebElement> creditAccountHeaders;

    public void navigateToLink(String linkText) {
        clickElement("//a[.='%s']", linkText);
    }
}
