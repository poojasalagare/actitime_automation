package com.actitime.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.common.commonFunctions;
import org.testng.annotations.Test;
import com.common.BaseClass;

import java.util.List;

public class CreateTaskTest extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {   //1.set capabilities for setting the browser..
        commonFunctions commonFunctions=new commonFunctions(driver);
        driver=launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
        Thread.sleep(10000);
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
        Thread.sleep(5000);
    }


    @Test
    public void createTask() throws InterruptedException {
        //5.locate 'Tasks' option and click on it
        driver.findElement(By.xpath("//div[text()='Tasks']")).click();

        //6.select 'Big Bang company' customer
        driver.findElement(By.xpath("//div[text()='Big Bang Company' and @class='text']")).click();

        //7.select 'Flight operation' project and click on it
        driver.findElement(By.xpath("//div[text()='Flight operations' and @class='text']")).click();

        //8.create new task
        //i)click add task button
        driver.findElement(By.xpath("//div[@class='components_button addNewTaskButton withPlusIcon']")).click();
        Thread.sleep(5000);
        //ii)enter the task name
        // WebElement enterTaskName=driver.findElement(By.xpath("//div[@class='nameInfo editable invalid']/child::div[2]"));
        WebElement enterTaskName=driver.findElement(By.xpath("//span[text()='Big Bang Company']/following::input[@placeholder='Enter Task Name']"));
        enterTaskName.click();
        enterTaskName.sendKeys("Automated Task1");

        //iii)set the deadline as-> today
        driver.findElement(By.xpath("//div[@class=\"classicBridge-taskPanel-DetailWrapper-element--FwpOD0CA\"]/child::div/child::div[text()=\"Set up deadline\"]")).click();
        driver.findElement(By.xpath("//span[text()='Today']")).click();
        //iv)set type of work as->testing
        driver.findElement(By.xpath("//div[text()=\"Type of Work\"]/../child::div[2]")).click();
        driver.findElement(By.xpath("//div[text()=\"Type of Work\"]/../child::div[2]")).click();
        Thread.sleep(2000);
        WebElement budget=driver.findElement(By.xpath("//div[text()='Priority']/following::div[7]/child::input"));
        Thread.sleep(2000);
        budget.click();
        Thread.sleep(2000);
        budget.sendKeys("10");//vii)enter code
        Thread.sleep(2000);
        WebElement code=driver.findElement(By.xpath("//div[text()='Code (custom field)']/../child::div[2]/child::div/child::input"));
        Thread.sleep(2000);
        code.click();
        Thread.sleep(2000);
        code.sendKeys("20");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@id='disablePopup']/following-sibling::div[contains(@class,'create_task')]//div[text()='Add Task']")).click();

         Thread.sleep(5000);
    }

@Test
public void verifyAddedTask() throws InterruptedException {
    WebElement verifytask=driver.findElement(By.xpath("//input[@placeholder='Start typing a name...']"));
    verifytask.click();
    verifytask.sendKeys("Automated");

    Actions actions=new Actions(driver);
    actions.keyDown(Keys.ENTER).build().perform();
    actions.keyUp(Keys.ENTER).build().perform();
    Thread.sleep(5000);

    List<WebElement> searchedtasks=driver.findElements(By.xpath("//td[@class='names']//div[@class='title']"));
    for(WebElement addedTask:searchedtasks)
    {    String taskNames=addedTask.getText();
        if(!taskNames.isBlank())
        {System.out.println(addedTask.getText());}
        else
        { actions.moveToElement(addedTask).build().perform();
          System.out.println(addedTask.getText());
        }
    }
}

@AfterClass
    public void tearDown()
{
    driver.quit();
}

}







