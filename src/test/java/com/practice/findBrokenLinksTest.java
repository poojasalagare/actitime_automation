package com.practice;
import com.common.BaseClass;
import com.common.commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class findBrokenLinksTest extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        //launch the browser
        commonFunctions commonFunctions = new commonFunctions(driver);
        driver =launchBrowser("chrome");

        driver.get("https://www.amazon.in/");

    }
    @Test
     public void brokenLinks() throws IOException {
        //find all links present on webpage
        List<WebElement> webElements = driver.findElements(By.tagName("a"));
        //iterate the links
        for (WebElement element : webElements) {
            String links = element.getAttribute("href");
            // System.out.println(links);
            if (links != null && !links.startsWith("javascript")) {
                // System.out.println(links);

                //convert the links from string to actual links
                URL url = new URL(links);
                //open the port for connection
                URLConnection connection = url.openConnection();
                //cast connection of var into URLConnection..
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
                //hit the url
                httpsURLConnection.connect();
                //get status code
                int responseCode = httpsURLConnection.getResponseCode();
                //validate the the respone code..
                if (responseCode != 200) {
                    System.out.println("Broken url : " + responseCode + " " + links);
                }
            }
        }


    }

    @AfterClass
    public void closeBrowser()
    {
      driver.quit();
    }


}




