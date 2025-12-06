package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

 

public class HomePage extends BasePage {
	//constructor
 public	 HomePage(WebDriver driver)
	 {
		 super(driver);
	 }
 //locators
 
  By account=By.xpath(" //span[normalize-space()='My Account']");
  By registor=By.xpath(" //a[normalize-space()='Register']");
  By login=By.xpath(" //ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']");
  
  //Action
  public void clickaccount()
  {
	  driver.findElement(account).click();
	 
  }
  public void clickregistor()
  {
	 driver.findElement(registor).click();
  }
  public void clicklogin()
  {
	  driver.findElement(login).click();
  }
  
}
