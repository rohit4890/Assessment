package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	
	private By welcomeMsg = By.xpath("//p[contains(text(),'Welcome to Jupiter')]");
	private By contactTab = By.cssSelector("li#nav-contact>a[href$='contact']");
	private By shopTab = By.cssSelector("li#nav-shop>a[href$='shop']");
	private By cartTab = By.cssSelector("li#nav-cart>a[href$='cart']");
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// Click on contact tab
	public void clickContactTab() {
		WebElement contactTabElement = waitForClickable(contactTab);
		if (contactTabElement != null) {
			contactTabElement.click();
		}
	}
	
	// Click on contact tab
	public void clickShopTab() {
		WebElement shopTabElement = waitForClickable(shopTab);
		if (shopTabElement != null) {
			shopTabElement.click();
		}
	}
		
	// Click on contact tab
	public void clickCartTab() {
		WebElement cartTabElement = waitForClickable(cartTab);
		if (cartTabElement != null) {
			cartTabElement.click();
		}
	}
	
	// Check for home page open
	public boolean isHomePageOpened() {
		WebElement welcomeElement = waitForVisibility(welcomeMsg);
		String msg = welcomeElement.getText();
		 if (msg.equalsIgnoreCase("Welcome to Jupiter Toys, a magical world for good girls and boys."))
		 {
			 return true;
		 }else {
			 return false;
		 } 
	}
}
