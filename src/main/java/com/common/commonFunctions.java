package com.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.function.Function;

public class commonFunctions {
 WebDriver driver;
 public commonFunctions(WebDriver driver)

 {
     this.driver=driver;
 }

    public void waitElementToBePresent(WebDriver driver, WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBePresent(By by)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public void waitelementToBeClickable(WebElement by)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }
    public void elementToBeSelected(WebDriver driver,WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }


    //fluent wait
    public void fluentWait(By by) {
          Wait<WebDriver> fluentWait=new FluentWait<>(driver)
                  .pollingEvery(Duration.ofSeconds(2))
                  .withTimeout(Duration.ofSeconds(25))
                  .ignoring(Exception.class);

        Function <WebDriver, WebElement> function =(var)-> {
            System.out.println("wait until element is available.. ");
            return driver.findElement(by);
        };
        fluentWait.until(function);
    }

    public void click(WebElement element)
    { waitelementToBeClickable(element);
        element.click();

    }
    public void click(By by)
    {
        fluentWait(by);
        driver.findElement(by).click();
    }


}
