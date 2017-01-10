import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Apply {
	Pattern ApplyNavigation;
	Pattern ApplyOnlineNewApplication;
	Pattern SubmitApplication;
	
	public Apply(String Imagefolderlocation){
		ApplyNavigation = new Pattern(Imagefolderlocation + "Apply.PNG");
		ApplyOnlineNewApplication = new Pattern(Imagefolderlocation + "ApplyOnlineNewApplication.PNG");
		SubmitApplication = new Pattern(Imagefolderlocation + "ApplicationSubmit.PNG");
	}
	
	public static boolean CaptureTypeOfLodgement(Apply apply, Screen screen){
		try {
			App.pause(2);
			screen.click(apply.ApplyNavigation);
			App.pause(6);
			screen.click(apply.ApplyOnlineNewApplication);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean SubmitApplication(Apply apply, Screen screen){
		
		try {
			App.pause(2);
			screen.click(apply.SubmitApplication);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
}
