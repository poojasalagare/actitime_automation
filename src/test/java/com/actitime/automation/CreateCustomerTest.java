package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.BaseClass;

import java.util.List;

public class CreateCustomerTest extends BaseClass{
    WebDriver driver;
    String customer="Dr. Marlyn Sandoor";
    @BeforeClass
    public void setUp() throws InterruptedException {
        commonFunctions commonFunctions=new commonFunctions(driver);
        driver=launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        Thread.sleep(10000);
    }
    
    @BeforeMethod
    public void login() throws InterruptedException {
        loginPage_actitime loginPage=new loginPage_actitime(driver);
        loginPage.login("poojasalagare19@gmail.com","Pooja@123#");
        driver.findElement(By.id("keepLoggedInCheckBox")).click();
        driver.findElement(By.id("loginButton")).click();

        Thread.sleep(5000);
        //4.verify if the user is successfully logged into app or not..
        WebElement tasks=driver.findElement(By.xpath("//div[text()='Tasks']"));
        String verifyTasks=tasks.getText();
        if(verifyTasks.equals("Tasks"))
        {
            System.out.println("Logged in successfully into application..");
        }
        else
        {
            System.out.println("Not able to login into application");
        }
    }
    @Test
    public void createCustomer() throws InterruptedException {
        Thread.sleep(5000);
        //tap on task
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();
        //tap on Add New button
        driver.findElement(By.xpath("//div[text()='Add New']")).click();
        //Tap on New customer
        driver.findElement(By.xpath("//div[text()='+ New Customer']")).click();
        Thread.sleep(2000);
        //Fill details on Create new customer page
        //enter customer name
//**
        WebElement custName=driver.findElement(By.xpath("//div[@class='customerNameDiv']//input"));
        custName.sendKeys(customer);
        //add description
        WebElement description=driver.findElement(By.xpath("//textarea[@placeholder='Enter Customer Description']"));
        description.sendKeys("Dentist application");
        //select customer from dropdown-->aa
        driver.findElement(By.xpath("//div[@class='customerImportDiv']//div[@class='selectedItem']")).click();
         driver.findElement(By.xpath("//div[@class='emptySelection']/../following::div[text()='Big Bang Company']")).click();
        //zoom out the app
        Actions actions=new Actions(driver);
        actions.keyUp(Keys.CONTROL).build().perform();
        actions.keyUp(Keys.SUBTRACT).build().perform();
        actions.keyDown(Keys.CONTROL).build().perform();
        actions.keyDown(Keys.SUBTRACT).build().perform();

        Thread.sleep(2000);
        //click on create customer button
        driver.findElement(By.xpath("//div[text()='Create Customer']")).click();
        //click on serach box and get the created cutomer's name
        WebElement searchbox=driver.findElement(By.xpath("//div[@class='customersProjectsPanel']//input"));
//**
        searchbox.sendKeys("Marlyn");
        Thread.sleep(2000);
        actions.keyUp(Keys.ENTER).build().perform();
        actions.keyDown(Keys.ENTER).build().perform();

        List<WebElement> result=driver.findElements(By.xpath("//div[@class='itemsContainer']//div[@class='collapseButton']/..//div[@class='title']"));
        for(WebElement element:result)
        {
            System.out.println(element.getText());
        }


    }

    
    
    
    
    
    
    
    
    
    
    
}
