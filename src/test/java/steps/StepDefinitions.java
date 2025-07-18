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
    @Given("User is on Homepage")
    public void userIsOnHomepage() {
        Logger.setGivenStep("User is on Homepage");
        getDriver().get("https://www.akasaair.com/");
        Logger.screenshotInfo("User is on Homepage");
    }

    @When("User enters valid credentials")
    public void userEntersValidCredentials() {
        Logger.setWhenStep("User enters valid credentials");
        HomePage homepage = new HomePage();
        homepage.enterCredentials();
        Logger.screenshotInfo("Credentials are entered");
    }

    @And("User signs in")
    public void userSignsIn() {
        Logger.setAndStep("User signs in");
        HomePage homePage = new HomePage();
        homePage.confirmAndSignIn();
        Logger.screenshotInfo("User is signed in");
    }

    @Then("User should be successfully signed in")
    public void userShouldBeSuccessfullySignedIn() {
        Logger.setThenStep("User should be successfully signed in");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isAccountNameDisplayed());
    }

    @When("User signs in with password")
    public void userSignsInWithPassword() {
        Logger.setThenStep("User signs in with password");
        HomePage homePage = new HomePage();
        homePage.signIn();
    }
}
