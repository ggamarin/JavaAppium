package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class WelcomePageObject extends MainPageObject
{
    //Локаторы  Android
    private static final String
            STEP_LEARN_MORE_LINK = "org.wikipedia:id/addLangContainer",
            STEP_NEW_WAYS_TO_EXPLORE = "//*[@text='New ways to explore']",
            STEP_ADD_OR_EDIT_PREFERRED = "//*[@text='Reading lists with sync']",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "//*[@text='Send anonymous data']",
            NEXT_LINK = "org.wikipedia:id/fragment_onboarding_forward_button",
            GET_STARTED_BUTTON = "org.wikipedia:id/fragment_onboarding_done_button";

    //Локаторы  iOS
  /* private static final String
           STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
           STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
           STEP_ADD_OR_EDIT_PREFERRED = "Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "Learn more about data collected",
           NEXT_LINK = "Next",
           SKIP = "id:Skip",
            GET_STARTED_BUTTON = "Get started";*/

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForScreenAddLang()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find link 'ADD OR EDIT LANGUAGES'",
                10);
    }

    public void waitForNewWaysToExp()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find 'New ways to explore' article",
                10);
    }

    public void waitForReadingList()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED,
                "Cannot find 'Reading lists with sync' article",
                10);
    }

    public void waitForSendAnonymousData()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Send anonymous data' article",
                10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find and click 'Continue' button",
                10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get Started' button",
                10);
    }

   /* public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 5 );
    }*/
}
