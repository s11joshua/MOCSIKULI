import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Apply {
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	static Pattern AddThirdParty;
	static Pattern ApplicationSubmit;
	static Pattern Apply;
	static Pattern ApplyOnlineNewApplication;
	static Pattern CreateNewApplicationFromPrevious;
	static Pattern ManualApply;

	
	public Apply(){
		new Apply("C:\\Sikuli Images\\Apply\\");
	}
	
	public Apply(String Imagefolderlocation){
		AddThirdParty = new Pattern(Imagefolderlocation + "AddThirdParty.PNG");
		ApplicationSubmit = new Pattern(Imagefolderlocation + "ApplicationSubmit.PNG");
		Apply = new Pattern(Imagefolderlocation + "Apply.PNG");
		ApplyOnlineNewApplication = new Pattern(Imagefolderlocation + "ApplyOnlineNewApplication.PNG");
		CreateNewApplicationFromPrevious = new Pattern(Imagefolderlocation + "CreateNewApplicationFromPrevious.PNG");
		ManualApply = new Pattern(Imagefolderlocation + "ManualApply.PNG");
	}
	
	public static boolean CaptureTypeOfLodgement(JSONObject RawFile) {
		JSONObject LodgementType = (JSONObject) RawFile.get("Apply");
		try {
			App.pause(2);
			screen.click(Apply);
			App.pause(10);
			screen.wait(ApplyOnlineNewApplication);
			
			//Add third party details not implemented.
			
			if(LodgementType.get("ApplyOnlineNewApplication") != null && LodgementType.get("ApplyOnlineNewApplication").toString().equals("Apply")){
				screen.click(ApplyOnlineNewApplication);
			}
			else if(LodgementType.get("CreateNewApplicationFromPrevious") != null && LodgementType.get("CreateNewApplicationFromPrevious").toString().equals("Apply")){
				screen.click(CreateNewApplicationFromPrevious);
				if(LodgementType.get("PreviousApplicationNumber") != null){
					screen.find(CreateNewApplicationFromPrevious).right(Offset[2]).click();
					Helper.ClearTextBoxandEnterValue(LodgementType.get("PreviousApplicationNumber").toString());
					Helper.Keystrokeenter(1);
				}
			}
			else if(LodgementType.get("ManualApply") != null && LodgementType.get("ManualApply").toString().equals("Apply")){
				screen.click(ManualApply);
			}
			else{
				System.out.println("Invalid Options for the Lodgementype");
				Helper.WriteToTxtFile("Invalid Options for the Lodgementype", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "ApplyScreen_BeforeSubmission");
			//screen.click(ApplicationSubmit);
			//App.pause(15);
			//Helper.ScreenDump(TestExecution.TestExecutionFolder, "ApplyScreen_AfterSubmission");
			
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
}
