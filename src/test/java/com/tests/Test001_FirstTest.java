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
        for (int i = 0; i < 100; i++) {
            homeSteps.performSearch("European collaboration in trials of new agents for children with cancer");
            searchResultsSteps.checkThatPaginationNumbersAreCorrect(5);
            searchResultsSteps.selectNumberOfResultsPerPage("20");
            searchResultsSteps.checkThatPaginationNumbersAreCorrect(20);
            searchResultsSteps.selectNumberOfResultsPerPage("10");
            searchResultsSteps.checkThatPaginationNumbersAreCorrect(10);
        }
    }
}
