package org.assessment.demo.seleniumframework.core;

import org.openqa.selenium.WebDriver;

public class UIDriver {
	private WebDriver driver = null;

	public UIDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void switchTo() {
		this.driver.switchTo();
	}

	public void alert() {
		this.driver.switchTo().alert();
	}

	public void accept() {
		this.driver.switchTo().alert().accept();
	}

	public WebDriver getDriver() {
		return driver;
	}
}
