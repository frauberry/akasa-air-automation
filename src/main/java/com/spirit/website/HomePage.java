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
}
