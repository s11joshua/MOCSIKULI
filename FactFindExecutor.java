package FactFind;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;


import Discovery.Helper;
import Dynamics.Selenium;

public class FactFindExecutor {
	static Log logger = LogFactory.getLog(Selenium.class);
	
	public static WebDriver driver = null;
	
	
	public static void FillFactFind(){
		Helper Config = new Helper();
		driver = Selenium.LaunchBrowser(Config.GetConfigParameter("BrowserType"));
		driver.get(Config.GetConfigParameter("FactFindURL"));
		driver.manage().window().maximize();
		new FactFindLogin(driver);
		FactFindLogin.LoginFactFindFirstTime(Config.GetConfigParameter("DynamicsUsername"));
		new PersonalDetails(driver);
		PersonalDetails.CustomerPersonalDetails();
	}
	
	
}
