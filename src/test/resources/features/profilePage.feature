Feature: Profile testing

  Scenario: Member adds traveler
    Given Member is on Saved Travelers page
    When Member adds a new traveler
    Then A new traveler should be saved