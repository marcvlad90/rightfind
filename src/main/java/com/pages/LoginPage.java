package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.constants.EnvironmentConstants;

public class LoginPage extends AbstractPage {
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(css = "input[class='btn btn-submit btn-block']")
    private WebElement signInButton;

    public void signIn() {
        element(usernameField).waitUntilVisible();
        element(usernameField).sendKeys(EnvironmentConstants.TEST_USER_NAME);
        element(passwordField).sendKeys(EnvironmentConstants.TEST_USER_PASSWORD);
        signInButton.click();
    }
}
