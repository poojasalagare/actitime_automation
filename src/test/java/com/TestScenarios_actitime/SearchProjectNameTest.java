package com.TestScenarios_actitime;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.BaseClass;
public class SearchProjectNameTest extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver = launchBrowser("chrome");

        driver.get("https://online.actitime.com/cyber17/");
        driver.manage().window().maximize();
        Thread.sleep(10000);
    }

    @BeforeMethod
    public void login() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("kalpana@yopmail.com");
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("Pooja@123#");
        driver.findElement(By.id("keepLoggedInCheckBox")).click();
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(5000);
        //2.verify if the user is successfully logged into app or not..
        WebElement tasks = driver.findElement(By.xpath("//div[text()='Tasks']"));
        String verifyTasks = tasks.getText();
        if (verifyTasks.equals("Tasks")) {
            System.out.println("Logged in successfully into application..");
        } else {
            System.out.println("Not able to login into application");
        }

    }

    @Test
    public void searchProject() throws InterruptedException {
        //3.tap on tasks
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();
        Thread.sleep(5000);
        //4.search project name
        WebElement searchProject=driver.findElement(By.xpath("//div[@class='searchFieldContainer']/descendant::input"));
        searchProject.click();
        Thread.sleep(2000);
        searchProject.sendKeys("Android Testing");
        Thread.sleep(5000);
        //5.validate the project name..
        WebElement android=driver.findElement(By.xpath("//div[@class='node projectNode notSelected editable']/descendant::span[text()='Android']"));
        Thread.sleep(2000);
        String text=android.getText();
        Thread.sleep(2000);
        if(text.equals("Android"))
        {
            System.out.println("Pass : Able to search project name-Android Testing");
        }
        else
        {
            new Exception("Fail: Not able to search project name");
        }

    }

    @AfterClass
    public void closeBrowser()
    {
          driver.quit();
    }

    }









