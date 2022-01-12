package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for starting work")
public class GetStartedTest extends CoreTestCase
{

    @Test
    @Feature(value = "Skip welcome page")
    @DisplayName("Skip welcome on iOS")
    @Description("Skipping the welcome screens on iOS")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) ||  (Platform.getInstance().isMW()))
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