package steps;

import com.spirit.website.HomePage;
import com.spirit.website.Profile;
import com.spirit.website.SavedTravelers;
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

    @Given("Member is on Saved Travelers page")
    public void memberIsOnSavedTravelersPage() {
        Logger.setGivenStep("Member is on Saved Travelers page");
        getDriver().get("https://www.akasaair.com/");
        HomePage homePage = new HomePage();
        homePage.signIn();
        Profile profile = new Profile();
        profile.navigateToSavedTravelers();
        Logger.screenshotInfo("Member is on Saved Travelers page");
    }

    @When("Member adds a new traveler")
    public void memberAddsANewTraveler() {
        Logger.setWhenStep("Member adds a new traveler");
        SavedTravelers savedTravelers = new SavedTravelers();
        savedTravelers.addNewTraveler();
        Logger.screenshotInfo("Member added a new traveler");
    }

    @Then("A new traveler should be saved")
    public void aNewTravelerShouldBeSaved() {
        Logger.setThenStep("A new traveler should be saved");
        SavedTravelers savedTravelers = new SavedTravelers();
        Assert.assertTrue(savedTravelers.isTravelersNameDisplayed());
    }

    @When("User fills out Search form")
    public void userFillsOutSearchForm() {
        Logger.setWhenStep("User fills out Search form");
        HomePage homePage = new HomePage();
        homePage.fillOutSearch();
    }

    @Then("Flight Search result should be displayed")
    public void flightSearchResultShouldBeDisplayed() {
        Logger.setThenStep("Flight Search result should be displayed");
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isFlightResultDisplayed());
    }
}
