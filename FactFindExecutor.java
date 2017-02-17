package FactFind;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import Discovery.ClientInformation;
import Discovery.Helper;
import Discovery.JSON;
import Discovery.LoginPage;
import Discovery.TestExecution;
import Dynamics.Selenium;

public class FactFindExecutor {
	static Log logger = LogFactory.getLog(FactFindExecutor.class);
	public static WebDriver driver = null;
		
	public static boolean FillFactFind(){
		
		Helper Config = new Helper();
		driver = Selenium.LaunchBrowser(Config.GetConfigParameter("BrowserType"));
		driver.get(Config.GetConfigParameter("FactFindURL"));
		driver.manage().window().maximize();
		new FactFindLogin(driver);
		new PersonalDetails(driver);
		new AddressDetails(driver);
		new EmploymentDetails(driver);
		new FactFindAssets(driver);
		
		if (FactFindLogin.LoginFactFindFirstTime(ReturnCustomerUserId()) == false){return false;}
		//if (PersonalDetails.CustomerPersonalDetails() == false){return false;}
		//if (AddressDetails.EnterAddressDetails() == false){return false;}
		//if (EmploymentDetails.EnterEmploymentDetails() == false){return false;}
		if (FactFindAssets.EnterAssetDetails() == false){return false;}
		//driver.close();
		return true;
	}
	
	static String ReturnCustomerUserId(){
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		while (CustomerInformationArray.hasNext()){
			logger.debug("Looping through the number of customer");
			JSONObject CustomerInformation = CustomerInformationArray.next();
		if(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("EmailId") != null){
			if (JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("EmailId").toString().equals("No")){
				//This is intentional since if we want the Email id to be blank
				logger.warn("Might not be able to send fact find form unless "
						+ "email id is specified when creating the lead in dynamics");
				
				logger.info("Email Id passed to Dynamics DB for retreival :" + null);
				return null;
			}else{
				logger.info("Email Id passed to Dynamics DB for retreival :" + JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("EmailId").toString());
				return JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("EmailId").toString();
			}
		}else{
			logger.info("Email Id passed to Dynamics DB for retreival :" + JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString()+"."
					+JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString() + "@moctestdomain.com");
			return JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString()+"."
					+JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString() + "@moctestdomain.com";
		}
		
		}
		return null;
	}
}
