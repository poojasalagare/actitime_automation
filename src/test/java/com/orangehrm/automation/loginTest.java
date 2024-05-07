package com.orangehrm.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import com.common.commonFunctions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.common.BaseClass;

public class loginTest extends  BaseClass{
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver = launchBrowser("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com");
     //   commonFunctions.waitForElementToBePresent(driver,driver.findElement(By.xpath("//h6[text()='Dashboard']")));
    }

    @Test(dataProvider = "getTestData")
    public void loginTestData(Object username, Object password, Object status) throws Exception {   //valid username and password
        
        LoginPage_orangehrm loginPageOrangehrm = new LoginPage_orangehrm(driver);
        loginPageOrangehrm.login(username.toString(), password.toString());
        if (status.toString().equals("valid")) {
            //Validate after successful login the user should be able to land on Dashboard page..
            WebElement dashboard = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
            String dashboardText = dashboard.getText();
            System.out.println(dashboardText);
            if (dashboardText.equals("Dashboard")) {
                System.out.println("User is successfully landed on dashboard page..");
            } else {
                throw new Exception("Unable to login into application..");
            }

            //perform logout operation
            loginPageOrangehrm.logout();
        } else {
            //verify error msg.
            if (username.toString().isBlank() || (password.toString().isBlank())) {
                WebElement requiredUsername = driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::span"));
                WebElement requiredPassword = driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::span"));
                if (requiredPassword.isDisplayed() || requiredUsername.isDisplayed()) {
                    System.out.println("Test case pass-blank");
                } else {
                    throw new Exception("test case failed-blank");
                }
            } else {
                WebElement errormsg = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
                String verifyerrormsg = errormsg.getText();
                loginPageOrangehrm.verifyerrormsg(verifyerrormsg);
                driver.navigate().refresh();
            }

        }
    }

    @DataProvider
    public Object[][] getTestData() {
        Object[][] obj = new Object[][]
                {
                        {"Admin", "admin123", "valid"},
                        {"pooja", "admin123", "invalid"},
                        {"Admin", "poojaa", "invalid"},
                        {"", "", "invalid"},
                        {"123", "456", "invalid"},
                        {"length of both fields are too much longerlength of both fields are too", "length of both fields are too much longerlength of both fields are too", "invalid"},
                        {"a_bc@#", "xy_*", "invalid"},
                        {"ADMIN", "ADMIN123", "invalid"},
                        {"Ad min", "admin 123", "invalid"},
//                       //  {"","password","invalid"}
                };
        return obj;
    }
}




