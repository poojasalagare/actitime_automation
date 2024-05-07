package com.actitime.automation;

import com.common.BaseClass;
import com.common.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.actitime.automation.loginPage_actitime;
import com.common.commonFunctions;

import java.io.IOException;
import java.util.List;

public class GetTaskNames_FileHandling extends BaseClass {
    PropertyHandling propertyHandling;
    WebDriver driver;
    commonFunctions commonFunctions;
    loginPage_actitime loginPage_actitime;
    @BeforeClass
    public void setup() throws IOException {
        propertyHandling=new PropertyHandling("config.properties");
        String browser=propertyHandling.getProperty("browser");  //get value of key(browser)->value(chrome)
        String url=propertyHandling.getProperty("url");
        driver=launchBrowser(browser);             //use value of key(browser)
        driver.get(url);

    }

    @BeforeMethod
    public void login()
    {   loginPage_actitime=new loginPage_actitime(driver);
        String username=propertyHandling.getProperty("username");
        String password=propertyHandling.getProperty("password");
        loginPage_actitime.login(username,password);

    }

    @Test
    public void getTaskNames() throws InterruptedException {
        //By by=By.xpath("//div[text()='Tasks']");
        //commonFunctions.fluentWait(driver,by);
        Thread.sleep(10000);
        //click on tasks
        WebElement task = driver.findElement(By.xpath("//div[text()='Tasks']"));
        task.click();
        //5.click on Spaceship building
        driver.findElement(By.xpath("//div[@class='customersProjectsTreeContainer']/descendant::div[text()='Spaceship building']")).click();
         Thread.sleep(5000);
        List<WebElement> alltask = driver.findElements(By.xpath("//tr[contains(@class,'taskRow')]//td[2]//div[@class='title']"));
        System.out.println(alltask.size());

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

        }

    }
}





