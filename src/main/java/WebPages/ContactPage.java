package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage{
	
	
	private By foreNameField = By.id("forename");
	private By surnameField = By.id("surname");
	private By emailField = By.id("email");
	private By telephoneField = By.id("telephone");
	private By messageField = By.id("message");
	private By submitButton = By.xpath("//a[text()='Submit']");
	
	// Error Messages
	private By foreNameFieldError = By.id("forename-err");
	private By emailFieldError = By.id("email-err");
	private By messageFieldError = By.id("message-err");
	
	private By alertMsg = By.className("alert");

	private By successMsg = By.xpath("//div[@class='alert alert-success']");

	public ContactPage(WebDriver driver) {
		super(driver);
	}
	
	// Get Success message after submission
	public String getSuccessMessage() {
		WebElement msgElement = waitForVisibility(successMsg);
		if(msgElement != null) {
			return waitForVisibility(successMsg).getText();
		}else {
			return null;
		}
	}
	
	// Check for contact page open
	public boolean isContactPageOpened() {
		WebElement welcomeElement = waitForVisibility(alertMsg);
		String msg = welcomeElement.getText();
		if (msg.equalsIgnoreCase("We welcome your feedback - tell it how it is."))
		{
			return true;
		}else {
			return false;
		} 
	}
	
	// Enter text under forename field
	public void enterForeName(String forename) {
		WebElement forenameElement = waitForVisibility(foreNameField);
		if(forenameElement != null) {
			forenameElement.clear();
			forenameElement.sendKeys(forename);
		}
	}
	
	// Enter text under surname field
	public void enterSuname(String surname) {
		WebElement sunameElement = waitForVisibility(surnameField);
		if(sunameElement != null) {
			sunameElement.clear();
			sunameElement.sendKeys(surname);
		}
	}
	// Enter text under email field
	public void enterEmail(String email) {
		WebElement emailElement = waitForVisibility(emailField);
		if(emailElement != null) {
			emailElement.clear();
			emailElement.sendKeys(email);
		}
	}

	// Enter text under telephone field
	public void enterTelephone(String telephone) {
		WebElement telephoneElement = waitForVisibility(telephoneField);
		if(telephoneElement != null) {
			telephoneElement.clear();
			telephoneElement.sendKeys(telephone);
		}
	}

	// Enter text under message field
	public void enterMessage(String message) {
		WebElement messageElement = waitForVisibility(messageField);
		if(messageElement != null) {
			messageElement.clear();
			messageElement.sendKeys(message);
		}
	}
	
	// Click on submit button
	public void clickOnSubmit() {
		WebElement submitElement = waitForClickable(submitButton);
		if(submitElement != null) {
			submitElement.click();
		}
	}
	
	// Check for header message for error
	public boolean isAlertErrorDisplayed() {
		
		WebElement welcomeElement = waitForVisibility(alertMsg);
		String msg = welcomeElement.getText();
		if (msg.equalsIgnoreCase("We welcome your feedback - but we won't get it unless you complete the form correctly.")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	// Check for forename error message
	public boolean isErrorOnForenameField() {
		return isElementDisplayedOnPage(foreNameFieldError);
	}
	
	// Check for email error message
	public boolean isErrorOnEmailField() {
		return isElementDisplayedOnPage(emailFieldError);
	}

	// Check for message error message
	public boolean isErrorOnMessageField() {
		return isElementDisplayedOnPage(messageFieldError);
	}
}
