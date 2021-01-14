Feature: Show me the weather button

Scenario: Button exists on page load
    Given I am on the 'Search' page
    Then The element 'mainSearchButton' exists
    And The 'mainSearchButton' has text 'Show me the weather'

Scenario: Results are Displayed by Button Click
    Given I am on the 'Search' page
    When I input 'mainSearchBox' with 'atlanta,us'
    And I click 'mainSearchButton'
    And I wait 1000 milliseconds
    Then I receive main search results

Scenario: Error Message is Displayed by Button Click
    Given I am on the 'Search' page
    When I input 'mainSearchBox' with 'atlanta,us'
    And I click 'mainSearchButton'
    And I wait 1000 milliseconds
    Then The 'errorMessage' is visible