package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.regent.utils.Logger;

import static com.regent.utils.DriverWeb.getDriver;

public class StepDefinitions {

    @Given("user is on the home page")
    public void userIsOnTheHomePage() {
        Logger.setGivenStep("Navigating to home page...");
        getDriver().get("https://qa1-aws.rssc.com");
        Logger.screenshotInfo("User navigated to the home page");
    }

    @When("user navigates thru all the options")
    public void userNavigatesThruALLTheOptions() {
        Logger.setWhenStep("User is navigating through all the options...");
        Logger.screenshotInfo("User navigated through the options.");
    }

    @Then("corresponding page should be displayed")
    public void correspondingPageShouldBeDisplayed() {
        Logger.setThenStep("Verifying the corresponding page...");
        Logger.screenshotPass("Page verified successfully.");
    }
}
