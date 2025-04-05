package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",  // Path to the feature files
        glue = "org.regent.steps",                 // Path to the step definitions
        plugin = {"pretty", "html:target/cucumber-reports"}, // Output format
        monochrome = true                          // Makes the console output more readable
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
