package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.pages.SearchResultsPage;

public class SearchResultsSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private SearchResultsPage searchResultsPage;

    @Step
    public void clickOnHomeLogo() {
        searchResultsPage.clickOnHomeLogo();
    }

    @Step
    public void insertSearchQuery(String searchQuery) {
        searchResultsPage.insertSearchQuery(searchQuery);
    }

    @Step
    public void clickOnSearchIcon() {
        searchResultsPage.clickOnSearchIcon();
    }

    @Step
    public void loadAdditionalResultsIfExists() {
        searchResultsPage.loadAdditionalResultsIfExists();
    }

    @Step
    public void checkThatPaginationNumbersAreCorrect(int numberOfItemsPerPage) {
        searchResultsPage.checkThatPaginationNumbersAreCorrect(numberOfItemsPerPage);
    }

    @Step
    public void selectNumberOfResultsPerPage(int numberOfResults) {
        searchResultsPage.selectNumberOfResultsPerPage(numberOfResults);
    }

    @Step
    public void checkThatItemIsPresentInTheList(String resultItemTitle) {
        searchResultsPage.checkThatItemIsPresentInTheList(resultItemTitle);
    }

    private String searchQuery, resultItemTitle;

    @StepGroup
    public void searchAndFindTheResult() {
        insertSearchQuery(searchQuery);
        clickOnSearchIcon();
        loadAdditionalResultsIfExists();
        checkThatItemIsPresentInTheList(resultItemTitle);
    }
}
