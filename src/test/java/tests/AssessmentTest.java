package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import WebPages.CartPage;
import WebPages.ContactPage;
import WebPages.HomePage;
import WebPages.ShopPage;



public class AssessmentTest extends BaseTest{
	
	@Test(description = "Test 1")
	public void verifyMandatoryField() {
		
		// Step 1: Verify Home page is open
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		Assert.assertEquals(homePage.isHomePageOpened(), true);
		extentTest.get().log(Status.PASS, "Home Page is opened");
	
		// Step 2: Navigate to contact tab
		homePage.clickContactTab();
		ContactPage contactPage = parentPage.getInstanceOf(ContactPage.class);
		Assert.assertEquals(contactPage.isContactPageOpened(), true);
		extentTest.get().log(Status.PASS, "Contact Page is opened");
		
		// Step 3: Click on submit button
		contactPage.clickOnSubmit();
		extentTest.get().log(Status.PASS, "Submit button is clicked");
		
		// Step 4: Verify error alert is displayed
		Assert.assertEquals(contactPage.isAlertErrorDisplayed(), true);
		extentTest.get().log(Status.PASS, "Error displayed on alert");
		
		// Step 5: Verify error message on Forename field
		Assert.assertEquals(contactPage.isErrorOnForenameField(), true);
		extentTest.get().log(Status.PASS, "Error displayed on ForeName field");
		
		// Step 6: Verify error message on Email field
		Assert.assertEquals(contactPage.isErrorOnEmailField(), true);
		extentTest.get().log(Status.PASS, "Error displayed on Email field");
				
		// Step 7: Verify error message on Message field
		Assert.assertEquals(contactPage.isErrorOnMessageField(), true);
		extentTest.get().log(Status.PASS, "Error displayed on Message field");
		
		// Step 9: Enter text in all the mandatory fields
		contactPage.enterForeName(propReader.getDataFromPropertiesFile("forename"));
		contactPage.enterEmail(propReader.getDataFromPropertiesFile("email"));
		contactPage.enterMessage(propReader.getDataFromPropertiesFile("message"));
		extentTest.get().log(Status.INFO, "All the mandatory fields are filled");
		
		// Step 10: Verify error alert is gone
		if(contactPage.isAlertErrorDisplayed()) {
			extentTest.get().log(Status.FAIL, "Error displayed on alert");
		}else {
			extentTest.get().log(Status.PASS, "Error not displayed on alert");
		}
		Assert.assertEquals(contactPage.isAlertErrorDisplayed(), false);
		
	}
	
	@Test(dataProvider = "data-provider", description = "Test 2")
	public void verifySuccessfullSubmission(String forename, String email, String message) {
		
		// Step 1: Verify Home page is open
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		Assert.assertEquals(homePage.isHomePageOpened(), true);
		extentTest.get().log(Status.PASS, "Home Page is opened");
		
		// Step 2: Navigate to contact tab
		homePage.clickContactTab();
		ContactPage contactPage = parentPage.getInstanceOf(ContactPage.class);
		Assert.assertEquals(contactPage.isContactPageOpened(), true);
		extentTest.get().log(Status.PASS, "Contact Page is opened");
		
		// Step 3: Populate all the mandatory fields
		contactPage.enterForeName(forename);
		contactPage.enterEmail(email);
		contactPage.enterMessage(message);
		extentTest.get().log(Status.INFO, "All the mandatory fields are filled");
		
		// Step 4: Click on submit button
		contactPage.clickOnSubmit();
		extentTest.get().log(Status.PASS, "Submit button is clicked");
		
		// Step 5: Verify successful submission
		if(contactPage.getSuccessMessage() != null) {
			String msg = contactPage.getSuccessMessage();
			Assert.assertEquals(msg, "Thanks " +forename+", we appreciate your feedback.");
			extentTest.get().log(Status.PASS, "Message: "+"Thanks " +forename+", we appreciate your feedback."+" is displayed");
		}else {
			extentTest.get().log(Status.FAIL, "There is no successful submission");
			Assert.fail("There is no successful submission");
		}
		
	}
	

	@Test(description = "Test 3")
	public void verifyTotalAmount() {
		
		double singlePriceOnShopPage = 0.0;
		double totalProductPriceOnShopPage = 0.0;
		
		double singlePriceOnCartPage = 0.0;
		double totalProductPriceOnCartPage = 0.0;
		
		double totalAmount = 0.0;
		
		// Step 1: Verify Home page is open
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		Assert.assertEquals(homePage.isHomePageOpened(), true);
		extentTest.get().log(Status.PASS, "Home Page is opened");
		
		// Step 2: Navigate to Shop Page
		homePage.clickShopTab();
		extentTest.get().log(Status.PASS, "Shop Page is opened");
		
		// Step 3: Buy 2 Stuffed frog
		ShopPage shopPage = parentPage.getInstanceOf(ShopPage.class);
		shopPage.buyProduct("Stuffed Frog", 2);
		
		// Step 3.1: Navigate to Cart page
		homePage.clickCartTab();
		CartPage cartPage = parentPage.getInstanceOf(CartPage.class);
		boolean isAddedToCart = cartPage.isElementDisplayedOnPage("Stuff Frog");
		Assert.assertEquals(isAddedToCart, true);
		extentTest.get().log(Status.PASS, "2 Stuff Frog are added to cart");
		
		// Step 3.2: Verify that prices match on cart page
		singlePriceOnShopPage = shopPage.getProductPrice();
		totalProductPriceOnShopPage = shopPage.getTotalPriceForEachProduct();
		System.out.println("Shop Page: Stuff single price: "+singlePriceOnShopPage);
		System.out.println("Shop Page: Stuff total price: "+totalProductPriceOnShopPage);
		singlePriceOnCartPage = cartPage.getSinglePrice();
		totalProductPriceOnCartPage = cartPage.getTotalProductPrice();
		System.out.println("Cart Page: Stuff single price: "+singlePriceOnCartPage);
		System.out.println("Cart Page: Stuff total price: "+totalProductPriceOnCartPage);
		Assert.assertEquals(singlePriceOnShopPage, singlePriceOnCartPage);
		Assert.assertEquals(totalProductPriceOnShopPage, totalProductPriceOnCartPage);
		extentTest.get().log(Status.PASS, "Price Matched for Stuff Frog");
		totalAmount = totalAmount + totalProductPriceOnCartPage;
		// Resetting values
		isAddedToCart = false;
		singlePriceOnShopPage = 0.0;
		totalProductPriceOnShopPage = 0.0;
		singlePriceOnCartPage = 0.0;
		totalProductPriceOnCartPage = 0.0;
		
		
		//Step 4: Navigate to shop page
		homePage.clickShopTab();
		extentTest.get().log(Status.PASS, "Shop Page is opened");
			
		// Step 5: Buy 5 Fluffy Bunny
		shopPage.buyProduct("Fluffy Bunny", 5);
		
		// Step 5.1: Navigate to Cart page
		homePage.clickCartTab();
		isAddedToCart = cartPage.isElementDisplayedOnPage("Fluffy Bunny");
		Assert.assertEquals(isAddedToCart, true);
		extentTest.get().log(Status.PASS, "5 Fluffy Bunny are added to cart");
		
		// Step 5.2: Verify that prices match on cart page
		singlePriceOnShopPage = shopPage.getProductPrice();
		totalProductPriceOnShopPage = shopPage.getTotalPriceForEachProduct();
		System.out.println("Shop Page: Fluffy Bunny single price: "+singlePriceOnShopPage);
		System.out.println("Shop Page: Fluffy Bunny total price: "+totalProductPriceOnShopPage);
		singlePriceOnCartPage = cartPage.getSinglePrice();
		totalProductPriceOnCartPage = cartPage.getTotalProductPrice();
		System.out.println("Cart Page: Fluffy Bunny single price: "+singlePriceOnCartPage);
		System.out.println("Cart Page: Fluffy Bunny total price: "+totalProductPriceOnCartPage);
		Assert.assertEquals(singlePriceOnShopPage, singlePriceOnCartPage);
		Assert.assertEquals(totalProductPriceOnShopPage, totalProductPriceOnCartPage);
		extentTest.get().log(Status.PASS, "Price Matched for Fluffy Bunny");
		totalAmount = totalAmount + totalProductPriceOnCartPage;
		// Resetting values
		isAddedToCart = false;
		singlePriceOnShopPage = 0.0;
		totalProductPriceOnShopPage = 0.0;
		singlePriceOnCartPage = 0.0;
		totalProductPriceOnCartPage = 0.0;
		
		//Step 6: Navigate to shop page
		homePage.clickShopTab();
		extentTest.get().log(Status.PASS, "Shop Page is opened");
		
		// Step 7: Buy 3 Valentine Bear
		shopPage.buyProduct("Valentine Bear", 3);
		
		// Step 7.1: Navigate to Cart page
		homePage.clickCartTab();
		isAddedToCart = cartPage.isElementDisplayedOnPage("Valentine Bear");
		Assert.assertEquals(isAddedToCart, true);
		extentTest.get().log(Status.PASS, "3 Valentine Bear are added to cart");
		
		// Step 5.2: Verify that prices match on cart page
		singlePriceOnShopPage = shopPage.getProductPrice();
		totalProductPriceOnShopPage = shopPage.getTotalPriceForEachProduct();
		System.out.println("Shop Page: Valentine Bear single price: "+singlePriceOnShopPage);
		System.out.println("Shop Page: Valentine Beartotal price: "+totalProductPriceOnShopPage);
		singlePriceOnCartPage = cartPage.getSinglePrice();
		totalProductPriceOnCartPage = cartPage.getTotalProductPrice();
		System.out.println("Cart Page: Valentine Bear single price: "+singlePriceOnCartPage);
		System.out.println("Cart Page: Valentine Bear total price: "+totalProductPriceOnCartPage);
		Assert.assertEquals(singlePriceOnShopPage, singlePriceOnCartPage);
		Assert.assertEquals(totalProductPriceOnShopPage, totalProductPriceOnCartPage);
		extentTest.get().log(Status.PASS, "Price Matched for Valentine Bear");
		totalAmount = totalAmount + totalProductPriceOnCartPage;
		// Resetting values
		isAddedToCart = false;
		singlePriceOnShopPage = 0.0;
		totalProductPriceOnShopPage = 0.0;
		singlePriceOnCartPage = 0.0;
		totalProductPriceOnCartPage = 0.0;
		
		// Verify total amount on cart
		Assert.assertEquals(cartPage.getCartTotal(), totalAmount);
		extentTest.get().log(Status.PASS, "Total price matched which is: "+totalAmount);
	
	}
}
