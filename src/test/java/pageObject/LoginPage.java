package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	By email = By.xpath("//input[@id='input-email']");
	By pwd = By.xpath("//input[@id='input-password']");
	By login = By.xpath("//input[@value='Login']");

	public void enteremailid(String email) {
		 driver.findElement(this.email).clear();
		driver.findElement(this.email).sendKeys(email);
	}

	public void enterpwd(String pwd) {
		 driver.findElement(this.pwd).clear();
		driver.findElement(this.pwd).sendKeys(pwd);
	}

	public void loginbwt() {
        driver.findElement(login).click();
	}
}
