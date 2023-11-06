package org.assessment.demo.restassured;

import java.util.Map;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class PostCall extends RestCallBase {

	private String payload = null;

	public void init(Map<String, String> map) {
		inputFromCSVParams = ApiTestUtils.filterAttributes(map, "param_");
		this.payload = map.get("payload");

	}

	public String getPayload() {
		return this.payload;
	}

	public Headers setHeaders() {
		Header authorization = new Header("Authorization", "Bearer " + CONF.get("TOKEN"));
		return new Headers(authorization);

	}

	public Response execRestCall() {
		LOGGER.info("Input payload::" + getPayload());
		RequestSpecBuilder rsb = setQueryParams();

		if (url.toString().contains("/login")) {
			response = RestAssured.given().spec(rsb.build()).contentType("application/json;charset=UTF-8")
					.body(getPayload()).when().urlEncodingEnabled(false).post(url).then().extract().response();

		} else {
			Headers headers = setHeaders();
			response = RestAssured.given().spec(rsb.build()).headers(headers)
					.contentType("application/json;charset=UTF-8").body(getPayload()).when().urlEncodingEnabled(false)
					.post(url).then().extract().response();
		}

		LOGGER.info("RESPONSE::" + response.asString());
		return response;
	}

}
