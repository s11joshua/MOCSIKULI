package Dynamics;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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


public class DynamicsExecutor {
	static Log logger = LogFactory.getLog(DynamicsExecutor.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	public static WebDriver driver = null;
	
	static Pattern sales;
	static Pattern QuickCreate;
	static Pattern QuickCreateLead;
	static Pattern QuickContact;
	static Pattern QuickOpportunity;
	static Pattern SelectOpportunity;
	static Pattern Sales;
	public static Pattern SearchItem;
	
	public DynamicsExecutor(){
		new DynamicsExecutor(TestExecution.PatternRootFolderlocation+"Dynamics\\");
	}
	public DynamicsExecutor(String Imagefolderlocation){
		sales = new Pattern (Imagefolderlocation + "Sales.PNG");
		QuickCreate = new Pattern (Imagefolderlocation + "QuickCreate.PNG");
		QuickCreateLead = new Pattern (Imagefolderlocation + "QuickLead.PNG");
		QuickContact = new Pattern (Imagefolderlocation + "QuickContact.PNG");
		QuickOpportunity = new Pattern (Imagefolderlocation + "QuickOpportunity.PNG");
		SelectOpportunity = new Pattern (Imagefolderlocation + "SelectOpportunity.PNG");
		Sales = new Pattern (Imagefolderlocation + "Sales.PNG");
		SearchItem = new Pattern (Imagefolderlocation + "SearchItem.PNG");
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
		
		
		driver = DynamicsExecutor.LaunchBrowser(Config.GetConfigParameter("BrowserType"));
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
		
		if(DynamicsExecutor.LogintoDynamics() != true){
			return false;
		}
		
		if (DynamicsLeadsPage.CreateLead() != true){
			driver.close();
			return false;
		}else{
			driver.close();
			return true;
		}
		
	}
 
}
