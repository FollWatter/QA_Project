package tests.pages;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

import static framework.DriverManager.getDriver;

public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testCategoryMenuFunctionality() {
        homePage.open();
        homePage.selectCategoryFromMenu("Ноутбуки");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement categoryPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Ноутбуки')]")));
        Assert.assertTrue(homePage.isCategoryPageDisplayed("Ноутбуки"), "Category page for 'Ноутбуки' is not displayed");
    }

    @Test
    public void testChangeLanguage() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.changeLanguage("Eng");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Eng')]")));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/en/"), "Language did not change to 'Eng'");
    }

    @Test
    public void testSelectArticleFromList() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectFirstArticle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement articleContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".article-title")));

        Assert.assertTrue(articleContent.isDisplayed(), "Article page is not displayed");
    }

    @Test
    public void testAccountRegistration() {
        homePage.open();

        homePage.registerAccount("testmail@gmail.com", "123456ab");
    }

    @Test
    public void testSelectCategoryFromCatalog() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.selectCategory("Зв'язок і гаджети");
        homePage.selectSubCategory("Мобільні телефони");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement subCategoryPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Мобільні телефони')]")));
        Assert.assertTrue(homePage.isSubCategoryPageDisplayed("Мобільні телефони"), "Sub-category page for 'Мобільні телефони' is not displayed");
    }

}
