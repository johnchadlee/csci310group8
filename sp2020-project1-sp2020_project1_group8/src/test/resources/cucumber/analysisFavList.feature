@tag
Feature: left click to select city
  @tag1
  Scenario: city exist in favorite list
    Given I am on 'Analysis' page
    When I click 'favCity' 
    Then I select 'favCity'