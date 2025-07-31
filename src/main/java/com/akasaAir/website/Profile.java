package com.akasaAir.website;

import org.openqa.selenium.By;

import static com.akasaAir.utils.DriverWeb.*;

public class Profile {
    By loginButton = By.xpath("//li[@aria-label='Login']");
    By manageProfile = By.xpath("//span[text()='Manage Profile']");
    By savedTravelers = By.xpath("//p[text()='Saved travellers']");
    By logoutButton = By.xpath("//p[text()='Logout']");

    public void navigateToSavedTravelers() {
        navigateToManageProfile();
        waitForElementToBeVisible(savedTravelers, 5);
        getDriver().findElement(savedTravelers).click();
    }

    public void logout() {
        waitForElementToBeClickable(logoutButton,5);
        getDriver().findElement(logoutButton).click();
    }

    public void navigateToManageProfile() {
        sleep(5);
        waitForElementToBeVisible(loginButton, 5);
        hoverElement(loginButton);
        getDriver().findElement(manageProfile).click();
    }
}
