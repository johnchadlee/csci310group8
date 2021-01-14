Feature: Shows current weather
  @tag1
  Scenario: Show current weather on weather display area
    Given I am on 'Analysis' page
    When I select a 'favCity'
    Then display 'location'
