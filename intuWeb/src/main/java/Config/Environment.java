package Config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Test.Setup;


public class Environment extends Setup {

//public  WebDriver driver;
	
	public Environment(WebDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * From the environment properties file the user can control which
	 * environment they wish to test.
	 */
	public void theEnvironment() throws Exception {
		Properties environmentProperties = new Properties();
		FileInputStream environmentFileInput = new FileInputStream("..\\intuWeb\\Config\\TestEnvironment\\environment.properties");
		environmentProperties.load(environmentFileInput);
		driver.get(environmentProperties.getProperty("environment"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}