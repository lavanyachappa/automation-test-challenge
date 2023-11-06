package org.assessment.demo.seleniumframework.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.assessment.demo.utils.ExtentTestManager;
import org.assessment.demo.utils.IConf;
import org.assessment.demo.utils.IThContext;
import org.assessment.demo.utils.Utils;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Browser {

	public final static String FIREFOX = "firefox";
	public final static String CHROME = "chrome";
	public final static String HEADLESS = "headless";

	private static final int MAX_WAIT_FOR_PAGE = 180;
	private static final int MAX_IDLETIMEOUT = 1200;
	private static ThreadLocal<WebDriver> browserLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> platformTypeLocal = new ThreadLocal<String>();
	private static ThreadLocal<String> customProfileLocal = new ThreadLocal<String>();

	public Utils UTILS = null;
	public ExtentTest LOGGER = null;
	public IConf CONF = null;

	enum supporttedBrowsers {
		WINDOWS_CHROME, WINDOWS_FIREFOX, LINUX_CHROME, LINUX_FIREFOX
	}

	public Browser(IThContext testDataObj) {

		CONF = testDataObj.getConf();
		LOGGER = ExtentTestManager.getTest();
		UTILS = testDataObj.getUtilityLayer();

	}

	public void createBrowser(Map<String, String> config) {
		try {
			_createBrowser(config);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void _createBrowser(Map<String, String> cfg) {

		WebDriver browser = null;
		if (getDriver() == null) {

			if (Boolean.valueOf(System.getProperty("grid"))) {
				browser = createRemoteWebDriver(cfg);
				((RemoteWebDriver) browser).setFileDetector(new LocalFileDetector());
			} else {
				browser = createLocalWebDriver(cfg);
			}
		}
		browser.manage().window().maximize();
		browser.manage().timeouts().pageLoadTimeout(MAX_WAIT_FOR_PAGE, TimeUnit.SECONDS);

		browserLocal.set(browser);
		platformTypeLocal.set(cfg.get("platform"));
		customProfileLocal.set(cfg.get("customProfile"));
	}

	private WebDriver createLocalWebDriver(Map<String, String> config) {

		WebDriver browser = null;
		String platform = config.get("platform");
		String browserName = config.get("browserName");
		String os = System.getProperty("os.name");
		System.out.println("OperatingSystem used is::" + os);

		try {
			if (CHROME.equalsIgnoreCase(config.get("browserName"))) {
				
				if (os.equalsIgnoreCase("Mac OS X") || os.contains("Mac"))
				 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +  File.separator + "driver" +  File.separator + "chromedriver");
				else
					 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +  File.separator + "driver" +  File.separator + "chromedriver.exe");
				browser = new ChromeDriver();

			} else if (FIREFOX.equalsIgnoreCase(config.get("browserName"))) {
				FirefoxOptions options = new FirefoxOptions();
				options.setCapability("browserName", browserName);
				options.setCapability("idleTimeout", MAX_IDLETIMEOUT);
				options.setCapability("platform", platform);
				browser = new FirefoxDriver();

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(this.getClass().getName() + " -- Cannot create local browser");
		}

		return browser;

	}

	private WebDriver createRemoteWebDriver(Map<String, String> config) {

		WebDriver browser = null;

		try {
			if (CHROME.equalsIgnoreCase(config.get("browserName"))) {
				ChromeOptions options = new ChromeOptions();
				if (config.get("HEADLESS") == "true") {
					options.addArguments("headless");
				}
				browser = new RemoteWebDriver(new URL(CONF.get("HUB_URL")), options);

			}
			if (FIREFOX.equalsIgnoreCase(config.get("browserName"))) {
				FirefoxOptions options = new FirefoxOptions();
				if (config.get("HEADLESS") == "true") {
					options.addArguments("headless");
				}
				browser = new RemoteWebDriver(new URL(CONF.get("HUB_URL")), options);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(this.getClass().getName() + " -- Cannot create remote browser");
		}
		return browser;
	}

	protected WebDriver getDriver() {

		return (WebDriver) browserLocal.get();
	}

	public String getPlatformType() {
		return (String) platformTypeLocal.get();
	}

	public String getCustomProfileLocal() {
		return (String) customProfileLocal.get();
	}

	public void cleanUp() {
		this.tearDown();
		browserLocal.remove();
		LOGGER.log(Status.INFO, "Cleanup successfull at end of test");
	}

	public void tearDown() {
		try {
			this.getDriver().quit();

		} catch (Exception e) {
			LOGGER.log(Status.FAIL, " failed with exception::" + e);
		}
	}

	public String takeScreenShotFoExtentReports() {
		if (browserLocal.get() != null)

			return "data:image/png;base64,"
					+ ((TakesScreenshot) (browserLocal.get())).getScreenshotAs(OutputType.BASE64);
		return null;
	}

	public void takeScreenShot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) getDriver();

		if (ts == null) {
			LOGGER.log(Status.INFO, "COULD NOT TAKE SCREENSHOT !!!");
			return;
		}
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
