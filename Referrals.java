import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Referrals {
	
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern Referralsnavigation;
	static Pattern AllianceOptOutCheckbox;
	static Pattern OptOutReason;
	
	public Referrals(){
		new Referrals("C:\\Sikuli Images\\Referrals\\");
	}
	
	public Referrals(String Imagefolderlocation){
		Referralsnavigation = new Pattern(Imagefolderlocation + "Referrals.PNG");
		AllianceOptOutCheckbox = new Pattern(Imagefolderlocation + "AllianceOptOutcheckbox.PNG");
		OptOutReason = new Pattern(Imagefolderlocation + "OptOutReason.PNG");
	}
	
	public static boolean CaptureReferrals(){
		
		try{
			App.pause(2);
			screen.click(Referralsnavigation);
			screen.click(AllianceOptOutCheckbox);
			screen.click(OptOutReason);
			screen.type("TestingPurpose");	
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}

}
