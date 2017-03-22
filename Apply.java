package Discovery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Apply {
	static Log logger = LogFactory.getLog(Apply.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	
	static Pattern AddThirdParty;
	static Pattern ApplicationSubmit;
	static Pattern Apply;
	static Pattern ApplyOnlineNewApplication;
	static Pattern CreateNewApplicationFromPrevious;
	static Pattern ManualApply;
	static Pattern SuccessResponse;
	static Pattern UpdateApplyOnline;
	static Pattern ApplyOnlineMCLogo;

	
	public Apply(){
		new Apply(TestExecution.PatternRootFolderlocation+"Apply\\");
	}
	
	public Apply(String Imagefolderlocation){
		AddThirdParty = new Pattern(Imagefolderlocation + "AddThirdParty.PNG");
		ApplicationSubmit = new Pattern(Imagefolderlocation + "ApplicationSubmit.PNG");
		Apply = new Pattern(Imagefolderlocation + "Apply.PNG");
		ApplyOnlineNewApplication = new Pattern(Imagefolderlocation + "ApplyOnlineNewApplication.PNG");
		CreateNewApplicationFromPrevious = new Pattern(Imagefolderlocation + "CreateNewApplicationFromPrevious.PNG");
		ManualApply = new Pattern(Imagefolderlocation + "ManualApply.PNG");
		SuccessResponse = new Pattern(Imagefolderlocation + "SuccessResponse.PNG");
		UpdateApplyOnline = new Pattern(Imagefolderlocation + "UpdateApplyOnline.PNG");
		ApplyOnlineMCLogo = new Pattern(Imagefolderlocation + "ApplyOnlineMCLogo.PNG");
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
				logger.error("Invalid Options for the Lodgement type");
				Helper.WriteToTxtFile("Invalid Options for the Lodgement type", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}
			
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "ApplyScreen_BeforeSubmission");
			screen.click(ApplicationSubmit);
			
			
			if (screen.exists(SuccessResponse,110) == null){
				App.focus("Qualifier Analyser");
				if(screen.exists(SuccessResponse,10) == null){
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "ApplyScreen_AfterSubmission");
					logger.info("The application was not sent successfully to Broker Center or the wait counter expired.");
					return false;
				}
			}
			
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "ApplyScreen_AfterSubmission");
			logger.info("Application was submitted successfully");
			Helper.WriteToTxtFile("Application was submitted successfully", TestExecution.TestExecutionFolder + "logs.txt");
			App.pause(10);//This is to wait for broker center to be opened .. this should be changed to dynamically wait for BC to open.
			App.focus("Qualifier Analyser");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static boolean UpdateApplyOnline(){
		
		try {
			Thread.sleep(2000);
			screen.click(Apply);
			Thread.sleep(5000);
			screen.wait(UpdateApplyOnline).click();
			screen.wait(ApplyOnlineMCLogo,120);
			return true;
		} catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
		
		
	}
}
