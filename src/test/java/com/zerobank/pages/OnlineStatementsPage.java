package com.zerobank.pages;

import com.zerobank.utilities.GlobalDataUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.zerobank.utilities.BrowserUtils.*;

public class OnlineStatementsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,' active')]//td[contains(.,'Statement')]")
    public List<WebElement> statements;

    @FindBy(xpath = "//div[contains(@class,' active')]/table")
    public WebElement statementTable;

    public void selectRecentStatement(String year) {
        String xpath = "//a[.='%s' and @data-toggle='pill']";
        clickElement(xpath, year);
    }

    public String numberOfStatements() {
        waitForVisibility(statementTable);
        return statements.size() + "";
    }

    public void selectFile(String statement) {
        waitForVisibility(statementTable);
        clickElement("//a[.='%s']", statement);
        GlobalDataUtils.numberOfFilesDownloaded++;
        waitUntilDownloadComplete();
    }

    public boolean isDownloaded(String fileName) {
        File dir = new File(GlobalDataUtils.getDownloadPath());
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0].getName().contains(fileName);
    }

    public void deleteDownloadedFile() {
        File dir = new File(GlobalDataUtils.getDownloadPath());
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        files[0].deleteOnExit();
    }

    public void waitUntilDownloadComplete() {
        File dir = new File(GlobalDataUtils.getDownloadPath());
        File[] files = dir.listFiles();
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        while (files.length != GlobalDataUtils.numberOfFilesDownloaded) {
            files = dir.listFiles();
        }
        for (int i = 0; i < files.length; i++) {
            files = dir.listFiles();
            if (files[i].getName().contains(".crdownload")) {
                i--;
            }
        }
    }
}
