package framework;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;

@Listeners({AllureTestNg.class})
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String allureResultsDir = "target/allure-results";
        System.setProperty("allure.results.directory", allureResultsDir);
        System.out.println("Allure results directory set to: " + System.getProperty("allure.results.directory"));
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
