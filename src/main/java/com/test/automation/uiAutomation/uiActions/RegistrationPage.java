package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	WebDriver driver;
	public static final Logger log=Logger.getLogger(LoginPage.class.getName());
	
	@FindBy(xpath="//*[@class='container clearfix']/div[2]/a[2]")
	WebElement register;
	
	@FindBy(id="text1")
	WebElement name;
	
	@FindBy(id="email_ID")
	WebElement emailid;

	@FindBy(id="password-show")
	WebElement pwd;
	
	@FindBy(id="password_show")
	WebElement rpwd;
	
	@FindBy(id="pass")
	WebElement explore;
	
	@FindBy(css=".signbutton1")
	WebElement signup;
	
	public RegistrationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void registerToApplication(String name,String emailid,String pwd,String rpwd,String explore)
	{
		
		register.click();
		log.info("click on register and object is:- "+register.toString());
		this.name.sendKeys(name);
		log.info("entered the user name:- "+name+" and object is "+ name.toString());
		this.emailid.sendKeys(emailid);
		log.info("entered the emailid:- "+emailid+" and object is "+ emailid.toString());
		this.pwd.sendKeys(pwd);
		this.rpwd.sendKeys(rpwd);
		this.explore.sendKeys(explore);
		this.signup.click();
	}
}
