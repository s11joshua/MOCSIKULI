package Dynamics;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DynamicsLoginPage {
	
	static Log logger = LogFactory.getLog(DynamicsLoginPage.class);
	
	public DynamicsLoginPage(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Email']")
	static WebElement TXTEmailID;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Passwd']")
	static WebElement TXTPassword;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='next']")
	static WebElement BTNNext;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='signIn']")
	static WebElement BTNSignIn;
	
	public static boolean Login(String UserName, String Password){
		try {
			TXTEmailID.sendKeys(UserName);
			BTNNext.click();
			Thread.sleep(2000);
			TXTPassword.sendKeys(Password);
			BTNSignIn.click();
			logger.info("Dynamics Login Completed Successfully");
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return true;
	}
	
}
