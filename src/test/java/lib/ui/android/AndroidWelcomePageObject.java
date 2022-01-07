package lib.ui.android;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject {
    static
    {
        STEP_LEARN_MORE_LINK = "id:org.wikipedia:id/addLangContainer";
        STEP_NEW_WAYS_TO_EXPLORE = "xpath://*[@text='New ways to explore']";
        STEP_ADD_OR_EDIT_PREFERRED = "xpath://*[@text='Reading lists with sync']";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://*[@text='Send anonymous data']";
        NEXT_LINK = "id:org.wikipedia:id/fragment_onboarding_forward_button";
        GET_STARTED_BUTTON = "id:org.wikipedia:id/fragment_onboarding_done_button";
        SKIP = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    }
    public AndroidWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}