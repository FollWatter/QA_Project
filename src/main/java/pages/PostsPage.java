package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class PostsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PostsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Відкрити головну сторінку")
    public void open() {
        driver.get("https://ek.ua/");
    }
    @Step("Вибрати формат повної сторінки для постів")
    public void selectFullPageFormat() {
        WebElement allMaterialsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.articles-all[href*='ek-post.php']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allMaterialsLink);
        WebElement fullPageFormatButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.post-list-mode-12.ib")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fullPageFormatButton);
    }
    @Step("Сортувати статті за корисністю")
    public void sortByUsefulness() {
        WebElement allMaterialsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.articles-all[href*='ek-post.php']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allMaterialsLink);
        WebElement sortByUsefulnessButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='order_=useful']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sortByUsefulnessButton);
    }
    @Step("Вибрати тему статті")
    public void selectArticleTopic() {
        try {
            WebElement allMaterialsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.articles-all[href*='ek-post.php']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allMaterialsLink);
            WebElement autoMotoLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/ua/posts/2/']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", autoMotoLink);
            WebElement fmTransmittersLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/ua/posts/537/']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fmTransmittersLink);
            WebElement articleLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.article-link[title='Гід по ключовим суббрендам Xiaomi']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", articleLink);
        } catch (TimeoutException e) {
            System.out.println("Element not found within the time frame: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        }
    }
    @Step("Відкрити конкретну статтю")
    public void openArticle() {
        try {
            WebElement allMaterialsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.articles-all[href*='ek-post.php']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allMaterialsLink);
            WebElement secondPageLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/ua/ek-post.php?katalog_=1&view_=posts&page_=1&mode_=short']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondPageLink);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 6; i++) {
                js.executeScript("window.scrollBy(0, 400)");
                Thread.sleep(1000);
            }
            WebElement articleLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.article-link[title='Найкращі портативні колонки з функцією Power Bank']")));
            js.executeScript("arguments[0].scrollIntoView(true);", articleLink);
            js.executeScript("arguments[0].click();", articleLink);
        } catch (TimeoutException e) {
            System.out.println("Element not found within the time frame: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }
    }
    @Step("Перейти до продукту в статті")
    public void navigateToProductInArticle() {
        try {
            WebElement allMaterialsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.articles-all[href*='ek-post.php']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allMaterialsLink);
            WebElement articleLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.article-link[title='Найкращі гігабітні маршрутизатори з підтримкою Wi-Fi 6']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", articleLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", articleLink);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.artimg__href[href*='TP-LINK-ARCHER-AX12.htm']")));
            js.executeScript("arguments[0].scrollIntoView(true);", productLink);
            js.executeScript("arguments[0].click();", productLink);
        } catch (TimeoutException e) {
            System.out.println("Element not found within the time frame: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        }
    }
}
