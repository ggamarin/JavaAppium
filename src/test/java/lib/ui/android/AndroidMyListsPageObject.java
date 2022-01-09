package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        DESCRIPTION = "id:org.wikipedia:id/page_list_item_description";
}
     public AndroidMyListsPageObject(RemoteWebDriver driver)
     {
         super(driver);
     }
 }

