package tests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import WebDrivers.DriverFactory;
import WebPages.BasePage;
import WebPages.ParentPage;
import listners.TestListner;
import utility.PropertiesReader;


public class BaseTest extends TestListner{
	
	public PropertiesReader propReader;
	public WebDriver driver;
	public ParentPage parentPage;
	public SoftAssert softAssert = new SoftAssert();
	
	@DataProvider (name = "data-provider")
	public Object[][] getDataFromDataprovider(){
	    return new Object[][] 
	    	{
	            { "rohit1", "rohit1@test.com", "message1"},
	            { "rohit2", "rohit2@test.com", "message2"},
	            { "rohit3", "rohit3@test.com", "message3"},
	            { "rohit4", "rohit4@test.com", "message4"},
	            { "rohit5", "rohit5@test.com", "message5"}
	        };
	}

	@BeforeMethod
	public void setUp() {
		propReader = new PropertiesReader();
		DriverFactory.initializeWebDriver(propReader.getDataFromPropertiesFile("browserName"));
		driver = DriverFactory.getDriver();
		driver.get(propReader.getDataFromPropertiesFile("appUrl"));
		parentPage = new BasePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
		//DriverFactory.removeDriver();
		
	}

}
