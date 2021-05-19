package GivelifyPages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import junit.framework.Assert;

public class GivelifySignInPage {
	static WebDriver driver;

	public GivelifySignInPage(WebDriver driver) {
		GivelifySignInPage.driver = driver;
	}

	/************************************************************************************************************************
	 * WebElements for Givelify SignIn Page
	 * 
	 ************************************************************************************************************************/
	// WebElements for Givelify SignIn Page
	public static String signIn_Tab = "(//span[text()='Sign In'])[2]";
	public static String email_Input = "//input[@type='email']";
	public static String password_Input = "//input[@type='password']";
	public static String forgotpassword_Link = "//span[contains(text(),'Forgot password')]";
	public static String signIn_Button = "//button[text()='Sign In']";
	public static String signIn_Message = "//span[contains(text(),'You have successfully logged in')]";
	public static String continue_Button = "//button[text()='Continue']";
	public static String username_Button = "//span[contains(@class,'username')]";
	public static String logout_Button = "//span[text()='Logout']";
	public static String email_ErrorMsg = "//span[text()='Sign In with your email']/following::div[1]";
	public static String forgotPswd_Modal = "//span[text()='Recover your password']";
	public static String forgotPswd_Email = "(//input[@type='email'])[2]";
	public static String forgotPswd_Email_Msg = "//span[text()='This field must be an email']";
	public static String resetPswd_Button = "//button[text()='Reset Password']";
	public static String resetPswd_Msg = "//div[contains(text(),'Reset password link sent to your email address')]";
	public static String cancel_Button = "//button[text()='Cancel']";

	/************************************************************************************************************************
	 * navigateGivelifySignIn() - Navigates to Givelify SignIn Page and Verifies the
	 * SignIn to Givelify Page based on passed emailID & password
	 * 
	 ************************************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static WebDriver navigateGivelifySignIn(String url, String emailId, String password) {
		try {
			String homePath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", homePath + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);
			FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signIn_Tab)));
			driver.findElement(By.xpath(signIn_Tab)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(email_Input)));
			driver.findElement(By.xpath(email_Input)).sendKeys(emailId);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(password_Input)));
			driver.findElement(By.xpath(password_Input)).sendKeys(password);
			driver.findElement(By.xpath(signIn_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signIn_Message)));
			Boolean isDisplayed = driver.findElement(By.xpath(signIn_Message)).isDisplayed();
			Assert.assertTrue(isDisplayed);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(continue_Button)));
			driver.findElement(By.xpath(continue_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(username_Button)));
			driver.findElement(By.xpath(username_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logout_Button)));
			driver.findElement(By.xpath(logout_Button)).click();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return driver;
	}

	/************************************************************************************************************************
	 * verifyInvalidCredentials() - Verify Whether Error Message is getting
	 * displayed by Entering Invalid Email & Password
	 * 
	 ************************************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static WebDriver verifyInvalidCredentials(String emailId, String password) {
		try {
			FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signIn_Tab)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(email_Input)));
			driver.findElement(By.xpath(email_Input)).sendKeys("Automationtesting");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(password_Input)));
			driver.findElement(By.xpath(password_Input)).sendKeys(password);
			driver.findElement(By.xpath(signIn_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(email_ErrorMsg)));
			Boolean isDisplayed = driver.findElement(By.xpath(email_ErrorMsg)).isDisplayed();
			Assert.assertTrue(isDisplayed);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return driver;
	}

	/************************************************************************************************************************
	 * verifyForgotPasswordModal() - Verify Whether 'Recover your password' Popup is
	 * getting displayed after clicking on Forgot Password Link
	 * 
	 ************************************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static WebDriver verifyForgotPasswordModal(String emailId, String password) {
		try {
			FluentWait wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotpassword_Link)));
			driver.findElement(By.xpath(forgotpassword_Link)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotPswd_Modal)));
			Boolean isDisplayed = driver.findElement(By.xpath(forgotPswd_Modal)).isDisplayed();
			Assert.assertTrue(isDisplayed);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotPswd_Email)));
			driver.findElement(By.xpath(forgotPswd_Email)).sendKeys("Automationtesting");
			driver.findElement(By.xpath(forgotPswd_Email)).sendKeys(Keys.TAB);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotPswd_Email_Msg)));
			isDisplayed = driver.findElement(By.xpath(forgotPswd_Email_Msg)).isDisplayed();
			Assert.assertTrue(isDisplayed);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cancel_Button)));
			driver.findElement(By.xpath(cancel_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotpassword_Link)));
			driver.findElement(By.xpath(forgotpassword_Link)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgotPswd_Modal)));					
			driver.findElement(By.xpath(forgotPswd_Email)).sendKeys(emailId);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resetPswd_Button)));
			driver.findElement(By.xpath(resetPswd_Button)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resetPswd_Msg)));
			isDisplayed = driver.findElement(By.xpath(resetPswd_Msg)).isDisplayed();
			Assert.assertTrue(isDisplayed);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return driver;
	}

}