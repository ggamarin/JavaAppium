package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;


public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON = "xpath://body/div/div/a[text()='Log in']",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Clicking the 'Log in' button")
    public  void clickAuthButton() throws InterruptedException {
        Thread.sleep(1000);
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button",5);
    }

    @Step("Input login and password")
    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"Cannot find and put a login to the login input",5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT,password,"Cannot find and put a password to the password input",5);
    }

    @Step("Clicking the submit button")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
