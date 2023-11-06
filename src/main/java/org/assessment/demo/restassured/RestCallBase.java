package org.assessment.demo.restassured;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.assessment.demo.utils.Configuration;
import org.assessment.demo.utils.ExtentTestManager;
import org.assessment.demo.utils.IConf;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public abstract class RestCallBase implements IRestCall {

	public ExtentTest LOGGER = null;
	public IConf CONF = null;
	private ApiTestUtils apiUtils = new ApiTestUtils();

	public RestCallBase() {
		CONF = new Configuration();
		LOGGER = ExtentTestManager.getTest();

	}

	protected Map<String, String> inputFromCSVParams = null;
	protected Map<String, String> credentials = new LinkedHashMap<String, String>();
	protected Map<String, String> orderedParams = new LinkedHashMap<String, String>();

	protected Response response = null;
	URL url = null;

	public Response getAPIResponse() {
		return response;
	}

	public void initURL(String endpoint_resource) {
		url = apiUtils.generateUrl(endpoint_resource);
		LOGGER.info("Inital URL::" + url);

	}

	public RequestSpecBuilder setQueryParams() {

		Map<String, String> params = this.loadParamters();
		RequestSpecBuilder rsb = new RequestSpecBuilder();

		for (String key : params.keySet()) {
			String value = params.get(key);

			// partnerID = 100,101,102
			if (apiUtils.isList(value)) {
				// let us add them individually
				String[] itemList = value.split(",");
				for (String item : itemList) {
					rsb.addQueryParam(key, item);
				}
			}
			rsb.addQueryParam(key, value);
		}
		LOGGER.info("PARAMETERS::" + params);

		return rsb;

	}

	public RequestSpecBuilder setParams() {

		Map<String, String> params = this.loadParamters();
		RequestSpecBuilder rsb = new RequestSpecBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (apiUtils.isList(value)) {

				// let us add them individually
				String[] itemList = value.substring(2, value.length() - 2).split(",");
				for (String item : itemList) {
					rsb.addParam(key, item);
				}

			} else {
				rsb.addParam(key, value);
			}
		}
		return rsb;

	}

	private Map<String, String> loadParamters() {

		Map<String, String> params = new LinkedHashMap<String, String>();
		params.putAll(this.orderedParams);
		params.putAll(this.credentials);

		return params;

	}

}
