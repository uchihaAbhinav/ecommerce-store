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
                By.xpath("//*[contains(text(),'Change your password')]//button[normalize-space()='OK']"),
                By.xpath("//button[normalize-space()='OK']"),
                By.xpath("//button[contains(@aria-label,'Close') or contains(@title,'Close') or contains(@class,'close')]"),
                By.xpath("//button[normalize-space()='Close']"),
                By.xpath("//a[contains(@class,'close') or contains(@data-test,'close')]"),
                By.xpath("//*[contains(@class,'notification') or contains(@class,'toast')]//button[contains(.,'Close')]"),
                By.xpath("//button[contains(@id,'close') or contains(@data-test,'close')]"),
                By.xpath("//div[contains(@class,'modal')]//button[contains(.,'Close')]")
        );

        for (By locator : popupLocators) {
            try {
                WebElement element = waitForClickable(locator);
                if (element.isDisplayed()) {
                    element.click();
                }
            } catch (TimeoutException | NoSuchElementException | ElementClickInterceptedException ignored) {
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
