package com.spirit.website;

import com.spirit.utils.User;
import org.openqa.selenium.By;

import static com.spirit.utils.DriverWeb.*;

public class HomePage {
    By loginButton = By.xpath("//button[text()='Login']");
    By customerLogin = By.xpath("//div[text()='Customer Login']");
    By signUpButton = By.xpath("//button[text()='Sign Up']");
    By firstName = By.xpath("//input[@name='firstName']");
    By lastName = By.xpath("//input[@name='lastName']");
    By gender = By.xpath("//input[@name='gender']/parent::*");
    By genderFemale = By.xpath("//li[text()='Female']");
    By dateOfBirth = By.xpath("//input[@placeholder='DD MMMM YYYY']");
    By emailAddress = By.xpath("//input[@name='emailID']");
    By phoneNumber = By.xpath("//input[@name='mobile']");
    By submitButton = By.xpath("//button[text()='Submit']");
    By password = By.xpath("//input[@name='password']");
    By confirmPassword = By.xpath("//input[@name='confirmPassword']");
    By confirmSignInButton = By.xpath("//button[text()='Confirm & Sign In']");
    By accountName = By.xpath("//span[text()='Tita Rita']");
    By signInWithPassword = By.xpath("//button[text()='Sign in with password']");
    By emailId = By.xpath("//input[@name='emailOrMobile']");
    By signInPassword = By.id("password");
    By signInButton = By.xpath("//button[text()='Sign In']");
    By fromField = By.id("From");
    By fromBomOption = By.xpath("//span[text()='BOM']");
    By toField = By.id("To");
    By toAuhOption = By.xpath("//span[text()='AUH']");
    By returnTrip = By.xpath("//input[@name='returnDate']");
    By returnDate = By.xpath("//div[not(@aria-disabled='true')]/div[text()='5']");
    By searchFlightsButton = By.xpath("//button[text()='Search Flights']");
    By flightResultBom = By.xpath("//p[contains(text(),'BOM')]");
    By flightResultAuh = By.xpath("//p[contains(text(),'AUH')]");

    public void enterCredentials() {
        User user = new User();
        hoverElement(loginButton);
//        getDriver().findElement(loginButton).click();
        getDriver().findElement(customerLogin).click();
        getDriver().findElement(signUpButton).click();
        getDriver().findElement(firstName).sendKeys(user.firstName);
        getDriver().findElement(lastName).sendKeys(user.lastName);
        getDriver().findElement(gender).click();
        getDriver().findElement(genderFemale).click();
        getDriver().findElement(dateOfBirth).click();
        getDriver().findElement(dateOfBirth).sendKeys(user.dateOfBirth);
        getDriver().findElement(emailAddress).sendKeys(user.email);
        System.out.println(user.email);
        getDriver().findElement(phoneNumber).sendKeys(user.phoneNumber);
        getDriver().findElement(submitButton).click();
        waitForElementToBeVisible(password,5);
        getDriver().findElement(password).sendKeys(user.password);
//        waitForElementToBeClickable(confirmPassword,5);
        sleep(2);
        getDriver().findElement(confirmPassword).sendKeys(user.password);
    }

    public void confirmAndSignIn() {
        getDriver().findElement(confirmSignInButton).click();
    }

    public boolean isAccountNameDisplayed () {
        waitForElementToBeVisible(accountName,10);
        return getDriver().findElement(accountName).isDisplayed();
    }

    public void signIn() {
        hoverElement(loginButton);
        getDriver().findElement(customerLogin).click();
        getDriver().findElement(signInWithPassword).click();
        sleep(1);
        getDriver().findElement(emailId).sendKeys("Rita_5620195072025_94@gmail.com");
        getDriver().findElement(signInPassword).sendKeys("Spirit1!");
        getDriver().findElement(signInButton).click();
    }

    public void fillOutSearch() {
        scrollToElementMinusPx(fromField, 200);
        getDriver().findElement(fromField).click();
        waitForElementToBeClickable(fromBomOption,10);
        getDriver().findElement(fromBomOption).click();
        getDriver().findElement(toField).click();
        getDriver().findElement(toAuhOption).click();
        getDriver().findElement(returnTrip).click();
        getDriver().findElement(returnDate).click();
        getDriver().findElement(searchFlightsButton).click();
    }

    public boolean isFlightResultDisplayed() {
        waitForElementToBeClickable(flightResultBom,15);
        return getDriver().findElement(flightResultBom).isDisplayed() &&
                getDriver().findElement(flightResultAuh).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        waitForElementToBeVisible(loginButton, 5);
        return getDriver().findElement(loginButton).isDisplayed();
    }
}
