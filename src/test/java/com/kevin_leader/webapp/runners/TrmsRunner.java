package com.kevin_leader.webapp.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kevin_leader.webapp.pages.IndexPage;
import com.kevin_leader.webapp.pages.RequestPage;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        glue = "com.kevin_leader.webapp.steps", strict = true,
        plugin = { "pretty" })
public class TrmsRunner {

    public static WebDriver driver;
    public static IndexPage indexPage;
    public static RequestPage requestPage;

    @BeforeClass
    public static void setUp() {
        String path = "C:/Users/Kevin/Desktop/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);

        driver = new ChromeDriver();
        indexPage = new IndexPage(driver);
        requestPage = new RequestPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
