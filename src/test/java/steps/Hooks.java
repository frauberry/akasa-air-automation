package steps;

import com.spirit.utils.Logger;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.spirit.utils.DriverWeb;

public class Hooks {

    // Setup before each scenario
    @Before
    public void setUp(Scenario scenario) {
        // Initialize the Logger before each scenario
        Logger.getLogger("TestSuiteName", "BuildNumber", scenario.getName());

        // Automatically start the Logger for each scenario
        Logger.start(scenario.getName(), "Running test scenario");

        // You can add additional logging or setup if required
        System.out.println("Logger initialized and test started for scenario: " + scenario.getName());
        DriverWeb.getDriver();  // Initialize WebDriver
        System.out.println("Setting up the WebDriver...");
    }

    // Cleanup after each scenario
    @After
    public void tearDown(Scenario scenario) {
        // Finalize the report after each scenario
        Logger.tearDown(scenario); // Pass the scenario object to finalize the report
        DriverWeb.quitDriver();
        System.out.println("Test finished, report finalized.");
    }
}
