package org.assessment.demo.restassured;

import java.util.Map;

import io.restassured.response.Response;

public interface IRestCall {
	public void init (Map<String, String> map);
	public void initURL (String url);
	public Response execRestCall();
	public Response getAPIResponse();

}
