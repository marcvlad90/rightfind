package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.utils.StringUtils;

public class SearchResultsPage extends AbstractPage {
    private final String paginationContainerCssSelector = "div[class*='__results-number']";
    @FindBy(css = ".__per-page")
    private WebElement numberOfResultsPerPageDropdown;

    public void selectNumberOfResultsPerPage(String numberOfResults) {
        element(numberOfResultsPerPageDropdown).waitUntilVisible();
        element(numberOfResultsPerPageDropdown).selectByVisibleText(numberOfResults);
    }

    public Integer getSearchResultsNumber() {
        return StringUtils.getFirstIntegerNumberFromString(getDriver().findElement(By.cssSelector(paginationContainerCssSelector)).getText());
    }

    public Integer getCurrentPageNumber() {
        return StringUtils.getFirstIntegerNumberAfterKeyFromString(getDriver().findElement(By.cssSelector(paginationContainerCssSelector)).getText(), "Page ");
    }

    public Integer getNumberOfPages() {
        return StringUtils.getFirstIntegerNumberAfterKeyFromString(getDriver().findElement(By.cssSelector(paginationContainerCssSelector)).getText(), "of ");
    }

    public void checkThatPaginationNumbersAreCorrect(int numberOfItemsPerPage) {
        waitForTextToAppear("Page 1 of");
        int searchResultsNumber = getSearchResultsNumber();
        int numberOfPages = getNumberOfPages();
        int expectedResultsNumber;
        int modValue = searchResultsNumber % numberOfItemsPerPage;
        if (modValue == 0) {
            expectedResultsNumber = numberOfItemsPerPage * numberOfPages;
        }
        else {
            expectedResultsNumber = (numberOfItemsPerPage * (numberOfPages - 1)) + modValue;
        }
        System.out.println("Actual number is:" + searchResultsNumber);
        System.out.println("Expected number is:" + expectedResultsNumber);
        Assert.assertTrue(String.format("The expected results number is %d not %d", expectedResultsNumber, searchResultsNumber),
                searchResultsNumber == expectedResultsNumber);
    }
}
