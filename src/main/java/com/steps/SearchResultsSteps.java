package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

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
    public void checkThatPageDisplaysTheNumberOfResultsPerPage(int expectedNumberOfResults) {
        Assert.assertTrue(
                String.format("There are %d results displayed instead of %d", searchResultsPage.getTheNumberOfResultsPerCurrentPage(), expectedNumberOfResults),
                searchResultsPage.getTheNumberOfResultsPerCurrentPage() == expectedNumberOfResults);
    }

    @Step
    public void selectNumberOfResultsPerPage(int numberOfResults) {
        searchResultsPage.selectNumberOfResultsPerPage(numberOfResults);
    }

    @Step
    public void sortResults(String sortingRule) {
        searchResultsPage.sortResults(sortingRule);
    }

    @Step
    public void checkThatTheListIsAlphabeticallyAscendingOrdered(int numberOfPagesToSearchIn) {
        searchResultsPage.checkThatTheListIsAlphabeticallyAscendingOrdered(numberOfPagesToSearchIn);
    }

    @Step
    public void checkThatTheListIsAlphabeticallyDescendingOrdered(int numberOfPagesToSearchIn) {
        searchResultsPage.checkThatTheListIsAlphabeticallyDescendingOrdered(numberOfPagesToSearchIn);
    }

    @Step
    public void selectTheNumberOfResultsPerPage(int numberOfResultsPerPage) {
        searchResultsPage.selectTheNumberOfResultsPerPage(numberOfResultsPerPage);
    }

    @Step
    public void checkThatItemIsPresentInTheList(String resultItemTitle) {
        searchResultsPage.checkThatItemIsPresentInTheList(resultItemTitle);
    }

    @StepGroup
    public void performSearch(String searchQuery) {
        insertSearchQuery(searchQuery);
        clickOnSearchIcon();
    }

    private String searchQuery, resultItemTitle;

    @StepGroup
    public void searchAndFindTheResult() {
        insertSearchQuery(searchQuery);
        clickOnSearchIcon();
        checkThatItemIsPresentInTheList(resultItemTitle);
    }
}
