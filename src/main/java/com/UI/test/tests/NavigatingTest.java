package com.UI.test.tests;

import com.UI.pages.LoginPage;
import com.UI.test.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class NavigatingTest extends BaseTest {
   @Test
   @Description("Test To Validate Opening of Different Browser")
    public void testLogin2() throws InterruptedException {
        System.out.println(driver);
        driver.get("https://www.google.com");
        Thread.sleep(5000);
    }
}
