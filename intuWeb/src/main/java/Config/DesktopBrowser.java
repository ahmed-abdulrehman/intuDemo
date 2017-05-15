package Config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import Config.Environment;
import Test.Setup;

public class DesktopBrowser extends Setup {
	
	static Environment environment;
	
	public DesktopBrowser(WebDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// This method is used to run on the Chrome browser for both Windows & Mac.
	public void startBrowser() throws Exception {

		Properties chromeProperties = new Properties();
		FileInputStream chromeFileInput = new FileInputStream(
				"..\\intuWeb\\Config\\Browser\\Chrome\\chromeDriver.properties");
		chromeProperties.load(chromeFileInput);

		// This block of code allows us to run the test on WINDOWS Chrome
		// browser
		if (chromeProperties.getProperty("operatingSystem").contains("windows")) {
			System.setProperty("webdriver.chrome.driver", "..\\intuWeb\\Config\\Browser\\Chrome\\chromedriver.exe");
						
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		// This block of code allows us to run the test on MAC Chrome browser
		else {
			System.setProperty("webdriver.chrome.driver", "..\\intuWeb\\Config\\Browser\\Chrome\\chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		environment = new Environment(driver);
		environment.theEnvironment();
	}
	

}