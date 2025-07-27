Feature: Profile testing

  Scenario: Member adds traveler
    Given Member is on Saved Travelers page
    When Member adds a new traveler
    Then A new traveler should be saved

    Scenario: Member successfully logs out
      Given Member is on Profile page
      When Member logs out
      Then Member should be redirected to the Homepage