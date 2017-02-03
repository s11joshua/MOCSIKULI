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

public class SaveScenario {
	static Log logger = LogFactory.getLog(SaveScenario.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	static Pattern AmountRequested;
	static Pattern FOLead;
	static Pattern LeadSourceMajor;
	static Pattern LeadSourceMinor;
	static Pattern LoanConsultant;
	static Pattern NoButton;
	static Pattern OkButton;
	static Pattern OpportunityName;
	static Pattern ScenarioName;
	static Pattern Savebutton;
	static Pattern JointClentOK;

	public SaveScenario(){
		new SaveScenario("C:\\Sikuli Images\\SaveScenario\\");
	}
	
	public SaveScenario(String Imagefolderlocation){
		AmountRequested = new Pattern(Imagefolderlocation + "AmountRequested.PNG");
		FOLead = new Pattern(Imagefolderlocation + "FOLead.PNG");
		LeadSourceMajor = new Pattern(Imagefolderlocation + "LeadSourceMajor.PNG");
		LeadSourceMinor = new Pattern(Imagefolderlocation + "LeadSourceMinor.PNG");
		LoanConsultant = new Pattern(Imagefolderlocation + "LoanConsultant.PNG");
		NoButton = new Pattern(Imagefolderlocation + "NoButton.PNG");
		OkButton = new Pattern(Imagefolderlocation + "OkButton.PNG");
		OpportunityName = new Pattern(Imagefolderlocation + "OpportunityName.PNG");
		ScenarioName = new Pattern(Imagefolderlocation + "ScenarioName.PNG");
		Savebutton = new Pattern(Imagefolderlocation + "ScenarioSave.PNG");
		JointClentOK = new Pattern(Imagefolderlocation + "JointClientOk.PNG");
	}
	
	public static boolean SaveAsNewLead(JSONObject RawFile){
		logger.debug("Entering save as new lead section");
		JSONObject SaveScenarioDetails = JSON.GetTestData(RawFile, "SaveScenario");		
		try {
			screen.wait(Savebutton);
			screen.click(Savebutton);
			
			
			if(JSON.GetTestData(RawFile, "EnvironmentDetails").get("Replicadatabase").toString().equals("Yes")){
				screen.wait(NoButton);
				screen.click(NoButton);
			}
			
			if(IsJointClient(RawFile) == true){
				screen.wait(JointClentOK);
				screen.click(JointClentOK);
			}
			
			App.pause(4);
			App.focus("Sceanrio Details");
						
			//Not implemented LinkLeadtoScenario which is there in the JSON File, all save are assumed to be new lead.
			
			if (SaveScenarioDetails.get("LoanConsultant") != null && Integer.parseInt(SaveScenarioDetails.get("LoanConsultant").toString()) >=1 ){
				screen.find(LoanConsultant).right(Offset[2]).click();
				Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("LoanConsultant").toString()));
				Helper.Keystrokeenter(1);
			}else{
				System.out.println("Invalid Parameter passed for loan Consultant in SaveScenario");
				Helper.WriteToTxtFile("Invalid Parameter passed for loan Consultant in SaveScenario", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}
			if (SaveScenarioDetails.get("OpportunityName") != null){
				screen.find(OpportunityName).right(Offset[2]).click();
				Helper.ClearTextBox(30, (float) 0.1);
				Helper.ClearTextBox(100);
				Helper.ClearTextBoxandEnterValue(SaveScenarioDetails.get("OpportunityName").toString());
				Helper.Keystrokeenter(1);
			}
			if (SaveScenarioDetails.get("ScenarioName") != null){
				screen.find(ScenarioName).right(Offset[2]).click();
				Helper.ClearTextBox(30, (float) 0.1);
				Helper.ClearTextBox(100);
				Helper.ClearTextBoxandEnterValue(SaveScenarioDetails.get("ScenarioName").toString());
				Helper.Keystrokeenter(1);
			}
			if (SaveScenarioDetails.get("LeadSourceMajor") != null && Integer.parseInt(SaveScenarioDetails.get("LeadSourceMajor").toString()) >=1 ){
				screen.find(LeadSourceMajor).right(Offset[2]).click();
				Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("LeadSourceMajor").toString()));
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid Parameter passed for LeadSourceMajor in SaveScenario");
				return false;
			}
			if (SaveScenarioDetails.get("LeadSourceMinor") != null && Integer.parseInt(SaveScenarioDetails.get("LeadSourceMinor").toString()) >=1 ){
				screen.find(LeadSourceMinor).right(Offset[2]).click();
				Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("LeadSourceMinor").toString()));
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid Parameter passed for LeadSourceMinor in SaveScenario");
				return false;
			}
			if (SaveScenarioDetails.get("FOLead") != null ){
				if(Integer.parseInt(SaveScenarioDetails.get("FOLead").toString()) >=1 ){			
					screen.find(FOLead).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("FOLead").toString()));
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid Parameter passed for FOLead in SaveScenario");
					return false;
				}				
			}
			//Not implemented Amount which is there in the JSON File
			
			screen.click(OkButton);
			screen.waitVanish(OkButton,15);
			App.pause(10); // this is for the pop up screen to disappear
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "SaveScenario");
			logger.info("Scenario Saved as a new lead successfully");
			Helper.WriteToTxtFile("Scenario Saved as a new lead successfully", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}
	
	public static boolean Save(JSONObject RawFile){
		logger.debug("Entering save application section");
		try {
			screen.wait(Savebutton);
			screen.click(Savebutton);
			App.pause(10);
			logger.info("Scenario Saved Successfully");
			Helper.WriteToTxtFile("Scenario Saved Successfully", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static boolean IsJointClient(JSONObject RawFile){
		int Counter = 0;
		JSONArray CustomerInformation_Array = (JSONArray) RawFile.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		while (CustomerInformationArray.hasNext()){
			JSONObject CustomerInformation = CustomerInformationArray.next();
			if (CustomerInformation.get("CustomerType").toString().equals("Individual")){
				Counter++;
			}
		}
		if (Counter >= 2){
			return true;
		}else{
			return false;
		}
	}
}
