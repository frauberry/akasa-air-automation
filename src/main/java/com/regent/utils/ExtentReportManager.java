package com.regent.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    // Initialize ExtentReports and ExtentSparkReporter
    public static ExtentReports setupExtentReport(String reportName) {
        if (extent == null) {
            String reportPath = "reports/" + reportName + "_report.html";
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs(); // Create the reports folder if not present
            }

            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Test Automation Report");
            sparkReporter.config().setDocumentTitle("Extent Reports");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Capture screenshot and return the path or Base64 string
    public static String captureScreenshot(WebDriver driver) {
        try {
            // Ensure the screenshots directory exists
            File screenshotsDir = new File("reports/screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs(); // Create the screenshots folder if not present
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "reports/screenshots/" + System.currentTimeMillis() + ".png"; // Path to save screenshot
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath)); // Save screenshot to file
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Close the report
    public static void closeReport() {
        if (extent != null) {
            extent.flush(); // This will save and close the report
        }
    }
}
