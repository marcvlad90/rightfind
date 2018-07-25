package com.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.utils.StringUtils;

public class SearchResultsPage extends AbstractPage {
    private final String paginationContainerCssSelector = ".result-page__center>div:nth-child(1)";
    @FindBy(css = "span[class*='__results-per-page'] .ember-view")
    private WebElement numberOfResultsPerPageElement;
    @FindBy(css = "i[class='fa fa-fas fa-angle-right']")
    private WebElement nextPageNavigationElement;
    @FindBy(css = "div[class*='info ember-view'] button:nth-child(2)")
    private WebElement addionalResultsAddButton;
    @FindBy(css = "input[placeholder='Enter your query to search multiple data sources']")
    private WebElement searchField;
    @FindBy(css = "i[class='fa fa-lg fa-search']")
    private WebElement searchIcon;
    @FindBy(css = "div[class*='__left']>a>img")
    private WebElement homeLogo;
    private final String resultsItemsListCssSelector = "div[class*='result-page__results-list ']>ul>li";
    private final String resultTitleCssSelector = "div>div>a>span:nth-child(1)";

    public void clickOnHomeLogo() {
        clickOnElementIfExists(homeLogo);
    }

    public void clickOnSearchIcon() {
        element(searchIcon).waitUntilVisible();
        String beforeUrl = getDriver().getCurrentUrl();
        searchIcon.click();
        do {
            waitABit(1000);
        } while (beforeUrl.contentEquals(getDriver().getCurrentUrl()));
        waitForListToLoad(getDriver().findElements(By.cssSelector(resultsItemsListCssSelector)), 5, false);
    }

    public void insertSearchQuery(String searchQuery) {
        element(searchField).waitUntilVisible();
        searchField.click();
        searchField.clear();
        element(searchField).sendKeys(searchQuery);
    }

    public void loadAdditionalResultsIfExists() {
        try {
            waitForTextToAppear("There are additional results available - please add them to see the complete list of results and update the filters and the content insights");
            clickOnElementIfExists(addionalResultsAddButton);
        } catch (Exception e) {
            searchIcon.click();
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
        WebElement firstResultFromCurrentPage = getDriver().findElements(By.cssSelector("div[class*='result-page__results-list']>ul>li")).get(0);
        nextPageNavigationElement.click();
        waitUntilElementDoesntExist(firstResultFromCurrentPage, 5);
    }

    public void checkThatItemIsPresentInTheList(String resultItemTitle) {
        int numberOfPages = getNumberOfPages();
        boolean isFound = false;
        for (int i = 1; i < numberOfPages; i++) {
            if (getResultItemIfExists(resultItemTitle) != null) {
                isFound = true;
                break;
            }
            else {
                navigateToNextPage();
            }
        }
        Assert.assertTrue("\"" + resultItemTitle + "\"" + " item was not found!", isFound);
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
        Assert.assertTrue(String.format("The expected results number is %d not %d", searchResultsNumber, expectedResultsNumber),
                searchResultsNumber == expectedResultsNumber);
    }

}
