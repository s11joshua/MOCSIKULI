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
		 		return IEdriver;
		 	case "Chrome":
		 		WebDriver Chromedriver = new ChromeDriver();
		 		return Chromedriver;
		 	case "FireFox":
		 		WebDriver Firefoxdriver = new FirefoxDriver();
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
	
	public static boolean CreateQuicklead(JSONObject testdata){
		
		if(Selenium.LogintoDynamics() != true){
			return false;
		}
		
		JSONArray CustomerInformation_Array = (JSONArray) testdata.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		JSONObject CustomerNames = null;
		JSONObject CustomerContact = null;
		
		while (CustomerInformationArray.hasNext()){
			JSONObject CustomerInformation = CustomerInformationArray.next();
			CustomerNames = (JSONObject) CustomerInformation.get("CustomerNames");
			CustomerContact = (JSONObject) CustomerInformation.get("CustomerContactDetails");
			break;
		}
		
		try {
			screen.click(QuickCreate);				
			screen.click(QuickCreateLead);
			Thread.sleep(3000);
			DynamicsLeadsPage.CreateQuickLead(CustomerNames,CustomerContact);
			driver.close();
			return true;
		} catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			logger.error(e.toString());
			return false;
		}
		
	}
 
}
