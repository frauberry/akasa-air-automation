package steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.regent.utils.DriverWeb; // Assuming you have DriverWeb for WebDriver setup

public class Hooks {

    // WebDriver setup before each scenario
    @Before
    public void setUp() {
        WebDriver driver = DriverWeb.getDriver(); // Get the WebDriver instance from DriverWeb utility
        System.out.println("Setting up the WebDriver...");
    }

    // Cleanup after each scenario
    @After
    public void tearDown() {
        DriverWeb.quitDriver();


    }
}
