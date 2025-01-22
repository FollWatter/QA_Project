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
import pages.ProductPage;
import java.time.Duration;
import java.util.List;

public class ProductPageTests extends BaseTest {

    private ProductPage productPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpPage() {
        driver.get("https://ek.ua/ua/list/122/"); // Відкриваємо сторінку каталогу
        productPage = new ProductPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testGoToFirstStore() {
        productPage.goToStore();
        wait.until(ExpectedConditions.urlContains("prices"));
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String pricesPageUrl = driver.getCurrentUrl();
        Assert.assertTrue(pricesPageUrl.contains("prices"), "Сторінка цін не відкрилася!");
        productPage.goToFirstStoreFromPrices();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    @Test
    public void testViewSpecifications() {
        driver.get("https://ek.ua/ua/list/122/");
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.model-short-title")));
        firstProduct.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        productPage.openSpecificationsTab();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//a[contains(text(), 'Характеристики')]"), "class", "active"));
    }
    @Test
    public void testAddToComparison() {
        String productId = "2603099";
        productPage.addToComparison(productId);
    }
    @Test
    public void testViewReviews() {
        driver.get("https://ek.ua/ua/list/122/");
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.model-short-title")));
        firstProduct.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        productPage.openReviewsTab();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//a[contains(text(), 'Відгуки')]"), "class", "active"));
    }
    @Test
    public void testPriceFilter() {
        productPage.applyPriceFilter("5000", "12000");
        wait.until(ExpectedConditions.urlContains("pf_=1"));
        List<WebElement> prices = driver.findElements(By.cssSelector("div[class*='price'] span"));
        for (WebElement price : prices) {
            String priceText = price.getText().replaceAll("[^\\d]", "");
            System.out.println("Ціна товару: " + priceText);
        }
    }
}
