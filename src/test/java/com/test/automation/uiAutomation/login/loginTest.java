package com.test.automation.uiAutomation.login;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.LoginPage;

public class loginTest extends TestBase{
	
	LoginPage login;
	Properties prop;
	public static final Logger log=Logger.getLogger(loginTest.class.getName());

	@BeforeTest	
	public void setup()
	{
		
		init();
		
	}
	@DataProvider(name="loginValidData")
	public Object[][] getTestData()
	{
		prop=loadProperties();
		System.out.println(prop);
		Object[][] getRecords=getData(System.getProperty("user.dir")+"login_Workbook", "login_sheetName");
		System.out.println(getRecords);

		return getRecords;
	}
@Test(dataProvider="loginValidData")
public void verifylogin(String email,String pwd,String runmode) throws IOException, InterruptedException
{
	if(runmode.equalsIgnoreCase("Y"))
	{
	log.info("****Starting Verfiy Login Page****");
	login=new LoginPage(driver);
	login.loginToApplication(email,pwd);
	Thread.sleep(1000);
	login.getScreenShots(driver,"verifyLogin - "+email);
	String text= login.verifyLoginSuccessfully(driver);
	Assert.assertEquals( text,"http://planncktest.ap-south-1.elasticbeanstalk.com/home", "Login Unsuccesfully");
	
	}
	else
	{
		throw new SkipException("User Select as No Run");
	}
}



@AfterClass

public void close()
{
	 driver.close();
}
}
