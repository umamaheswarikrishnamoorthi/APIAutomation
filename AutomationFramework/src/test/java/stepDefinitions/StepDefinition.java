package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GetResourcesDetails;
import pojo.RetrieveComments;
import pojo.UpdateResources;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.hamcrest.Matcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StepDefinition extends Utils{

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Posts API Payload with {string} {string} {string}")
	public void posts_api_payload_with(String userId, String id, String title) throws IOException {
								
		reqSpec = given().spec(requestSpecification()).body(data.posttApiPayload(userId,id,title));
		
	}
		
	@When("user calls {string} with {string} http method")
	public void user_calls_with_http_method(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST"))					
			response = reqSpec.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			response = reqSpec.when().delete(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("PUT"))
			response = reqSpec.when().put(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("PATCH"))
			response = reqSpec.when().patch(resourceAPI.getResource());
	}
	@Then("the API call got Success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statusCode) {		
		resSpec = new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).build();		
	    assertEquals(response.getStatusCode(),statusCode);	    
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {			
	    assertEquals(getJsonPath(response,keyValue).toString(),expectedValue);
	}
	
	@Given("{string} Validation")
	public void validation(String apiName) throws IOException {
		reqSpec = given().spec(requestSpecification());
	}
	
	@Given("PUTAPI Validation {string} {string} {string} {string}")
	public void putapi_validation(String userId, String id, String title, String body) throws IOException {
		reqSpec = given().spec(requestSpecification()).body(data.updateApiPayload(userId,id,title,body));
	}

	
	@Given("PostAPI Validation {string}")
	public void post_api_validation(String title) throws IOException {
		reqSpec = given().spec(requestSpecification()).body(data.patchMethodPayload(title));
	}
	
	@Then("the retrieved records size should be {int}")
	public void the_retrieved_records_size_should_be(Integer expectedCount) {		
		assertEquals(data.retrieveResources(response).size(),100);		
	}
	
	@Then("the fields in response body should not be NULL")
	public void the_fields_in_response_body_should_not_be_null() {
		List<RetrieveComments> retrieveDetails = data.retrieveComments(response);
		for(int i=0; i<retrieveDetails.size(); i++)
		{			
			assertNotNull(retrieveDetails.get(i).getId());
			assertNotNull(retrieveDetails.get(i).getEmail());
			assertNotNull(retrieveDetails.get(i).getBody());
		}		
	}
	
	@Given("{string} Validation for the userId {string}")
	public void validation_for_the_user_id(String string, String userId) throws IOException {
		reqSpec = given().spec(requestSpecification()).queryParam("userId", userId);
	}
	
	@Then("the {string} in response body should be {string}")
	public void the_in_response_body_should_be(String userId, String keyValue) {
		List<GetResourcesDetails> retrieveDetails = data.retrieveDetails(response);
		for(int i=0; i<retrieveDetails.size(); i++)
		{			
			assertEquals(retrieveDetails.get(i).getUserId().toString(),keyValue);
			assertNotNull(retrieveDetails.get(i).getId());
			assertNotNull(retrieveDetails.get(i).getTitle());
		}	
	}
	
	@Then("the {string} in response body for retrieving post API should be {string}")
	public void the_in_response_body_for_retrieving_post_api_should_be(String userId, String keyValue) {
		List<UpdateResources> retrieveDetails = data.retrieveResources(response);
		for(int i=0; i<retrieveDetails.size(); i++)
		{			
			assertEquals(retrieveDetails.get(i).getUserId().toString(),keyValue);
			assertNotNull(retrieveDetails.get(i).getId());
			assertNotNull(retrieveDetails.get(i).getTitle());
		}
	}
	
	@Then("the {string} response body should be {string}")
	public void the_response_body_should_be(String userId, String keyValue) {
		List<GetResourcesDetails> retrieveDetails = data.retrieveDetails(response);
		for(int i=0; i<retrieveDetails.size(); i++)
		{			
			assertNull(retrieveDetails);			
		}	
	}
}
	

