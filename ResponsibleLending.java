import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ResponsibleLending {
	Pattern ResponsibleLendingNavigation;
	Pattern PreferredLoanRepayment;
	Pattern PreferredLoanTerm;
	Pattern FinancialCircumstancesradiobutton;
	
	public ResponsibleLending(String Imagefolderlocation){
		PreferredLoanRepayment = new Pattern (Imagefolderlocation + "PreferedLoanRepayment.PNG");
		PreferredLoanTerm = new Pattern(Imagefolderlocation + "PreferedLoanTerm.PNG");
		FinancialCircumstancesradiobutton = new Pattern(Imagefolderlocation + "FinancialCircumstances.PNG");
		ResponsibleLendingNavigation = new Pattern(Imagefolderlocation + "ResponsibleLending.PNG");
	}
	
	public static boolean captureResponsibleLending(ResponsibleLending Responsiblelending, Screen screen){
		try {
			App.pause(2);
			screen.click(Responsiblelending.ResponsibleLendingNavigation);
			screen.click(Responsiblelending.PreferredLoanTerm);
			Helper.Keystrokebackspace(3);
			Helper.Keystrokedelete(3);
			screen.type("30");
			screen.click(Responsiblelending.PreferredLoanRepayment);
			Helper.Keystrokebackspace(3);
			Helper.Keystrokedelete(3);
			screen.type("2700");
			screen.click(Responsiblelending.FinancialCircumstancesradiobutton);
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
