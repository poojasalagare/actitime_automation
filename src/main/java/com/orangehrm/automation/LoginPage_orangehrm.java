package com.orangehrm.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class LoginPage_orangehrm {
    WebDriver driver;
    public LoginPage_orangehrm(WebDriver driver)
    {
         this.driver=driver;
    }
   @Test
    public void login(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }
    @Test
    public void logout()
    {
        driver.findElement(By.xpath("//i[contains(@class,'oxd-icon bi-caret')]")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }
    @Test
    public void verifyerrormsg(String verifyerrormsg) throws Exception {

        if (verifyerrormsg.equals("Invalid credentials")) {
            System.out.println("Pass->with invalid credentials");
        } else {
            throw new Exception("Failed->error msg is not displayed on login page with invalid credentials..");
        }
    }


//    @DataProvider
//    public Object[][] getTestData() {
//        Object[][] obj = new Object[][]
//                {
//                        {"Admin", "admin123", "valid"},
//                        {"pooja", "admin123", "invalid"},
//                        {"Admin", "poojaa", "invalid"},
//                        {"", "", "invalid"},
////                        {"123", "456", "invalid"},
////                        {"length of both fields are too much longerlength of both fields are too", "length of both fields are too much longerlength of both fields are too", "invalid"},
////                        {"a_bc@#", "xy_*", "invalid"},
////                        {"ADMIN", "ADMIN123", "invalid"},
////                        {"Ad min", "admin 123", "invalid"},
//                        //  {"","password","invalid"}
//                };
  //      return obj;


    }

