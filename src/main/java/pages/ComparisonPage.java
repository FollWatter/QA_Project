package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ComparisonPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ComparisonPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openProductPage(String url) {
        driver.get(url);
    }

    public void addFirstProductToComparison() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.model-short-title")));
        firstProduct.click();
        WebElement addToCompareButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-compare")));
        addToCompareButton.click();
        driver.navigate().back();
    }

    public void addSecondProductToComparison() {
        WebElement secondProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.model-short-title")));
        secondProduct.click();
        WebElement addToCompareButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.add-to-compare")));
        addToCompareButton.click();
    }

    public void openComparisonPage() {
        driver.get("https://ek.ua/ua/compare/");
    }

    public int getNumberOfComparedProducts() {
        List<WebElement> comparedProducts = driver.findElements(By.cssSelector("div.comparison-product"));
        return comparedProducts.size();
    }

    public void removeProductFromComparison() {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.remove-from-compare")));
        removeButton.click();
    }

    public WebElement getCommonSpecificationsSection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.common-specs")));
    }

    public void filterDifferences() {
        WebElement differencesFilter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.filter-differences")));
        differencesFilter.click();
    }

    public List<WebElement> getDifferences() {
        return driver.findElements(By.cssSelector("div.difference-item"));
    }

    public void clearComparisonList() {
        By clearListButtonLocator = By.cssSelector("a.compare-action-delete-all");
        wait.until(ExpectedConditions.presenceOfElementLocated(clearListButtonLocator)); // Очікуємо наявність елемента
        WebElement clearListButton = wait.until(ExpectedConditions.elementToBeClickable(clearListButtonLocator));
        clearListButton.click();
    }
    public void compareTwoProductsAndClearList() {
        driver.get("https://ek.ua/ua/list/122/");
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.model-short-title")));
        firstProduct.click();
        WebElement addToCompareButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='chk_g_2603099']"))); // Змініть на ID вашого товару
        addToCompareButton.click();
        driver.navigate().back();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a.model-short-title")));
        WebElement secondProduct = products.get(1);
        secondProduct.click();
        addToCompareButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='chk_g_2529964']")));
        addToCompareButton.click();
        By comparisonToolbar = By.id("compared_goods_toolbar");
        WebElement comparisonButton = wait.until(ExpectedConditions.elementToBeClickable(comparisonToolbar));
        comparisonButton.click();
        js.executeScript("let button = document.querySelector('a.compare-action-delete-all[title=\"Удалить все модели из сравнения\"]'); if (button) { button.click(); } else { console.log('Кнопку не знайдено'); }");
    }

}
