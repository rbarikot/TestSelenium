package com.UI.main.driver;

import com.UI.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser,String trigger) {
        if (driver.get() == null) {
            try {
                URL gridUrl = new URL("http://localhost:4444/wd/hub");

                if (browser.equalsIgnoreCase(String.valueOf(DriverType.CHROME))&& trigger.equalsIgnoreCase("remote")) {
                    ChromeOptions options = new ChromeOptions();
                    driver.set(new RemoteWebDriver(gridUrl, options));
                } else if (browser.equalsIgnoreCase(String.valueOf(DriverType.FIREFOX))&& trigger.equalsIgnoreCase("remote")) {
                    FirefoxOptions options = new FirefoxOptions();
                    driver.set(new RemoteWebDriver(gridUrl, options));
                }else if (browser.equalsIgnoreCase(String.valueOf(DriverType.CHROME))&& trigger.equalsIgnoreCase("local")) {
                    driver.set(new ChromeDriver());
                }else if (browser.equalsIgnoreCase(String.valueOf(DriverType.FIREFOX))&& trigger.equalsIgnoreCase("local")) {
                    System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                    driver.set(new FirefoxDriver());
                }

                driver.get().manage().window().maximize();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }



}
