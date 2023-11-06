package org.assessment.demo.seleniumframework.core;
import java.time.Duration;
import java.util.List;

import org.assessment.demo.utils.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WaitForElement  {
	
	public static int MAX_TIMEOUT_SEC = 30;
	public static int MAX_TIMEOUT_MIL = 30000;
	WebDriverWait wait = null;
	WebDriver driver = null;
	ExtentTest LOGGER = ExtentTestManager.getTest();
	

    public WaitForElement(WebDriver driver) {
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver,Duration.ofSeconds(MAX_TIMEOUT_SEC));
    
    }
    public WaitForElement(WebDriver driver, int timeout) {
    	
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
    }
    public WebElement locateElement(String elementLocator) {
		
		WebElement webElement = null;
		
		webElement = getWebElementById(elementLocator);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByName(elementLocator, true);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByXpath(elementLocator);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByLinkText(elementLocator);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByCSS(elementLocator);
		if (webElement != null) { return webElement;}
		
		return webElement;
	}
    public WebElement locateElementWithOutDelay(String locator) {
    	
    	WebElement webElement = null;
		
		webElement = getWebElementByXpath(locator, false);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByName(locator, false);
		if (webElement != null) { return webElement;}
		
		webElement = getWebElementByCSS(locator, false);
		if (webElement != null) { return webElement;}
    	
		return webElement;
    	
    }
    
	public WebElement getWebElementById(String locator) {
		
		if (locator.startsWith("id")) {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.substring(3))));
		}
		return null;
		
	}
	private WebElement getWebElementByName(String locator, boolean delay) {
		
		if (locator.startsWith("name")) {
			if (delay) {
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator.substring(5))));
			}else {
				return driver.findElement(By.name(locator.substring(5)));
			}
		}
		return null;
		
	}
	private WebElement getWebElementByCSS(String locator, boolean delay) {
		
	
		if (locator.startsWith("css")) {
			if (delay) {
				return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator.substring(4))));
			}else {
				return driver.findElement(By.xpath(locator.substring(4)));
			}
		}
		return null;
		
	}
	public List<WebElement> getWebElementsByTagName(String tag) {
		return driver.findElements(By.tagName(tag));
		
	}
	public List<WebElement> getWebElementsById(String id) {
		return driver.findElements(By.id(id));
		
	}
	
	public List<WebElement> getWebElementsByTagName(String parent, String tag) {
		return this.getWebElementByXpath(parent).findElements(By.tagName(tag));
		
	}
	public List<WebElement> getWebElementsByXpath(String locator) {
		return driver.findElements(By.xpath(locator.substring(6)));
		
	}
	private WebElement getWebElementByCSS(String locator) {
		return this.getWebElementByCSS(locator, true);
		
	}
	private WebElement getWebElementByXpath(String locator) {
		return getWebElementByXpath(locator, true);
		
	}
	private WebElement getWebElementByXpath(String locator, boolean delay) {
		try {
			if (locator.startsWith("xpath")) {
				if (delay) {
					return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.substring(6))));
				}else {
					return driver.findElement(By.xpath(locator.substring(6)));
				}
			}
		}catch (NoSuchElementException e) {
			LOGGER.log(Status.FAIL,e.getMessage());
		}catch (TimeoutException e) {
			LOGGER.log(Status.FAIL,e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private WebElement getWebElementByLinkText(String locator) {
		
		if (locator.startsWith("link")) {
			return wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator.substring(9))));
		}
		return null;
		
	}
}

