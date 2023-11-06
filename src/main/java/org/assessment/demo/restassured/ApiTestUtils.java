package org.assessment.demo.restassured;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.assessment.demo.utils.IConf;

import com.aventstack.extentreports.ExtentTest;

public class ApiTestUtils {

	ExtentTest LOGGER = null;
	IConf CONF = null;

	public URL generateUrl(String strUrl) {

		URL url = null;

		try {
			url = new URL(strUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("FAILED to create URL Object");
		}

		return url;
	}

	public boolean isList(String input) {

		return input.matches("^\\%\\{.*\\}\\%$");

	}

	public static Map<String, String> filterAttributes(Map<String, String> map, String token) {

		Map<String, String> attributes = new HashMap<String, String>();
		Set<String> filteredAttributes = new HashSet<String>();

		if (map == null)
			return attributes;

		for (String key : map.keySet()) {
			if (key.contains(token)) {
				filteredAttributes.add(key);
			}
		}

		// let load filtered attributes
		if (!filteredAttributes.isEmpty()) {
			for (String key : filteredAttributes) {
				String value = map.get(key);
				if (value != null && !value.isEmpty()) {
					attributes.put(key.substring(token.length()), value);
				}
			}
		}
		return attributes;
	}

}
