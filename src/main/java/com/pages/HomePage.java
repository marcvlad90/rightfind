package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {
    @FindBy(css = "input[placeholder='Enter your query to search multiple data sources']")
    private WebElement searchField;
    @FindBy(css = "i[class='fa fa-lg fa-search']")
    private WebElement searchIcon;
    @FindBy(css = "div[class*='__left']>a>img")
    private WebElement homeLogo;

    public void insertSearchQuery(String searchQuery) {
        element(searchField).waitUntilVisible();
        searchField.click();
        element(searchField).sendKeys(searchQuery);
    }

    public void clickOnSearchIcon() {
        element(searchIcon).waitUntilVisible();
        searchIcon.click();
        waitForTextToAppear("Page 1 of");
    }
}
