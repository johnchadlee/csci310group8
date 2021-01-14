Feature: Displays a single text box to search

Scenario: Text box exists on page load
    Given I am on the 'Search' page
    Then The element 'mainSearchBox' exists

Scenario: Placeholder text correct
    Given I am on the 'Search' page
    Then The 'mainSearchBox' 'placeholder' is 'Enter location (city or zip)'

Scenario: Placeholder text disappears
    Given I am on the 'Search' page
    When I input 'mainSearchBox' with 'a'
    Then The 'mainSearchBox' 'value' is 'a'

Scenario: Text box has default focus
    Given I am on the 'Search' page
    Then The 'mainSearchBox' has focus