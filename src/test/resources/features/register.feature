Feature: Customer registration

  @registration @mutating
  Scenario: Register one new customer
    Given the registration page is open
    When I register a new customer
    Then the new customer registration succeeds
