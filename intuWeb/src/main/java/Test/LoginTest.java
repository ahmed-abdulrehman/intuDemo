package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import Config.Utility;
import PageFactory.CustomerLoginPage;


public class LoginTest extends Setup {
	
	Utility utility;
	CustomerLoginPage customerLoginPage;

	
	@Test
	public void loginWithVaildDetails() throws InterruptedException, IOException {
		utility = new Utility(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		
		utility.testName("Login with valid details");
		utility.logInfoStatus("Browser Started");
		
		customerLoginPage.login("emailAddress", "password");
		customerLoginPage.assertPageTitle();
		
		utility.logPassStatus("Successfully logged in");
	}
	
	@Test
	public void loginWithInvaildDetails() throws InterruptedException, IOException {
		utility = new Utility(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		
		utility.testName("Login with invalid details");
		utility.logInfoStatus("Browser Started");
		
		customerLoginPage.login("emailAddress", "invalidPassword");
		customerLoginPage.assertInvalidMessage();
		
		utility.logPassStatus("Invalid message displayed successfully");
	}

}
