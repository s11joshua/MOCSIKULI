import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class ScenarioSummary {
	
	Pattern ScenarioSummaryNavigation;
	Pattern SelectLender;
	Pattern SelectLoan1;
	Pattern SelectLoan2;
	Pattern SelectProduct;
	
	public ScenarioSummary(String Imagefolderlocation){
		ScenarioSummaryNavigation = new Pattern(Imagefolderlocation + "ScenarioSummary.PNG");
		SelectLender = new Pattern(Imagefolderlocation + "SelectLenderDropdown.PNG");
		SelectLoan1 = new Pattern(Imagefolderlocation + "SelectLoan1.1.PNG");
		SelectLoan2 = new Pattern(Imagefolderlocation + "SelectLoan1.2.PNG");
		SelectProduct = new Pattern(Imagefolderlocation + "SelectProduct.PNG");
	}
	
	public static boolean SelectLenderandProduct(ScenarioSummary Scenariosummary, Screen screen){
		
		try {
			App.pause(3);
			screen.click(Scenariosummary.ScenarioSummaryNavigation);
			screen.click(Scenariosummary.SelectLender);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			App.pause(2);
			screen.click(Scenariosummary.SelectLoan1);
			screen.click(Scenariosummary.SelectProduct);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			screen.click(Scenariosummary.SelectLoan2);
			screen.click(Scenariosummary.SelectProduct);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
}
