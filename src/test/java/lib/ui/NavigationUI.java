package lib.ui;


import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class NavigationUI extends MainPageObject {
    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION,
            ITEM_CONTAINER;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Open navigation menu( for MobileWeb)")
    public void openNavigation() throws InterruptedException {
        if (Platform.getInstance().isMW()) {
            Thread.sleep(1000);
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button.", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Click My list")
    public void clickMyLists() throws InterruptedException {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My List",
                    5
            );
            Thread.sleep(1000);
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5);
            this.waitForElementPresent(ITEM_CONTAINER,
                    "Cannot find folder",
                    10);
        }
    }
}
