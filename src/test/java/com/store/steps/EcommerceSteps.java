package com.store.steps;

import com.store.driver.DriverFactory;
import com.store.pages.InventoryPage;
import com.store.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class EcommerceSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        Reporter.getCurrentTestResult().getTestContext().setAttribute("driver", driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Given("the user opens the SauceDemo login page")
    public void openLoginPage() {
        loginPage.open("https://www.saucedemo.com/");
    }

    @When("the user logs in with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the inventory page is displayed")
    public void verifyInventoryPage() {
        Assert.assertTrue(inventoryPage.isDisplayed(), "Inventory page is not displayed after login");
        Assert.assertEquals(inventoryPage.getTitleText(), "Products");
    }
}
