package PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Config.Utility;

public class CustomerLoginPage {

	public WebDriver driver;
	Utility utility;

	// Email address field element
	@FindBy(css = "#email")
	private WebElement emailAddressField;

	// Password field element
	@FindBy(css = "#pass")
	private WebElement passwordField;

	// Login button element
	@FindBy(css = "#send2")
	private WebElement loginButton;

	// Forgot Your password link element
	@FindBy(className = "button intu clear")
	private WebElement forgotPasswordLink;

	// Invalid message element
	@FindBy(className = "alert-box__message")
	private WebElement invalidMessage;
	
	public CustomerLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Set input
	private void setInput(String input, WebElement element) throws IOException {
		utility = new Utility(driver);
		
		Properties inputProperty = new Properties();
		FileInputStream fileInput = new FileInputStream("..//intuWeb//Config//Login//loginDetails.properties");
		inputProperty.load(fileInput);
				
		element.clear();
		element.sendKeys(inputProperty.getProperty(input));
		
		utility.logInfoStatus("Adding "+input);
	}
	
	// This method is used for clicking
	private void clicking(WebElement element, String logInfo) throws IOException, InterruptedException {
		utility = new Utility(driver);
		
		element.click();
		Thread.sleep(3000);
		
		utility.logInfoStatus("Clicked "+logInfo);	
	}
	
	// Assert page title
	public void assertPageTitle() throws InterruptedException
	{
		Thread.sleep(2000);
		Assert.assertEquals("My Account - intu.co.uk", driver.getTitle());
	}
	
	// Assert invalid message
	public void assertInvalidMessage() throws InterruptedException
	{
		Thread.sleep(2000);
		Assert.assertEquals("Invalid login or password.", invalidMessage.getText());
	}
	
	// Login 
	public void login(String theEmailAddress, String thePassword) throws IOException, InterruptedException
	{
		setInput(theEmailAddress, emailAddressField);
		setInput(thePassword, passwordField);
		clicking(loginButton, "Login button");
	}

}
