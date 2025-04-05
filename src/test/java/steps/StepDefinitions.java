package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.regent.utils.DriverWeb.getDriver;

public class StepDefinitions {
    @Given("user is on the home page")
    public void userIsOnTheHomePage() {
        getDriver().get("https://qa2-aws.rssc.com");
    }

    @When("user navigates thru all the options")
    public void userNavigatesThruAllTheOptions() {

    }

    @Then("corresponding page should be displayed")
    public void correspondingPageShouldBeDisplayed() {

    }
}
