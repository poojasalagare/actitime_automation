package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.common.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class MoveAllTasksTest extends BaseClass {

    WebDriver driver;
    WebElement movedTasks;
    List<String> taskList1=new ArrayList<>();;
    @BeforeClass
    public void setup() throws InterruptedException {
        //1.set capabilities for setting the browser..
        commonFunctions functions = new commonFunctions(driver);
        driver = launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        Thread.sleep(10000);
        //3. enter valid username and password ,click on checkbox and tap on login button..
        loginPage_actitime loginPage = new loginPage_actitime(driver);
        loginPage.login("poojasalagare19@gmail.com", "Pooja@123#");
    }

    @Test
    public void moveAllTask() throws InterruptedException {
        //4.click on tasks
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();
        //5.click on Spaceship building
        driver.findElement(By.xpath("//div[@class='customersProjectsTreeContainer']/descendant::div[text()='Spaceship building']")).click();
        Thread.sleep(5000);
        List<WebElement> alltask = driver.findElements(By.xpath("//tr[contains(@class,'taskRow')]//td[2]//div[@class='title']"));
        System.out.println("Size of Spaceship building : "+alltask.size());
        System.out.println("*** Following are tasks before moving from 'Spaceship building' ***");
        //System.out.println(alltask);
        for (WebElement element : alltask) {
            if (element.isDisplayed()) {
                System.out.println(element.getText());
            } else {
                //scroll till element is displayed
                Actions actions = new Actions(driver);
                actions.moveToElement(element).build().perform();
                System.out.println(element.getText());
            }
            //add tasks in a seperate list
            taskList1.add(element.getText());
        }
        Thread.sleep(5000);
        //select checkbox for all tasks
        driver.findElement(By.xpath("//tr[@class='headers']//div[@class='checkbox inactive']")).click();
        Thread.sleep(2000);

        //select move to
        driver.findElement(By.xpath("//div[@class='moveTo button']")).click();
        Thread.sleep(5000);

        //select to dropdown
        driver.findElement(By.xpath("//div[@class='comboboxButton']//div[@class='dropdownButton']")).click();
        //select Galaxy Corporation
        driver.findElement(By.xpath("//div[@class='comboboxButton focused']/following-sibling::div//div[text()='Galaxy Corporation']")).click();
        //select dropdown..
        driver.findElement(By.xpath("//div[@class='emptySelection']")).click();
        Thread.sleep(2000);
        //select project as ->android testing
        driver.findElement(By.xpath("//div[@class='comboboxButton focused']/..//div[text()='Android testing']")).click();
       Thread.sleep(5000);
        //tap on move
        driver.findElement(By.xpath("//div[@id='taskManagementPage']//div[contains(text(),'Who will get access to this task')]/../../following-sibling::div[@class='buttonsContainer']/child::div[1]")).click();

        Thread.sleep(5000);
    }

    @Test
    public void verifyMovedTasks() throws InterruptedException {
        //tap on android testing and get/verify all tasks..
        WebElement androidTesting=driver.findElement(By.xpath("//div[text()='Galaxy Corporation']/..//following::div[contains(text(),'Android testing')and(@class='text')]"));
        if(androidTesting.isDisplayed())
        {
            androidTesting.click();
        }
        else {
            Actions actions = new Actions(driver);
            actions.moveToElement(androidTesting).build().perform();
            androidTesting.click();
        }
        Thread.sleep(5000);
        //verify moved tasks..
        //tap on search

        WebElement search=driver.findElement(By.xpath("//input[@placeholder='Start typing a name...']"));
        for(String task:taskList1)
        {   if(search.isDisplayed())
            {  search.click();
               search.sendKeys(task);
               Actions actions=new Actions(driver);
               actions.keyDown(Keys.ENTER).build().perform();
                actions.keyUp(Keys.ENTER).build().perform();
                Thread.sleep(5000);
                List<WebElement> taskAfterMove = driver.findElements(By.xpath("//tr[contains(@class,'taskRow')]//td[2]//div[@class='title']"));
                for(WebElement movedTasks:taskAfterMove) {
                    Thread.sleep(2000);
                    System.out.println(movedTasks.getText());
                    Thread.sleep(2000);}
                    driver.findElement(By.xpath("//div[@class='components-SearchInput-icon--mqgNLbmO']")).click();
                    Thread.sleep(2000);

            }



        }

        }

        @AfterClass
    public void tearDown()
        {
            driver.quit();
        }
    }






