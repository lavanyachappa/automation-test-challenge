package org.assessment.demo.restassured;

import java.net.MalformedURLException;
import io.restassured.response.Response;
import java.util.Map;

public class RestCallHandler {

	public Response execMethod(String method, String baseurl, String endpoint, Map<String, String> map)
			throws MalformedURLException {

		IRestCall handler = null;

		if ("GET".equalsIgnoreCase(method)) {
			handler = new GetCall();

		} else if ("POST".equalsIgnoreCase(method)) {
			handler = new PostCall();

		} else if ("PUT".equalsIgnoreCase(method)) {
			handler = new PutCall();

		} else if ("DELETE".equalsIgnoreCase(method)) {
			handler = new DeleteCall();
		} else {
			System.out.println("Invalid Operations");
		}

		// make the call
		handler.init(map);
		handler.initURL(baseurl + endpoint);
		return handler.execRestCall();

	}

}
