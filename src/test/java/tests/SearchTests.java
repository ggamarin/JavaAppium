package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Feature(value="Search")
    @DisplayName("Search for the  article")
    @Description("Open search line, input name of article and checking search result ")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Feature(value="Search")
    @DisplayName("Search cancel")
    @Description("Finding  some results and canceling the search")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Not empty search amount")
    @Description("The valid search returns a few articles")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results  = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Empty search")
    @Description("The not valid search returns null result")
    @Severity(value= SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "zvergsf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }



    @Test
    @Feature(value = "Search")
    @DisplayName("Cancel searching the articles")
    @Description("Search the articles and cancel the search")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchAndCancel()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticle();

        Assert.assertTrue(
                "Search result still present on the page",
                amount_of_search_results > 0);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Search title in results")
    @Description("Search the article by word and make sure that search result has it in title name")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAssertTitle() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        SearchPageObject.assertTextHasTitle();
    }
}
