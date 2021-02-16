package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.zerobank.utilities.BrowserUtils.*;

public class LoginPage extends LoginBasePage {

    @FindBy(css = "input#user_login")
    public WebElement usernameInput;

    @FindBy(css = "input#user_password")
    public WebElement passwordInput;

    @FindBy(css = "input[name='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'alert-error')]")
    public WebElement errorMessage;

    public void login() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        clickElement(submitButton);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        clickElement(submitButton);
    }

    public String getErrorMessage() {
        return errorMessage.getText().trim();
    }

}
