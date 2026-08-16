package com.store.steps;

import com.store.driver.DriverFactory;
import com.store.pages.CartPage;
import com.store.pages.CheckoutPage;
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
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
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

    @When("the user adds the Sauce Labs Backpack to the cart")
    public void addBackpackToCart() {
        inventoryPage.addBackpackToCart();
        Assert.assertTrue(inventoryPage.isBackpackAddedToCart(), "Backpack was not added to cart");
    }

    @When("the user opens the shopping cart")
    public void openShoppingCart() {
        inventoryPage.openCart();
        Assert.assertTrue(cartPage.hasBackpackInCart(), "Backpack is not in the cart");
    }

    @When("the user proceeds to checkout")
    public void proceedToCheckout() {
        cartPage.checkout();
    }

    @When("the user enters checkout details with first name {string} and last name {string} and postal code {string}")
    public void enterCheckoutDetails(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);
    }

    @When("the user finishes the order")
    public void finishOrder() {
        checkoutPage.finishOrder();
    }

    @Then("the inventory page is displayed")
    public void verifyInventoryPage() {
        Assert.assertTrue(inventoryPage.isDisplayed(), "Inventory page is not displayed after login");
        Assert.assertEquals(inventoryPage.getTitleText(), "Products");
    }

    @Then("the order confirmation is displayed")
    public void verifyOrderConfirmation() {
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order confirmation page is not displayed");
    }
}
