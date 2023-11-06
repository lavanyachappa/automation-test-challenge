package org.assessment.demo.common;

import org.assessment.demo.utils.Configuration;
import org.assessment.demo.utils.Utils;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteData implements ISuiteListener {

	private String environment = null;
	private String envfilename = null;
	private String executionRunId = null;
	public static boolean RERUN = false;
	public static long RUNID = 0;

	public static Configuration CONF = new Configuration();
	public static Utils UTILS = new Utils();

	public void onStart(ISuite suite) {

		this.environment = (String) suite.getParameter("environment");
		this.envfilename = (String) suite.getParameter("envFile");
		this.executionRunId = (String) suite.getParameter("executionRunId");

		RUNID = this.executionRunId != null && !this.executionRunId.isEmpty() ? Long.parseLong(this.executionRunId)
				: Long.parseLong(UTILS.getRandom(17));
		RERUN = Boolean.valueOf(System.getProperty("rerun"));

		if (environment != null && !environment.isEmpty()) {
			CONF.setExecutionEnv(environment);
			CONF.setExecutionEnvFileName(envfilename);
			CONF.init();

		} else {
			System.out.println("Define environment in your xml file");
			System.exit(1);
		}
		// Read bearerToken for api if we know the type of authentication used
	}

	public void onFinish(ISuite suite) {

		// Code to do on test suite finish

	}

}
