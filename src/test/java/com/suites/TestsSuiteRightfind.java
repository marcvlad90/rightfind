package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.Test001_SearchForItems;
import com.tests.Test002_DirectSearchForItems;
import com.tests.Test003_BooleanLogicOnSearch;
import com.tests.Test004_PaginationAndResultsNumber;
import com.tests.Test005_SortResultsAlphabetically;
import com.tests.Test006_SortResultsByPublishingYear;

@RunWith(Suite.class)
@SuiteClasses({
    Test001_SearchForItems.class,
    Test002_DirectSearchForItems.class,
    Test003_BooleanLogicOnSearch.class,
    Test004_PaginationAndResultsNumber.class,
    Test005_SortResultsAlphabetically.class,
    Test006_SortResultsByPublishingYear.class,
})
public class TestsSuiteRightfind {

}
