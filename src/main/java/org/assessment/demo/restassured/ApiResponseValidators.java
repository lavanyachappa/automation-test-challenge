package org.assessment.demo.restassured;

import java.util.List;
import java.util.Map;
import org.assessment.demo.utils.ExtentTestManager;
import org.testng.Assert;

import io.restassured.response.Response;
import com.aventstack.extentreports.ExtentTest;

public class ApiResponseValidators {
	ExtentTest LOGG = ExtentTestManager.getTest();

	public static void validateResponseStatusCode(Response resp, String status) {
		Assert.assertTrue(String.valueOf(resp.getStatusCode()).equalsIgnoreCase(status));

	}

	public static List<String> getListValuesFromResponse(Response resp, String path) {
		return resp.getBody().jsonPath().getList(path);

	}

	public static String getValueFromResponse(Response resp, String path) {
		return resp.getBody().jsonPath().getString(path);

	}

	public static void validateResponse(Response resp, String path, String expected) {
		resp.getBody().jsonPath().get(path).equals(expected);
		ExtentTestManager.getTest().info(path + "::" + "is validated in response");

	}

	public static void validate(Response resp, Map<String, String> map) throws Exception {

		System.out.println("\n== Validation - Started ==\n");
		validateResponseStatusCode(resp, map.get("status"));

		Map<String, String> expected = ApiTestUtils.filterAttributes(map, "expected_");
		for (Map.Entry<String, String> pair : expected.entrySet()) {
             validateResponse(resp, pair.getKey(), pair.getValue());

		}

	}
}
