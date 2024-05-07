package com.kite.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class KiteLoginTest {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //1.Launch the chrome browser..
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://kite.zerodha.com/");
        driver.manage().window().maximize();

        //2.Add the userid
        WebElement userid=driver.findElement(By.xpath("//input[@id='userid']"));
        userid.sendKeys("GQT530");

        //3.Add password..
        WebElement password=driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("Pooja@123");

        //4.Click on login ..
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);
        //5.check the user should land on GQT530 page/profile page
        WebElement profilepage=driver.findElement(By.xpath("//h2[text()='GQT530']"));
        if(profilepage==profilepage)
        {
            System.out.println("Logged in successfully and landed on gqt/profile page..");
        }
        else
        {
            System.out.println("Failed-->Not able to login");
        }
        Thread.sleep(5000);

        //6.click on problem with mobile code link
        driver.findElement(By.xpath("//a[@class='text-light forgot-link']")).click();

        //7.Click on SMS/email OTP
        //Thread.sleep(30000);
        //driver.findElement(By.xpath("//a[@class='twofa-option']")).click();
        //System.out.println("email send successfully..");





    }
}
