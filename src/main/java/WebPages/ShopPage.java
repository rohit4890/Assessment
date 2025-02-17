package WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPage extends BasePage{

	int count = 0;
	int productQty = 0;
	double amount = 0.0;
	private By products = By.xpath("//h4");
	private By productPrice = null;
	private By buyButton = null;
	

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	public void buyProduct(String name, int qty) {
		productQty = qty;
		List<WebElement> elements = waitForVisibilityList(products);
		for (WebElement webElement : elements) {
			count++;
			if(webElement.getText().equalsIgnoreCase(name)) {
				productPrice = By.xpath("(//span[@class='product-price ng-binding'])["+count+"]");
				buyButton = By.xpath("(//a[@class='btn btn-success'])["+count+"]");
				
				// Get Price
				String ProductPrice = waitForVisibility(productPrice).getText();
				int index = ProductPrice.indexOf("$")+1;
				String price = ProductPrice.substring(index);
				amount = Double.parseDouble(price);

				
				for(int i =1; i<=qty; i++) {
					// Click on buy button
					waitForClickable(buyButton).click();
				}
			}
		}
		count = 0;
	}
	
	public double getProductPrice() {
		return amount;
	}
	
	public double getTotalPriceForEachProduct() {
		return amount * productQty;
	}
}
