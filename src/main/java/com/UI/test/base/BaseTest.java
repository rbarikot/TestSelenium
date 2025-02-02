package com.UI.test.base;

import com.UI.constants.FrameworkConstants;
import com.UI.main.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public synchronized void setUp(){
        DriverManager.initDriver(FrameworkConstants.BROWSER,FrameworkConstants.TRIGGER);
        driver = DriverManager.getDriver();
        driver.get("https://www.facebook.com/login/");
    }
    @AfterMethod
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
