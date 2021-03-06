#Author: Uma Maheswari
#Comments: Validating the scenarios for GET method

Feature: Validating Post API's using GET Method  
 
  Scenario: Verify getting success response and retreiving the resources when hitting Get API
    Given "GetResource API" Validation
    When user calls "PostAPI" with "Get" http method
		Then the API call got Success with status code 200
		And the retrieved records size should be 100
		
			
  Scenario: Verify getting success response and retreiving the comments when hitting Get API
    Given "Retrieve Comments" Validation
    When user calls "Comments" with "Get" http method
		Then the API call got Success with status code 200
		And the fields in response body should not be NULL
		
  Scenario Outline: Verify getting success response and retreiving the albums based on the User ID when hitting Get API
    Given "Retrieve Albums based on User ID" Validation for the userId "<userId>"
    When user calls "Albums" with "Get" http method
		Then the API call got Success with status code 200
		And the "userId" in response body should be "<userId>"
	
	Examples:
	| userId |
  |   1    |  
  |   8    |
	
		
  Scenario Outline: Verify getting success response and retreiving the details based on the User ID when hitting Get API
    Given "Retrieve details based on User ID" Validation for the userId "<userId>"
    When user calls "PostAPI" with "Get" http method
		Then the API call got Success with status code 200
		And the "userId" in response body for retrieving post API should be "<userId>"
	
	Examples:
	| userId |
  |   1    |  
  |   8    |
	