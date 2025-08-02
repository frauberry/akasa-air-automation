Feature: Flight search testing

  Scenario: Privacy Policy opens in the new tab
    Given Guest is on Flight Search page
    When Guest opens Privacy Policy
    Then Privacy Policy should be open in the new tab