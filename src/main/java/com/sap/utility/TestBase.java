package com.sap.utility;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

//@Listeners(com.sap.reports.TestListener.class)
public class TestBase {

	public static BrowserUtility oBrowserUtil = new BrowserUtility();
	public static CommonUtility oCommon = new CommonUtility();
	public static Constants oConstant = new Constants();
	public static String sHost = null;
	public static RemoteWebDriver driver;
	public static String sClassNameForScreenShot;

    public static ExtentTest extLogger;
//    public static ExtentTest t1;
//	public static WebDriver driver;
	
	
	@BeforeClass
	@Parameters("browser")
	public void triggerDependency(String browser) throws Exception {
		System.out.println("Test running on browser: "+browser);
		oCommon.loadConfigProperty(System.getProperty("user.dir")+"/src/main/java/com/sap/properties/config.properties");
		
		if (System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
		//	oBrowserUtil.launchBrowser(System.getProperty("browser"));
			oBrowserUtil.launchBrowser(browser);

			System.out.println("Automation running on: "+System.getProperty("AutomationRunning"));
			System.out.println("Environment is: "+System.getProperty("Environment"));
		}
		if (System.getProperty("AutomationRunning").equalsIgnoreCase("API")) {
			sHost = System.getProperty("stageHost");
		} 
	}
	
	@AfterClass
	public void closeBrowser() {
		if(System.getProperty("AutomationRunning").equalsIgnoreCase(Constants.AutomationWeb)) {
			driver.quit();
			System.out.println("Browser closed");
	}
	}
}
