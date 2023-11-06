package org.assessment.demo.seleniumframework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HtmlBy {

	private By by;

	public List<WebElement> findElements(SearchContext context) {
		List<WebElement> allElements = findElements(context);
		if (allElements == null || allElements.isEmpty())
			throw new NoSuchElementException("Cannot locate an element using " + toString());
		return allElements;
	}

	public HtmlBy(By by) {
		this.by = by;
	}

	public HtmlBy(HtmlBy by) {
		this.by = by.getBy();
	}

	public By getBy() {
		return this.by;
	}

	public static HtmlBy tagName(final String name) {
		if (name == null)
			throw new IllegalArgumentException("Cannot find elements when name tag name is null.");

		return new HtmlBy(By.tagName(name));
	}

	public static HtmlBy xpath(final String xpathExpression) {
		if (xpathExpression == null)
			throw new IllegalArgumentException("Cannot find elements when the XPath expression is null.");

		return new HtmlBy(By.xpath(xpathExpression));
	}

}
