package org.assessment.demo.restassured;

import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class GetCall extends RestCallBase {

	private boolean isQueryById = false;
	private String Id_to_query = null;
	private ApiTestUtils apiUtils = null;

	public void initURL(String endpoint_resource) {
		if (isQueryById) {
			String strUrl = endpoint_resource;
			this.Id_to_query = "/" + this.Id_to_query;
			strUrl = strUrl + this.Id_to_query;
			url = apiUtils.generateUrl(strUrl);

		} else {
			super.initURL(endpoint_resource);
		}

	}

	public void init(Map<String, String> map) {

		this.Id_to_query = map.get("ID");
		if (Id_to_query != null && !Id_to_query.isEmpty()) {
			this.isQueryById = true;
		}

		this.inputFromCSVParams = ApiTestUtils.filterAttributes(map, "param_");

	}

	public Response execRestCall() {

		RequestSpecBuilder rsb = setParams();

		response = RestAssured.given().spec(rsb.build()).contentType("application/json;charset=UTF-8")
				.header("Authorization", CONF.get("TOKEN")).urlEncodingEnabled(false).when().get(url).then().extract()
				.response();
		LOGGER.info("RESPONSE::" + response.getBody().asString());
		return response;
	}

}
