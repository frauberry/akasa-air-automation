Feature: Flight Status

  Scenario: Guest checks flight status
    Given Guest is on Flight Status page
    When Guest fills out Flight Status form
    Then The flight should be found