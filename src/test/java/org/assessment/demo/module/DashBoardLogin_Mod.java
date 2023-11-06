package org.assessment.demo.module;

import org.assessment.demo.common.BaseUtilityLayer;
import org.assessment.demo.library.Contacts_Lib;
import org.assessment.demo.library.LibFactory;
import org.assessment.demo.library.LoginPage_Lib;
import org.testng.Assert;

public class DashBoardLogin_Mod extends BaseUtilityLayer {

	LibFactory libFactory = LibFactory.getLibraryInstance();
	// initialize library
	LoginPage_Lib loginLib = libFactory.getLoginPage();
	Contacts_Lib contactsPage = libFactory.getContactsPage();

	public void login(String login, String pwd) {
		loginLib.loadPage(CONF.get("webBaseUrl"));
		loginLib.enterUserName(login);
		loginLib.enterPassword(pwd);
		loginLib.clickSignIn();
		Assert.assertTrue(contactsPage.isContactsTableExist(), " log-in successfully and COntacts table displayed");
		LOGGER.info("log-in successfully and Contacts table displayed");

	}

}
