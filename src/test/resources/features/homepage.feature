Feature: Homepage testing

  Scenario: User successfully creates a new account
    Given User is on Homepage
    When User enters valid credentials
    And User signs in
    Then User should be successfully signed in

  Scenario: Member logs in
    Given User is on Homepage
    When User signs in with password
    Then User should be successfully signed in

  Scenario: User searches for flight
    Given User is on Homepage
    When User fills out Search form
    Then Flight Search result should be displayed
