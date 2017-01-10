import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class DiscoveryHomePage {
	Pattern qashortcut;
	public DiscoveryHomePage(String Imagefolderlocation){
		qashortcut = new Pattern(Imagefolderlocation + "Q&A_shortcut.PNG");
	}
	
	public static boolean NavigatetoQualifyandAnalize(DiscoveryHomePage HomePage ,Screen screen){
		try {
			screen.wait(HomePage.qashortcut,15);
			screen.doubleClick(HomePage.qashortcut);
			App.pause(15);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
