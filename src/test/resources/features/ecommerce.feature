Feature: SauceDemo login flow
  As a user
  I want to log in to the shopping app
  So that I can verify the product catalog loads

  Scenario: User logs in successfully
    Given the user opens the SauceDemo login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the inventory page is displayed
