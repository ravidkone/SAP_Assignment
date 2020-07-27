package com.sap.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sap.utility.TestBase;

public class SignUpPage extends TestBase{
	
	@FindBy(xpath = "//div[text()='OK']")
	WebElement cookies;
	@FindBy(xpath = "//div[text()='Sign up']")
	WebElement signUpBtn;
	@FindBy(tagName = "iframe")
	WebElement frame;
	@FindBy(xpath = "//h1[text()='Registration']")
	WebElement regPopUp;
	@FindBy(name = "firstName")
	WebElement enterFirstName;
	@FindBy(id = "lastName")
	WebElement enterLastName;
	@FindBy(id = "mail")
	WebElement enterEmail;
	@FindBy(id = "newPasswordInput")
	WebElement enterPassword;
	@FindBy(id = "retypeNewPasswordInput")
	WebElement re_EnterPassword;
	@FindBy(id = "pdAccept")
	WebElement privacyStmt;
	@FindBy(id = "touAccept")
	WebElement termsCondition;
	@FindBy(id = "sapStoreRegisterFormSubmit")
	WebElement submitBtn;
	@FindBy(className = "ids-heading-1")
	WebElement successMsg;
	@FindBy(xpath = "//a[@title='Close']")
	WebElement popUpclose;
	
	public SignUpPage(WebDriver driver) {
		PageFactory.initElements(TestBase.driver, this);
	}
	
	public void acceptCookies() {
		cookies.click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

	public void clickSignUp() throws InterruptedException {
		signUpBtn.click();
		oBrowserUtil.waitForElementVisible(driver, frame, 2);
	}
	
	public void verifyRegisterPopUp() {
			String text =regPopUp.getText();
			System.out.println("frame text is: "+text);
	}
	
	public void fillDetails(String firstName, String LastName, String Email, String password, String reEnterPasswprd) throws InterruptedException {
		enterFirstName.sendKeys(firstName);
		enterLastName.sendKeys(LastName);
		enterEmail.sendKeys(Email);
		enterPassword.sendKeys(password);
		re_EnterPassword.sendKeys(reEnterPasswprd);
		privacyStmt.click();
		termsCondition.click();
	}
	public void submit() {
		submitBtn.click();
	}
	public String getSucessMsg() throws InterruptedException {
		oBrowserUtil.waitForElementVisible(driver, successMsg, 3);
		return successMsg.getText();
	}
	public void closePopUp() {
		popUpclose.click();
	}
}
