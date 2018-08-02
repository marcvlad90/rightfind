package com.pages;

import java.util.ArrayList;
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
    @FindBy(css = "div[class*='__options'] i[class*='fa-angle-right']")
    private WebElement nextPageNavigationElement;
    @FindBy(css = "div[class*='info ember-view'] button:nth-child(2)")
    private WebElement addionalResultsAddButton;
    @FindBy(css = "input[placeholder='Enter your query to search multiple data sources']")
    private WebElement searchField;
    @FindBy(css = "i[class='fa fa-lg fa-search']")
    private WebElement searchIcon;
    @FindBy(css = "div[class*='result-page result-page'] div[class*='__left']>a>img")
    private WebElement homeLogo;
    @FindBy(css = "div[class*='cdd ember-view']")
    private WebElement footerElement;
    @FindBy(css = "div[class*='__controls']>div:nth-child(1) div[class*='c-select__trigger']")
    private WebElement numberOfResultsExpanderButton;
    @FindBy(css = "div[class*='__controls']>div:nth-child(2) div[class*='c-select__trigger']")
    private WebElement sortByExpanderButton;
    private final String resultsItemsListCssSelector = "div[class*='result-page__results-list ']>ul>li";
    private final String resultTitleCssSelector = "div>div>a>span:nth-child(1)";
    private final String resultYearCssSelector = "div[class*='__content']>span:nth-child(2)";
    private final String displayedControlValuesListCssSelector = "ul[class$='__options'] li";

    public void selectTheControlValue(WebElement controllerWebElement, String optionValue) {
        footerElement.click();
        element(controllerWebElement).waitUntilVisible();
        controllerWebElement.click();
        List<WebElement> optionsList = getDriver().findElements(By.cssSelector(displayedControlValuesListCssSelector));
        for (WebElement optionItem : optionsList) {
            if (optionItem.getText().contentEquals(optionValue)) {
                optionItem.click();
                break;
            }
        }
    }

    public void sortResults(String sortingRule) {
        selectTheControlValue(sortByExpanderButton, sortingRule);
    }

    public int getTheNumberOfResultsPerCurrentPage() {
        return getDriver().findElements(By.cssSelector(resultsItemsListCssSelector)).size();
    }

    public void selectTheNumberOfResultsPerPage(int numberOfResultsPerPage) {
        selectTheControlValue(numberOfResultsExpanderButton, String.valueOf(numberOfResultsPerPage));
    }

    public void clickOnHomeLogo() {
        clickOnElementIfExists(homeLogo);
    }

    public void clickOnSearchIcon() {
        element(searchIcon).waitUntilVisible();
        String beforeUrl = getDriver().getCurrentUrl();
        searchIcon.click();
        for (int i = 0; i < 5; i++) {
            waitABit(1000);
            if (!beforeUrl.contentEquals(getDriver().getCurrentUrl())) {
                break;
            }
        }
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

        }
    }

    public void selectNumberOfResultsPerPage(int numberOfResults) {
        element(numberOfResultsPerPageElement).waitUntilVisible();
        element(numberOfResultsPerPageElement).click();
        numberOfResultsPerPageElement.findElement(By.xpath("//li[text() = '" + String.valueOf(numberOfResults) + "']")).click();
    }

    public List<String> getTheStringListOfTheResultsFromTheFirstPages(int numberOfPagesToSearchIn, String partOfListItemCssSelector) {
        List<String> resultsList = new ArrayList<String>();
        int numberOfPages = getNumberOfPages();
        for (int i = 1; (i <= numberOfPages) && (i <= numberOfPagesToSearchIn); i++) {
            List<WebElement> currentPageItemsList = getDriver().findElements(
                    By.cssSelector(partOfListItemCssSelector));
            for (WebElement currentItem : currentPageItemsList) {
                resultsList.add(currentItem.getText());
            }
            if (getCurrentPageNumber() < numberOfPages) {
                navigateToNextPage();
                waitForTextToAppear("Page " + (i + 1) + " of");
            }
        }
        return resultsList;
    }

    public List<String> getTitlesFromTheFirstPagesResults(int numberOfPagesToSearchIn) {
        return getTheStringListOfTheResultsFromTheFirstPages(numberOfPagesToSearchIn, resultTitleCssSelector);
    }

    public List<String> getYearsFromTheFirstPagesResults(int numberOfPagesToSearchIn) {
        return getTheStringListOfTheResultsFromTheFirstPages(numberOfPagesToSearchIn, resultYearCssSelector);
    }

    public void checkThatTheListIsAlphabeticallyAscendingOrdered(int numberOfPagesToSearchIn) {
        List<String> resultsList = getTitlesFromTheFirstPagesResults(numberOfPagesToSearchIn);
        StringUtils.checkThatTheListIsAlphabeticallyAscendingOrdered(resultsList);
    }

    public void checkThatTheListIsOrderedByOldest(int numberOfPagesToSearchIn) {
        List<String> resultsList = getYearsFromTheFirstPagesResults(numberOfPagesToSearchIn);
        StringUtils.checkThatTheListIsAlphabeticallyAscendingOrdered(resultsList);
    }

    public void checkThatTheListIsOrderedByLatest(int numberOfPagesToSearchIn) {
        List<String> resultsList = getYearsFromTheFirstPagesResults(numberOfPagesToSearchIn);
        StringUtils.checkThatTheListIsAlphabeticallyDescendingOrdered(resultsList);
    }

    public void checkThatTheListIsAlphabeticallyDescendingOrdered(int numberOfPagesToSearchIn) {
        List<String> resultsList = getTitlesFromTheFirstPagesResults(numberOfPagesToSearchIn);
        StringUtils.checkThatTheListIsAlphabeticallyDescendingOrdered(resultsList);
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
        try {
            nextPageNavigationElement.click();
        } catch (Exception e) {
            footerElement.click();
            nextPageNavigationElement.click();
        }
        waitUntilElementDoesntExist(firstResultFromCurrentPage, 5);
    }

    public void checkThatItemIsPresentInTheList(String resultItemTitle) {
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
        Assert.assertTrue("\"" + resultItemTitle + "\"" + " item was not found!", isFound);
    }

    public Integer getSearchResultsNumber() {
        return StringUtils.getFirstIntegerNumberFromString(getDriver().findElement(By.cssSelector(paginationContainerCssSelector)).getText());
    }

    public Integer getCurrentPageNumber() {
        return StringUtils.getFirstIntegerNumberAfterKeyFromString(getDriver().findElement(By.cssSelector(paginationContainerCssSelector)).getText(), "Page ");
    }

    public Integer getNumberOfPages() {
        loadAdditionalResultsIfExists();
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
