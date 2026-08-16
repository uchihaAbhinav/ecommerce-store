package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final WebDriver driver;
    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge' and text()='1'] | //span[@data-test='shopping-cart-badge' and text()='1']");
    private final By cartButton = By.xpath("//a[@class='shopping_cart_link' or @data-test='shopping-cart-link']");
    private final By checkoutButton = By.xpath("//button[@id='checkout' or @data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void openCart() {
        dismissAnyPopupIfPresent();
        waitForClickable(cartButton).click();
        dismissAnyPopupIfPresent();
    }

    public boolean hasBackpackInCart() {
        dismissAnyPopupIfPresent();
        return driver.findElements(cartBadge).size() > 0;
    }

    public void checkout() {
        dismissAnyPopupIfPresent();
        waitForClickable(checkoutButton).click();
        dismissAnyPopupIfPresent();
    }
}
