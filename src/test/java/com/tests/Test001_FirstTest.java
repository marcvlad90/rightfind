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
                .performSearch("Pathology of familial breast cancer differences between breast cancers in carriers of BRCA1 or BRCA2 mutations and sporadic cases");
        searchResultsSteps.loadAdditionalResultsIfExists();
        searchResultsSteps
                .checkThatItemIsPresentInTheList("Overall survival and clinical characteristics of BRCA mutation carriers with stage I/II pancreatic cancer");
    }
}
