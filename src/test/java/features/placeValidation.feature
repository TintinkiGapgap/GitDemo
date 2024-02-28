Feature: Validating Google Place API's

@AddPlace @Regression
Scenario Outline: Verify if a place is being successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>" "<address>" "<language>"
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call should be successful with Status Code 200
    And "status" in response body should be "OK"
    And "scope" in response body should be "APP"
    And verify that the place ID created maps to "<name>" using "getPlaceAPI"

Examples:
    |      name        |      language     |               address               |
    |     Siya Ram     |      Sanskrit     |      4357, Weston Drive, 27513      |
    |  RadhaKrishna    |      Sanskrit     |      2319, Weston Drive, 27513      |


@UpdatePlace
Scenario: Verify if the Address is being updated with Update Place API
    Given updatePlace Payload
    When user calls "updatePlaceAPI" with "Put" http request
    Then the API call should be successful with Status Code 200
    And "msg" in response body should be "Address successfully updated"

@DeletePlace @Regression
Scenario: Verify if the Delete Place API is working

    Given deletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call should be successful with Status Code 200
    And "status" in response body should be "OK"