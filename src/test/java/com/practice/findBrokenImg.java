package com.practice;
import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import com.common.BaseClass;

public class findBrokenImg extends  BaseClass{
    WebDriver driver;
    @Test
    public void brokenImg() throws IOException {
        //launch the browser
        commonFunctions commonFunctions=new commonFunctions(driver);
        WebDriver driver=launchBrowser("chrome");

        driver.get("https://www.amazon.in/");

        List<WebElement> elements=driver.findElements(By.tagName("img"));

        //iterate each webelement
        for(WebElement img:elements)
        {
            String imglink= img.getAttribute("src");
            //System.out.println(imglink);
                //convert string to actual link
                URL url = new URL(imglink);
                //open connection port
                URLConnection connection = url.openConnection();
                //cast connection of var to httpconnection
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
                //hit url
                httpsURLConnection.connect();
                //get responsecode
                int statuscode = httpsURLConnection.getResponseCode();
                if (statuscode != 200) {
                    System.out.println("Broken imglink: " + statuscode + " " + imglink);
                }
            }

        }












    }

