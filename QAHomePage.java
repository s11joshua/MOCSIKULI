import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class QAHomePage {
	Pattern clientsearch;
	Pattern quickqualify;
	public QAHomePage(String Imagefolderlocation){
		clientsearch = new Pattern(Imagefolderlocation + "Clienttextbox.PNG");
		quickqualify = new Pattern(Imagefolderlocation + "QuickQualify.PNG");
	}
	
	public static boolean QuickQualify(QAHomePage HomePage,Screen screen,String FirstName){

		
		try {
			App.focus("Qualifier Analyser");
			screen.click(HomePage.clientsearch);
			screen.type(FirstName);
			screen.click(HomePage.quickqualify);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
