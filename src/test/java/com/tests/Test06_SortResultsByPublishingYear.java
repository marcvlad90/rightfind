package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test06_SortResultsByPublishingYear extends BaseTest {
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;

    @Test
    public void sest06_SortResultsByPublishingYear() {
        homeSteps.performSearch(Constants.NUMBER_OF_RESULTS_QUERY_FEW);
        searchResultsSteps.sortResults(Constants.SORT_BY_YEAR_OLDEST);
        searchResultsSteps.checkThatTheListIsOrderedByOldest(10);
        homeSteps.performSearch(Constants.NUMBER_OF_RESULTS_QUERY_FEW);
        searchResultsSteps.sortResults(Constants.SORT_BY_YEAR_LATEST);
        searchResultsSteps.checkThatTheListIsOrderedByLatest(10);
    }
}
