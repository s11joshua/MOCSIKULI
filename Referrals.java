package Discovery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Referrals {
	
	static Log logger = LogFactory.getLog(Referrals.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern AllianceOptOut;
	static Pattern BuildingInsurance;
	static Pattern PreferredContactTime;
	static Pattern ContentsInsurance;
	static Pattern LandLordInsurance;
	static Pattern MotorInsurance;
	static Pattern OptOutReason;
	static Pattern PreApproval;
	static Pattern PropertyBuiltYear;
	static Pattern Referrals;

	
	public Referrals(){
		new Referrals(TestExecution.PatternRootFolderlocation+"Referrals\\");
	}
	
	public Referrals(String Imagefolderlocation){
		AllianceOptOut = new Pattern(Imagefolderlocation + "AllianceOptOut.PNG");
		BuildingInsurance = new Pattern(Imagefolderlocation + "BuildingInsurance.PNG");
		PreferredContactTime = new Pattern(Imagefolderlocation + "PreferredContactTime.PNG");
		ContentsInsurance = new Pattern(Imagefolderlocation + "ContentsInsurance.PNG");
		LandLordInsurance = new Pattern(Imagefolderlocation + "LandLordInsurance.PNG");
		MotorInsurance = new Pattern(Imagefolderlocation + "MotorInsurance.PNG");
		OptOutReason = new Pattern(Imagefolderlocation + "OptOutReason.PNG");
		PreApproval = new Pattern(Imagefolderlocation + "PreApproval.PNG");
		PropertyBuiltYear = new Pattern(Imagefolderlocation + "PropertyBuiltYear.PNG");
		Referrals = new Pattern(Imagefolderlocation + "Referrals.PNG");

	}
	
	public static boolean CaptureReferrals(JSONObject RawFile){
		logger.debug("Entering Capture referrals section");
		JSONObject ReferralsData = JSON.GetTestData(RawFile, "Referrals");
		try{
			App.pause(1);
			screen.click(Referrals);
			screen.wait(AllianceOptOut);
			if(ReferralsData.get("AllianceOptOut") != null && ReferralsData.get("AllianceOptOut").toString().equals("Check")){
				screen.click(AllianceOptOut);
				if(ReferralsData.get("OptOutReason") != null){
					screen.click(OptOutReason);
					Helper.ClearTextBoxandEnterValue(ReferralsData.get("OptOutReason").toString());
					Helper.Keystrokeenter(1);
				}
			}else{
				if(ReferralsData.get("MotorInsurance") != null && ReferralsData.get("MotorInsurance").toString().equals("Check")){
					screen.click(MotorInsurance);
				}
				if(ReferralsData.get("BuildingInsurance") != null && ReferralsData.get("BuildingInsurance").toString().equals("Check")){
					screen.click(BuildingInsurance);
				}
				if(ReferralsData.get("LandLordInsurance") != null && ReferralsData.get("LandLordInsurance").toString().equals("Check")){
					screen.click(LandLordInsurance);
				}
				if(ReferralsData.get("ContentsInsurance") != null && ReferralsData.get("ContentsInsurance").toString().equals("Check")){
					screen.click(ContentsInsurance);
				}
				if(ReferralsData.get("PreferredContactTime") != null && Integer.parseInt(ReferralsData.get("PreferredContactTime").toString()) >= 1){
					screen.click(PreferredContactTime);
					Helper.Keystrokedown(Integer.parseInt(ReferralsData.get("PreferredContactTime").toString()));
					Helper.Keystrokeenter(1);
				}
				if(ReferralsData.get("PropertyBuiltYear") != null && Integer.parseInt(ReferralsData.get("PropertyBuiltYear").toString()) >= 1){
					screen.find(PropertyBuiltYear).below(Offset[2]).click();
					Helper.ClearTextBoxandEnterValue(ReferralsData.get("PropertyBuiltYear").toString());
					Helper.Keystrokeenter(1);
				}
				if(ReferralsData.get("PreApproval") != null && ReferralsData.get("PreApproval").toString().equals("Check")){
					screen.click(PreApproval);
				}
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Referrals");
			logger.info("Referrals section completed successfully");
			Helper.WriteToTxtFile("Referrals successful", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}

}
