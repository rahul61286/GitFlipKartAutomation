package com.flipkartAutomation.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
	public Actions a;
	
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
			log.info("login button functionality verified");
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
			log.info("Login functionality working as expected");
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
		log.info("Title verified");
	}
	
	@Test
	public void verifyCart(){
		Assert.assertTrue(lp.cart().isDisplayed());
		log.info("Verify cart functionality");
	}
	
	
	@Test(dependsOnMethods= {"verifyTitle"})
	public void verifyMyProfile(){
		a=new Actions(driver);
		a.moveToElement(lp.loginText()).moveToElement(lp.MyProfile()).click().build().perform();
		log.info("Verifying the email address for the logged in person");
		Assert.assertEquals(lp.loginEmail(),prop.getProperty("email"));
		log.info("Verification of login details is completed");
		
	}
	
	
	@AfterTest
	public void tearDown() throws IOException {
		try {
			if(fis!=null) {
				fis.close();
			}
		}catch(Exception e) {
			log.error("Error closing inputstream object");
		}
		
		try {
			if (driver!=null) {
				driver.close();
			}
		}catch(Exception e) {
			log.error("Error closing driver object");
		}
		
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data=new Object[1][2];
		data[0][0]="rahul.singhdec06@gmail.com";
		data[0][1]="abhaya100";
		return data;
	}

}
