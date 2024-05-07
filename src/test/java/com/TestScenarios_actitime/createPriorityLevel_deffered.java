package com.TestScenarios_actitime;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.common.BaseClass;

public class createPriorityLevel_deffered extends BaseClass{
    WebDriver driver;
    public void priority() throws InterruptedException {
        commonFunctions commonFunctions = new commonFunctions(driver);
        WebDriver driver = launchBrowser("chrome");

        driver.get("https://online.actitime.com/cyber17/");
        driver.manage().window().maximize();
        Thread.sleep(10000);

        //login into application


        driver.findElement(By.id("username")).sendKeys("kalpana@yopmail.com");
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("Pooja@123#");
        driver.findElement(By.id("keepLoggedInCheckBox")).click();
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(5000);
        //verify if the user is successfully logged into app or not..
        WebElement tasks = driver.findElement(By.xpath("//div[text()='Tasks']"));
        String verifyTasks = tasks.getText();
        if (verifyTasks.equals("Tasks")) {
            System.out.println("Logged in successfully into application..");
        } else {
            System.out.println("Not able to login into application");
        }
        Thread.sleep(5000);
        //enter the time track
        driver.findElement(By.xpath("//div[@class='menuTable']/descendant::div[4]")).click();


    }
}
