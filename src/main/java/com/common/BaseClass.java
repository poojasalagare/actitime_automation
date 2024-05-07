package com.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {

        WebDriver driver;

        public WebDriver launchBrowser(String browserName) {
            switch (browserName) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    options.setBrowserVersion("121");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setBrowserVersion("stable");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBrowserVersion("stable");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.setBrowserVersion("122");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
            }

            return driver;
        }
}


