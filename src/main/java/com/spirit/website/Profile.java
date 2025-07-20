package com.spirit.website;

import org.openqa.selenium.By;

import static com.spirit.utils.DriverWeb.*;

public class Profile {
    By loginButton = By.xpath("//li[@aria-label='Login']");
    By manageProfile = By.xpath("//span[text()='Manage Profile']");
    By savedTravelers = By.xpath("//p[text()='Saved travellers']");

    public void navigateToSavedTravelers() {
        sleep(5);
        waitForElementToBeVisible(loginButton, 5);
        hoverElement(loginButton);
        getDriver().findElement(manageProfile).click();
        waitForElementToBeVisible(savedTravelers, 5);
        getDriver().findElement(savedTravelers).click();
    }
}
