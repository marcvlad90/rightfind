package com.tests;

import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

import java.io.IOException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.HomeSteps;
import com.steps.SearchResultsSteps;
import com.tools.constants.Constants;

@RunWith(SerenityRunner.class)
public class Test001_SearchForItem extends BaseTest {
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private SearchResultsSteps searchResultsSteps;

    @Test
    public void test001_FirstTest() throws IOException {
        withTestDataFrom(Constants.CSV_FILES_PATH + "Test001_SearchForItem.csv").run(searchResultsSteps).searchAndFindTheResult();
    }
}
