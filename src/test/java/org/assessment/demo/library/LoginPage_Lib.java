package org.assessment.demo.library;

import org.assessment.demo.seleniumframework.core.GUILibraryBase;
import org.assessment.demo.utils.ExtentTestManager;
import org.assessment.demo.utils.IThContext;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class LoginPage_Lib extends GUILibraryBase {
	public static final String USERNAME = "id=email";
	public static final String PASSWORD = "id=password";
	public static final String SIGNIN = "id=submit";

	public LoginPage_Lib(IThContext tData) {
		super(tData);
	}

	LibFactory libFactory = LibFactory.getLibraryInstance();

	public void loadPage(String url) {
		app.loadPage(url);

	}

	public void enterUserName(String userName) {
		app.getElement(USERNAME).sendKeys(userName);
	}

	public void enterPassword(String password) {
		app.getElement(PASSWORD).sendKeys(password);
	}

	public void clickSignIn() {
		app.getElement(SIGNIN).click();

	}

}
