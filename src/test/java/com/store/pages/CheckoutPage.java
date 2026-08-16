package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {
    private final WebDriver driver;
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By confirmationHeader = By.xpath("//h2[@data-test='complete-header' and contains(.,'Thank you for your order!')]");
    private final By checkoutTitle = By.xpath("//span[@data-test='title' and contains(.,'Checkout: Your Information')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        dismissAnyPopupIfPresent();
        wait.until(driver -> driver.getCurrentUrl().contains("checkout-step-one") || driver.findElements(checkoutTitle).size() > 0);

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
