package com.tools.utils;

import java.util.List;

import org.junit.Assert;

public class StringUtils {
    public static Integer getFirstIntegerNumberFromString(String text) {
        String intNumber = "";
        char[] characters = text.toCharArray();
        boolean foundDigit = false;
        for (char ch : characters) {
            if (Character.isDigit(ch)) {
                foundDigit = true;
                intNumber += ch;
            } else {
                if (foundDigit) {
                    break;
                }
            }
        }
        Assert.assertTrue(
                "No matching integer was found in the provided string!",
                intNumber != "");
        return Integer.valueOf(intNumber);
    }

    public static Integer getFirstIntegerNumberAfterKeyFromString(String text,
            String key) {
        text = text.substring(text.indexOf(key) + key.length());
        return getFirstIntegerNumberFromString(text);
    }

    public static void checkThatTheListIsAlphabeticallyOrdered(List<String> itemsList, boolean isOrderedAscendingNotDescending) {
        for (int i = 1; i < itemsList.size(); i++) {
            if (isOrderedAscendingNotDescending) {
                Assert.assertTrue("The list is not ascending ordered", itemsList.get(i - 1).compareToIgnoreCase(itemsList.get(i)) <= 0);
            }
            else {
                Assert.assertTrue("The list is not descending ordered", itemsList.get(i - 1).compareToIgnoreCase(itemsList.get(i)) >= 0);
            }
        }
    }

    public static void checkThatTheListIsAlphabeticallyAscendingOrdered(List<String> itemsList) {
        checkThatTheListIsAlphabeticallyOrdered(itemsList, true);
    }

    public static void checkThatTheListIsAlphabeticallyDescendingOrdered(List<String> itemsList) {
        checkThatTheListIsAlphabeticallyOrdered(itemsList, false);
    }
}
