package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegisterPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegisterPage extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void accountRegistration() {
		HomePage hp = new HomePage(driver);
		hp.clickaccount();
		hp.clickregistor();

		AccountRegisterPage rp = new AccountRegisterPage(driver);
		rp.enterFirstName(randomstring().toUpperCase());
		rp.enterLastName(randomstring().toUpperCase());
		rp.entereMail(randomstring()+"@gmail.com");
		String  password =randomAlphanumber();
		rp.enterPassword(password);
		rp.enterPasswordConfirm(password);
		rp.enterTelephone(randomnumber());
		rp.PrivacyPolicy();
		rp.btnContinue();

		String msg = rp.getconformationmsg();
		Assert.assertEquals(msg, "Your Account Has Been Created!");
	}
}
