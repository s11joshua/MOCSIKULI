package Dynamics;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Discovery.Helper;
import Discovery.TestExecution;
import FactFind.PersonalDetails;


public class Selenium {
	static Log logger = LogFactory.getLog(Selenium.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	public static WebDriver driver = null;
	
	static Pattern sales;
	static Pattern QuickCreate;
	static Pattern QuickCreateLead;
	static Pattern QuickContact;
	static Pattern QuickOpportunity;
	
	public Selenium(){
		new Selenium("C:\\Sikuli Images\\Dynamics\\");
	}
	public Selenium(String Imagefolderlocation){
		sales = new Pattern (Imagefolderlocation + "Sales.PNG");
		QuickCreate = new Pattern (Imagefolderlocation + "QuickCreate.PNG");
		QuickCreateLead = new Pattern (Imagefolderlocation + "QuickLead.PNG");
		QuickContact = new Pattern (Imagefolderlocation + "QuickContact.PNG");
		QuickOpportunity = new Pattern (Imagefolderlocation + "QuickOpportunity.PNG");
	}
	
	public static WebDriver LaunchBrowser(String BrowserType) {
		  
		 switch (BrowserType){
		 
		 	case "IE":
		 		WebDriver IEdriver = new InternetExplorerDriver();
		 		PersonalDetails.driver = IEdriver;
		 		return IEdriver;
		 	case "Chrome":
		 		WebDriver Chromedriver = new ChromeDriver();
		 		PersonalDetails.driver = Chromedriver;
		 		return Chromedriver;
		 	case "FireFox":
		 		WebDriver Firefoxdriver = new FirefoxDriver();
		 		PersonalDetails.driver = Firefoxdriver;
		 		return Firefoxdriver;
		 	default:
		 		return null;
	 	
		 }
	}
	
	public static boolean LogintoDynamics(){
	 	
		Helper Config = new Helper();
		driver = Selenium.LaunchBrowser(Config.GetConfigParameter("BrowserType"));
		driver.get(Config.GetConfigParameter("DynamicsURL"));
		driver.manage().window().maximize();
		DynamicsLoginPage Login = new DynamicsLoginPage(driver);
		DynamicsLoginPage.Login(Config.GetConfigParameter("DynamicsUsername"), Config.GetConfigParameter("DynamicsPassword"));
		if(Helper.Waitforelement(driver, "CssSelector", "#TabSFA > a.navTabButtonLink > span.navTabButtonImageContainer > img.navTabButtonArrowDown", 30) == true){
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "DynamicsLoginPage");
			return true;
		}else{
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
	
	public static boolean CreateQuicklead(){
		
		if(Selenium.LogintoDynamics() != true){
			return false;
		}
		
		try {
			screen.click(QuickCreate);				
			screen.click(QuickCreateLead);
			Thread.sleep(3000);
			if (DynamicsLeadsPage.CreateQuickLead() != true){
				driver.close();
				return false;
			}else{
				driver.close();
				return true;
			}
		} catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			logger.error(e.toString());
			return false;
		}
		
	}
 
}
