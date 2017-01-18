import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ResponsibleLending {
	
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern ResponsibleLendingNavigation;
	static Pattern PreferredLoanRepayment;
	static Pattern PreferredLoanTerm;
	static Pattern FinancialCircumstancesradiobutton;
	
	public ResponsibleLending(){
		new ResponsibleLending("C:\\Sikuli Images\\ResponsibleLending\\");
	}
	
	public ResponsibleLending(String Imagefolderlocation){
		PreferredLoanRepayment = new Pattern (Imagefolderlocation + "PreferedLoanRepayment.PNG");
		PreferredLoanTerm = new Pattern(Imagefolderlocation + "PreferedLoanTerm.PNG");
		FinancialCircumstancesradiobutton = new Pattern(Imagefolderlocation + "FinancialCircumstances.PNG");
		ResponsibleLendingNavigation = new Pattern(Imagefolderlocation + "ResponsibleLending.PNG");
	}
	
	public static boolean CaptureResponsibleLending(){
		try {
			App.pause(2);
			screen.click(ResponsibleLendingNavigation);
			screen.click(PreferredLoanTerm);
			Helper.Keystrokebackspace(3);
			Helper.Keystrokedelete(3);
			screen.type("30");
			screen.click(PreferredLoanRepayment);
			Helper.Keystrokebackspace(3);
			Helper.Keystrokedelete(3);
			screen.type("6000");
			screen.click(FinancialCircumstancesradiobutton);
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
