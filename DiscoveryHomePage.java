package Discovery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class DiscoveryHomePage {
	static Log logger = LogFactory.getLog(DiscoveryHomePage.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern qashortcut;
	
	public DiscoveryHomePage(){
		new DiscoveryHomePage("C:\\Sikuli Images\\DiscoveryHomePage\\");
	}
	
	public DiscoveryHomePage(String Imagefolderlocation){
		qashortcut = new Pattern(Imagefolderlocation + "Q&A_shortcut.PNG");
	}
	
	public static boolean NavigatetoQualifyandAnalize(){
		try {
			screen.wait(qashortcut,60);
			screen.doubleClick(qashortcut);
			App.pause(15);
			logger.debug("Completed the double click on the Discovery Home Page");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}
	
}
