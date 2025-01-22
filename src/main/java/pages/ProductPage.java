package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToStore() {
        By storeLink = By.cssSelector("a[href*='/prices']");
        WebElement store = wait.until(ExpectedConditions.elementToBeClickable(storeLink));
        store.click();
    }

    public void goToFirstStoreFromPrices() {
        By firstStoreButton = By.cssSelector("div.hide-blacked a.yel-but-2");
        WebElement firstStore = wait.until(ExpectedConditions.elementToBeClickable(firstStoreButton));
        firstStore.click();
    }
    public void openSpecificationsTab() {
        By specificationsTab = By.xpath("//a[contains(text(), 'Характеристики')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(specificationsTab));
        WebElement specifications = wait.until(ExpectedConditions.elementToBeClickable(specificationsTab));
        specifications.click();
    }

    public void addToComparison(String productId) {
        By addToComparisonButton = By.id("label_" + productId);
        WebElement addToComparison = wait.until(ExpectedConditions.elementToBeClickable(addToComparisonButton));
        addToComparison.click();

        By comparisonToolbar = By.id("compared_goods_toolbar");
        WebElement comparisonButton = wait.until(ExpectedConditions.elementToBeClickable(comparisonToolbar));
        comparisonButton.click();
    }
    public void openReviewsTab() {
        By reviewsTab = By.xpath("//a[contains(text(), 'Відгуки')]");
        WebElement reviews = wait.until(ExpectedConditions.elementToBeClickable(reviewsTab));
        reviews.click();
    }


    public void applyPriceFilter(String minPrice, String maxPrice) {
        By minPriceInput = By.id("minPrice_");
        By maxPriceInput = By.id("maxPrice_");
        By showModelsButton = By.cssSelector("a.show-models");
        WebElement minPriceField = wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceInput));
        minPriceField.clear();
        minPriceField.sendKeys(minPrice);
        WebElement maxPriceField = wait.until(ExpectedConditions.visibilityOfElementLocated(maxPriceInput));
        maxPriceField.clear();
        maxPriceField.sendKeys(maxPrice);

        // Очікуємо, поки кнопка "Показати" стане клікабельною
        WebElement showModels = wait.until(ExpectedConditions.elementToBeClickable(showModelsButton));
        showModels.click();
    }

}
