package WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends ParentPage{

	
	public BasePage(WebDriver driver) {
		super(driver);
	}

	// To get page title
	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	// To get current page URL
	@Override
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	// To check if element is displayed
	@Override
	public boolean isElementDisplayedOnPage(By locator) {
		if (driver.findElements(locator).size() != 0) {
			return true;
		}else{
			return false;
		}
	}

	// Wait until element is visible
	@Override
	public WebElement waitForVisibility(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e){
			System.out.print("Element not found: " +locator);
			return null;
		}
	}

	// Wait for an element to be clickable
	@Override
	public WebElement waitForClickable(By locator) {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e){
			System.out.print("Element not clickable: " +locator);
			return null;
		}
	}

	// Handle StaleElementReferenceException
	@Override
	public WebElement safeFindElement(By locator) {
		try {
			return driver.findElement(locator);
		} catch (StaleElementReferenceException e){
			System.out.print("Stale Element Exception: " +locator);
			return driver.findElement(locator);
		}
	}

	@Override
	public List<WebElement> waitForVisibilityList(By locator) {
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e){
			System.out.print("Element not found: " +locator);
			return null;
		}
	}

}
