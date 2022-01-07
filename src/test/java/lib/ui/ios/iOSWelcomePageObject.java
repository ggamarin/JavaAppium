package lib.ui.ios;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSWelcomePageObject extends WelcomePageObject {
    static
    {
        //Локаторы для iOS, версия приложения apks/Wikipedia.app
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia";
        STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore";
        STEP_ADD_OR_EDIT_PREFERRED = "id:Add or edit preferred languages";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected";
        NEXT_LINK = "id:Next";
        GET_STARTED_BUTTON = "id:Get started";
        SKIP = "id:Skip";
    }
    public iOSWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}