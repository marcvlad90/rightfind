package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.pages.HomePage;

public class HomeSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private HomePage homePage;

    @Step
    public void insertSearchQuery(String searchQuery) {
        homePage.insertSearchQuery(searchQuery);
    }

    @Step
    public void clickOnSearchIcon() {
        homePage.clickOnSearchIcon();
    }

    @StepGroup
    public void performSearch(String searchQuery) {
        insertSearchQuery(searchQuery);
        clickOnSearchIcon();
    }
}
