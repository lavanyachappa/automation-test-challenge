package org.assessment.demo.seleniumframework.core;



import org.openqa.selenium.WebElement;
import org.assessment.demo.common.BaseUtilityLayer;
import org.assessment.demo.utils.IThContext;


public abstract class GUILibraryBase  extends BaseUtilityLayer{
	protected AppLibrary app = null;
	protected HtmlWebElement element = null;
	public GUILibraryBase(IThContext tData) {
		app = new AppLibrary(tData);
		
	}
	public GUILibraryBase(WebElement ele) {
		element = new HtmlWebElement(ele);
		
	}
}
