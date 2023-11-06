package org.assessment.demo.module;

public class ModuleFactory {

	private static final ModuleFactory mFactory = new ModuleFactory();

	private ModuleFactory() {
	}

	public static ModuleFactory getModuleInstance() {
		return mFactory;
	}

	public DashBoardLogin_Mod getDashBoardLogin() {
		return new DashBoardLogin_Mod();
	}

	public Contacts_Mod getContacts_Mod() {
		return new Contacts_Mod();
	}

}
