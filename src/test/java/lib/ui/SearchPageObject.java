package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 abstract public class SearchPageObject extends  MainPageObject {

   protected  static  String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT ,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_ELEMENT ,
    SEARCH_EMPTY_RESULT_ELEMENT ,
    TEXT_ELEMENT ,
    SEARCH_RESULT_TITLE ;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");

        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }

    //Получение результатов поиска
    public int getAmountOfFoundArticle()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results");
    }


    public void assertTextHasTitle()
    {
        this.assertElementPresent(
                SEARCH_RESULT_TITLE,
                "A title not present." );
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring" + substring,
                10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request" ,
                5
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element", 10);

    }

   public  void elementContainsText() {
       this.assertElementHasText(TEXT_ELEMENT,
               "Search…",
               "Unexpected text");
   }
}
