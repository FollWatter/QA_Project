package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;
public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Відкрити головну сторінку")
    public void open() {
        driver.get("https://ek.ua/");
    }
    @Step("Вибрати категорію з меню: {category}")
    public void selectCategoryFromMenu(String category) {
        WebElement categoryMenu = driver.findElement(By.linkText(category));
        categoryMenu.click();
    }
    @Step("Перевірити, чи відображається сторінка категорії: {category}")
    public boolean isCategoryPageDisplayed(String category) {
        WebElement categoryPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + category + "')]"));
        return categoryPageHeader.isDisplayed();
    }
    @Step("Змінити мову на: {language}")
    public void changeLanguage(String language) {
        WebElement languageLink = driver.findElement(By.xpath("//a[contains(text(),'" + language + "')]"));
        languageLink.click();
    }
    @Step("Перемикання режиму день/ніч")
    public void toggleDayNightMode() {
        driver.get("https://ek.ua/");
        WebElement themeToggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg.dark-mode-switch-icon")));
        Actions actions = new Actions(driver);
        actions.moveToElement(themeToggleButton).click().perform();
    }
    @Step("Реєстрація облікового запису з email: {email}")
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
    @Step("Вибрати категорію: {category}")
    public void selectCategory(String category) {
        WebElement categoryLink = driver.findElement(By.linkText(category));
        categoryLink.click();
    }
    @Step("Вибрати підкатегорію: {subCategory}")
    public void selectSubCategory(String subCategory) {
        WebElement subCategoryLink = driver.findElement(By.linkText(subCategory));
        subCategoryLink.click();
    }
    @Step("Перевірити, чи відображається сторінка підкатегорії: {subCategory}")
    public boolean isSubCategoryPageDisplayed(String subCategory) {
        WebElement subCategoryPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + subCategory + "')]"));
        return subCategoryPageHeader.isDisplayed();
    }
}
