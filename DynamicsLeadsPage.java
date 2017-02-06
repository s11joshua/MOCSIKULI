package Dynamics;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Discovery.Helper;
import Discovery.TestExecution;

public class DynamicsLeadsPage {
	static Log logger = LogFactory.getLog(DynamicsLeadsPage.class);
	static Screen screen = new Screen();
	
	static Pattern BusinessPhone;
	static Pattern Email;
	static Pattern HomePhone;
	static Pattern LeadFirstName;
	static Pattern LeadLastName;
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
	
	public DynamicsLeadsPage(){
		new DynamicsLeadsPage("C:\\Sikuli Images\\Dynamics\\Leads\\");
	}
	
	public DynamicsLeadsPage(String Imagefolderlocation){
		BusinessPhone = new Pattern(Imagefolderlocation + "BusinessPhone.PNG");
		Email = new Pattern(Imagefolderlocation + "Email.PNG");
		HomePhone = new Pattern(Imagefolderlocation + "HomePhone.PNG");
		LeadFirstName = new Pattern(Imagefolderlocation + "LeadFirstName.PNG");
		LeadLastName = new Pattern(Imagefolderlocation + "LeadLastName.PNG");
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
	}
		
	public static boolean CreateQuickLead(JSONObject CustomerNames,JSONObject CustomerContacts){
		
		try {
			screen.click(LeadFirstName);
			screen.type(CustomerNames.get("FirstName").toString());
			screen.click(LeadLastName);
			screen.type(CustomerNames.get("LastName").toString());
			screen.click(LeadSourceMinor);
			screen.type("Family&Friends");
			Helper.Keystrokeenter(1);
			Thread.sleep(2000);
			Helper.Keystrokeenter(1);
			screen.click(Email);
			if(CustomerContacts.get("EmailId") != null){
				//screen.type(CustomerContacts.get("EmailId").toString());
				screen.type("suresh.anthony@mortgagechoice.com.au");
				Helper.Keystrokeenter(1);
				Thread.sleep(2000);
				screen.click(Savelead);
			}else{
				logger.warn("No Email Id provided, so cannot send customer enquiry form");
			}
			screen.wait(SavedSuccessfully, 30);
			logger.info("Quick Lead Created Successfully");
			screen.wait(ViewLead, 30).click();			
			screen.wait(QualifyLead, 30).click();
			if (screen.exists(PopUpOk,30) != null){
				screen.click(PopUpOk);
			}
			if(screen.wait(SendInvite,30) == null){
				logger.error("Lead was not Qualified sucessfully");
				return false;
			}
			logger.info("Lead Qualified sucessfully");
			Thread.sleep(5000);
			screen.wait(OpportunityTypeDropdown,30);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			screen.click(SaveOpportunity);
			Thread.sleep(3000);
			if(CustomerContacts.get("EmailId") != null){
				screen.click(SendInvite);
				screen.wait(PopUpOk,30).click();
			}
			screen.wait(PopUpOk,30).click();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "DynamicsQuickLead");
			logger.info("Quick Lead Created Sucessfully");
			return true;
		} catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			logger.error(e.toString());
			return false;
		}
				
	}

}
