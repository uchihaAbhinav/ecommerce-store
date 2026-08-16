package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final WebDriver driver;
    private final By usernameInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
        dismissAnyPopupIfPresent();
    }

    public void login(String username, String password) {
        dismissAnyPopupIfPresent();
        WebElement usernameField = waitForVisible(usernameInput);
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = waitForVisible(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(password);

        waitForClickable(loginButton).click();
        dismissAnyPopupIfPresent();
    }
}
