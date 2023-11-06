package org.assessment.demo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.assessment.demo.common.BaseUtilityLayer;
import org.assessment.demo.seleniumframework.core.Browser;

public abstract class BrowserTestBase extends BaseUtilityLayer {

	private List<String> steps = null;
	private Map<String, String> stepsStatus = null;

	public void status(String stepNumber, String pf) {
		stepsStatus.put(stepNumber.toLowerCase(), pf);
	}

	public void testCase(String step) {
		steps.add(step);
	}

	public Map<String, String> getTestStepsStatus() {
		return stepsStatus;
	}

	public List<String> getTestSteps() {
		return steps;
	}

	@Parameters({ "platform", "browserName", "headless" })
	@BeforeMethod(alwaysRun = true)
	public void setup(@Optional String platform, @Optional String browserName, @Optional String headless) {
		steps = new ArrayList<String>();
		stepsStatus = new HashMap<String, String>();

		Map<String, String> remoteConfiguration = new HashMap<String, String>();
		remoteConfiguration.put("platform", platform);
		remoteConfiguration.put("browserName", browserName);

		tContext.setConf(CONF);
		tContext.setUtilityLayer(UTILS);

		if (headless == "true") {
			remoteConfiguration.put("HEADLESS", "true");
			new Browser(tContext).createBrowser(remoteConfiguration);

		} else {
			if (platform == null || browserName == null) {
				throw new RuntimeException("Platform and browserName are not specified in suite file");
			}
			new Browser(tContext).createBrowser(remoteConfiguration);
		}

	}

	@AfterMethod(alwaysRun = true)
	public void cleanup() {
		new Browser(tContext).cleanUp();
	}

}
