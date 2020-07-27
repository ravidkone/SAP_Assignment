package com.sap.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sap.pages.SignUpPage;
import com.sap.utility.Constants;
import com.sap.utility.TestBase;

public class SignUpTest extends TestBase {
	SignUpPage sp;

	@BeforeMethod
	public void setUp() {
		sp = new SignUpPage(driver);
		sClassNameForScreenShot = getClass().getSimpleName();

	}

	@Test(priority = 1)
	public void verifyTitle() {
		AssertJUnit.assertEquals(Constants.pageTitle, sp.getTitle());
		System.out.println("title matched");
	}

	@Test(priority = 2)
	public void cookies() {
		sp.acceptCookies();
		System.out.println("cookies accepted");
	}

	@Test(priority = 3)
	public void clikcSignUpBtn() throws InterruptedException {
		sp.clickSignUp();
		System.out.println("clicked on Sign Up button");
		driver.switchTo().frame(0);
	}

	@Test(priority = 4)
	public void verifyRegisterPopUp() {
		sp.verifyRegisterPopUp();
	}

	@DataProvider
	public Object[][] retriveData() {
		Object data[][] = oCommon.getExceldata("UserData");
		return data;
	}

	@Test(priority = 5, dataProvider = "retriveData")
	public void EnterUserDetails(String firstName, String LastName, String Email, String password,
			String reEnterPasswprd) throws InterruptedException {
		sp.fillDetails(firstName, LastName, Email, password, reEnterPasswprd);
		sp.submit();
	}

	@Test(priority = 6)
	public void verifySuccessMsg() throws InterruptedException {
		AssertJUnit.assertEquals(Constants.successMsg, sp.getSucessMsg());
		sp.closePopUp();
	}

}
