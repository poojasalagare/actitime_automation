package com.practice;
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

import java.util.Set;

public class tabOperationsTest extends  BaseClass{
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        //1.launch the browser
        commonFunctions commonFunctions=new commonFunctions(driver);
        driver=launchBrowser("chrome");
        //2.navigate to url->amazon
        driver.get("https://www.amazon.in/");
        Thread.sleep(4000);
    }

    @Test
    public void keyboardAndMouseActions()
    {
        //3.Right click on AmazonMiniTV
        WebElement miniTv = driver.findElement(By.xpath("//div[@id='nav-xshop']/child::a[text()=\"Amazon miniTV\"]"));
        Actions actions=new Actions(driver);
        //4.right click on minitv and open it into new tab
        actions.keyDown(Keys.CONTROL).click(miniTv).keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.ARROW_DOWN).build().perform();
//        actions.keyUp(Keys.ARROW_DOWN).build().perform();
//        actions.keyDown(Keys.ENTER).build().perform();
//        actions.keyUp(Keys.ENTER).build().perform();
        //5.print title of amazon web page-1st tab
        System.out.println("Amazon page tab tile(1st tab) : "+driver.getTitle());
        //6.switch control from 1st tab to 2nd tab..
        String id_1st_tab=driver.getWindowHandle();
        System.out.println("Current window id :"+id_1st_tab);

        Set<String> allWindowid=driver.getWindowHandles();
        System.out.println(allWindowid);

        for(String id:allWindowid)
        {
            if(!id.equals(id_1st_tab))
            {
                System.out.println("second tab id : "+id);
                //7.jump to 2nd window
                driver.switchTo().window(id);
                //8.get 2nd tab title
                System.out.println("2nd window title : "+driver.getTitle());
                //9.close second tab
                driver.close();
                //10.switch control back to login page
                driver.switchTo().window(id_1st_tab);
                ///11.Print title of 1st page
                System.out.println("1st tab title : "+driver.getTitle());
                driver.quit();
            }
    }

    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }

}
