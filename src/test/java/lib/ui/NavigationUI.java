package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {
   protected static  String
    MY_LISTS_LINK ,
    ITEM_CONTAINER;

    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists() {

        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My list",
                5
        );

        this.waitForElementPresent(ITEM_CONTAINER,"Cannot find folder", 10);
    }
}
