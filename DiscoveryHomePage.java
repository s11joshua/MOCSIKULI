package Discovery;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class DiscoveryHomePage {
	static Log logger = LogFactory.getLog(DiscoveryHomePage.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static String FirstName;
	public static String LastName;
	public static String JointName;
	static Pattern qashortcut;
	static Pattern FindCustomer;
	static Pattern GotoQAHomepage;
	static Pattern loanApplicationTab;
	static Pattern QueryFirstName;
	static Pattern QuerySurname;
	static Pattern RunQuery;
	static Pattern SelectOpportunity;
	
	
	public DiscoveryHomePage(){
		new DiscoveryHomePage("C:\\Sikuli Images\\DiscoveryHomePage\\");
	}
	
	public DiscoveryHomePage(String Imagefolderlocation){
		qashortcut = new Pattern(Imagefolderlocation + "Q&A_shortcut.PNG");
		FindCustomer = new Pattern(Imagefolderlocation + "FindCustomer.PNG");
		GotoQAHomepage = new Pattern(Imagefolderlocation + "GotoQAHomepage.PNG");
		loanApplicationTab = new Pattern(Imagefolderlocation + "LoanApplication.PNG");
		QueryFirstName = new Pattern(Imagefolderlocation + "QueryFirstName.PNG");
		QuerySurname = new Pattern(Imagefolderlocation + "QueryLastSurname.PNG");
		RunQuery = new Pattern(Imagefolderlocation + "RunQuery.PNG");
		SelectOpportunity = new Pattern(Imagefolderlocation + "SelectOpportunity.PNG");
		
	}
	
	public static boolean NavigatetoQualifyandAnalize(){
		JSONObject LeadDetails =  (JSONObject) TestExecution.JSONTestData.get("LeadDetails");
		
		try {
			if(LeadDetails.get("LeadOrigination").toString().equals("Dynamics")){
				screen.wait(loanApplicationTab,15);
				screen.click(loanApplicationTab);
				Helper.Keystrokedown(4);
				Helper.Keystrokeenter(1);
				App.pause(3);
				screen.click(RunQuery);
				App.pause(3);
				getNames();
				if (JointName != null){
					screen.find(QuerySurname).below(Offset[1]).click();
					screen.type(JointName);
				}else{
					screen.find(QueryFirstName).below(Offset[1]).click();
					screen.type(FirstName);
					screen.find(QuerySurname).below(Offset[1]).click();
					screen.type(LastName);
				}
				Helper.Keystrokeenter(1);
				App.pause(2);
				screen.find(QuerySurname).below(Offset[3]).doubleClick();
				App.pause(5);
				screen.click(GotoQAHomepage);
				screen.wait(QAHomePage.SelectOpportunity,15);
				return true;
			}else{
				screen.wait(qashortcut,15);
				screen.doubleClick(qashortcut);
				App.pause(15);
				logger.debug("Completed the double click on the Discovery Home Page for QA shortcut");
				return true;
			}
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}
	
	static void getNames(){
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		FirstName = null;
		LastName = null;
		JointName= null;
		String FirstCustomerFlag = "Yes";
		while (CustomerInformationArray.hasNext()){
			JSONObject CustomerInformation = CustomerInformationArray.next();
			if(CustomerInformation.get("IsApplicant").equals("Yes") && CustomerInformation.get("CustomerType").equals("Individual")){
				if(FirstCustomerFlag.equals("Yes")){
					FirstCustomerFlag = "No";
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName") != null){
						FirstName = JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString(); 
					}
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName") != null){
						LastName = JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString();
					}
				}else if(FirstCustomerFlag.equals("No")){
					JointName = FirstName + " " + LastName + " & ";
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName") != null){
						JointName = JointName + JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString() + " "; 
					}
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName") != null){
						JointName = JointName + JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString();
					}
					return;
				}				
				
			}
		}
		return;
	}
	
}
