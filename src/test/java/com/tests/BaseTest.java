package com.tests;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.steps.LoginSteps;
import com.tools.constants.EnvironmentConstants;

public class BaseTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    @Steps
    private LoginSteps loginSteps;

    @Before
    public void before() {
        webdriver.manage().window().maximize();
        webdriver.get(EnvironmentConstants.BASE_URL);
        loginSteps.signIn();
    }
}
