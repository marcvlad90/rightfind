package com.tests;

import net.thucydides.core.annotations.Managed;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.tools.constants.EnvironmentConstants;

public class BaseTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Before
    public void before() {
        webdriver.manage().window().maximize();
        webdriver.get(EnvironmentConstants.BASE_URL);
    }
}
