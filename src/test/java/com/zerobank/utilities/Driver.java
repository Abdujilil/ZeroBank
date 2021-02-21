package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.HashMap;

public class Driver {

    private static final InheritableThreadLocal<WebDriver> driverThreadLocal = new InheritableThreadLocal<>();

    private Driver() {
    }

    public synchronized static WebDriver get() {

        if (driverThreadLocal.get() == null) {

            String browser = ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().browserVersion("88").setup();
                    ChromeOptions options = new ChromeOptions().addArguments("--ignore-certificate-errors");
                    driverThreadLocal.set(new ChromeDriver(options));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions optionsHL = new ChromeOptions().addArguments("--ignore-certificate-errors");
                    optionsHL.setHeadless(true);
                    String downloadFilepath = "C:\\Users\\Duke\\Downloads"; // directory download
                    HashMap<String, Object> prefs = new HashMap<>();
                    prefs.put("download.default_directory", downloadFilepath);
                    prefs.put("download.prompt_for_download", false);
                    optionsHL.addArguments("--disable-gpu");
                    optionsHL.addArguments("- window-size = 1920,1200");
                    optionsHL.addArguments("--disable-infobars");
                    optionsHL.addArguments("--disable-notifications");
                    optionsHL.setExperimentalOption("prefs", prefs);
                    DesiredCapabilities cap = new DesiredCapabilities();
                    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    cap.setCapability(ChromeOptions.CAPABILITY, optionsHL);
                    driverThreadLocal.set(new ChromeDriver(optionsHL));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverThreadLocal.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverThreadLocal.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    driverThreadLocal.get().manage().window().setSize(new Dimension(1200, 900));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverThreadLocal.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverThreadLocal.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverThreadLocal.set(new SafariDriver());
                    break;
                case "remote-chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.setCapability("platform", Platform.ANY);
                    try {
//                        URL url = new URL("http://localhost:4444/wd/hub");
                        driverThreadLocal.set(new RemoteWebDriver(new URL("http://34.204.186.21:4444/wd/hub"), chromeOptions));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return driverThreadLocal.get();
    }

    public synchronized static void closeDriver() {

        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
