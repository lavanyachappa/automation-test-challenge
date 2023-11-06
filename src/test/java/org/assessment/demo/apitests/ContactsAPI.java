package org.assessment.demo.apitests;

import java.util.List;
import java.util.Map;

import org.assessment.demo.common.ApiTestCallBase;
import org.assessment.demo.common.Data;
import org.assessment.demo.common.TestDataProvider;
import org.assessment.demo.restassured.ApiResponseValidators;
import org.assessment.demo.utils.ExtentTestManager;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;

public class ContactsAPI extends ApiTestCallBase {

	static Response response;

	@Test(dataProvider = "odcDataProvider", dataProviderClass = TestDataProvider.class, priority = 1)

	@Data(file = "data/apitestdata.csv", rows = { 1 })
	public void login_POST(Map<String, String> map, ITestContext context) throws Exception {
		// This test can be made as a prereq script before running nay API tests to read
		// the bearer token once
		ExtentTest logReport = ExtentTestManager.startTest(map.get("TestName"), map.get("TestDescription"));
		String payload_final = map.get("payload").replace("PASSWORD",
				CONF.get("PASSWORD_LOGIN").replace("RANDOM", UTILS.getRandom(5)));
		map.put("payload", payload_final);
		logReport.info("Request Payload::" + payload_final);
		endpoint = CONF.get("LOGIN_ENDPOINT");
		response = execPOST(map);
		ApiResponseValidators.validate(response, map);
		CONF.setKey("TOKEN", ApiResponseValidators.getValueFromResponse(response, "token"));

	}

	@Test(dataProvider = "odcDataProvider", dataProviderClass = TestDataProvider.class, priority = 2)

	@Data(file = "data/apitestdata.csv", rows = { 2 })
	public void contacts_GET(Map<String, String> map, ITestContext context) throws Exception {
		ExtentTestManager.startTest(map.get("TestName"), map.get("TestDescription"));
		endpoint = CONF.get("CONTACTS_ENDPOINT");
		response = execGET(map);
		ApiResponseValidators.validate(response, map);
		List<String> ids = ApiResponseValidators.getListValuesFromResponse(response, "_id");
		Assert.assertTrue(ids.size() > 0);

	}

	@Test(dataProvider = "odcDataProvider", dataProviderClass = TestDataProvider.class, priority = 3)

	@Data(file = "data/apitestdata.csv", rows = { 3 })
	public void contacts_POST(Map<String, String> map, ITestContext context) throws Exception {
		ExtentTestManager.startTest(map.get("TestName"), map.get("TestDescription"));
		String payload_final = map.get("payload").replace("RANDOM", UTILS.getRandom(5));
		map.put("payload", payload_final);
		endpoint = CONF.get("CONTACTS_ENDPOINT");
		response = execPOST(map);
		ApiResponseValidators.validate(response, map);

	}

}
