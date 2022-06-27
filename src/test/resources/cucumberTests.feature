Feature: Car Adding Feature

  Scenario: adding cars
    Given There are 3 cars in database
    When I add one car
    Then There are 4 cars in database