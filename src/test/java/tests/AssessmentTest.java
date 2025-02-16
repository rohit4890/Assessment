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
		
		// Step 1: Verify Home page is open
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		Assert.assertEquals(homePage.isHomePageOpened(), true);
		extentTest.get().log(Status.PASS, "Home Page is opened");
		
		// Step 2: Navigate to Shop Page
		homePage.clickShopTab();
		
		// Step 3: Buy 2 Stuffed frog
		ShopPage shopPage = parentPage.getInstanceOf(ShopPage.class);
		shopPage.buyProduct("Stuffed Frog", 2);
		// Step 3.1: Verify each and total price for the product
		System.out.println("1 Stuff frog price"+ shopPage.getProductPrice());
		System.out.println("2 Stuff frog price"+ shopPage.getTotalPriceForEachProduct());
		

		// Step 4: Buy 5 Fluffy Bunny
		shopPage.buyProduct("Fluffy Bunny", 5);
		// Step 4.1: Verify each and total price for the product
		System.out.println("1 Fluffy Bunny"+ shopPage.getProductPrice());
		System.out.println("5 Fluffy Bunny"+ shopPage.getTotalPriceForEachProduct());
		
		
		// Step 5: Buy 3 Valentine Bear
		shopPage.buyProduct("Valentine Bear", 3);
		// Step 5.1: Verify each and total price for the product
		System.out.println("1 Valentine Bear"+ shopPage.getProductPrice());
		System.out.println("3 Valentine Bear"+ shopPage.getTotalPriceForEachProduct());
		
		// Step 6: Navigate to Cart
		homePage.clickCartTab();
		
		
	}
}
