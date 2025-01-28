package framework;

import framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        System.setProperty("allure.results.directory", "target/allure-results");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
