package org.assessment.demo.seleniumframework.core;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.assessment.demo.utils.IThContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;


public class AppLibrary extends Browser {
	private static final int MAX_WAIT_FOR_PAGE = 180;

	public AppLibrary(IThContext tData) {
		super(tData);
	}

	public boolean clickInvisible(String elementLocator) {

		LOGGER.log(Status.INFO,"click :: " + elementLocator);
		WebElement element = null;
		for (int i = 0; i < 7; i++) {
			element = getWebElement(elementLocator, 5);
			if (element != null && element.isDisplayed()) {
				try {
					element.click();
					waitForPageLoad();
					break;
				} catch (Exception ex) {
					// sometime we get exception, just loop through
				}
			}
			UTILS.pause(5);
		}
		if (element == null)
			return false;
		else
			return true;
	}

	public boolean clickWithNoPageLoad(String elementLocator) {

		LOGGER.log(Status.INFO,"click :: " + elementLocator);
		WebElement element = null;
		for (int i = 0; i < 7; i++) {
			element = getWebElement(elementLocator, 5);
			if (element != null && element.isDisplayed()) {
				try {
					element.click();
					break;
				} catch (Exception ex) {
					// sometime we get exception, just loop through
				}
			}
			UTILS.pause(5);
		}
		if (element == null)
			return false;
		else
			return true;
	}

	public void click(String elementLocator, int timeout) {
		LOGGER.log(Status.INFO,"click :: " + elementLocator);
		getWebElement(elementLocator, timeout).click();
		waitForPageLoad();
	}

	public boolean debug(String elementLocator) {
		return false;
	}

	public boolean click(String elementLocator) {

	
		WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));

		LOGGER.log(Status.INFO,"click :: " + elementLocator);
		WebElement element = null;

		for (int i = 0; i < 7; i++) {
			element = getWebElement(elementLocator, 5);

			if (element != null && element.isDisplayed()) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					waitForPageLoad();
					break;
				} catch (Exception ex) {
					// sometime we get exception, just loop through
				}
			}
			UTILS.pause(5);
		}
		if (element == null)
			return false;
		else
			return true;
	}

	public void naviateBack() {
		LOGGER.log(Status.INFO,"Navigate Back On Browser ");
		this.getBrowser().navigate().back();
		waitForPageLoad();
	}

	public WebElement getElement(String locator) {
		LOGGER.log(Status.INFO,"Get Element::" + locator);
		return getWebElement(locator);
	}

	public WebElement getElement(String locator, int timeout) {
		LOGGER.log(Status.INFO,"Get Element :: " + locator + "in timeout::" + timeout);
		return getWebElement(locator, timeout);
	}

	public void clickHiddenElement(String locator) {

		LOGGER.log(Status.INFO,"click :: " + locator);

		WebElement element = getWebElementWithNoDeplay(locator);
		Actions act = new Actions(this.getBrowser());
		act.moveToElement(element).click().perform();
		waitForPageLoad();
	}

	public String getPageSource() {
		return this.getBrowser().getPageSource();
	}

	public void selectFrame(String name) {
		WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
	}


	public void selectFrame(int index) {
		WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void switchWindow(String handle) {
		this.getBrowser().switchTo().window(handle);
	}

	public String getWindowHandle() {
		return this.getBrowser().getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return this.getBrowser().getWindowHandles();
	}

	public void deSelectFrame() {
		this.getBrowser().switchTo().defaultContent();
	}

	public void sendKeysHiddenElement(String locator, String text) {
		LOGGER.log(Status.INFO,"Enter Text::" + text + " On Element:: " + locator);
		WebElement element = getWebElementWithNoDeplay(locator);
		Actions act = new Actions(this.getBrowser());
		act.moveToElement(element).sendKeys(text).perform();
		waitForPageLoad();
	}

	public void sendKeys(String locator, String text) {
		LOGGER.log(Status.INFO,"Enter Text::" + text + " On Element:: " + locator);
		getWebElement(locator).sendKeys(text);
	}

	public String getTextFromTextField(String locator) {
		return getWebElement(locator).getAttribute("value");
	}
	public void loadPage(String pageURL) {
		this.getBrowser().get(pageURL);
		waitForPageLoad();
	}
	public void loadPageFresh(String pageURL) {
		this.getBrowser().manage().deleteAllCookies();
		this.getBrowser().get(pageURL);
		waitForPageLoad();
	}
	public String getPageTitle() {
		return this.getBrowser().getTitle();
	}

	public String getCurrentURL() {
		return this.getBrowser().getCurrentUrl();
	}

	public void selectItemByLabel(String locator, String Label) {
		LOGGER.log(Status.INFO,"Select Item::" + Label + " On Element:: " + locator);
		Select select = new Select(this.getWebElement(locator));
		select.selectByVisibleText(Label);
		waitForPageLoad();
	}

	public void selectItemByValue(String locator, String value) {
		LOGGER.log(Status.INFO,"Select Item::" + value + " On Element:: " + locator);
		Select select = new Select(this.getWebElement(locator));
		select.selectByValue(value);
		waitForPageLoad();
	}

	public String getFirstSelectedOption(String locator) {
		LOGGER.log(Status.INFO,"Get First selected Item::" + " On Element:: " + locator);
		Select select = new Select(this.getWebElement(locator));
		return select.getFirstSelectedOption().getText();
	}

	public List<String> getAllOptions(String locator) {

		List<String> vals = new ArrayList<String>();
		Select select = new Select(this.getWebElement(locator));
		List<WebElement> options = select.getOptions();

		for (WebElement option : options) {
			vals.add(option.getText());
		}
		return vals;


	}

	private WebElement getWebElement(String locator) {

		WaitForElement wait = new WaitForElement(this.getBrowser());
		return wait.locateElement(locator);

	}

	private WebElement getWebElement(String locator, int timeout) {

		WaitForElement wait = new WaitForElement(this.getBrowser(), timeout);
		return wait.locateElement(locator);

	}


	private WebElement getWebElementWithNoDeplay(String locator) {

		WaitForElement noWait = new WaitForElement(this.getBrowser());
		return noWait.locateElementWithOutDelay(locator);
	}

	public WebDriver getBrowser() {
		return this.getDriver();

	}

	public boolean isEnabled(String locator) {
		return this.getWebElement(locator).isEnabled();
	}

	public boolean isEnabled(String locator, int timeout) {
		return this.getWebElement(locator, timeout).isEnabled();
	}

	public void sendEnterKey() {
		this.sendKeys(Keys.ENTER);
		waitForPageLoad();
	}

	public void sendKeys(Keys enter) {
		Actions act = new Actions(getBrowser());
		act.sendKeys(enter);
		waitForPageLoad();
	}

	public void sendEnterKeyWithNoWait() {
		Actions act = new Actions(getBrowser());
		act.sendKeys(Keys.ENTER);
	}

	public void enableJavaScript() {
		WebDriver _driver = this.getBrowser();
		if (_driver instanceof HtmlUnitDriver) {
			((HtmlUnitDriver) _driver).setJavascriptEnabled(true);
		}
	}

	public void disableJavaScript() {
		WebDriver _driver = this.getBrowser();
		if (_driver instanceof HtmlUnitDriver) {
			((HtmlUnitDriver) _driver).setJavascriptEnabled(false);
		}
	}

	public List<WebElement> getWebElementsById(String id) {
		WaitForElement wait = new WaitForElement(this.getBrowser());
		return wait.getWebElementsById(id);

	}

	public List<WebElement> getWebElementsByTagName(String parentLocator, String tag) {
		WaitForElement wait = new WaitForElement(this.getBrowser());
		return wait.getWebElementsByTagName(parentLocator, tag);

	}

	public List<WebElement> getWebElementsByXpath(String locator) {
		WaitForElement wait = new WaitForElement(this.getBrowser());
		return wait.getWebElementsByXpath(locator);

	}

	public void refresh() {
		getBrowser().navigate().refresh();
		waitForPageLoad();
	}

	public void getIsTextPresent(String text) {

		// IWebElement bodyElement =
		// this.getBrowser().FindElement(By.TagName("body"));
		// return bodyElement.Text.contains(text);
	}

	public void sendKeys(String locator, Keys enter) {
		getWebElement(locator).sendKeys(enter);
		waitForPageLoad();

	}

	public boolean isSelected(String locator) {
		return this.getWebElement(locator).isSelected();
	}

	public String getText(String locator) {
		return this.getWebElement(locator).getText();
	}

	public void maximizeBrowser() {
		try {
			this.getBrowser().manage().window().maximize();
		}catch(Exception e) {
			this.getBrowser().manage().window().setSize(new Dimension(1600,900));
		}
	}

	public void clear(String locator) {
		this.getWebElement(locator).clear();
	}

	public void closeBrowser() {
		this.getBrowser().close();
	}

	public void acceptAlert() {
		this.getBrowser().switchTo().alert().accept();
	}

	public void dismissAlert() {
		this.getBrowser().switchTo().alert().dismiss();
	}

	public Actions GetActionsObject() {
		return new Actions(this.getBrowser());
	}
	public void execJS(String Script,String Locator) {
		JavascriptExecutor js = (JavascriptExecutor) this.getBrowser();
		js.executeScript(Script, this.getElement(Locator));
	}

	public void moveToElement(WebElement we) {
		org.openqa.selenium.interactions.Actions actions = GetActionsObject();
		actions.moveToElement(we).click(we);
		waitForPageLoad();
	}

	public String execJS(String Script) {
		JavascriptExecutor js = (JavascriptExecutor) this.getBrowser();
		return String.valueOf(js.executeScript(Script));
	}

	public void moveToElement(String locator) {
		Actions actions = GetActionsObject();
		WebElement we = this.getElement(locator);
		actions.moveToElement(we).click(we);
		actions.perform();
	}

	public void moveToElementWithoutclick(String locator) {
		Actions actions = GetActionsObject();
		WebElement we = this.getElement(locator);
		actions.moveToElement(we);
		actions.perform();
	}

	public void rightClickOnElement(String locator) {
		Actions actions = GetActionsObject();
		WebElement we = this.getElement(locator);
		actions.contextClick(we);
		actions.perform();
	}

	public void rightClickOnElement() {
		Actions actions = GetActionsObject();
		actions.contextClick();
		actions.perform();
	}

	public void clickandHoldKey(String key,String locator) {
		Actions actions = GetActionsObject();
		WebElement we = this.getElement(locator);
		switch (key) {
		case "CONTROL":
			if (locator.isEmpty())
				actions.keyDown(Keys.CONTROL).build().perform();
			else
				actions.keyDown(Keys.CONTROL).click(we).build().perform();
			break;

		case "SHIFT":
			if (locator.isEmpty())
				actions.keyDown(Keys.SHIFT).build().perform();
			else
				actions.keyDown(Keys.SHIFT).click(we).build().perform();
			break;

		case "COMMAND":
			if (locator.isEmpty())
				actions.keyDown(Keys.COMMAND).build().perform();
			else
				actions.keyDown(Keys.COMMAND).click(we).build().perform();
			break;
		}
	}

	public void clickandReleaseKey(String key) {
		Actions actions = GetActionsObject();
		switch (key) {
		case "CONTROL":
			actions.keyUp(Keys.CONTROL).build().perform();
			break;

		case "SHIFT":
			actions.keyUp(Keys.SHIFT).build().perform();
			break;

		case "COMMAND":
			actions.keyUp(Keys.COMMAND).build().perform();
			break;
		}
	}

	public void zoomIn() {
		WebElement we = this.getElement("xpath=/html");
		we.sendKeys(Keys.chord(Keys.COMMAND, Keys.ADD));
		we.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
	}

	public void zoomOut() {
		WebElement we = this.getElement("xpath=/html");
		we.sendKeys(Keys.chord(Keys.COMMAND, Keys.SUBTRACT));
		we.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
	}

	public void dragAndDropBy(String locator, int xOffset, int yOffset) {
		Actions actions = this.GetActionsObject();
		WebElement we = this.getElement(locator);
		actions.dragAndDropBy(we, xOffset, yOffset).build().perform();
	}

	public void sendDownKey(String locator) {
		WebElement we = this.getElement(locator);
		try {
			we.sendKeys(Keys.PAGE_DOWN);
			waitForPageLoad();
		}catch (Exception e) {
			Actions actions = GetActionsObject();
			actions.moveToElement(we);
			actions.click();
			actions.sendKeys(Keys.PAGE_DOWN);
			actions.build().perform();
			waitForPageLoad();
		}
	}

	public void sendUpKey(String locator) {
		WebElement we = this.getElement(locator);
		we.sendKeys(Keys.PAGE_UP);
		waitForPageLoad();
	}
	public void arrowRight(String locator) {
		WebElement we = this.getElement(locator);
		we.sendKeys(Keys.ARROW_RIGHT);
		waitForPageLoad();
	}
	public void clickKey(String locator,String key) {
		WebElement we = this.getElement(locator);
		switch (key) {
		case "TAB":
			we.sendKeys(Keys.TAB);
		}

		waitForPageLoad();
	}

	public String getCssvalue(String locator, String value) {
		WebElement we = this.getElement(locator);
		return we.getCssValue(value);
	}

	public void waitForPageLoad(String clickableelement) {

		WebElement element = getWebElement(clickableelement);
		WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	public void mouseOver(String locator) {

		LOGGER.log(Status.INFO,"MouseOver :: " + locator);

		WebElement element = getWebElementWithNoDeplay(locator);
		Actions act = new Actions(this.getBrowser());
		act.moveToElement(element).build().perform();
		waitForPageLoad();
	}

	public void waitForPageLoad() {

		long start = System.currentTimeMillis();
		long finish;

		try {
			final ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			final WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
			final boolean IsPageLoad = wait.until(pageLoadCondition);

			if (!IsPageLoad) {
				LOGGER.log(Status.FAIL,"Page doesn't load after " + MAX_WAIT_FOR_PAGE + " seconds");
			}
			finish = System.currentTimeMillis();
			double loading_time = (((double) finish - (double) start) / 1000);
			LOGGER.log(Status.INFO,"Page Loaded in " + loading_time + " seconds");

		} catch (TimeoutException te) {
			LOGGER.log(Status.FAIL,"Page Not loaded in " + MAX_WAIT_FOR_PAGE + " seconds with below Exception:");
			LOGGER.log(Status.FAIL,te.toString());

		}
	}
	public void waitForPageToStartLoad() {

		long start = System.currentTimeMillis();
		long finish;

		try {
			final ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loading");
				}
			};
			final WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
			final boolean IsPageLoad = wait.until(pageLoadCondition);

			if (!IsPageLoad) {
				LOGGER.log(Status.FAIL,"Page doesn't load after " + "30" + " seconds");
			}
			finish = System.currentTimeMillis();
			double loading_time = (((double) finish - (double) start) / 1000);
			LOGGER.log(Status.INFO,"Page Loaded in " + loading_time + " seconds");

		} catch (TimeoutException te) {
			LOGGER.log(Status.FAIL,"Page Not loaded in " + "30" + " seconds with below Exception:");
			LOGGER.log(Status.FAIL,te.toString());

		}
	}

	public void waitForElementToDisAppear(String locator) {

		long start = System.currentTimeMillis();
		long finish = start;

		try {
			WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
			//    wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator.substring(6)))));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator.substring(6))));
			finish = System.currentTimeMillis();
			double loading_time = (((double) finish - (double) start) / 1000);
			LOGGER.log(Status.INFO,"Element Disappear in" + loading_time + " seconds");
		} catch (TimeoutException te) {
			LOGGER.log(Status.FAIL,"Page Not loaded in " + MAX_WAIT_FOR_PAGE + " seconds with below Exception:");
			LOGGER.log(Status.FAIL,te.toString());

		}
	}



	public void clickInFrame(String locatorFrame, String elementLocator) {
		final WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		WebElement element = wait.until(elementToBeClickableInFrame(locatorFrame,elementLocator));
		if (element==null) {
			LOGGER.log(Status.FAIL,"Unable to Switched to frame :: " + locatorFrame + " and click on element :: " + elementLocator + " Please verify loactors.");
			deSelectFrame();
		} else {
			LOGGER.log(Status.INFO,"Switched to frame :: " + locatorFrame + " and clicked on element :: " +   elementLocator);
			element.click();
			deSelectFrame();
			waitForPageLoad();
		}
	}

	/*
	 * Method added to handle frames
	 */
	public void waitForElementToBeVisibleInFrame(Object locatorFrame, String elementLocator) {
		for (int i=0;i<3;i++) {
			boolean element = elementToBeVisibleInFrame(locatorFrame,elementLocator);
			if (element==false) {
				LOGGER.log(Status.INFO,"Unable to Switched to frame :: " + locatorFrame + " and find the element :: " + elementLocator + " Please verify loactors.");
				UTILS.pause(1);
			} else {
				LOGGER.log(Status.INFO,"Switched to frame :: " + locatorFrame + " and found the element :: " +   elementLocator);
				break;
			}
		}
		deSelectFrame();
		waitForPageLoad();
	}

	private boolean elementToBeVisibleInFrame(Object index, String locator) {
		try {
			deSelectFrame();
			if (index instanceof String) {
				String frameId = (String) index;
				selectFrame(frameId);
			}
			else {
				int frameIndex = (Integer) index;
				selectFrame(frameIndex);
			}
			WebElement elem = getWebElement(locator);
			return elem.isDisplayed() && elem.isEnabled() ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	public void waitForElementToBeClickableInFrame(String locatorFrame, String elementLocator) {
		final WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		WebElement element = wait.until(elementToBeClickableInFrame(locatorFrame,elementLocator));
		if (element==null) {
			LOGGER.log(Status.FAIL,"Unable to Switched to frame :: " + locatorFrame + " and click on element :: " + elementLocator + " Please verify loactors.");
		} else {
			LOGGER.log(Status.INFO,"Switched to frame :: " + locatorFrame + " and clicked on element :: " +   elementLocator);
		}
		deSelectFrame();
		waitForPageLoad();
	}
	public void waitForElementToBeClickableInSecondFrame(String locatorFrame, String elementLocator) {
		final WebDriverWait wait = new WebDriverWait(this.getBrowser(),Duration.ofSeconds(MAX_WAIT_FOR_PAGE));
		WebElement element = wait.until(elementToBeClickableInSecondFrame(locatorFrame,elementLocator));
		if (element==null) {
			LOGGER.log(Status.FAIL,"Unable to Switched to frame :: " + locatorFrame + " and click on element :: " + elementLocator + " Please verify loactors.");
		} else {
			LOGGER.log(Status.INFO,"Switched to frame :: " + locatorFrame + " and clicked on element :: " +   elementLocator);
		}
		deSelectFrame();
		waitForPageLoad();
	}

	private ExpectedCondition<WebElement> elementToBeClickableInFrame(final String locatorFrame, final String locator) {
		return new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				try {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(locatorFrame);
					WebElement elem = getWebElement(locator);
					return elem.isDisplayed() && elem.isEnabled() ? elem : null;
				} catch (Exception e) {
					return null;
				}
			}

		};
	}
	private ExpectedCondition<WebElement> elementToBeClickableInSecondFrame(final String locatorFrame, final String locator) {
		return new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				try {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(0);
					driver.switchTo().frame(locatorFrame);
					WebElement elem = getWebElement(locator);
					return elem.isDisplayed() && elem.isEnabled() ? elem : null;
				} catch (Exception e) {
					System.out.println("Exception caught for deadobject issue::" + e);
					return null;
				}
			}

		};
	}
	public void openNewTab() {
		JavascriptExecutor jse = (JavascriptExecutor)this.getBrowser();
		jse.executeScript("window.open()");
	}

}