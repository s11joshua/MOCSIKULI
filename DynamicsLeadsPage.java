package Dynamics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Discovery.Helper;
import Discovery.JSON;
import Discovery.TestExecution;
import FactFind.FactFindExecutor;

public class DynamicsLeadsPage {
	static Log logger = LogFactory.getLog(DynamicsLeadsPage.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static WebDriver driver = null;
	
	static Pattern BusinessPhone;
	static Pattern Email;
	static Pattern HomePhone;
	static Pattern LeadFirstName;
	static Pattern LeadLastName;
	static Pattern SpouseFirst;
	static Pattern SpouseLast;
	static Pattern LeadSourceMinor;
	static Pattern Mobile;
	static Pattern PreferredMethod;
	static Pattern Savelead;
	static Pattern SavedSuccessfully;
	static Pattern ViewLead;
	static Pattern QualifyLead;
	static Pattern SendInvite;
	static Pattern SaveOpportunity;
	static Pattern PopUpOk;
	static Pattern OpportunityTypeDropdown;
	static Pattern CreateNewfullFormLead;
	static Pattern ScroolDown;
	static Pattern FullFormLeadSourceMinor;
	static Pattern SaveFullFormlead;
	
	public DynamicsLeadsPage(){
		new DynamicsLeadsPage(TestExecution.PatternRootFolderlocation+"Dynamics\\Leads\\");
	}
	
		
	public DynamicsLeadsPage(String Imagefolderlocation){
		
		BusinessPhone = new Pattern(Imagefolderlocation + "BusinessPhone.PNG");
		Email = new Pattern(Imagefolderlocation + "Email.PNG");
		HomePhone = new Pattern(Imagefolderlocation + "HomePhone.PNG");
		LeadFirstName = new Pattern(Imagefolderlocation + "LeadFirstName.PNG");
		LeadLastName = new Pattern(Imagefolderlocation + "LeadLastName.PNG");
		SpouseFirst = new Pattern(Imagefolderlocation + "SpouseFirstName.PNG");
		SpouseLast = new Pattern(Imagefolderlocation + "SpouseLastName.PNG");
		LeadSourceMinor = new Pattern(Imagefolderlocation + "LeadSourceMinor.PNG");
		Mobile = new Pattern(Imagefolderlocation + "Mobile.PNG");
		PreferredMethod = new Pattern(Imagefolderlocation + "PreferredMethod.PNG");
		Savelead = new Pattern(Imagefolderlocation + "SaveQuickLead.PNG");
		SavedSuccessfully = new Pattern(Imagefolderlocation + "LeadSavedSuccessfully.PNG");
		ViewLead = new Pattern(Imagefolderlocation + "ViewLead.PNG");
		QualifyLead = new Pattern(Imagefolderlocation + "QualifyLead.PNG");
		SendInvite = new Pattern(Imagefolderlocation + "SendInvite.PNG");
		SaveOpportunity = new Pattern(Imagefolderlocation + "SaveOpportunity.PNG");
		PopUpOk = new Pattern(Imagefolderlocation + "PopUpOk.PNG");
		OpportunityTypeDropdown = new Pattern(Imagefolderlocation + "OpportunityTypeDropdown.PNG");
		CreateNewfullFormLead = new Pattern(Imagefolderlocation + "CreateNew.PNG");
		ScroolDown = new Pattern(Imagefolderlocation + "ScroolDown.PNG");
		FullFormLeadSourceMinor = new Pattern(Imagefolderlocation + "FullFormLeadSourceMinor.PNG");
		SaveFullFormlead = new Pattern(Imagefolderlocation + "SaveFullFormLead.PNG");
		
	}
	
	public static boolean CreateLead(){
		String LeadType = JSON.GetTestData(TestExecution.JSONTestData,"LeadDetails").get("QuickCreateLead").toString();
		if (LeadType.equals("Yes")){
			if(CreateQuickLead() != true){
				return false;
			}
		}else{
			if(FullFormLead() != true){
				return false;
			}
		}
		return true;
	}
	
	static boolean CreateQuickLead(){
		
		String SendFactFindInvite = null;
		String FactFindInvitation = JSON.GetTestData(TestExecution.JSONTestData,"LeadDetails").get("SendFactFindInvitaion").toString();
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		JSONObject CustomerNames = null;
		JSONObject CustomerContact = null;
		String CustomerFirstName = null;
		String CustomerLastName = null;
		String CustomerEmailId = null;
		String SpouseFirstName = null;
		String SpouseLastName = null;
		int Numberofcustomer = 0;
				
		try {
		while (CustomerInformationArray.hasNext()){
				
						
			JSONObject CustomerInformation = CustomerInformationArray.next();
			CustomerNames = (JSONObject) CustomerInformation.get("CustomerNames");
			if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").toString().equals("Individual")){
				if (Numberofcustomer == 0){
					CustomerFirstName = CustomerNames.get("FirstName").toString();
					CustomerLastName = CustomerNames.get("LastName").toString();
					CustomerContact = (JSONObject) CustomerInformation.get("CustomerContactDetails");
					if(CustomerContact.get("EmailId") != null){
						CustomerEmailId = CustomerContact.get("EmailId").toString();
					}
					Numberofcustomer ++;
				}else if (Numberofcustomer > 0){
					SpouseFirstName = CustomerNames.get("FirstName").toString();
					SpouseLastName = CustomerNames.get("LastName").toString();
					break;
				}
			}
		}
		
		screen.click(DynamicsExecutor.QuickCreate);				
		screen.click(DynamicsExecutor.QuickCreateLead);
		Thread.sleep(5000);		
		screen.click(LeadFirstName);
		Thread.sleep(2000);
		screen.type(CustomerFirstName);
		screen.click(LeadLastName);
		screen.type(CustomerLastName);
		if (SpouseFirstName != null){
			screen.click(SpouseFirst);
			screen.type(SpouseFirstName);
			Helper.Keystrokeenter(1);
			if (SpouseLastName != null){
				screen.click(SpouseLast);
				screen.type(SpouseLastName);
				Helper.Keystrokeenter(1);
			}
		}
		
		screen.click(LeadSourceMinor);
		screen.type("Family&Friends");
		Helper.Keystrokeenter(1);
		Thread.sleep(2000);
		Helper.Keystrokeenter(1);
		screen.click(Email);
		if(CustomerEmailId != null){
			if (CustomerEmailId.equals("No")){
				//This is intentional, if we want the Email id to be blank
				SendFactFindInvite = "No";
			}
			else{
				screen.type(CustomerEmailId.toString());
				Helper.Keystrokeenter(1);
				Thread.sleep(2000);
				screen.click(Savelead);
				SendFactFindInvite = "Yes";
			}
		}else{
			screen.type(CustomerFirstName.toString()+"."
					+CustomerLastName.toString() + "@moctestdomain.com");
			Helper.Keystrokeenter(1);
			Thread.sleep(2000);
			screen.click(Savelead);
			SendFactFindInvite = "Yes";
		}
					
		screen.wait(SavedSuccessfully, 30);
		logger.info("Quick Lead Created Successfully");
		screen.wait(ViewLead, 30).click();			
		screen.wait(QualifyLead, 30).click();
		Thread.sleep(30000);
		if (screen.exists(PopUpOk) != null) {
			screen.click(PopUpOk);
		}
		if(screen.wait(SendInvite,60) == null){
			logger.error("Lead was not Qualified sucessfully");
			return false;
		}
		logger.info("Lead Qualified sucessfully");
		Thread.sleep(5000);
		screen.wait(OpportunityTypeDropdown,30);
		Helper.Keystrokedown(1);
		Helper.Keystrokeenter(1);
		screen.click(SaveOpportunity);
		Thread.sleep(10000);
		if(SendFactFindInvite.equals("Yes") && FactFindInvitation.equals("Yes")){
			screen.click(SendInvite);
			screen.wait(PopUpOk,30).click();
			logger.info("FactFind Invite has been sent to the Customer");
			screen.wait(PopUpOk,30).click();
		}
		
		Thread.sleep(3000);
		Helper.ScreenDump(TestExecution.TestExecutionFolder, "DynamicsQuickLead");
		logger.info("Quick Lead Created Sucessfully");
		
		return true;
		
		}catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			logger.error(e.toString());
			return false;
		}
				
	}
	
	static boolean FullFormLead(){
		String SendFactFindInvite = null;
		
		String FactFindInvitation = JSON.GetTestData(TestExecution.JSONTestData,"LeadDetails").get("SendFactFindInvitaion").toString();
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
				
		JSONObject CustomerNames = null;
		JSONObject CustomerContact = null;
		String CustomerFirstName = null;
		String CustomerLastName = null;
		String CustomerEmailId = null;
		String SpouseFirstName = null;
		String SpouseLastName = null;
		int Numberofcustomer = 0;
		
		try{
			Thread.sleep(10000);
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				CustomerNames = (JSONObject) CustomerInformation.get("CustomerNames");
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").toString().equals("Individual")){
					if (Numberofcustomer == 0){
						CustomerFirstName = CustomerNames.get("FirstName").toString();
						CustomerLastName = CustomerNames.get("LastName").toString();
						CustomerContact = (JSONObject) CustomerInformation.get("CustomerContactDetails");
						if(CustomerContact.get("EmailId") != null){
							CustomerEmailId = CustomerContact.get("EmailId").toString();
						}
						Numberofcustomer ++;
					}else if (Numberofcustomer > 0){
						SpouseFirstName = CustomerNames.get("FirstName").toString();
						SpouseLastName = CustomerNames.get("LastName").toString();
						if (CustomerEmailId == null){
							if(CustomerContact.get("EmailId") != null){
								CustomerEmailId = CustomerContact.get("EmailId").toString();
							}
						}
						break;
					}
				}
			}
			
			
			
			screen.click(DynamicsExecutor.Sales);				
			screen.wait(DynamicsExecutor.QuickCreateLead,15).click();
			screen.wait(CreateNewfullFormLead,10).click();
			Thread.sleep(20000);
			
			screen.wait(LeadFirstName,30).click();
			Thread.sleep(2000);
			screen.type(CustomerFirstName);
			screen.click(LeadLastName);
			screen.type(CustomerLastName);
			if (SpouseFirstName != null){
				screen.click(SpouseFirst);
				screen.type(SpouseFirstName);
				Helper.Keystrokeenter(1);
				if (SpouseLastName != null){
					screen.click(SpouseLast);
					screen.type(SpouseLastName);
					Helper.Keystrokeenter(1);
				}
			}
			
			screen.click(FullFormLeadSourceMinor);
			screen.type("Family&Friends");
			Helper.Keystrokeenter(1);
			Thread.sleep(2000);
			Helper.Keystrokeenter(1);
			ScroolDown(10);			
			screen.click(Email);
			
			if(CustomerEmailId != null){
				if (CustomerEmailId.equals("No")){
					//This is intentional, if we want the Email id to be blank
					SendFactFindInvite = "No";
				}
				else{
					screen.type(CustomerEmailId.toString());
					Helper.Keystrokeenter(1);
					Thread.sleep(2000);
					screen.click(SaveFullFormlead);
					SendFactFindInvite = "Yes";
				}
			}else{
				screen.type(CustomerFirstName.toString()+"."
						+CustomerLastName.toString() + "@moctestdomain.com");
				Helper.Keystrokeenter(1);
				Thread.sleep(2000);
				screen.click(SaveFullFormlead);
				SendFactFindInvite = "Yes";
			}
			
			screen.wait(QualifyLead, 30);
			logger.info("Lead Created Successfully");
			screen.wait(QualifyLead, 30).click();
			Thread.sleep(30000);
			if (screen.exists(PopUpOk) != null) {
				screen.click(PopUpOk);
			}
			
			if(screen.wait(SendInvite,60) == null){
				logger.error("Lead was not Qualified sucessfully");
				return false;
			}
			logger.info("Lead Qualified sucessfully");
			Thread.sleep(5000);
			screen.wait(OpportunityTypeDropdown,30);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			screen.click(SaveOpportunity);
			Thread.sleep(10000);
			if(SendFactFindInvite.equals("Yes") && FactFindInvitation.equals("Yes")){
				screen.click(SendInvite);
				screen.wait(PopUpOk,30).click();
				logger.info("FactFind Invite has been sent to the Customer");
				screen.wait(PopUpOk,30).click();
			}
			
			Thread.sleep(3000);
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "DynamicsQuickLead");
			logger.info("Full Form Lead Created Sucessfully");			
			
			return true;
			
		}catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			logger.error(e.toString());
			return false;
		}
		
	}
	
	static void ScroolDown(int NumberOfClicks){
		int Counter = 0;
		while (Counter <= NumberOfClicks){
			try {
				screen.click(ScroolDown);
				Thread.sleep(250);
				Counter ++;
			} catch (FindFailed | InterruptedException e) {
				e.printStackTrace();
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				logger.error(e.toString());
			}
		}
	}
}
