package com.pages;

import com.common.PropertyHandling;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.common.commonFunctions;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class loginPage {
    WebDriver driver;
    commonFunctions commonFunctions;
    public loginPage(WebDriver driver)

    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

//    public void setup() throws IOException {
//        PropertyHandling propertyHandling=new PropertyHandling("config.properties");
//        String url= propertyHandling.getProperty("url");
//        String username=propertyHandling.getProperty("username");
//        String password=propertyHandling.getProperty("password");
//        //launch browser
//        driver.get(url);
//    }

    public By userName=By.id("username");

    public  By passWord=By.xpath("//input[@name='pwd']");

    @FindBy(id="loginButton")
    WebElement loginButton;
    public void login(String username,String password) throws InterruptedException {
        commonFunctions=new commonFunctions(driver);
        driver.findElement(this.userName).sendKeys(username);
        driver.findElement(this.passWord).sendKeys(password);
        Thread.sleep(5000);
        commonFunctions.click(loginButton);



       // driver.findElement(By.id("username")).sendKeys(username);
        //driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys(password);
        //driver.findElement(By.id("loginButton")).click();

    }
}
