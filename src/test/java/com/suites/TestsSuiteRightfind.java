package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.Test001_SearchForItem;
import com.tests.Test002_PaginationAndResultsNumber;

@RunWith(Suite.class)
@SuiteClasses({
    Test001_SearchForItem.class,
    Test001_SearchForItem.class, Test001_SearchForItem.class,
    Test001_SearchForItem.class, Test001_SearchForItem.class, Test001_SearchForItem.class, Test001_SearchForItem.class, Test001_SearchForItem.class,
    Test001_SearchForItem.class, Test001_SearchForItem.class, Test001_SearchForItem.class,
    Test002_PaginationAndResultsNumber.class,
})
public class TestsSuiteRightfind {

}
