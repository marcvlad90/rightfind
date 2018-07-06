package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test001_FirstTest extends BaseTest {
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;

    @Test
    public void test001_FirstTest() {
        homeSteps
                .performSearch("European collaboration");
        searchResultsSteps.selectNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_10);
        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_10);
        searchResultsSteps
                .checkIfItemIsPresentInTheList(
                        "Incomplete Infarction Trial of European Research Collaborators Evaluating Prognosis Post-Thrombolysis (INTERCEPT): a randomized comparison of diltiazem once daily and placebo following thrombolysis-treated myocardial infarction.",
                        true);
        searchResultsSteps.checkIfItemIsPresentInTheList(
                "vlad marc", false);
        //            searchResultsSteps.checkThatPaginationNumbersAreCorrect(20);
        //            searchResultsSteps.selectNumberOfResultsPerPage(10);
        //            searchResultsSteps.checkThatPaginationNumbersAreCorrect(10);
    }
}
