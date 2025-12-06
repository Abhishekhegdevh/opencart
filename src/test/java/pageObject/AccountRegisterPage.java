package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountRegisterPage extends BasePage {
	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}

	By txtFirstName = By.xpath("//input[@id='input-firstname']");
	By LastName = By.xpath("//input[@id='input-lastname']");
	By eMail = By.xpath("//input[@id='input-email']");
	By Telephone = By.xpath("//input[@id='input-telephone']");
	By Password = By.xpath("//input[@id='input-password']");
	By PasswordConfirm = By.xpath("//input[@id='input-confirm']");
	By PrivacyPolicy = By.xpath("//input[@name='agree']");
	By btnContinue = By.xpath("//input[@value='Continue']");
	By confmsg = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");

	public void enterFirstName(String FirstName) {
		driver.findElement(txtFirstName).sendKeys(FirstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(LastName).sendKeys(lastName);
	}

	public void entereMail(String eMailId) {
		driver.findElement(eMail).sendKeys(eMailId);
	}

	public void enterTelephone(String TelephoneNo) {
		driver.findElement(Telephone).sendKeys(TelephoneNo);
	}

	public void enterPassword(String Passwordname) {
		driver.findElement(Password).sendKeys(Passwordname);
	}

	public void enterPasswordConfirm(String PasswordConfirmname) {
		driver.findElement(PasswordConfirm).sendKeys(PasswordConfirmname);
	}

	public void PrivacyPolicy() {
		driver.findElement(PrivacyPolicy).click();
	}

	public void btnContinue() {
		driver.findElement(btnContinue).click();
	}

	public String getconformationmsg() {
		try {
			return driver.findElement(confmsg).getText();
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
