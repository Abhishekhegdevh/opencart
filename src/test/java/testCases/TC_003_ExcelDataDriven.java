package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataProviders;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
public class TC_003_ExcelDataDriven extends BaseClass {
	// dataProviderClass =DataProvider.class its present in different class
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verityloginfromExcel(String email,String pwd,String result) {
		HomePage hp = new HomePage(driver);
		hp.clickaccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.enteremailid(email);
		lp.enterpwd(pwd);
		lp.loginbwt();

		MyAccountPage accountPage = new MyAccountPage(driver);
		boolean targetpage = accountPage.ismyaccpageExits();
		/*
		 * Data is valid - login success - test pass - logout login failed - test fail
		 * 
		 * Data is invalid - login success - test fail - logout login failed - test pass
		 */

		if (result.equalsIgnoreCase("Valid")) {
			if (targetpage == true) {
				accountPage.logout();
				Assert.assertTrue(true);
			} else {
				Assert.assertFalse(false);
			}
		}
		if (result.equalsIgnoreCase("Invalid")) {
			if (targetpage == true) {
				accountPage.logout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertFalse(true);
			}
		}
	}
}
