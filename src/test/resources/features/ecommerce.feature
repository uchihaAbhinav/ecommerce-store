Feature: SauceDemo login flow
  As a user
  I want to log in to the shopping app
  So that I can verify the product catalog loads and complete a purchase

  Scenario: User logs in successfully
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the inventory page is displayed

  Scenario: User adds a backpack to the cart and checks out
    Given the user opens the SauceDemo login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    When the user adds the Sauce Labs Backpack to the cart
    And the user opens the shopping cart
    And the user proceeds to checkout
    And the user enters checkout details with first name "Abhinav" and last name "Patel" and postal code "12345"
    And the user finishes the order
    Then the order confirmation is displayed
