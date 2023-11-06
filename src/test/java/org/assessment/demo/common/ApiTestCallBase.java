package org.assessment.demo.common;

import org.assessment.demo.restassured.RestCallHandler;

import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.util.Map;

public class ApiTestCallBase extends BaseUtilityLayer {

	protected RestCallHandler RESTCALL_HANDLER = new RestCallHandler();

	protected String endpoint;
	protected String method;
	protected String resource;
	protected String baseurl;

	public Response execGET(Map<String, String> params) throws Exception {
		params.put("method", "GET");
		return this.invokeTheCall(params);

	}

	public Response execPOST(Map<String, String> params) throws Exception {
		params.put("method", "POST");
		return this.invokeTheCall(params);
	}

	public Response execPUT(Map<String, String> params) throws Exception {
		params.put("method", "PUT");
		return this.invokeTheCall(params);
	}

	public Response execDELETE(Map<String, String> params) throws Exception {
		params.put("method", "DELETE");
		return this.invokeTheCall(params);
	}

	private Response invokeTheCall(Map<String, String> params) throws MalformedURLException {
		return this.RESTCALL_HANDLER.execMethod(params.get("method"), CONF.get("apiBaseUrl"), endpoint, params);

	}

}
