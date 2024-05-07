package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import com.common.BaseClass;

public class ChangeStatusTest extends BaseClass{

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {   //1.set capabilities for setting the browser..
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver = launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        Thread.sleep(10000);

    }

    @BeforeMethod
    public void login() throws InterruptedException {
        //3. enter valid username and password ,click on checkbox and tap on login button..
        loginPage_actitime loginPage = new loginPage_actitime(driver);
        loginPage.login("poojasalagare19@gmail.com", "Pooja@123#");
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

        //5.locate 'Tasks' option and click on it
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();

        Thread.sleep(5000);
    }

    @Test
    public void changeStatus() throws Exception {
        //6.select checkbox of '3D modelling' customer..
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='components_typeOfWorkSelector']/parent::div/preceding-sibling::div[3]/child::div[2]/child::table/child::tbody/child::tr/child::td[1]/child::div"));
        checkbox.click();

        Thread.sleep(2000);
        //7.select the change status button
        driver.findElement(By.xpath("//div[text()='Change Status']")).click();

        Thread.sleep(2000);

        //8.select the dropdown button
        WebElement dropdown = driver.findElement(By.xpath("//span[text()='Apply']/ancestor::div[2]/parent::div//preceding-sibling::div[1]/child::div[2]"));
        dropdown.click();

        //9.select the status as 'planning'
        WebElement planningStatus = driver.findElement(By.xpath("//div[contains(@class,\"workflowStatusNode checked\")]/following-sibling::div[1]/child::div"));
        planningStatus.click();

        Thread.sleep(5000);

        //10.click on apply button
        WebElement applyButton = driver.findElement(By.xpath("//span[text()='Apply']"));
        applyButton.click();

        Thread.sleep(5000);

        //11.verify the changed status of customer on webpage
        WebElement verifyStatus = driver.findElement(By.xpath("//table[@class='createdTasksRowsTable']/parent::div/following::div[15]"));
        String changedStatus = verifyStatus.getText();

        Thread.sleep(5000);
        if (changedStatus.equals("Planning")) {
            System.out.println("Pass->Status is successfully changed on webpage..");
        } else {
            throw new Exception("Fail-->Status is not changed on webpage..");
        }

    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}
