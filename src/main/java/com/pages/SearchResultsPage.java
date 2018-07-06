package com.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.utils.StringUtils;

public class SearchResultsPage extends AbstractPage {
    private final String paginationContainerCssSelector = "div[class*='__results-number']";
    @FindBy(css = "span[class*='__results-per-page'] .ember-view")
    private WebElement numberOfResultsPerPageElement;
    @FindBy(css = "span[class*='__pagination'] i[class='fa fa-fas fa-angle-right']")
    private WebElement nextPageNavigationElement;
    @FindBy(css = "div[class*='info ember-view'] button:nth-child(2)")
    private WebElement addiionalResultsAddButton;
    private final String resultsItemsListCssSelector = "div[class*='results-page__results-list ']>ul>li";
    private final String resultTitleCssSelector = "div[class*='__title']>a>span:nth-child(1)";

    public void loadAdditionalResultsIfExists() {
        if (getSearchResultsNumber() < 2000) {
            waitForTextToAppear("There are additional results available - please add them to see the complete list of results and update the filters and the content insights");
            clickOnButtonIfExists(addiionalResultsAddButton);
        }
    }

    public void selectNumberOfResultsPerPage(int numberOfResults) {
        element(numberOfResultsPerPageElement).waitUntilVisible();
        element(numberOfResultsPerPageElement).click();
        numberOfResultsPerPageElement.findElement(By.xpath("//li[text() = '" + String.valueOf(numberOfResults) + "']")).click();
    }

    public WebElement getResultItemIfExists(String resultItemTitle) {
        List<WebElement> resultsItems = getDriver().findElements(By.cssSelector(resultsItemsListCssSelector));
        for (WebElement resultItem : resultsItems) {
            if (resultItem.findElement(By.cssSelector(resultTitleCssSelector)).getText().contentEquals(resultItemTitle)) {
                return resultItem;
            }
        }
        return null;
    }

    public void navigateToNextPage() {
        int currentPageNumber = getCurrentPageNumber();
        nextPageNavigationElement.click();
        waitForTextToAppear("Page " + String.valueOf(currentPageNumber + 1) + " of " + String.valueOf(getNumberOfPages()));
    }

    public void checkIfItemIsPresentInTheList(String resultItemTitle, boolean shouldBePresent) {
        int numberOfPages = getNumberOfPages();
        boolean isFound = false;
        for (int i = 1; i <= numberOfPages; i++) {
            if (getResultItemIfExists(resultItemTitle) != null) {
                isFound = true;
                break;
            }
            else {
                navigateToNextPage();
            }
        }
        if (shouldBePresent) {
            Assert.assertTrue("\"" + resultItemTitle + "\"" + " item was not found!", isFound);
        }
        else {
            Assert.assertFalse("\"" + resultItemTitle + "\"" + " item was found!", isFound);
        }
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
        Assert.assertTrue(String.format("The expected results number is %d not %d", expectedResultsNumber, searchResultsNumber),
                searchResultsNumber == expectedResultsNumber);
    }

}
