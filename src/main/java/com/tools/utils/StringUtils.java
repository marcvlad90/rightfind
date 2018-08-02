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
        return Integer.valueOf(0 + intNumber);
    }

    public static Integer getFirstIntegerNumberAfterKeyFromString(String text,
            String key) {
        try {
            text = text.substring(text.indexOf(key) + key.length());
            return getFirstIntegerNumberFromString(text);
        } catch (Exception e) {
            return 0;
        }

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
