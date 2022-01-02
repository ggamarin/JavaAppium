package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;


public class MainPageObject {


    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }


    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public  WebElement waitForElementAndClear(String locator, String error_mesage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_mesage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public boolean assertElementHasText (String locator, String text_element, String error_message)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.attributeContains(by,"text",text_element)
        );
    }

    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }


    public void swipeUp(int timeofSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeofSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void  swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if(already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        int alreadySwiped = 0;                              //счетчик свайпов
        while (!this.isElementLocatedOnTheScreen(locator))  //пока элемент не находится на экране, мы будем swipeUpQuick и инкрементировать alreadySwiped
        {
            if(alreadySwiped > max_swipes) {                //при превышение максмального кол-ва свайпов max_swipes выход
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        //находим эл-т по локатору и получаем его расположение по оси Y
        int element_location_by_y = this.waitForElementPresent(locator,"Cannot find element by locator",5)
                .getLocation()
                .getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight(); //получаем длину всего экрана
        //пока не доскролим до переменной screen_size_by_y будем возвращать false, как только доберемся - true
        return element_location_by_y < screen_size_by_y;
    }

    // Для iOS: метод будет кликать по кнопке удаления (красная корзина) для удалении статьи из избранного
    public void clickElementToTheRightUpperCorner(String locator, String error_message)
    {
        WebElement element = this.waitForElementPresent(locator + "/..",error_message); //locator + "/.." - переход на элемент выше
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getWidth();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;  //на 3 пикселя левее чем ширина элемента
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(point_to_click_x,point_to_click_y)).perform();
    }

    public void swipeElementToLeft(String locator, String error_message)
    {
        WebElement element =  waitForElementPresent(locator, error_message,10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) /2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x,middle_y));

        //В Android работаем с относительными координатами, относительно элемента
        //и свайпаем от точке к точке, то в iOS  свайпаем на определенную ширину от начальной точки. Поэтому для iOS будем свайпать на
        //всю ширину элемента.
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
        if(Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());         //(-1 * ширину эл-та), т.е. крайняя левая точка
            action.moveTo(PointOption.point(offset_x, 0));       //свайп на всю ширину эл-та
        }
                action.release();
                action.perform();
    }

    public int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return  elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0){
            String default_message = "An element'" + locator + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return  element.getAttribute(attribute);
    }

    public void  assertElementPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements == 0){
            String default_message = "An element'" + locator + "'supposed to be  present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private By getLocatorByString(String locatorWithType)
    {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"),2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return  By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }

    /*protected String getFolderXpathByName(String name_of_Folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_Folder);
    }

    public void openFolderByName(String name_of_folder)
    {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder",
                10
        );
    }*/
}
