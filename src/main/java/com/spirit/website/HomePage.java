package com.spirit.website;

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

    public void enterCredentials() {
        hoverElement(loginButton);
//        getDriver().findElement(loginButton).click();
        getDriver().findElement(customerLogin).click();
        getDriver().findElement(signUpButton).click();
        getDriver().findElement(firstName).sendKeys("Tita");
        getDriver().findElement(lastName).sendKeys("Rita");
        getDriver().findElement(gender).click();
        getDriver().findElement(genderFemale).click();
        getDriver().findElement(dateOfBirth).click();
        getDriver().findElement(dateOfBirth).sendKeys("01N1974");
        getDriver().findElement(emailAddress).sendKeys("test89899@gmail.com");
        getDriver().findElement(phoneNumber).sendKeys("1112345678");
        getDriver().findElement(submitButton).click();
        waitForElementToBeVisible(password,5);
        getDriver().findElement(password).sendKeys("Spirit1!");
//        waitForElementToBeClickable(confirmPassword,5);
        sleep(2);
        getDriver().findElement(confirmPassword).sendKeys("Spirit1!");


    }

    public void signIn () {
        getDriver().findElement(confirmSignInButton).click();
    }

    public boolean isAccountNameDisplayed () {
        waitForElementToBeVisible(accountName,10);
        return getDriver().findElement(accountName).isDisplayed();
    }
}
