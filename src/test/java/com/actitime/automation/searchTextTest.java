package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.BaseClass;

import java.util.List;

public class searchTextTest extends  BaseClass{
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {   //launch browser
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver =launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        Thread.sleep(10000);
    }

    @BeforeMethod
    public void login() throws InterruptedException {
        loginPage_actitime login = new loginPage_actitime(driver);
        login.login("poojasalagare19@gmail.com", "Pooja@123#");
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
    public void searchText() throws InterruptedException {
        Thread.sleep(3000);
        //tap on task
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();
        // select 'Big Bang company' customer
        driver.findElement(By.xpath("//div[text()='Big Bang Company' and @class='text']")).click();
        //select on serach field..
        WebElement serachText = driver.findElement(By.xpath("//input[@placeholder='Start typing a name...']"));
        serachText.sendKeys("automated");
        //tap on enter
        Actions actions = new Actions(driver);
        actions.keyUp(Keys.ENTER).build().perform();
        actions.keyDown(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        //get the list of searched results/tasks
        List<WebElement> searchedText = driver.findElements(By.xpath("//tr[contains(@class,'taskRow')]//td[@class='names']//div[@class='title']"));
        for (WebElement element : searchedText) {

            System.out.println(element.getText());
        }
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
