package com.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.pages.PageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class AbstractPage extends PageObject {
    public void customWait(final int seconds) {
        waitABit(seconds * 1000);
    }

    public void goBack() {
        getDriver().navigate().back();
    }

    public void scrollToPageTop() {
        evaluateJavascript("window.scrollTo(document.body.scrollHeight,0);");
    }

    public void scrollToPageBottom() {
        evaluateJavascript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void checkThatElementDoesntExist(final WebElement element) {
        boolean elementPresent = false;
        try {
            elementPresent = element.isDisplayed();
        } catch (final Exception e) {
            elementPresent = false;
        }
        Assert.assertFalse("The element should not be present!", elementPresent);
    }

    public WebElement findAndWaitForElementToBePresent(final WebElement element, final int timeoutSeconds) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        final WebElement searchedElement = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(final WebDriver driver) {
                return element;
            }
        });
        return searchedElement;
    }

    public boolean waitUntilElementNotPresentOrInvisible(final By locator, final int timeoutSeconds) {
        final Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        final boolean elementNotPresent = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return elementNotPresent;
    }

    public WebElement getElementWhenVisible(final By by, final int timeoutSeconds) {
        getDriver().manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        WebElement element = null;
        for (int i = 0; i < (timeoutSeconds * 10); i++) {
            try {
                element = getDriver().findElement(by);
                if ($(element).isVisible()) {
                    return element;
                }
            } catch (final Exception e) {
            }
        }
        Assert.fail(String.format("The searched element '%s' was not visible after %d seconds!", by.toString(), timeoutSeconds));
        return element;
    }

    public String closeNewestOpenedTab() {
        final Set<String> winSet = getDriver().getWindowHandles();
        final List<String> winList = new ArrayList<String>(winSet);
        final String previousTab = winList.get(winList.size() - 2);
        final String newTab = winList.get(winList.size() - 1);
        getDriver().switchTo().window(newTab).close();
        getDriver().switchTo().window(previousTab);
        return previousTab;
    }

    public String switchToNewestOpenedTab() {
        final Set<String> winSet = getDriver().getWindowHandles();
        final List<String> winList = new ArrayList<String>(winSet);
        Assert.assertTrue("There is only one tab!", winList.size() > 1);
        final String previousTab = winList.get(winList.size() - 2);
        final String newTab = winList.get(winList.size() - 1);
        getDriver().switchTo().window(newTab);
        getDriver().manage().window().maximize();
        return previousTab;
    }

    public String switchToNewestOpenedTabIfExists() {
        final Set<String> winSet = getDriver().getWindowHandles();
        final List<String> winList = new ArrayList<String>(winSet);
        final String previousTab = winList.get(winList.size() > 2 ? winList.size() - 2 : 0);
        final String newTab = winList.get(winList.size() - 1);
        getDriver().switchTo().window(newTab);
        getDriver().manage().window().maximize();
        return previousTab;
    }

    public boolean checkIfElementExists(final WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    public boolean clickOnButtonIfExists(final WebElement element) {
        try {
            element.click();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    public void waitForListNewSize(final List<WebElement> list, final int sizeBefore, final int timeoutSeconds, final boolean assertListSizeChanged) {
        int counter = 0;
        while ((sizeBefore == list.size()) && (counter < timeoutSeconds)) {
            waitABit(1000);
            counter++;
        }
        if (assertListSizeChanged) {
            Assert.assertTrue("The list size did not change", sizeBefore == list.size());
        }
    }

    public void waitForPageToLoadCompletely(final long timeOutInSeconds) {
        final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            new WebDriverWait(getDriver(), timeOutInSeconds).until(expectation);
        } catch (final TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void waitForListToLoad(final List<WebElement> list, final int timeoutSeconds, final boolean assertListNotEmpty) {
        int counter = 0;
        while ((list.size() == 0) && (counter < timeoutSeconds)) {
            waitABit(1000);
            counter++;
        }
        if (assertListNotEmpty) {
            Assert.assertTrue("List is empty", list.size() > 0);
        }
    }

}
