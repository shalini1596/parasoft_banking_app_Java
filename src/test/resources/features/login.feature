Feature: Customer authentication

Background:
Given the ParaBank login page is open

  @smoke
  Scenario: Customer signs in with configured credentials
    When I log in with the configured customer credentials
    Then the accounts overview is displayed

  @negative
  Scenario: Customer cannot sign in with an invalid password
    When I log in with an invalid password
    Then a login error is displayed
