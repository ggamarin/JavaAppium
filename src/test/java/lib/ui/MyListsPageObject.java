package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


abstract public class MyListsPageObject extends MainPageObject {

    protected static  String ARTICLE_BY_TITLE_TPL ;
    protected static String FOLDER_BY_NAME_TPL;
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

   public void openFolderByName(String name_of_folder)
    {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find created folder",
                10
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if(Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath,"Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title" + article_title,
                15);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
               article_xpath,
                "Cannot find saved article by title" + article_title,
                15);
    }

    protected String getFolderXpathByName(String name_of_Folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_Folder);
    }

}
