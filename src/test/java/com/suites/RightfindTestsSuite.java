package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.Test001_SearchForItem;

@RunWith(Suite.class)
@SuiteClasses({
    Test001_SearchForItem.class,
})
public class RightfindTestsSuite {

}
