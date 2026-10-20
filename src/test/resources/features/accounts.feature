Feature: Customer account services

  Scenario: View account details and history
    Given I am logged in to ParaBank
    When I open the details for my first account
    Then the account details and transaction history are displayed

  Scenario: Log out of ParaBank
    Given I am logged in to ParaBank
    When I log out
    Then the customer login form is displayed

  @mutating
  Scenario: Open a savings account
    Given I am logged in to ParaBank
    When I open a savings account using my first account
    Then the new account is listed in my overview

  @mutating
  Scenario: Pay a bill
    Given I am logged in to ParaBank
    When I pay the configured bill from my first account
    Then the bill payment confirmation is displayed

  Scenario: Search account transactions
    Given I am logged in to ParaBank
    When I find transactions by the configured amount
    Then the transaction search results are displayed

  @mutating
  Scenario: Update customer contact details
    Given I am logged in to ParaBank
    When I update my contact details
    Then the contact details update confirmation is displayed

  @mutating
  Scenario: Request a loan
    Given I am logged in to ParaBank
    When I submit the configured loan request
    Then the loan decision is displayed
