package org.assessment.demo.module;

import java.util.Map;

import org.assessment.demo.common.BaseUtilityLayer;
import org.assessment.demo.library.Contacts_Lib;
import org.assessment.demo.library.LibFactory;
import org.testng.Assert;

public class Contacts_Mod extends BaseUtilityLayer {
	LibFactory libFactory = LibFactory.getLibraryInstance();
	Contacts_Lib contactsLib = libFactory.getContactsPage();

	public void createContact(Map<String, String> testdata) {
		contactsLib.clickNewContact();
		contactsLib.fillAndSubmitContactForm(testdata);

	}

	public void validateContactCreated(Map<String, String> testdata) {
		boolean found = contactsLib.isContactCreated(testdata.get("firstname") + " " + testdata.get("lastname"));
		Assert.assertTrue(found, "Contact Created Successfully");
		LOGGER.info("Contact::" + testdata.get("firstname") + " " + testdata.get("lastname") + " Created Successfully");
	}

}
