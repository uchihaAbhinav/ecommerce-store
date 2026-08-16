package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;
    private final By inventoryContainer = By.className("inventory_list");
    private final By title = By.className("title");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }
}
