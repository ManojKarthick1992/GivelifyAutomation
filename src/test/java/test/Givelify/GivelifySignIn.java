package test.Givelify;

import org.openqa.selenium.WebDriver;
import GivelifyPages.GivelifySignInPage;

public class GivelifySignIn {

	/************************************************************************************************************************
	 * This Automated Test verifies the following checks mentioned in below points.
	 * 
	 * 1. Using a valid login confirm successful login 2. Using an invalid email,
	 * valid password, confirm the error message text is present 3. Using a valid
	 * email and invalid password, confirm the error message text is present 4.
	 * Click on the forgot password link & confirm the forgot password modal is
	 * shown 5. Click on the forgot password link, enter an invalid email & confirm
	 * the error message text is present 6. Click on the forgot password link, enter
	 * a valid Givelify email, confirm that the modal closes & confirm that the
	 * password reset sent text is present
	 * 
	 ************************************************************************************************************************/
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			WebDriver driver = null;
			String signURL = "https://www.givelify.com/donate/anointed-city-of-the-lord-indianapolis-in-2j7wy5OTA=/account/login";
			String emailID = "meyiki2808@firmjam.com";
			String password = "Admin@123";
			GivelifySignInPage givelifySignInPage = new GivelifySignInPage(driver);
			givelifySignInPage.navigateGivelifySignIn(signURL, emailID, password);
			givelifySignInPage.verifyInvalidCredentials(emailID, password);
			driver = givelifySignInPage.verifyForgotPasswordModal(emailID, password);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
