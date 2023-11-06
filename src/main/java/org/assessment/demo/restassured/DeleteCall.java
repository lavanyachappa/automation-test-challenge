package org.assessment.demo.restassured;


import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class DeleteCall extends RestCallBase  {


	private String Id_to_be_deleted = null;
	private PostCall post = null;
	private ApiTestUtils apiUtils= null;
	
	public DeleteCall() {
		
		post = new PostCall();
		
	}
	public void init(Map<String, String> map) {

		this.Id_to_be_deleted = map.get("FEATURE_ID");
		this.inputFromCSVParams = ApiTestUtils.filterAttributes(map, "param_");
		post.init(map);

	}

	public void initURL(String endpoint_resource) {

		String partialURL = endpoint_resource;
		if (Id_to_be_deleted != null && !Id_to_be_deleted.isEmpty()) {
			this.Id_to_be_deleted = "/" + this.Id_to_be_deleted;
		}
		partialURL = partialURL + this.Id_to_be_deleted;
		url = apiUtils.generateUrl(partialURL);
	}


	public Response execRestCall() {

		RequestSpecBuilder rsb = setParams();
	    response = RestAssured.given().spec(rsb.build()).headers(post.setHeaders()).urlEncodingEnabled(false).delete(url).then()
					.extract().response();
		
		LOGGER.info(response.getBody().asString());
		return response;

	}
}

