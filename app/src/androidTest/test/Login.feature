Feature: Enter login details
  @smoke
  @e2e
  Scenario Outline: Successful login
    Given I start the application
    When I click email field
    And I enter valid email
    And I close the keyboard
    And I click password field
    And I enter valid password <password>
    And I close the keyboard
    And I click sign in button
    Then I expect to see sucessfull login message

    Examples:
      | email    | password |
      | sabin123 | 1234     |