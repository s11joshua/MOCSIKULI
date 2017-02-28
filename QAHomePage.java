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


public class QAHomePage {
	
	static Log logger = LogFactory.getLog(QAHomePage.class);
	
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	
	static Pattern clientsearch;
	static Pattern quickqualify;
	static Pattern SelectOpportunity;
	static Pattern NewScenario;
	
	public QAHomePage(){
		new QAHomePage("C:\\Sikuli Images\\QA Homepage\\");
	}
	
	public QAHomePage(String Imagefolderlocation){
		clientsearch = new Pattern(Imagefolderlocation + "Clienttextbox.PNG");
		quickqualify = new Pattern(Imagefolderlocation + "QuickQualify.PNG");
		SelectOpportunity = new Pattern(Imagefolderlocation + "SelectOpportunity.PNG");
		NewScenario = new Pattern(Imagefolderlocation + "NewScenario.PNG");
	}
	
	public static boolean QuickQualify(JSONObject RawFile){
		JSONObject LeadDetails =  (JSONObject) RawFile.get("LeadDetails");
		
		JSONArray CustomerInformation_Array = (JSONArray) RawFile.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		JSONObject CustomerInformation = CustomerInformationArray.next();
		
		try {
			if(LeadDetails.get("LeadOrigination").toString().equals("Dynamics")){
				screen.click(SelectOpportunity);
				App.pause(2);
				screen.click(NewScenario);
				App.pause(10);
				return true;
			}else{
				logger.debug("Entering the Quick Qualify Screen");
				App.pause(5);
				App.focus("Qualifier Analyser");
				App.pause(5);
				App.focus("Qualifier Analyser");
				screen.click(clientsearch);
				screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString() + JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString());
				screen.click(quickqualify);
				return true;
			}	
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
}
