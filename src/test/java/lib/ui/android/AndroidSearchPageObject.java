package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

     static {
         SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                 SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
                 SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                 SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description']//*[contains(@text, '{SUBSTRING}']";
                 SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                 SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
                 TEXT_ELEMENT = "id:org.wikipedia:id/search_src_text";
                 SEARCH_RESULT_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";
     }

    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
