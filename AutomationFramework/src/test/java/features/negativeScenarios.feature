#Author: Uma Maheswari
#Feature: Negative Scenarios

Feature: Validating the Negative Scenarios
 
@negative
 Scenario Outline: Verify getting success response and retreiving the albums based on the User ID when hitting Get API
    Given "Retrieve Albums based on User ID" Validation for the userId "<userId>"
    When user calls "Albums" with "Get" http method
		Then the API call got Success with status code 200
		And the "userId" response body should be "<userId>"
	
	Examples:
	| userId    |
  |   $%%%    |    
 