Feature: Homepage testing

  Scenario: Member successfully signs in
    Given Member is on Homepage
    When Member enters valid credentials
    And Member signs in
    Then Member should be successfully signed in
