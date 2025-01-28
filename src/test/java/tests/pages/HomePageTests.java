package tests.pages;
import framework.BaseTest;
import framework.DriverManager;
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
import io.qameta.allure.Description;
import static framework.DriverManager.getDriver;

public class HomePageTests extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = getDriver();
        homePage = new HomePage(driver);
    }
    @Test
    @Description("Перевірка функціональності меню категорій")
    public void testCategoryMenuFunctionality() {
        homePage.open();
        homePage.selectCategoryFromMenu("Ноутбуки");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement categoryPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Ноутбуки')]")));
        Assert.assertTrue(homePage.isCategoryPageDisplayed("Ноутбуки"), "Category page for 'Ноутбуки' is not displayed");
    }
    @Test
    @Description("Перевірка зміни мови")
    public void testChangeLanguage() {
        WebDriver driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.changeLanguage("Eng");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/en/"));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Eng')]")));
        Assert.assertTrue(header.isDisplayed(), "Language change link not displayed");
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("/en/"), "Language did not change to 'Eng'");
    }
    @Test
    @Description("Перевірка перемикання режиму день/ніч")
    public void testToggleDayNightMode() {
        homePage.toggleDayNightMode();
    }
    @Test
    @Description("Перевірка реєстрації облікового запису")
    public void testAccountRegistration() {
        homePage.open();
        homePage.registerAccount("testmail@gmail.com", "123456ab");
    }
    @Test
    @Description("Перевірка вибору категорії та підкатегорії")
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
