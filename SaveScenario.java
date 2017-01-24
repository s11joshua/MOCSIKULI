import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SaveScenario {
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
	}
	
	public static boolean SaveAsNewLead(JSONObject RawFile){
		JSONObject SaveScenarioDetails = JSON.GetTestData(RawFile, "SaveScenario");		
		try {
			screen.wait(Savebutton);
			screen.click(Savebutton);
			if(JSON.GetTestData(RawFile, "EnvironmentDetails").get("Replicadatabase").toString().equals("Yes")){
				screen.wait(NoButton);
				screen.click(NoButton);
			}
			App.pause(2);
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
				System.out.println("Invalid Parameter passed for LeadSourceMajor in SaveScenario");
				Helper.WriteToTxtFile("Invalid Parameter passed for LeadSourceMajor in SaveScenario", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}
			if (SaveScenarioDetails.get("LeadSourceMinor") != null && Integer.parseInt(SaveScenarioDetails.get("LeadSourceMinor").toString()) >=1 ){
				screen.find(LeadSourceMinor).right(Offset[2]).click();
				Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("LeadSourceMinor").toString()));
				Helper.Keystrokeenter(1);
			}else{
				System.out.println("Invalid Parameter passed for LeadSourceMinor in SaveScenario");
				Helper.WriteToTxtFile("Invalid Parameter passed for LeadSourceMinor in SaveScenario", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}
			if (SaveScenarioDetails.get("FOLead") != null ){
				if(Integer.parseInt(SaveScenarioDetails.get("FOLead").toString()) >=1 ){			
					screen.find(FOLead).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(SaveScenarioDetails.get("FOLead").toString()));
					Helper.Keystrokeenter(1);
				}else{
					System.out.println("Invalid Parameter passed for FOLead in SaveScenario");
					Helper.WriteToTxtFile("Invalid Parameter passed for FOLead in SaveScenario", TestExecution.TestExecutionFolder + "logs.txt");
					return false;
				}				
			}
			//Not implemented Amount which is there in the JSON File
			
			screen.click(OkButton);
			screen.waitVanish(OkButton,15);
			App.pause(10); // this is for the pop up screen to disappear
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "SaveScenario");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}
	
	public static boolean Save(JSONObject RawFile){
		try {
			screen.wait(Savebutton);
			screen.click(Savebutton);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
}
