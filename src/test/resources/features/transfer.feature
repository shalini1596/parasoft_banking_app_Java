Feature: Funds transfer

  @smoke @mutating
  Scenario: Transfer funds between customer accounts
    Given I am logged in to ParaBank
    When I transfer the configured amount between my first two accounts
    Then the transfer confirmation is displayed
