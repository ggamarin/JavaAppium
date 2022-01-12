package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for change application conditions")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search"),@Feature(value = "Article"),@Feature(value = "Condition")})
    @DisplayName("Change screen orientation on search results")
    @Description("Opens an article, rotate the screen and checks that the article has not changed.")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangesScreenOrientationOnSearchResults() throws InterruptedException
    {
        if(Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation =  ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value={@Feature(value="Search"),@Feature(value = "Article"),@Feature(value = "Condition")})
    @DisplayName("Check search article in background")
    @Description("Checking that after the application returns from the background, the search results still present.")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground()
    {
        if(Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
