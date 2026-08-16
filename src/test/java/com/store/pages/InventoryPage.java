package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private final WebDriver driver;
    private final By inventoryContainer = By.xpath("//div[@class='inventory_list' or @data-test='inventory-list']");
    private final By title = By.xpath("//span[@class='title' and text()='Products'] | //span[@data-test='title' and text()='Products']");
    private final By backpackAddToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack' or @data-test='add-to-cart-sauce-labs-backpack']");
    private final By cartLink = By.xpath("//a[@class='shopping_cart_link' or @data-test='shopping-cart-link']");

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDisplayed() {
        dismissAnyPopupIfPresent();
        return waitForVisible(inventoryContainer).isDisplayed();
    }

    public String getTitleText() {
        dismissAnyPopupIfPresent();
        return waitForVisible(title).getText();
    }

    public void addBackpackToCart() {
        dismissAnyPopupIfPresent();
        waitForClickable(backpackAddToCartButton).click();
        dismissAnyPopupIfPresent();
    }

    public boolean isBackpackAddedToCart() {
        dismissAnyPopupIfPresent();
        return driver.findElements(By.xpath("//span[@class='shopping_cart_badge' and text()='1']")).size() > 0;
    }

    public void openCart() {
        dismissAnyPopupIfPresent();
        waitForClickable(cartLink).click();
        dismissAnyPopupIfPresent();
    }
}
