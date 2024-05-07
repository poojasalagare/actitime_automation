package com.actitime.automation;

import com.common.commonFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.common.BaseClass;

public class practicalTest extends BaseClass{

    WebDriver driver;
    commonFunctions commonFunctions;
    @BeforeClass
    public void setup() throws InterruptedException {   //1.set capabilities for setting the browser..
         commonFunctions = new commonFunctions(driver);
        driver = launchBrowser("chrome");
        //2. navigate to actitime url..
        driver.get("https://online.actitime.com/abc5/");
       // commonFunctions.waitForElementToBePresent(driver, driver.findElement(By.id("keepLoggedInCheckBox")));
        //Thread.sleep(10000);

    }

    @BeforeMethod
    public void login() throws InterruptedException {
        //3. enter valid username and password ,click on checkbox and tap on login button..
        loginPage_actitime loginPage = new loginPage_actitime(driver);
        loginPage.login("poojasalagare19@gmail.com", "Pooja@123#");
        driver.findElement(By.id("keepLoggedInCheckBox")).click();
        driver.findElement(By.id("loginButton")).click();

       // commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//div[@id='container_users']")));
        //Thread.sleep(5000);

        //4.verify if the user is successfully logged into app or not..
        WebElement tasks = driver.findElement(By.xpath("//div[text()='Tasks']"));
        String verifyTasks = tasks.getText();
        if (verifyTasks.equals("Tasks")) {
            System.out.println("Logged in successfully into application..");
        } else {
            System.out.println("Not able to login into application");
        }

        //5.locate 'users' option and click on it
        driver.findElement(By.xpath("//div[@id='container_users']")).click();
       // commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.id("ext-comp-1659")));
        //Thread.sleep(5000);
    }

    @Test
    public void test() throws InterruptedException {
        //click on filter
        driver.findElement(By.id("ext-comp-1659")).click();
        //click on selected user
        driver.findElement(By.id("ext-gen516")).click();
        //select user
        driver.findElement(By.xpath("//span[@title='Meyers, Allison']")).click();

        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        WebElement apply=driver.findElement(By.id("ext-comp-1648"));
        apply.click();

      //  commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//table[@class='userNameContainer']//div[@class='name']")));
        //Thread.sleep(5000);

        WebElement element=driver.findElement(By.xpath("//table[@class='userNameContainer']//div[@class='name']"));
        System.out.println(element.getText());
        if(element.getText().equals("Meyers, Allison"))
        {
          System.out.println("user found");
        }
        else
        {
            new Exception("user not found");
        }

        driver.findElement(By.xpath("//div[@class='icon costReports']")).click();

     //   commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//div[@class='accountSettingsTab']")));
        //Thread.sleep(5000);

        //select account setting
        driver.findElement(By.xpath("//div[@class='accountSettingsTab']")).click();

 //       //Thread.sleep(5000);

        WebElement rate=driver.findElement(By.xpath("//div[@id='editUserPanel']//div[@class='userRatesList']//div[@class='userRates']//div[@class='regularRateColumn']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(rate).build().perform();
        rate.click();
        //actions.keyDown(Keys.BACK_SPACE).build().perform();
        driver.findElement(By.xpath("//div[@id='editUserPanel']//div[@class='userRatesList']//div[@class='userRates']//div[@class='regularRateColumn']//div[@class='clearButton']")).click();
 //       Thread.sleep(5000);
        actions.sendKeys("500");
        //js.executeScript("arguments[0].value=500;",rate);
 //       Thread.sleep(5000);

        //WebElement field=driver.findElement(By.xpath("//div[@id='editUserPanel_accountInformationSection']/../..//div[@class='userRatesTable']/child::div[2]/child::div[1]/child::div[1]//div[@class='regularRateColumn']"));

        WebElement cross=driver.findElement(By.xpath("//div[@class='edit_user_sliding_panel sliding_panel components_panelContainer']/child::div[@class='hideButton_panelContainer']"));
        actions.moveToElement(cross).build().perform();
        cross.click();

      //  commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//tr[@class='userListRow']//div[@class='icon manageUsers inactive']")));
        //Thread.sleep(5000);

        //verify changes rate value
        driver.findElement(By.xpath("//tr[@class='userListRow']//div[@class='icon manageUsers inactive']")).click();

    //    commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//div[@class='accountSettingsTab']")));
        //Thread.sleep(5000);

        //select account setting again..
        driver.findElement(By.xpath("//div[@class='accountSettingsTab']")).click();
        Thread.sleep(5000);
        WebElement verifyRate=driver.findElement(By.xpath("//div[@id='editUserPanel']//div[@class='userRatesList']//div[@class='userRates']//div[@class='regularRateColumn']"));
        actions.moveToElement(verifyRate).build().perform();
        Thread.sleep(2000);
        if(verifyRate.getText().equals("500"))
        {
          System.out.println(verifyRate.getText());
        }
        else
        {
            new Exception("text not found for rate");
        }




    }
}

