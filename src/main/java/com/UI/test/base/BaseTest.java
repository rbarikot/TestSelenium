package com.UI.test.base;

import com.UI.constants.FrameworkConstants;
import com.UI.main.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    public static String browser;
    public static String trigger;

    @BeforeMethod
    @Parameters({"browser","trigger"})
    public synchronized void setUp(String browser, String trigger) {
        //DriverManager.initDriver(FrameworkConstants.BROWSER,FrameworkConstants.TRIGGER);
        browser = System.getProperty("browser");
        trigger=System.getProperty("trigger");
        System.out.println("**************BROWSER*****************"+browser);
        System.out.println("**************TRIGGER*****************"+trigger);
        DriverManager.initDriver(browser,trigger);
        driver = DriverManager.getDriver();
        driver.get("https://www.facebook.com/login/");
    }
    @AfterMethod
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
