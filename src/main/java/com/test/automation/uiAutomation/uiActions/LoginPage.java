package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;

public class LoginPage extends TestBase {
	
	WebDriver driver;
	public static final Logger log=Logger.getLogger(LoginPage.class.getName());
	
	@FindBy(xpath="//*[@class='container clearfix']/div[2]/a[1]")
	WebElement signIn;
	
	@FindBy(id="text1")
	WebElement email;
	
	@FindBy(id="password_show")
	WebElement pwd;
	
	@FindBy(className="sign-on-in")
	WebElement submit;
	
public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
	public void loginToApplication(String emailAddress,String password)
	{
		//loginy.click();
		//waitForElement(driver,3000, signIn);
		signIn.click();
		log.info("click on SignIn button and object is:- "+signIn.toString());
		email.sendKeys(emailAddress);
		log.info("entered the email address:- "+emailAddress+" and object is "+ email.toString());
		pwd.sendKeys(password);
		log.info("entered the password:- "+password+" and object is "+ pwd.toString());
		//waitForElement(300, submit);
		submit.click();
		log.info("Click on Submit button:- "+submit.toString());
		
	}
	
	public String verifyLoginSuccessfully(WebDriver driver)
	{
		String getCurrentUrl=driver.getCurrentUrl();
		return getCurrentUrl;
	}
	
	public void logoutAccount()
	{
		
	}
}
