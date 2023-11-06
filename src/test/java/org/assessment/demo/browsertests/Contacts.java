package org.assessment.demo.browsertests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.assessment.demo.common.BrowserTestBase;
import org.assessment.demo.common.Data;
import org.assessment.demo.common.TestDataProvider;
import org.assessment.demo.module.Contacts_Mod;
import org.assessment.demo.module.DashBoardLogin_Mod;
import org.assessment.demo.module.ModuleFactory;
import org.assessment.demo.utils.ExtentTestManager;

import java.util.Map;

import org.testng.ITestContext;

public class Contacts extends BrowserTestBase {

	ModuleFactory factory = ModuleFactory.getModuleInstance();

	@Test(dataProvider = "odcDataProvider", dataProviderClass = TestDataProvider.class)

	@Data(file = "data/testdata.csv", rows = { 1 })
	public void loginAndVerifyCOntactsDisplayed(Map<String, String> map, ITestContext context) throws Exception {

		ExtentTestManager.startTest(map.get("TestName"), map.get("TestDescription"));
		DashBoardLogin_Mod dashBoardLogin_Mod = factory.getDashBoardLogin();
		dashBoardLogin_Mod.login(CONF.get("USERNAME_LOGIN"), CONF.get("PASSWORD_LOGIN"));

	}

	@Test(dataProvider = "odcDataProvider", dataProviderClass = TestDataProvider.class)

	@Data(file = "data/testdata.csv", rows = { 2 })
	public void newContactVerification(Map<String, String> map, ITestContext context) throws Exception {

		ExtentTestManager.startTest(map.get("TestName"), map.get("TestDescription"));
		DashBoardLogin_Mod dashBoardLogin_Mod = factory.getDashBoardLogin();
		Contacts_Mod contacts_Mod = factory.getContacts_Mod();
		dashBoardLogin_Mod.login(CONF.get("USERNAME_LOGIN"), CONF.get("PASSWORD_LOGIN"));
		// Create new contact
		contacts_Mod.createContact(map);
		contacts_Mod.validateContactCreated(map);

	}

}
