@tag
Feature: Display image after a city is selected
  @tag1
  Scenario: Clicking on a city displays image
    Given I am on 'Analysis' page
    When I click on a 'favCity'
    Then display on 'carousel'

