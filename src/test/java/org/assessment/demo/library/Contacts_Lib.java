package org.assessment.demo.library;

import java.util.Map;

import org.assessment.demo.seleniumframework.core.GUILibraryBase;
import org.assessment.demo.utils.IThContext;
import org.openqa.selenium.WebElement;

public class Contacts_Lib extends GUILibraryBase {
	public static final String CONTACTS_TABLE = "xpath=//table[@id='myTable']";
	public static final String CONTACTSNEW_BUTTON = "id=add-contact";
	public static final String FIRST_NAME = "id=firstName";
	public static final String LAST_NAME = "id=lastName";
	public static final String DOB = "id=birthdate";
	public static final String EMAIL = "id=email";
	public static final String PHONE = "id=phone";
	public static final String ADDRESS1 = "id=street1";
	public static final String ADDRESS2 = "id=street2";
	public static final String CITY = "id=city";
	public static final String STATE = "id=stateProvince";
	public static final String POSTALCODE = "id=postalCode";
	public static final String COUNTRY = "id=country";
	public static final String SUBMIT_FORM = "id=submit";
	public static final String TABLE_DATA = "xpath=//table/tr//td[text()='REPLACE']";

	public Contacts_Lib(IThContext tData) {
		super(tData);
	}

	LibFactory libFactory = LibFactory.getLibraryInstance();

	public boolean isContactsTableExist() {
		WebElement table_found = app.getElement(CONTACTS_TABLE);
		return table_found == null ? false : true;

	}

	public void clickNewContact() {
		app.click(CONTACTSNEW_BUTTON);
	}

	public void fillAndSubmitContactForm(Map<String, String> testdata) {

		if (testdata.get("firstname") != null) {
			app.sendKeys(FIRST_NAME, testdata.get("firstname"));
		}
		if (testdata.get("lastname") != null) {
			app.sendKeys(LAST_NAME, testdata.get("lastname"));
		}
		if (testdata.get("dob") != null) {
			app.sendKeys(DOB, testdata.get("dob"));
		}
		if (testdata.get("email") != null) {
			app.sendKeys(EMAIL, testdata.get("email"));
		}
		if (testdata.get("phone") != null) {
			app.sendKeys(PHONE, testdata.get("phone"));
		}
		if (testdata.get("address1") != null) {
			app.sendKeys(ADDRESS1, testdata.get("address1"));
		}
		if (testdata.get("address2") != null) {
			app.sendKeys(ADDRESS2, testdata.get("address2"));
		}
		if (testdata.get("city") != null) {
			app.sendKeys(CITY, testdata.get("city"));
		}
		if (testdata.get("state") != null) {
			app.sendKeys(STATE, testdata.get("state"));
		}
		if (testdata.get("postalcode") != null) {
			app.sendKeys(POSTALCODE, testdata.get("postalcode"));
		}
		if (testdata.get("country") != null) {
			app.sendKeys(COUNTRY, testdata.get("country"));
		}
		app.click(SUBMIT_FORM);

	}

	public boolean isContactCreated(String text) {
		WebElement data_found = app.getElement(TABLE_DATA.replace("REPLACE", text));
		return data_found == null ? false : true;

	}

}
