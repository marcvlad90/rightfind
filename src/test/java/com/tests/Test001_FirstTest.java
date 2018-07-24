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
        .performSearch("Pathology of familial breast cancer differences between breast cancers in carriers of BRCA1 or BRCA2 mutations and sporadic cases");
        searchResultsSteps.loadAdditionalResultsIfExists();
        searchResultsSteps.checkThatPaginationNumbersAreCorrect(Constants.NUMBER_OF_RESULTS_PER_PAGE_25);
        searchResultsSteps
        .checkThatItemIsPresentInTheList("Gene expression profiling in biopsied tumor tissues");
    }
}
