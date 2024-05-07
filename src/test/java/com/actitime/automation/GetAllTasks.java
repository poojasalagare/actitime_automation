package com.actitime.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.*;
import com.pages.*;

import java.util.List;

public class GetAllTasks extends BaseClass {

    WebDriver driver;
    loginPage loginPage;
    TaskPage taskPage;
    commonFunctions commonFunctions;

    @BeforeClass
    public void setup() throws InterruptedException {

        driver =launchBrowser("chrome");
        loginPage=new loginPage(driver);
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        taskPage=new TaskPage(driver);
        commonFunctions=new commonFunctions(driver);

    }

    @BeforeMethod
    public void login() throws InterruptedException {
        //3. enter valid username and password ,click on checkbox and tap on login button..
        loginPage.login("poojasalagare19@gmail.com","Pooja@123#");

       // commonFunctions.elementToBeClickable(driver,driver.findElement(By.id("loginButton")));
    }


    @Test
    public void allTask() throws InterruptedException {
        commonFunctions.click(taskPage.taskModule);
        commonFunctions.click(taskPage.project);



//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//div[text()='Tasks']")).click();
//        //5.click on Spaceship building
//        driver.findElement(By.xpath("//div[@class='customersProjectsTreeContainer']/descendant::div[text()='Spaceship building']")).click();
//        Thread.sleep(5000);
        List<WebElement> alltask = taskPage.taskList;
        System.out.println(alltask.size());

        //System.out.println(alltask);
        for (WebElement element : alltask) {
            if (element.isDisplayed()) {
                System.out.println(element.getText());
            } else {
                //scroll till element is displayed
                Actions actions = new Actions(driver);
                actions.moveToElement(element).perform();
                System.out.println(element.getText());
            }

        }
        Thread.sleep(5000);

    }
}

