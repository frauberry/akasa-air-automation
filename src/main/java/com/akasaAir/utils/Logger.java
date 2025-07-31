package com.akasaAir.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.akasaAir.utils.DriverWeb.getDriver;


public class Logger extends Assertions {

//    final static String timestamp = DateTimeFormatters.formatToStringFromDate(Timestamp.getCurrentDateTime(), Constants.Format.LOGGER_FORMAT);
    private final boolean enableScreenshots = true;
    private static final ThreadLocal<Logger> instance = new ThreadLocal<>();
    private static ExtentReports report;
    private final long threadId = Thread.currentThread().getId();
    private ExtentTest scenario;
    private static ExtentTest step;
    private static String scenarioName;
    private final String threadIdString = String.format("[T%s] ", threadId);

    public static String testLogFilePath = (new File("")).getAbsolutePath() + "/test-output/" + "timestamp" + "_Report.html";
    private final static String GIVEN = "<b><font color = 'green'>GIVEN</font></b> ";
    private final static String THEN = "<b><font color = 'green'>THEN</font></b> ";
    private final static String WHEN = "<b><font color = 'green'>WHEN</font></b> ";
    private final static String AND = "<b><font color = 'green'>AND</font></b> ";
    private static String screenshotsFolderPath;
    private static String folderName;


    private Logger() {
    }

    public static synchronized Logger getLogger(String testSuiteName, String buildNumber, String scenarioName) {
        instance.set(new Logger(testSuiteName, buildNumber, scenarioName));
        return instance.get();
    }

    public static synchronized Logger getInstance() {
        return instance.get();
    }

    // Set up report with custom configuration
    private Logger(String testSuiteName, String buildNumber, String scenarioName) {
        this.scenarioName = scenarioName;

        // Generate a timestamp for the test folder
        String timestamp = generateTimestamp();

        // Create a folder for the test report and screenshots
        String reportFolder = "reports/Test_report_" + scenarioName + "_" + timestamp;
        File folder = new File(reportFolder);
        if (!folder.exists()) {
            folder.mkdirs();  // Create the folder if it doesn't exist
        }

        // Set the path for storing screenshots inside this specific folder
        screenshotsFolderPath = reportFolder + "/screenshots";

        // Create the screenshots folder if it doesn't exist
        File screenshotsFolder = new File(screenshotsFolderPath);
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs();  // Create the screenshots folder
        }

        // Set the report path inside the newly created folder
        String reportPath = reportFolder + "/Test_report_" + scenarioName + "_" + timestamp + "_Report.html";

        // Initialize the ExtentReports and attach the reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle(testSuiteName);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName(testSuiteName);
        report = new ExtentReports();
        report.attachReporter(reporter);
    }

    private static String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        return sdf.format(new Date());
    }
    public static void start(String testName, String testInfo) {
        // Ensure folderName is initialized properly with scenario name and timestamp
        if (testName == null || testName.isEmpty()) {
            System.out.println("Test name is null or empty. Cannot proceed.");
            return;
        }
        String timestamp = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
        folderName = "Test_report_" + testName + "_" + timestamp;  // Correctly format the folder name

        // Proceed with the rest of the initialization
        getInstance().scenarioName = testName;

        if (getInstance().report == null) {
            throw new RuntimeException("Logger has not been initialized yet");
        }
        getInstance().scenario = getInstance().report.createTest(testName, testInfo);
    }


    // Set the current step to log
    public static void setStep(String message) {
        getInstance().step = getInstance().scenario.createNode(message);
    }

    public static void warningInfo(String message) {
        System.out.println("WARNING: " + message);
        getInstance().step.log(Status.WARNING, message);
    }

    public static boolean isPageReady() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        String readyState = (String) jsExecutor.executeScript("return document.readyState");
        return "complete".equals(readyState);  // The page is fully loaded
    }

    public static void takeScreenshotWhenReady() {
        // Wait for the page to be ready
        if (isPageReady()) {
            // If the page is fully loaded, capture the screenshot
            captureScreenshot();
        } else {
            // If the page is not ready, wait for 2 seconds and then check again
            System.out.println("Page is not ready. Waiting for 2 seconds...");
            try {
                Thread.sleep(2000);  // Wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();  // Handle the interrupted exception
            }

            // Recheck if the page is ready
            if (isPageReady()) {
                // If the page is now ready, capture the screenshot
                captureScreenshot();
            } else {
                System.out.println("Page is still not ready after waiting for 2 seconds.");
            }
        }
    }


    // Method to capture and return screenshot path
    private static String captureScreenshot() {
        if (folderName == null || folderName.isEmpty()) {
            System.out.println("Folder name is not initialized. Please check Logger.start() method.");
            return null;
        }
        try {
            if (getDriver() == null) {
                System.out.println("WebDriver is not initialized, cannot capture screenshot.");
                return null;
            }

            // Capture the screenshot
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

            // Define the screenshots directory using the test-specific folder name
            File screenshotsDir = new File("reports/" + folderName + "/screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs(); // Create the folder if it doesn't exist
            }

            // Define the screenshot path inside the folder
            String screenshotPath = screenshotsDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png";

            // Copy the screenshot to the destination folder
            File destFile = new File(screenshotPath);
            Files.copy(screenshot.toPath(), destFile.toPath());

            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath()); // Debugging line
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Log the message with optional screenshot capture
    public static void logEntry(Status status, String message, boolean takeScreenshot) {
        getInstance().step.log(status, message);  // Log the message

        if (takeScreenshot) {
            // Capture screenshot and return path
            String screenshotPath = captureScreenshot();
            if (screenshotPath != null) {
                // Add screenshot to the report
                getInstance().step.addScreenCaptureFromPath(screenshotPath);  // Attach screenshot to report
            } else {
                getInstance().step.log(Status.WARNING, "Screenshot not captured");
            }
        }
    }

    // Add screenshot when the test fails
    public static void screenshotFail(String message) {
        logEntry(Status.FAIL, message, true);
    }

    // Add screenshot when the test passes
    public static void screenshotPass(String message) {
        logEntry(Status.PASS, message, true);
    }

    // Take a screenshot regardless of the test status
    public static void screenshot() {
        logEntry(Status.INFO, "Taking screenshot", true);
    }

    public static void pass(String message) {
        logEntry(Status.PASS, message, false);
    }

    public static void fail(String message) {
        fail(message, false);
    }

    public static void fail(String message, boolean takeScreenshot) {
        logEntry(Status.FAIL, message, takeScreenshot);
    }

    // Finalize and close the report
    public static void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                fail(scenario.getName() + " - has " + scenario.getStatus(), true);
            } else {
                pass(scenario.getName() + " - has " + scenario.getStatus());
            }
        } catch (WebDriverException wex) {
            fail(scenario.getName() + " - has " + scenario.getStatus() + "\n", wex, true);
        } catch (Exception e) {
            fail(scenario.getName() + " - has " + scenario.getStatus() + "\n", e, true);
        } finally {
            getInstance().report.flush();  // Finalize and save the report
        }
    }

    public static void fail(String message, Throwable t, boolean takeScreenshot) {
        // Log the failure message
        logEntry(Status.FAIL, message, takeScreenshot);

        // Capture the stack trace if an exception is provided
        if (t != null) {
            getInstance().step.log(Status.FAIL, "Exception: " + t.getMessage());
            getInstance().step.log(Status.FAIL, t);
        }
    }

    public static void screenshotInfo(String message) {
        logEntry(Status.INFO, message, true);
    }


    // Capture stack trace
    public static void printStackTrace(Throwable exception) {
        exception.printStackTrace(System.err);
    }

    public static void info(String message) {
        if (getInstance() == null) {
            System.out.println("Logger has not been initialized yet");
        } else {
            logEntry(Status.INFO, message, false);
        }
    }

    public static void failEx(String message, Throwable t) {
        printStackTrace(t);
        logEntry(Status.FAIL, message, true);
        getInstance().step.log(Status.FAIL, t);
    }

    public static void setGivenStep(String message) {
        if (getInstance().scenario == null) {
            throw new RuntimeException(getInstance().threadIdString + "Logger has not been initialized yet");
        }
        getInstance().step = getInstance().scenario.createNode(GIVEN + message);
    }

    public static void setWhenStep(String message) {
        if (getInstance().scenario == null) {
            throw new RuntimeException(getInstance().threadIdString + "Logger has not been initialized yet");
        }
        getInstance().step = getInstance().scenario.createNode(WHEN + message);
    }

    public static void setThenStep(String message) {
        if (getInstance().scenario == null) {
            throw new RuntimeException(getInstance().threadIdString + "Logger has not been initialized yet");
        }
        getInstance().step = getInstance().scenario.createNode(THEN + message);
    }

    public static void setAndStep(String message) {
        if (getInstance().scenario == null) {
            throw new RuntimeException(getInstance().threadIdString + "Logger has not been initialized yet");
        }
        getInstance().step = getInstance().scenario.createNode(AND + message);
    }
}
