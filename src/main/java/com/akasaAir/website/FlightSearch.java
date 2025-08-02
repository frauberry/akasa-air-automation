package com.akasaAir.website;

import org.openqa.selenium.By;

import static com.akasaAir.utils.DriverWeb.*;

public class FlightSearch {
    By selectDepartButton = By.xpath("//button[(text()='Select')]");
    By selectReturnButton = By.xpath("(//button[(text()='Select')])[last()]");
    By continueButton = By.xpath("//button[@name='continue']");
    By noThanksBtn = By.xpath("//button[text()='No, Thanks']");
    By privacyPolicyLink = By.xpath("//a[text()='Privacy Policy ']");
    By acceptCookies = By.xpath("//button[text()='Accept cookies']");

    public void selectOutboundFlight() {
        waitForElementToBeClickable(selectDepartButton, 20);
        getDriver().findElement(selectDepartButton).click();
        getDriver().findElement(selectReturnButton).click();
    }
    public void proceedWithContinue() {
        waitForElementToBeClickable(acceptCookies, 10);
        getDriver().findElement(acceptCookies).click();
        waitForElementToBeClickable(continueButton, 10);
        getDriver().findElement(continueButton).click();
    }
    public void selectNoThanks() {
        waitForElementToBeClickable(noThanksBtn, 20);
        getDriver().findElement(noThanksBtn).click();
    }
    public void openPrivacyPolicy() {
        waitForElementToBeClickable(privacyPolicyLink, 10);
        scrollToElementMinusPx(privacyPolicyLink,200);
        sleep(3);
        getDriver().findElement(privacyPolicyLink).click();
    }
    public boolean isPrivacyPolicyOpenInNewTab() {
        if (getDriver().getWindowHandles().size() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
