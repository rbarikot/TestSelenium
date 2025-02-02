package com.UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    //private WebDriver driver;
    public LoginPage(WebDriver driver) {
        super(driver);

    }
    public By username= By.id("email");
    public By pwd= By.id("pass");
    public By submit= By.id("loginbutton");

    public void login(String email, String password) {
        driver.findElement(username).sendKeys(email);
        driver.findElement(pwd).sendKeys(password);
        driver.findElement(submit).click();
    }
    public void loginTest()
    {
        driver.findElement(username).sendKeys("Test1");
        driver.findElement(pwd).sendKeys("Test2");
        driver.findElement(submit).click();
    }


}
