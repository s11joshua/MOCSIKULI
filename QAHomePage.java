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
	static Pattern clientsearch;
	static Pattern quickqualify;
	
	public QAHomePage(){
		new QAHomePage("C:\\Sikuli Images\\QA Homepage\\");
	}
	
	public QAHomePage(String Imagefolderlocation){
		clientsearch = new Pattern(Imagefolderlocation + "Clienttextbox.PNG");
		quickqualify = new Pattern(Imagefolderlocation + "QuickQualify.PNG");
	}
	
	public static boolean QuickQualify(JSONObject RawFile){
		JSONArray CustomerInformation_Array = (JSONArray) RawFile.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		JSONObject CustomerInformation = CustomerInformationArray.next();
		
		try {
			App.focus("Qualifier Analyser");
			screen.click(clientsearch);
			screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString() + JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString());
			screen.click(quickqualify);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
}
