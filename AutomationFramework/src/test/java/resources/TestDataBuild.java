package resources;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import pojo.GetResourcesDetails;
import pojo.PatchResource;
import pojo.RetrieveComments;
import pojo.UpdateResources;

public class TestDataBuild {

ObjectMapper mapper = new ObjectMapper();	
	
	public GetResourcesDetails posttApiPayload(String userId, String id, String title) {
		GetResourcesDetails request = new GetResourcesDetails();
		request.setUserId(userId);
		request.setId(id);
		request.setTitle(title);
		return request;
	}
	
	public UpdateResources updateApiPayload(String userId, String id, String title, String body) {
		UpdateResources request = new UpdateResources();
		request.setUserId(userId);
		request.setId(id);
		request.setTitle(title);
		request.setBody(body);
		return request;
	}
	
	public PatchResource patchMethodPayload(String title) {
		PatchResource request = new PatchResource();		
		request.setTitle(title);	
		return request;
	}
	
	public List<UpdateResources> retrieveResources(Response response) {		
		List<UpdateResources> resourceDetails = mapper.convertValue(
				convertJsonResponseasNode(response), 
			    new TypeReference<List<UpdateResources>>(){}
			);
				
		return resourceDetails;
	}

	public List<RetrieveComments> retrieveComments(Response response) {
		List<RetrieveComments> resourceDetails = mapper.convertValue(
				convertJsonResponseasNode(response), 
			    new TypeReference<List<RetrieveComments>>(){}
			);
				
		return resourceDetails;
	}
	
	public List<GetResourcesDetails> retrieveDetails(Response response) {
		List<GetResourcesDetails> resourceDetails = mapper.convertValue(
				convertJsonResponseasNode(response), 
			    new TypeReference<List<GetResourcesDetails>>(){}
			);
				
		return resourceDetails;
	}
	
	public JsonNode convertJsonResponseasNode(Response response) {		
		return ((ResponseBodyExtractionOptions) response).as(JsonNode.class);		
	}

}
