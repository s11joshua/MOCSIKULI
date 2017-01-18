import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Apply {
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	static Pattern ApplyNavigation;
	static Pattern ApplyOnlineNewApplication;
	static Pattern SubmitApplication;
	
	public Apply(){
		new Apply("C:\\Sikuli Images\\Apply\\");
	}
	
	public Apply(String Imagefolderlocation){
		ApplyNavigation = new Pattern(Imagefolderlocation + "Apply.PNG");
		ApplyOnlineNewApplication = new Pattern(Imagefolderlocation + "ApplyOnlineNewApplication.PNG");
		SubmitApplication = new Pattern(Imagefolderlocation + "ApplicationSubmit.PNG");
	}
	
	public static boolean CaptureTypeOfLodgement(){
		try {
			App.pause(2);
			screen.click(ApplyNavigation);
			App.pause(6);
			screen.click(ApplyOnlineNewApplication);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean SubmitApplication(){
		
		try {
			App.pause(2);
			screen.click(SubmitApplication);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
}
