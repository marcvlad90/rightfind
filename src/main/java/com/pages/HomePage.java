package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {
    @FindBy(css = "input[placeholder='Enter your query to search multiple data sources']")
    private WebElement searchField;
    @FindBy(css = "#submit-search>i")
    private WebElement searchIcon;
    @FindBy(css = "div[class*='__left']>a>img")
    private WebElement homeLogo;

    public void insertSearchQuery(String searchQuery) {
        homeLogo.click();
        element(searchField).waitUntilVisible();
        element(searchField).type(searchQuery);
    }

    public void clickOnSearchIcon() {
        element(searchIcon).waitUntilVisible();
        searchIcon.click();
        waitForTextToAppear("Page 1 of");
    }
}
