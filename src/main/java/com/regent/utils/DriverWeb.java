package com.regent.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

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

    public static void quitDriver() {
        // Quit the driver for the current thread and remove it from the ThreadLocal
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        System.out.println("WebDriver closed after the scenario.");
    }
}
