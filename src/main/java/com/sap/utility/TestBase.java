package com.sap.utility;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.sap.reports.TestListener.class)
public class TestBase {

	public static BrowserUtility oBrowserUtil = new BrowserUtility();
	public static CommonUtility oCommon = new CommonUtility();
	public static Constants oConstant = new Constants();
	public static String sHost = null;
	public static RemoteWebDriver driver;
	public static String sClassNameForScreenShot;

//	public static WebDriver driver;
	
	
	@BeforeSuite
	public void triggerDependency() throws Exception {
		
		oCommon.loadConfigProperty(System.getProperty("user.dir")+"/src/main/java/com/sap/properties/config.properties");
		
		if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
			oBrowserUtil.launchBrowser(System.getProperty("browser"));
			System.out.println("Automation running on: "+System.getProperty("AutomationRunning"));
			System.out.println("Environment is: "+System.getProperty("Environment"));
		}
		if (System.getProperty("AutomationRunning").equalsIgnoreCase("API")) {
			sHost = System.getProperty("stageHost");
		} 
	}
	
	@AfterSuite
	public void closeBrowser() {
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
			driver.quit();
			System.out.println("Browser closed");
	}
	}
}
