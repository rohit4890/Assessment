package WebDrivers;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	
	/*
	 * This method is used to initialize the thread local driver on the basis of given browser
	 * @param browser
	 * @return This will return thread safe driver
	 */
	public static WebDriver initializeWebDriver(String browserName) {
		
		String browser = browserName.toLowerCase();
		switch (browser) {
		
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("-headed");
				threadLocalDriver.set(new ChromeDriver(chromeOptions));
				break;
			
			case "safari":
				WebDriverManager.safaridriver().setup();
				threadLocalDriver.set(new SafariDriver());
				break;
		
			case "edge":
				WebDriverManager.edgedriver().setup();
				threadLocalDriver.set(new EdgeDriver());
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				threadLocalDriver.set(new FirefoxDriver());
				break;
			
			default:
				System.out.println("You have provided wrong browser name: "+browserName+"  which is incorrect. Please provide valid browser name.");
				break;
		}
		threadLocalDriver.get().manage().deleteAllCookies();
		threadLocalDriver.get().manage().window().maximize();
		threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return threadLocalDriver.get();
	}
	
	// Return thread safe driver
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	public static void removeDriver() {
		threadLocalDriver.remove();
	}
}
