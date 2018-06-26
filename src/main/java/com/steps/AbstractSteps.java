package com.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import com.pages.AbstractPage;

public class AbstractSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private AbstractPage abstractPage;

    @Step
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    @Step
    public void customWait(int seconds) {
        abstractPage.customWait(seconds);
    }

    @Step
    public void goBack() {
        abstractPage.goBack();
    }
}
