import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Referrals {
	Pattern Referralsnavigation;
	Pattern AllianceOptOutCheckbox;
	Pattern OptOutReason;
		
	public Referrals(String Imagefolderlocation){
		Referralsnavigation = new Pattern(Imagefolderlocation + "Referrals.PNG");
		AllianceOptOutCheckbox = new Pattern(Imagefolderlocation + "AllianceOptOutcheckbox.PNG");
		OptOutReason = new Pattern(Imagefolderlocation + "OptOutReason.PNG");
	}
	
	public static boolean CaptureReferrals(Referrals referrals, Screen screen){
		
		try{
			App.pause(2);
			screen.click(referrals.Referralsnavigation);
			screen.click(referrals.AllianceOptOutCheckbox);
			screen.click(referrals.OptOutReason);
			screen.type("TestingPurpose");	
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}

}
