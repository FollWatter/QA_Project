package tests.pages;

import framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PostsPage;
import io.qameta.allure.Description;
import static framework.DriverManager.getDriver;

public class PostsPageTests extends BaseTest {
    private PostsPage postsPage;

    @BeforeMethod
    public void setUpMethod() {
        postsPage = new PostsPage(getDriver());
        postsPage.open();
    }

    @Test
    @Description("Тест перевірки вибору формату повної сторінки для постів")
    public void testSelectFullPageFormat() {
        postsPage.selectFullPageFormat();
    }

    @Test
    @Description("Тест сортування статей за корисністю")
    public void testSortByUsefulness() {
        postsPage.sortByUsefulness();
    }

    @Test
    @Description("Тест вибору теми статті")
    public void testSelectArticleTopic() {
        postsPage.selectArticleTopic();
    }

    @Test
    @Description("Тест відкриття конкретної статті")
    public void testOpenArticle() {
        postsPage.openArticle();
    }

    @Test
    @Description("Тест переходу до продукту через статтю")
    public void testNavigateToProductInArticle() {
        postsPage.navigateToProductInArticle();
    }
}
