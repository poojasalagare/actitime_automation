package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.BaseClass;

public class DeleteCustomerTest extends BaseClass {
    WebDriver driver;
    @BeforeClass
    public void setup() throws InterruptedException {
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver =launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/cyber17/");
        Thread.sleep(10000);
    }

    @BeforeMethod
    public void login() throws InterruptedException {
        loginPage_actitime login = new loginPage_actitime(driver);
        login.login("kalpana@yopmail.com", "Pooja@123#");
        driver.findElement(By.id("keepLoggedInCheckBox")).click();
        driver.findElement(By.id("loginButton")).click();

        Thread.sleep(5000);

        //4.verify if the user is successfully logged into app or not..
        WebElement tasks = driver.findElement(By.xpath("//div[text()='Tasks']"));
        String verifyTasks = tasks.getText();
        if (verifyTasks.equals("Tasks")) {
            System.out.println("Logged in successfully into application..");
        } else {
            System.out.println("Not able to login into application");
        }
    }

    @Test
    public void  deleteCustomer()
    {
        
    }



}
