package com.flipkartAutomation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkartAutomation.tests.Base;
import com.flipkartAutomation.tests.HomePageTest;


public class LandingPage extends Base {
	private static Logger log=LogManager.getLogger(HomePageTest.class.getName());
	
	public WebDriver driver;
	JavascriptExecutor js;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (xpath="//a[text()='Login']")
	WebElement loginButton;
	
	@FindBy (css=".LM6RPg")
	WebElement searchBox;
	
	@FindBy (css="._3ko_Ud")
	WebElement cart;
	
	@FindBy (css="._2AkmmA._29YdH8")
	WebElement closeLoginScreen;
	
	@FindBy (css="._2zrpKA._1dBPDZ")
	WebElement email;
	
	@FindBy (css="._2zrpKA._3v41xv._1dBPDZ")
	WebElement password;
	
	@FindBy (css="._2AkmmA._1LctnI._7UHT_c")
	WebElement submit;
	
	@FindBy (xpath="//div[@class='_2aUbKa' and text()='Flipkart']")
	WebElement loginText;
	
	@FindBy (css=".vh79eN")
	WebElement searchButton;
	
	
	public WebElement loginButton(){
		return loginButton;
	}
	
	public WebElement searchBox(){
		return searchBox;
	}
	
	public WebElement cart(){
		return cart;
	}
	
	public WebElement closeLoginScreen(){
		return closeLoginScreen;
	}
	
	public WebElement email(){
		return email;
	}
	
	public WebElement password(){
		return password;
	}
	
	public WebElement submit(){
		return submit;
	}
	
	public WebElement loginText() {
		return loginText;
	}
	
	public WebElement searchButton() {
		return searchButton;
	}
	
	
	public boolean searchItemInSearchBox(String item) {
		int flag=0;
		String value=(String)js.executeScript("return document.getElementsByClassName('LM6RPg')[0].value;");
		for(int i=0;i<9;i++) {
			if(!(value.equalsIgnoreCase(item))) {
				searchBox.sendKeys(Keys.DOWN);
				value=(String)js.executeScript("return document.getElementsByClassName('LM6RPg')[0].value;");
				flag=0;
			}else {
				flag=1;
				break;
			}
		}		
		if(flag==0) {
			return false;
		}else {
			log.info("element searched with method");
			return true;
		}
	}
	
	
	
}
