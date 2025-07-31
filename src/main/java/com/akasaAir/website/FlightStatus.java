package com.akasaAir.website;

import org.openqa.selenium.By;

import static com.akasaAir.utils.DriverWeb.getDriver;
import static com.akasaAir.utils.DriverWeb.waitForElementToBeVisible;

public class FlightStatus {
    By flightNumber = By.id("flightNumber");
    By departureDate = By.xpath("//div[@id='react-select-2-placeholder']");
    By departureDateOption = By.xpath("//div[contains(text(),'Tomorrow')]");
    By checkFlightStatus = By.xpath("//button[@type='submit']");
    By searchResult = By.xpath("//h2[text()='Flight - QP1410 ']");

    public void fillOutForm() {
        getDriver().findElement(flightNumber).sendKeys("1410");
        getDriver().findElement(departureDate).click();
        getDriver().findElement(departureDateOption).click();
        getDriver().findElement(checkFlightStatus).click();
    }

    public boolean isFlightResultDisplayed() {
        waitForElementToBeVisible(searchResult, 10);
        return getDriver().findElement(searchResult).isDisplayed();
    }
}
