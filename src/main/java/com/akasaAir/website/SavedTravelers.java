package com.akasaAir.website;

import org.openqa.selenium.By;

import static com.akasaAir.utils.DriverWeb.*;

public class SavedTravelers {
    By addTravelerButton = By.xpath("//button[text()='Add Traveller']");
    By relationship= By.id("relationship");
    By relationshipOption = By.xpath("//li[text()='Sister']");
    By firstName = By.xpath("//input[@name='firstName']");
    By lastName = By.xpath("//input[@name='lastName']");
    By dateOfBirth = By.xpath("//input[@placeholder='EEEE, DD MMMM YYYY']");
    By addButton = By.xpath("//button[text()='Add']");
    By travelersName = By.xpath("//p[text()='Rata Tata']");

    public void addNewTraveler() {
        waitForElementToBeVisible(addTravelerButton, 5);
        getDriver().findElement(addTravelerButton).click();
        getDriver().findElement(relationship).click();
        getDriver().findElement(relationshipOption).click();
        getDriver().findElement(firstName).sendKeys("Rata");
        getDriver().findElement(lastName).sendKeys("Tata");
        getDriver().findElement(dateOfBirth).click();
//        setElementValueByScript("mui-8", "Sun, 11 Nov 2000");
        getDriver().findElement(By.xpath("//button[@aria-label='Choose date']")).click();
        getDriver().findElement(By.xpath("//button[contains(@class, 'MuiPickersDay-today')]")).click();
        getDriver().findElement(addButton).click();
    }

    public boolean isTravelersNameDisplayed() {
        return getDriver().findElement(travelersName).isDisplayed();
    }


}
