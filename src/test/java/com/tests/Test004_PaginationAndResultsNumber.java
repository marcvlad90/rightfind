package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test004_PaginationAndResultsNumber extends BaseTest {
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;

    @Test
    public void test004_PaginationAndResultsNumber() {
        homeSteps.performSearch(Constants.NUMBER_OF_RESULTS_QUERY_MAX);
        searchResultsSteps.loadAdditionalResultsIfExists();
        searchResultsSteps.selectTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_25);
        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_25);
        searchResultsSteps.checkThatPageDisplaysTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_25);
        searchResultsSteps.selectTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_50);
        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_50);
        searchResultsSteps.checkThatPageDisplaysTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_50);
        searchResultsSteps.selectTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_100);
        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_100);
        searchResultsSteps.checkThatPageDisplaysTheNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_100);
    }
}
