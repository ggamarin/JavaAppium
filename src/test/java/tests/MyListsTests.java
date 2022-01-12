package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for saved list")
public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "My list";
    private static final String
    login = "Volj228",
    password = "school443";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="List")})
    @DisplayName("Save article to list")
    @Description("Saving article to reading list and  delete it from list")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="List")})
    @DisplayName("Save two articles and delete one")
    @Description("Saving two articles to reading list and delete one")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaveArticleInExistingFolder() throws InterruptedException {

        String search_line = "Java";
        String article_title = "Java";
        String article_description1 = "Object-oriented programming language";
        String article_description2 = "Indonesian island";
        String article_description3 = "Island of Indonesia";


        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_description1);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title1 = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login",
                    article_title1,
                    ArticlePageObject.getArticleTitle());
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line);
        }

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring(article_description3);
        } else {
            SearchPageObject.clickByArticleWithSubstring(article_description2);
        }

        String article_title2 = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }

        MyListPageObject.swipeByArticleToDelete(article_title1);

        MyListPageObject.waitForArticleToDisappearByTitle(article_title1);

        if (Platform.getInstance().isAndroid()) {
            Assert.assertEquals(
                    "Unexpected description",
                    article_description3.toLowerCase(),
                    MyListPageObject.getDescription(article_description3).getAttribute("text").toLowerCase()
            );
        } else {
            Assert.assertEquals(
                    "Unexpected title",
                    article_title,
                    article_title2
            );
        }
    }
}



