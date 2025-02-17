package WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{
	
	int counter = 0;
	private By productName =  null;
	private By productPrice = null;
	private By productQty =   null;
    private By productTotal = null;
    private By cartTotal = By.className("total");
    
   	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isElementDisplayedOnPage(String name) {
		counter++;
		productName = By.xpath("(//td//img)["+counter+"]");
		return isElementDisplayedOnPage(productName);
	}
	
	public double getSinglePrice() {
		productPrice = By.xpath("(//td//img)["+counter+"]"+"//parent::td//parent::tr//td[2]");
		String ProductPrice = waitForVisibility(productPrice).getText();
		int index = ProductPrice.indexOf("$")+1;
		String price = ProductPrice.substring(index);
		return Double.parseDouble(price);
	}
	
	public double getTotalProductPrice() {
		productTotal = By.xpath("(//td//img)["+counter+"]"+"//parent::td//parent::tr//td[4]");
		String ProductPrice = waitForVisibility(productTotal).getText();
		int index = ProductPrice.indexOf("$")+1;
		String price = ProductPrice.substring(index);
		return Double.parseDouble(price);
	}
	
	public double getCartTotal() {
		
		String cartTotalTxt = waitForVisibility(cartTotal).getText();
		String number = cartTotalTxt.replaceAll("[^0-9.]", "");
        return Double.parseDouble(number);
        
	}
}
