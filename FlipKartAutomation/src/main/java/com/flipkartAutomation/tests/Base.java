package com.flipkartAutomation.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;


public class Base {
	public static WebDriver driver=null;
	public static Properties prop=null;
	public static FileInputStream fis=null;
	public static WebDriverWait d=null;
	public static ExtentReports extent=null;
	private static Logger log=LogManager.getLogger(Base.class.getName());
	
	
	public WebDriver initializeDriver() throws IOException{
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resourcesnew\\TestData.properties");
		prop= new Properties();
		prop.load(fis);
		log.info("Successfully loaded property file");
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty(prop.getProperty("chromedriver"), System.getProperty("user.dir")+"\\chromedriver.exe");
			ChromeOptions options=new ChromeOptions();
			options.addArguments("headless");
			driver=new ChromeDriver();
			log.debug("Driver Instantiated successfully");
		} 
		else if(prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
			driver=new FirefoxDriver();	
		}
		else {
			log.error("Please provide the valid browser name in the property file");
		}	
		d=new WebDriverWait(driver, 10);
		return driver;
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destPath=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destPath));
		return destPath;
		
		
	}
}
