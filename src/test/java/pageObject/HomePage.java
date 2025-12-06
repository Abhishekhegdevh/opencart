package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	// constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	// locators

	By account = By.xpath(" //span[normalize-space()='My Account']");
	By registor = By.xpath(" //a[normalize-space()='Register']");
	By login = By.xpath(" //ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']");

	// Action
	public void clickaccount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		By myAccountLocator = By.xpath("//span[normalize-space()='My Account']");

		WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(myAccountLocator));
		myAccount.click();
	}

	public void clickregistor() {
		driver.findElement(registor).click();
	}

	public void clicklogin() {
		driver.findElement(login).click();
	}

}
