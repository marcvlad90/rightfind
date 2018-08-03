package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.Test001_SearchForItems;

@RunWith(Suite.class)
@SuiteClasses({
        Test001_SearchForItems.class,
    //    Test002_DirectSearchForItems.class,
    //    Test003_BooleanLogicOnSearch.class,
    //    Test004_PaginationAndResultsNumber.class,
    //    Test005_SortResultsAlphabetically.class,
    //    Test06_SortResultsByPublishingYear.class,
})
public class TestsSuiteRightfind {

}
