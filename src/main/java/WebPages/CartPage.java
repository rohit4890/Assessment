package WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{

String product;
	
	private By products = By.xpath("//td[1]//img//parent::td");
	private By productPrice = By.xpath("//td[1]//img//parent::td[text()=\' "+product+"\']//parent::tr//td[2]");
	private By totalProductPrice = By.xpath("//td[1]//img//parent::td[text()=\' "+product+"\']//parent::tr//td[4]");
	private By total = By.xpath("//strong[contains(text(),'Total')]");
	
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
		
	List<WebElement> getAllProduct() {
		return waitForVisibilityList(products);
	}
	
	WebElement getPriceField(String productName) {
		product = productName;
		productPrice = By.xpath("//td[1]//img//parent::td[text()=\' "+product+"\']//parent::tr//td[2]");
		return waitForVisibility(productPrice);
	}
	
	WebElement getTotalProductPriceField(String productName) {
		product = productName;
		totalProductPrice = By.xpath("//td[1]//img//parent::td[text()=\' "+product+"\']//parent::tr//td[4]");
		return waitForVisibility(totalProductPrice);
	}
	
	WebElement getTotalFieldOnCart() {
		return waitForVisibility(total);
	}

	public boolean isProductAdded(String productName) {
		
		boolean isProductAdded = false;
		List<WebElement> list = getAllProduct();
		for (WebElement element : list) {
		    if(element.getText().equalsIgnoreCase(productName)) {
		    	isProductAdded = true;
		    	break;
		    }
		}
		return isProductAdded;
	}
	
	public double getSingleProductPrice(String productName) {
		
		String amount = getPriceField(productName).getText();
		int index = amount.indexOf("$")+1;
		String amount1 = amount.substring(index);
		double price = Double.parseDouble(amount1);
		return price;
	}
	
	public double getTotalProductAmount(String productName) {
		
		String amount = getTotalProductPriceField(productName).getText();
		int index = amount.indexOf("$")+1;
		String amount1 = amount.substring(index);
		double price = Double.parseDouble(amount1);
		return price;
	}
	
	public double getTotalFromCartPage() {
		String amount = getTotalFieldOnCart().getText();
		int index = amount.indexOf(" ")+1;
		String amount1 = amount.substring(index);
		double price = Double.parseDouble(amount1);
		return price;
	}
}
