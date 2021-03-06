#Author: Uma Maheswari
#Comments: Validating POST APIs with the POST, PUT, PATCH methods

Feature: Validating Posts API's  

@CreateResource
  Scenario Outline: Verify getting success response and creating the resource when hitting Posts API
    Given Posts API Payload with "<userId>" "<id>" "<title>"
    When user calls "PostAPI" with "Post" http method
		Then the API call got Success with status code 201
		And "title" in response body is "<title>"
		
	Examples:
	| userId |  id    |         title       |
  |  1     |  101   | Testing with ID 101 |

	@DeleteResource
	Scenario: Verify getting success response and deleting the resource when hitting Delete API
    Given "DeleteAPI" Validation
    When user calls "AmendAPI" with "Delete" http method
		Then the API call got Success with status code 200
		
	@UpdateResource	
	Scenario Outline: Verify getting success response and update the payload when hitting PUT API
    Given PUTAPI Validation "<userId>" "<id>" "<title>" "<body>"
    When user calls "AmendAPI" with "Put" http method
		Then the API call got Success with status code 200
		And "body" in response body is "<body>"
		
	Examples:
	| userId |  id    |         title       |    body       |
  |  1     |  101   | Testing with ID 101 |  Testing Put  |
  
  @PatchResource	
  Scenario Outline: Verify getting success response and update the payload when hitting API with PATCH method
    Given PostAPI Validation "<title>"
    When user calls "AmendAPI" with "Patch" http method
		Then the API call got Success with status code 200
		And "title" in response body is "<title>"
		
	Examples:
  |         title       |
  | Testing for Patch   |
  