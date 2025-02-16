package WebPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ParentPage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	/*
	 * Constructor of ParentPage
	 * @param: WebDriver Object
	 * */
	public ParentPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	/*
	 * Abstract generic methods to find elements
	 * @param: Page Locator 
	 * */
	public abstract String getPageTitle();
	public abstract boolean isElementDisplayedOnPage(By locator);
	public abstract String getCurrentURL();
	
	public abstract WebElement waitForVisibility(By locator);
	public abstract WebElement waitForClickable(By locator);
	public abstract WebElement safeFindElement(By locator);
	public abstract List<WebElement> waitForVisibilityList(By locator);
	
	
	/*
	 * Generic method that gives object of any provided page class
	 * @param: anyClass name with ".". E.g: HomePage.class
	 * */
	public <Tpage extends BasePage>Tpage getInstanceOf(Class<Tpage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
