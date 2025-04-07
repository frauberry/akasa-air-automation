package com.regent.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class DriverWeb {

    // ThreadLocal WebDriver to ensure one driver per thread
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        // If the driver is not already set for the current thread, create a new instance
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();  // Set up ChromeDriver automatically
            driver.set(new ChromeDriver());// Set the driver for the current thread
            driver.get().manage().window().maximize();
        }

        return driver.get();  // Return the driver for the current thread
    }

    public static void clickOnElement(By by) {
        WebElement element = getDriver().findElement(by);
        element.click();
        System.out.println("clicking on " + element);
    }

    public static String getTextFromElement(By by) {
        WebElement element = getDriver().findElement(by);
        System.out.println("retrieving text from " + element);
        return element.getText();
    }

    public static void sendKeysToElement(By by, String text) {
        WebElement element = getDriver().findElement(by);
        element.sendKeys(text);
        System.out.println("Sending text: " + text + " to element: " + element);
    }

    public static void clearElement(By by) {
        WebElement element = getDriver().findElement(by);
        element.clear();
        System.out.println("Cleared element: " + element);
    }

    public static boolean isElementDisplayed(By by) {
        WebElement element = getDriver().findElement(by);
        boolean isDisplayed = element.isDisplayed();
        System.out.println("Is element displayed: " + isDisplayed + " for element: " + element);
        return isDisplayed;
    }

    public static boolean isElementEnabled(By by) {
        WebElement element = getDriver().findElement(by);
        boolean isEnabled = element.isEnabled();
        System.out.println("Is element enabled: " + isEnabled + " for element: " + element);
        return isEnabled;
    }

    public static boolean isElementSelected(By by) {
        WebElement element = getDriver().findElement(by);
        boolean isSelected = element.isSelected();
        System.out.println("Is element selected: " + isSelected + " for element: " + element);
        return isSelected;
    }

    public static void waitForElementToBeVisible(By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        System.out.println("Waited for element to be visible: " + by);
    }

    public static void waitForElementToBeClickable(By by, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        System.out.println("Waited for element to be clickable: " + by);
    }

    public static void selectDropdownOptionByText(By by, String visibleText) {
        WebElement dropdown = getDriver().findElement(by);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected option: " + visibleText + " from dropdown: " + dropdown);
    }

    public static void selectDropdownOptionByValue(By by, String value) {
        WebElement dropdown = getDriver().findElement(by);
        Select select = new Select(dropdown);
        select.selectByValue(value);
        System.out.println("Selected option with value: " + value + " from dropdown: " + dropdown);
    }

    public static String getAttributeValue(By by, String attribute) {
        WebElement element = getDriver().findElement(by);
        String value = element.getAttribute(attribute);
        System.out.println("Retrieved attribute value: " + value + " for attribute: " + attribute + " from element: " + element);
        return value;
    }

    public static void scrollToElement(By by) {
        WebElement element = getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("Scrolled to element: " + element);
    }

    public static int getElementCount(By by) {
        java.util.List<WebElement> elements = getDriver().findElements(by);
        int count = elements.size();
        System.out.println("Number of elements found: " + count + " for locator: " + by);
        return count;
    }

    public static void switchToFrame(String frameName) {
        getDriver().switchTo().frame(frameName);
        System.out.println("Switched to frame: " + frameName);
    }

    public static void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
        System.out.println("Switched to default content.");
    }

    public static void takeScreenshot(String filePath) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    public static void quitDriver() {
        // Quit the driver for the current thread and remove it from the ThreadLocal
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        System.out.println("WebDriver closed after the scenario.");
    }
}
