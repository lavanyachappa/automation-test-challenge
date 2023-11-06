package org.assessment.demo.restassured;

import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class PutCall extends RestCallBase {

	private ApiTestUtils apiUtils = null;
	String id_to_be_updated = null;

	PostCall post = null;

	public PutCall() {

		post = new PostCall();

	}

	public void init(Map<String, String> map) {
		this.id_to_be_updated = map.get("ID");

		post.init(map);

	}

	public Response execRestCall() {

		RequestSpecBuilder rsb = post.setQueryParams();
		Headers headers = post.setHeaders();
		response = RestAssured.given().spec(rsb.build()).headers(headers).contentType("application/json;charset=UTF-8")
				.body(post.getPayload()).when().urlEncodingEnabled(false).put(url).then().extract().response();
		LOGGER.info(response.getBody().asString());
		return response;
	}

	public void initURL(String endpoint_resource) {

		String partialURL = endpoint_resource + this.id_to_be_updated;
		url = apiUtils.generateUrl(partialURL);

	}

}
