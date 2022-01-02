package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

 public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
            ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
}
     public AndroidMyListsPageObject(AppiumDriver driver)
     {
         super(driver);
     }
 }

