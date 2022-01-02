package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT =  "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";

        //требуется обновление под iOS
        TEXT_ELEMENT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
