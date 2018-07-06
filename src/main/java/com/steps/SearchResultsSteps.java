package com.steps;

import net.thucydides.core.annotations.Step;

import com.pages.SearchResultsPage;

public class SearchResultsSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private SearchResultsPage searchResultsPage;

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
}
