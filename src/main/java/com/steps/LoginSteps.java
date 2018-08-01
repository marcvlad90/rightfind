package com.steps;

import net.thucydides.core.annotations.Step;

import com.pages.LoginPage;

public class LoginSteps extends AbstractSteps {

    private static final long serialVersionUID = -3284451004666641400L;

    private LoginPage loginPage;

    @Step
    public void signIn() {
        loginPage.signIn();
    }
}
