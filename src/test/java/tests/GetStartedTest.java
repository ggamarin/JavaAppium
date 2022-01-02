package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{

    @Test
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid())
        {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForScreenAddLang();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWaysToExp();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForReadingList();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForSendAnonymousData();
        WelcomePageObject.clickGetStartedButton();
    }
}