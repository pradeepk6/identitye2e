@verifyVehicle
Feature: Get vehicle information from DVLA

  Scenario Outline: lookup vehicle details on DVLA web site
    Given I go to DVLA get-vehicle-details page
    And I start the get-vehicle-details journey
    When I submit vehicle reg number <regNum>
    Then I should be able to confirm vehicle details: regNum <regNum>,make <make> and colour <colour>
    Examples:
      |regNum|make|colour|
      | bn14 yhl | seat     | silver |
      | bu67 jxt | Vauxhall | white  |
