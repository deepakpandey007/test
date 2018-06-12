package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.test.automation.uiAutomation.excelReader.ExcelReader;

public class TestBase {
	
	public static WebDriver driver;
	public static final Logger log=Logger.getLogger(TestBase.class.getName());
	String url="http://planncktest.ap-south-1.elasticbeanstalk.com";
	String browser="firefox";
	static FileInputStream fil;
	static Properties prop;
	ExcelReader excel;
	public void init()
	{
		selectBrowser(browser);
		getUrl(url);
		String log4jConfigPath=System.getProperty("user.dir")+"\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
	}
	public void selectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.out.println(System.getProperty("sun.arch.data.model"));
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver.exe");

			log.info("creating object of "+ browser);
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");
			log.info("creating object of "+ browser);
			driver=new ChromeDriver();
		}
	}
	public void getUrl(String url)
	{
		log.info("navigating to :"+url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static Properties loadProperties()
	{
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uiAutomation\\properties\\ObjectRepository.properties";
		File file=new File(path);
		try {
			fil=new FileInputStream(file);
			prop=new Properties();
			prop.load(fil);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;

	}
	public String[][] getData(String excelName,String sheetName)
	{
		prop=loadProperties();
		String path=System.getProperty("user.dir")+prop.getProperty("login_Workbook");
		System.out.println("First Load="+path);
		String sheetValue=prop.getProperty("login_sheetName");
		System.out.println("SheetName="+sheetValue);
		excel=new ExcelReader(path);
		String data[][]=excel.getDataFromSheet(sheetValue);
		return data;
		
	}
	
	public void waitForElement(WebDriver driver,long timeOut, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void getScreenShots(WebDriver driver,String name) throws IOException
	{
		//Calendar calender=Calendar.getInstance();
		//SimpleDateFormat d=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String reportDirectory=new File(System.getProperty("user.dir")) +"\\src\\main\\java\\com\\test\\automation\\uiAutomation\\screenshot";
		File destFile=new File((String)reportDirectory + name +"-"+".png");
		FileUtils.copyFile(src, destFile);
		// this will help to link screen shot in Testng Report
	//Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+ destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
	}
}
