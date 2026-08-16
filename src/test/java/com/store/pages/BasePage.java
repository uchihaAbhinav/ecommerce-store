package com.store.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void dismissAnyPopupIfPresent() {
        dismissAlertIfPresent();

        List<By> popupLocators = List.of(
                By.xpath("//*[contains(text(),'Change your password') or contains(text(),'password')]//button[normalize-space()='OK' or normalize-space()='Close']"),
                By.xpath("//div[contains(@class,'modal') or contains(@role,'dialog')]//button[normalize-space()='OK' or normalize-space()='Close']")
        );

        for (By locator : popupLocators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        element.click();
                    }
                }
            } catch (NoSuchElementException | ElementClickInterceptedException ignored) {
                // No popup present on this page.
            }
        }
    }

    private void dismissAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                alert.dismiss();
            }
        } catch (NoAlertPresentException ignored) {
            // No browser alert present.
        }
    }
}
