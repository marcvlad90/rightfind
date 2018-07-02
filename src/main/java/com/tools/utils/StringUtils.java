package com.tools.utils;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
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
}
