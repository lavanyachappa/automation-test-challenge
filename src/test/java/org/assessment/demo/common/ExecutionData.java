package org.assessment.demo.common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.assessment.demo.seleniumframework.core.Browser;
import org.assessment.demo.utils.ExtentTestManager;
import org.assessment.demo.utils.IThContext;
import org.assessment.demo.utils.ThContextData;

public class ExecutionData implements ITestListener {

	String screenShotURL = null;
	String jobName = null;

	public IThContext tContext = new ThContextData();

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentTestManager.getTest().getExtent().flush();

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {

		ExtentTestManager.getTest().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

		ExtentTestManager.getTest().fail(iTestResult.getThrowable().getMessage());

		String base64Screenshot = new Browser(tContext).takeScreenShotFoExtentReports();
		if (base64Screenshot != null)
			ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, base64Screenshot);

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		String methodName = iTestResult.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " - SKIPPED" + "</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		ExtentTestManager.getTest().skip(markup);
	}

	public void onStart(ITestContext context) {

	}

	public void onTestStart(ITestResult iTestResult) {

		// TODO Auto-generated method stub

	}

}
