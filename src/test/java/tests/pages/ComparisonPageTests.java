package tests.pages;
import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ComparisonPage;
import java.time.Duration;

public class ComparisonPageTests extends BaseTest {
    private ComparisonPage comparisonPage;

    @BeforeMethod
    public void setUpPage() {
        comparisonPage = new ComparisonPage(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
    }

    @Test
    public void testCompareTwoProducts() {
        comparisonPage.openProductPage("https://ek.ua/ua/list/122/");
        comparisonPage.addFirstProductToComparison();
        comparisonPage.addSecondProductToComparison();
        comparisonPage.openComparisonPage();
        Assert.assertEquals(comparisonPage.getNumberOfComparedProducts(), 2, "Не всі товари відображаються у списку порівняння");
    }

    @Test
    public void testRemoveProductFromComparison() {
        comparisonPage.openComparisonPage();
        comparisonPage.removeProductFromComparison();
        Assert.assertEquals(comparisonPage.getNumberOfComparedProducts(), 1, "Товар не видалено зі списку порівняння");
    }

    @Test
    public void testCommonSpecifications() {
        comparisonPage.openComparisonPage();
        Assert.assertTrue(comparisonPage.getCommonSpecificationsSection().isDisplayed(), "Секція загальних характеристик не відображається");
        Assert.assertFalse(comparisonPage.getCommonSpecificationsSection().findElements(By.cssSelector("div.spec-item")).isEmpty(), "Загальні характеристики не відображаються");
    }

    @Test
    public void testShowDifferencesOnly() {
        comparisonPage.openComparisonPage();
        comparisonPage.filterDifferences();
        Assert.assertFalse(comparisonPage.getDifferences().isEmpty(), "Відмінності між товарами не відображаються");
    }

    @Test
    public void testCompareTwoProductsAndClearList() {
        comparisonPage.compareTwoProductsAndClearList();
    }

}
