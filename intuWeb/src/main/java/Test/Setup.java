package Test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Config.DesktopBrowser;
import Config.MobileBrowser;
import Config.Utility;


public class Setup {

	public static WebDriver driver;
	
	DesktopBrowser desktopBrowser;
	MobileBrowser mobileBrowser ;
	Utility utility;


	@BeforeTest
	public void startingReport() throws Exception {
		utility = new Utility(driver);
		utility.startReport();
	}

	@BeforeMethod
	public void browser() throws Exception {
		desktopBrowser = new DesktopBrowser(driver);
		mobileBrowser = new MobileBrowser(driver);
		
		desktopBrowser.startBrowser();
		//mobileBrowser.startBrowser();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		utility = new Utility(driver);
		utility.logFailStatus(result, "Fail");

		driver.quit();
	}

	@AfterTest
	public void endingReport() {
		utility = new Utility(driver);
		utility.endReport();
	}
}