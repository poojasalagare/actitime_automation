package com.actitime.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class loginPage_actitime {
    WebDriver driver;
    public loginPage_actitime(WebDriver driver)
    {
        this.driver =driver;
        //PageFactory initElements(driver, this);

    }
    public void login(String username,String password)
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }

}
