package testCases;
/*Data is valid   - login success - test pass   - logout
Data is valid   - login failed  - test fail

Data is invalid - login success - test fail   - logout
Data is invalid - login failed  - test pass*/

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC_002_DataDrivenTesting extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_login()
	{
     HomePage hp =new HomePage(driver);
     hp.clickaccount();
     hp.clicklogin();
     
     LoginPage lp =new LoginPage(driver);
     lp.enteremailid(P.getProperty("email"));
     lp.enterpwd(P.getProperty("password"));
     lp.loginbwt();
     
     MyAccountPage accountPage=new MyAccountPage(driver);
   boolean targetpage=accountPage.ismyaccpageExits();
   Assert.assertTrue(targetpage);
	}
}

