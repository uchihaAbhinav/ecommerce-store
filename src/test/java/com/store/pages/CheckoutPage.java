package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {
    private final WebDriver driver;
    private final By firstNameInput = By.xpath("//input[@id='first-name' or @data-test='firstName']");
    private final By lastNameInput = By.xpath("//input[@id='last-name' or @data-test='lastName']");
    private final By postalCodeInput = By.xpath("//input[@id='postal-code' or @data-test='postalCode']");
    private final By continueButton = By.xpath("//input[@id='continue' or @data-test='continue']");
    private final By finishButton = By.xpath("//button[@id='finish' or @data-test='finish']");
    private final By confirmationHeader = By.xpath("//h2[@class='complete-header' and text()='Thank you for your order!'] | //div[@data-test='checkout-complete-container']//h2[contains(text(),'Thank you for your order!')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        dismissAnyPopupIfPresent();

        WebElement firstNameField = waitForVisible(firstNameInput);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = waitForVisible(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement postalCodeField = waitForVisible(postalCodeInput);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);

        waitForClickable(continueButton).click();
        dismissAnyPopupIfPresent();
    }

    public void finishOrder() {
        dismissAnyPopupIfPresent();
        waitForClickable(finishButton).click();
        dismissAnyPopupIfPresent();
    }

    public boolean isOrderConfirmed() {
        dismissAnyPopupIfPresent();
        return waitForVisible(confirmationHeader).isDisplayed();
    }
}
