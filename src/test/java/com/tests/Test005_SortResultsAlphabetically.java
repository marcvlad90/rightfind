package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test005_SortResultsAlphabetically extends BaseTest {
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;

    @Test
    public void test005_SortResultsAlphabetically() {
        homeSteps.performSearch(Constants.NUMBER_OF_RESULTS_QUERY_FEW);
        searchResultsSteps.sortResults(Constants.SORT_ALPHABETICALLY_ASCENDENT);
        WebElement[] list = searchResultsSteps.getAllTheResultsFromTheTheFirstPages(8);
    }
}
