package com.test.automation.uiAutomation.registration;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.login.loginTest;
import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.RegistrationPage;

public class RegistrationTest extends TestBase {
	
	
	RegistrationPage registration;
	public static final Logger log=Logger.getLogger(RegistrationTest.class.getName());
	@BeforeClass
	public void setup()
	{
		init();
	}
	
	@Test
	
	public void validRegistration()
	{
		log.info("****Starting Verfiy Registration Page****");
		registration=new RegistrationPage(driver);
		registration.registerToApplication("qwerty", "hu@y.au", "qwerty", "qwerty", "Yups");
	}
	
@AfterClass

public void close()
{
	driver.close();
}
	

}
