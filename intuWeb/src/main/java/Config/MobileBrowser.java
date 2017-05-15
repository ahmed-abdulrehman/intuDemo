package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

import Config.Environment;
import Test.Setup;


public class MobileBrowser extends Setup {
	
	static Environment environment;
	//public WebDriver driver;
	
	Utility utility;
	DesiredCapabilities capabilities;
	
	private String deviceFileInputPath = "..//intuWeb//Config//DeviceSettings//device.properties";
	
	public MobileBrowser(WebDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Getting the tested Device values which can be found in the config folder
	private void gettingValues(String value, String fileInputPath) throws IOException {
		utility = new Utility(driver);
		Properties settingProperty = new Properties();
		FileInputStream fileInput = new FileInputStream(fileInputPath);
		settingProperty.load(fileInput);		
		capabilities.setCapability(value, settingProperty.getProperty(value));
	}
	
	// Getting the parameter for the tested android device
	private void getDevice(String deviceValue) throws IOException {
		gettingValues(deviceValue, deviceFileInputPath);
	}
	
	// Open chrome browser on android device
	public void startBrowser() throws Exception {	
		utility = new Utility(driver);
		capabilities = new DesiredCapabilities();

		getDevice("platformName");
		getDevice("deviceName");
		getDevice("platformVersion");
		getDevice("browserName");
		getDevice("device");

		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		environment = new Environment(driver);
		environment.theEnvironment();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}