package com.regent.utils;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import static com.regent.utils.Logger.info;

public abstract class Assertions {

    public static synchronized void assertFail(String message) {
    }

    public static synchronized void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, "condition " + message);
            info(message);
        } catch (AssertionError asEr) {
            Logger.failEx(message, asEr);
            Logger.screenshotFail(message + "is not true");
        }
    }

    public static void assertSmoothTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, "condition " + message);
            info(message);
        } catch (AssertionError asEr) {
            Logger.warningInfo(message);
            Logger.screenshotInfo(message + " is not true");
        }
    }

    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" is equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } catch (AssertionError asEr) {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "</font color ='red'>" + "\" is NOT equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
            Logger.failEx(message, asEr);
        }
    }

    public static void assertEquals(Collection<?> actual, Collection<?> expected, String message) {
        try {
            Assert.assertEquals(actual.stream().sorted().collect(Collectors.toList()), expected.stream().sorted().collect(Collectors.toList()), message);
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" is equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } catch (AssertionError asEr) {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "</font color ='red'>" + "\" is NOT equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
            Logger.failEx(message, asEr);
        }
    }

    public static void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" is equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } catch (AssertionError asEr) {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "</font color ='red'>" + "\" is NOT equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
            Logger.failEx(message, asEr);
        }
    }

    public static void assertNotEmpty(String actual, String message) {
        try {
            Assert.assertFalse(actual.isEmpty(), message);
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\"");
        } catch (AssertionError asEr) {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "</font color ='red'>" + "\" is not Null \"" + "</font>" + "<b>" + "\"");
            Logger.failEx(message, asEr);
        }
    }

    public static void assertContains(String actual, String expected, String message) {
        if (actual.contains(expected)) {
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" contains expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } else {
            Logger.fail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'red'>" + "\" does  NOT contain expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        }
    }

    public static boolean assertContainsLead(String actual, String expected, String field, List<String> list) {
        if (actual.contains(expected)) {
            info("Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" contains expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
            return true;
        } else {
            list.add(field);
            info("Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'red'>" + "\" does  NOT contain expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
            return false;
        }
    }

    public static void assertContainsPageSource(String actual, String expected) {
        if (actual.contains(expected)) {
            info("Found actual Page Source contains expected text \"" + expected + "\"");
        } else {
            Logger.screenshotFail("Found actual message: \"" + actual + "\" does  NOT contain expected \"" + expected + "\"");
        }
    }

    public static synchronized <T extends Comparable> void assertEquals(T actual, T expected) {
        assertEquals(actual, expected, "");
    }

    public static synchronized <T extends Comparable> void assertEquals(T actual, T expected, final String message) {
        final boolean equal;
        if (actual instanceof String) {
            equal = ((String) actual).equalsIgnoreCase(String.valueOf((expected)));
        } else {
            equal = (actual.compareTo(expected) == 0);
        }
        if (equal) {
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" is equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } else {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<b>" + actual + "</b>" + "</font color ='red'>" + "\" is NOT equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        }
    }

    public static void assertContainsIgnoreCase(String actual, String expected) {
        assertContainsIgnoreCase(actual, expected, "");
    }

    public static <T> void assertContainsIgnoreCase(T actual, T expected, String message) {
        if (actual.toString().toLowerCase().contains(expected.toString().toLowerCase())) {
            info("<b>" + message + "</b>" + ": Found actual message: \"" + "<strong>" + actual + "</strong>" + "\" contains expected \"" + "<strong>" + expected + "</strong>" + "\"");
        } else {
            Logger.screenshotFail("<b>" + message + "</b>" + ": Found actual message: \"" + "<strong>" + actual + "</strong>" + "\" does  NOT contain expected \"" + "<strong>" + expected + "</strong>" + "\"");
        }

    }

    public static synchronized void assertEqualsSoft(String description, String actual, String expected) {
        if (actual.equalsIgnoreCase(expected)) {
            info("Found actual message: \"" + " " + description + " " + "<b>" + actual + "</b>" + "<font color = 'green'>" + "\" is equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        } else {
            Logger.warningInfo("Found actual message: \"" + " " + description + " " + "<b>" + actual + "</b>" + "<font color ='red'>" + "\" is NOT equal to expected \"" + "</font>" + "<b>" + expected + "</b>" + "\"");
        }
    }

    public static synchronized <T extends Comparable> void assertEquals_noScreenshot(T actual, T expected, String message) {
        if (actual.compareTo(expected) == 0) {
            info("<b>" + message + "</b>" + ": Found actual message: \"" + actual + "\" is equal to expected \"" + expected + "\"");
        } else {
            Logger.fail("<b>" + message + "</b>" + ": Found actual message: \"" + actual + "\" is NOT equal to expected \"" + expected + "\"");
        }
    }

}