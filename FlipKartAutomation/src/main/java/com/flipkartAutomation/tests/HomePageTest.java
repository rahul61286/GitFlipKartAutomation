package com.flipkartAutomation.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.flipkartAutomation.pages.LandingPage;

public class HomePageTest extends Base {
	public WebDriver driver;
	public LandingPage lp;
	
	private static Logger log=LogManager.getLogger(HomePageTest.class.getName());
	
	@BeforeTest
	public void intailization() throws IOException {
		if((driver=initializeDriver())!=null) {
			driver.get(prop.getProperty("url"));
			log.info("Navigated to the application");
		}else {
			log.error("Failed to intialize driver");
		}

	}
		
	@Test
	public void loginButtonTest() throws IOException {
			lp=new LandingPage(driver);
			d.until(ExpectedConditions.visibilityOf(lp.loginButton()));
			Assert.assertTrue(lp.loginButton().isEnabled());
	}
	
	
	@Test(dataProvider="getData")
	public void loginFunctionality(String userName,String password) {
			lp.email().clear();
			lp.email().sendKeys(userName);
			lp.password().clear();
			lp.password().sendKeys(password);
			lp.submit().click();
			d.until(ExpectedConditions.visibilityOf(lp.loginText()));
			Assert.assertTrue(lp.loginText().getText().equalsIgnoreCase(prop.getProperty("logintxt")));
	}
	
	
	@Test
	public void searchItem(){
		lp.searchBox().sendKeys(prop.getProperty("searchData"));
		if(lp.searchItemInSearchBox(prop.getProperty("orderItem"))) {
			lp.searchBox().sendKeys(Keys.ENTER);
			log.info("Item successfully searched");
		} else {
			log.error("search item not present in the list");
		}		
	}
	
	@Test
	public void verifyTitle() {
		Assert.assertEquals(prop.getProperty("homePageTitle"), driver.getTitle());
	}
	
	@Test
	public void verifyCart(){
		Assert.assertTrue(lp.cart().isDisplayed());
	}
	
	
	@AfterTest
	public void tearDown() throws IOException {
		fis.close();
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data=new Object[1][2];
		data[0][0]="rahul.singhdec06@gmail.com";
		data[0][1]="abhaya100";
		return data;
	}

}
