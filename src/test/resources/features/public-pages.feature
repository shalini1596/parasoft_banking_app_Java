Feature: Public ParaBank pages

  Scenario Outline: Open public information pages
    Given I open the ParaBank "<link>" page
    Then the page heading contains "<heading>"

    Examples:
      | link       | heading     |
      | About Us   | About       |
      | Services   | Services    |
      | Contact Us | Customer     |

  @mutating
  Scenario: Submit the contact form
    Given the ParaBank login page is open
    When I open the ParaBank "Contact Us" page
    And I submit the contact form with configured details
    Then the contact form confirmation is displayed

  Scenario: Look up forgotten login information
    Given the ParaBank login page is open
    When I open the ParaBank "Forgot login info?" page
    And I look up my configured login details
    Then the login lookup result is displayed
