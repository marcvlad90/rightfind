package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;

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
        searchResultsSteps.loadAdditionalResultsIfExists();
        //        searchResultsSteps.selectNumberOfResultsPerPage(Constants.NUMBER_OF_RESULTS_PER_PAGE_10);
        //        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_10);
        searchResultsSteps
                .checkIfItemIsPresentInTheList(
                        "Periprocedural anticoagulation during left atrial ablation: interrupted and uninterrupted vitamin K-antagonists or uninterrupted novel anticoagulants",
                        true);
        //            searchResultsSteps.checkThatPaginationNumbersAreCorrect(20);
        //            searchResultsSteps.selectNumberOfResultsPerPage(10);
        //            searchResultsSteps.checkThatPaginationNumbersAreCorrect(10);
    }
}
