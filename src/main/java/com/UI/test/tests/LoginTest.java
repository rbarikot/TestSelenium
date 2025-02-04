package com.UI.test.tests;

import com.UI.pages.LoginPage;
import com.UI.test.base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    @Description("Test login functionality with valid credentials")
    public void testLogin() {
        LoginPage login = new LoginPage(driver);
        System.out.println(driver);
        login.login("admin", "admin");
        System.out.println("The title of the login page: " + driver.getTitle());
    }
    @Test
    @Description("Test To Validate Opening of Different Browser")
    public void testLogin2() throws InterruptedException {
        System.out.println(driver);
        driver.get("https://www.google.com");
        Thread.sleep(5000);
        System.out.println("The title of the login page: " + driver.getTitle());
    }
}
