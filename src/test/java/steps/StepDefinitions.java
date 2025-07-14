package steps;

import com.spirit.website.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.spirit.utils.Logger;
import org.testng.Assert;

import static com.spirit.utils.DriverWeb.getDriver;

public class StepDefinitions {
    @Given("Member is on Homepage")
    public void memberIsOnHomepage() {
        Logger.setGivenStep("Member is on Homepage");
        getDriver().get("https://www.akasaair.com/");
        Logger.screenshotInfo("Member is on Homepage");
    }

    @When("Member enters valid credentials")
    public void memberEntersValidCredentials() {
        Logger.setWhenStep("Member enters valid credentials");
        HomePage homepage = new HomePage();
        homepage.enterCredentials();
        Logger.screenshotInfo("Credentials are entered");
    }

    @And("Member signs in")
    public void memberSignsIn() {
        Logger.setAndStep("Member signs in");
        HomePage homePage = new HomePage();
        homePage.signIn();
        Logger.screenshotInfo("Member is signed in");
    }

    @Then("Member should be successfully signed in")
    public void memberShouldBeSuccessfullySignedIn() {
        Logger.setThenStep("Member should be successfully signed in");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isAccountNameDisplayed());
    }
}
