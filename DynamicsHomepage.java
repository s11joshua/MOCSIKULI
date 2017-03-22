package Dynamics;

import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import Discovery.Helper;
import Discovery.TestExecution;

public class DynamicsHomepage {
	
	static Log logger = LogFactory.getLog(DynamicsHomepage.class);
	static Screen screen = new Screen();
		
		public static boolean SearchOpportunity(){
						
			
			try {
				System.out.println("entering the Search Opportunity");		
				Thread.sleep(2000);
				screen.click(DynamicsExecutor.SearchItem);
				System.out.println("after search item click");	
				screen.type(GetOpportunityTitle());
				Helper.Keystrokeenter(1);
				screen.wait(DynamicsExecutor.SelectOpportunity,30).click();
				screen.wait(DynamicsLeadsPage.OpportunityTypeDropdown,30);
				Helper.Keystrokedown(1);
				Helper.Keystrokeenter(1);
				screen.click(DynamicsLeadsPage.SaveOpportunity);
				Thread.sleep(10000);
				return true;
			} catch (InterruptedException | FindFailed e) {
				e.printStackTrace();
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				logger.error(e.toString());
				return false;
			}
			
			
			
		}
		
		public static String GetOpportunityTitle(){
			
			String OpportunityTile = null;
			
			JSONObject CustomerNames = null;
			String CustomerFirstName = null;
			String CustomerLastName = null;
			String SpouseFirstName = null;
			String SpouseLastName = null;
			int Numberofcustomer = 0;
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				CustomerNames = (JSONObject) CustomerInformation.get("CustomerNames");
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").toString().equals("Individual")){
					if (Numberofcustomer == 0){
						CustomerFirstName = CustomerNames.get("FirstName").toString();
						CustomerLastName = CustomerNames.get("LastName").toString();
						OpportunityTile = CustomerFirstName + " " + CustomerLastName;
						Numberofcustomer ++;
					}else if (Numberofcustomer > 0){
						SpouseFirstName = CustomerNames.get("FirstName").toString();
						SpouseLastName = CustomerNames.get("LastName").toString();
						OpportunityTile = OpportunityTile + "&" + SpouseFirstName + " " + SpouseLastName;
						break;
					}
				}
			}
			
			if (OpportunityTile != null){
				return OpportunityTile;
			}else{
				return null;
			}
		}
		
		public static boolean SendFactFindInvite(){
			boolean SendInvite = false;
			
			JSONObject CustomerNames = null;
			JSONObject CustomerContact = null;
			//String CustomerFirstName = null;
			//String CustomerLastName = null;
			String CustomerEmailId = null;
			//String SpouseFirstName = null;
			//String SpouseLastName = null;
			int Numberofcustomer = 0;
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				CustomerNames = (JSONObject) CustomerInformation.get("CustomerNames");
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").toString().equals("Individual")){
					if (Numberofcustomer == 0){
						//CustomerFirstName = CustomerNames.get("FirstName").toString();
						//CustomerLastName = CustomerNames.get("LastName").toString();
						CustomerContact = (JSONObject) CustomerInformation.get("CustomerContactDetails");
						if(CustomerContact.get("EmailId") != null){
							CustomerEmailId = CustomerContact.get("EmailId").toString();
						}
						Numberofcustomer ++;
					}else if (Numberofcustomer > 0){
						//SpouseFirstName = CustomerNames.get("FirstName").toString();
						//SpouseLastName = CustomerNames.get("LastName").toString();
						break;
					}
				}
			}
			
			if(CustomerEmailId != null){
				if (CustomerEmailId.equals("No")){
					//This is intentional, if we want the Email id to be blank
					SendInvite = false;
				}
				else{
					SendInvite = true;
				}
			}else{
				SendInvite = true;
			}
			
			if(SendInvite == true){
				try {
					screen.click(DynamicsLeadsPage.SendInvite);
					screen.wait(DynamicsLeadsPage.PopUpOk,30).click();
					logger.info("FactFind Invite has been sent to the Customer");
					screen.wait(DynamicsLeadsPage.PopUpOk,30).click();
					return true;
					
				} catch (FindFailed e) {
					e.printStackTrace();
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
					logger.error(e.toString());
					return false;
				}
				
			}else{
				return false;
			}
			
		}
	
}
