package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://ek.ua/ua/?cgi_idsr_=104926");
    }

    public void selectCategoryFromMenu(String category) {
        WebElement categoryMenu = driver.findElement(By.linkText(category));
        categoryMenu.click();
    }

    public boolean isCategoryPageDisplayed(String category) {
        WebElement categoryPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + category + "')]"));
        return categoryPageHeader.isDisplayed();
    }
    public void changeLanguage(String language) {
        WebElement languageLink = driver.findElement(By.xpath("//a[contains(text(),'" + language + "')]"));
        languageLink.click();
    }

    public void selectFirstArticle() {
        WebElement firstArticle = driver.findElement(By.cssSelector(".articles .article-link"));
        firstArticle.click();
    }

    public boolean isArticlePageDisplayed() {
        WebElement articleContent = driver.findElement(By.cssSelector(".article-title"));
        return articleContent.isDisplayed();
    }

    public void registerAccount(String email, String password) {
        WebElement loginButton = driver.findElement(By.className("header_action_login"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailLoginOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("signin-with-ek")));
        emailLoginOption.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("l_")));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.name("pw_"));
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(), 'УВІЙТИ')]"));
        submitButton.click();
    }
    public void selectCategory(String category) {
        WebElement categoryLink = driver.findElement(By.linkText(category));
        categoryLink.click();
    }

    public void selectSubCategory(String subCategory) {
        WebElement subCategoryLink = driver.findElement(By.linkText(subCategory));
        subCategoryLink.click();
    }

    public boolean isSubCategoryPageDisplayed(String subCategory) {
        WebElement subCategoryPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + subCategory + "')]"));
        return subCategoryPageHeader.isDisplayed();
    }

}
