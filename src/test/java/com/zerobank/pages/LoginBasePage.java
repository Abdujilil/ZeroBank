package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LoginBasePage {

    public LoginBasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = ".brand")
    public WebElement homePageButton;
}
