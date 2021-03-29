package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

import static com.zerobank.utilities.BrowserUtils.*;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }


    public void navigateTo(String tab) {
        String xpath = String.format("//a[.='%s']", tab);
        clickElement(xpath);
    }

}
