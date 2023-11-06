package org.assessment.demo.seleniumframework.core;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

class HtmlWebElement {

	private WebElement ele;

	public HtmlWebElement(WebElement element) {
		this.ele = element;
	}

	public HtmlWebElement(HtmlWebElement element) {
		this.ele = element.getWebElement();
	}

	public WebElement getWebElement() {
		return this.ele;
	}

	public String getAttribute(String attribute) {
		return ele.getAttribute(attribute);
	}

	public boolean isDisplayed() {
		return ele.isDisplayed();
	}

	public boolean isEnabled() {
		return ele.isEnabled();
	}

	public boolean isSelected() {
		return ele.isSelected();
	}

	public String getText() {
		return ele.getText();
	}

	public void click() {
		ele.click();
	}

	public void clear() {
		ele.clear();
	}

	public void sendKeys(CharSequence... keysToSend) {
		ele.sendKeys(keysToSend);
	}

	// @Override
	public List<HtmlWebElement> _findElements(HtmlBy by) {
		List<WebElement> webElements = ele.findElements(by.getBy());

		List<HtmlWebElement> odcWebelements = new ArrayList<>();

		for (WebElement ele : webElements) {
			odcWebelements.add(new HtmlWebElement(ele));
		}
		return odcWebelements;

	}

}