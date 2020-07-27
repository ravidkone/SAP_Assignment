package com.sap.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility extends TestBase {

	DesiredCapabilities dc;

	@BeforeClass
	@Parameters("browser")
	public void launchBrowser(String browser) throws MalformedURLException {
		dc = new DesiredCapabilities();

		if (browser.equalsIgnoreCase(System.getProperty("browser"))) {
			WebDriverManager.chromedriver().setup();
		dc.setCapability(CapabilityType.BROWSER_NAME,BrowserType.CHROME);
		dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);

		} else if (browser.equalsIgnoreCase(System.getProperty("browser"))) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		}

		URL url=new URL(System.getProperty("Remote_WebDriver_IP"));
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resource");
		driver=new RemoteWebDriver(url,dc);
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public boolean isDisplayed(WebElement ele) {
		boolean bRes_flag = false;
		try {
			if (ele.isDisplayed()) {
				bRes_flag = true;
			}
		} catch (Exception e) {
		}

		return bRes_flag;
	}

	public boolean waitForElementVisible(WebDriver driver, WebElement ele, int iTimeInSeconds)
			throws InterruptedException {
		boolean bRes_flag = false;
		for (int i = 0; i < iTimeInSeconds; i++) {
			if (!isDisplayed(ele))
				Thread.sleep(1000);
			else
				break;
		}

		return bRes_flag;

	}

}
