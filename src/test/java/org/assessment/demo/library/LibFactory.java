package org.assessment.demo.library;
import org.assessment.demo.utils.IThContext;
import org.assessment.demo.utils.ThContextData;


public class LibFactory {

	private static final LibFactory lFactory = new LibFactory();

	private LibFactory() {}

	public static LibFactory getLibraryInstance () {
		return lFactory;
	}


	public LoginPage_Lib getLoginPage() {
		IThContext tData = new ThContextData();
		return new LoginPage_Lib(tData);
	}

	public Contacts_Lib getContactsPage() {
		IThContext tData = new ThContextData();
		return new Contacts_Lib(tData);
	}

	
}

