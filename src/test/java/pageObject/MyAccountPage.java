package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
   By acc=By.xpath("//h2[normalize-space()='My Account']");
   By logout =By.xpath(" //a[@class='list-group-item'][normalize-space()='Logout']");
    public  boolean ismyaccpageExits()
    {
    	try {
			return(driver.findElement(acc).isDisplayed());
		} catch (Exception e) {
			return false;
		}
    }
    public void logout()
    {
    	driver.findElement(logout).click();
    }
}
