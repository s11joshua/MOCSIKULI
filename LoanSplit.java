import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class LoanSplit {
	Pattern LoanStructure;
	Pattern FixedIntPeriod;
	Pattern InterestOnlyPeriod;
	Pattern InterestType;
	Pattern ReasonforIO;
	Pattern ReasonTypeSelection;
	Pattern FixedRateSelection;
	Pattern LoanAmount;
	Pattern SplitButton;
	Pattern RepaymentType;
	Pattern TermDropdown;

	public LoanSplit(String Imagefolderlocation){
		
		LoanStructure = new Pattern(Imagefolderlocation + "LoanStructure.PNG");
		FixedIntPeriod = new Pattern(Imagefolderlocation + "SplitFixedInterestPeriod.PNG");
		InterestOnlyPeriod = new Pattern(Imagefolderlocation + "SplitInterestOnlyPeriod.PNG");
		InterestType = new Pattern(Imagefolderlocation + "SplitInterestType.PNG");
		FixedRateSelection = new Pattern(Imagefolderlocation + "FixedRateSelection.PNG");
		ReasonforIO = new Pattern(Imagefolderlocation + "ReasonForInterestOnly.PNG");
		ReasonTypeSelection = new Pattern(Imagefolderlocation + "ReasonforIOselection.PNG");
		LoanAmount = new Pattern(Imagefolderlocation + "SplitLoanAmount.PNG");
		SplitButton = new Pattern(Imagefolderlocation + "SplitLoanbutton.PNG");
		RepaymentType = new Pattern(Imagefolderlocation + "SplitRepaymentType.PNG");
		TermDropdown = new Pattern(Imagefolderlocation + "TermDropdown.PN");
		
	}
	
	public static boolean CaptureLoanSplits(LoanSplit Loansplit,Screen screen){
		
		try {
			
			App.pause(2);
			screen.click(Loansplit.LoanStructure);
			screen.click(Loansplit.SplitButton);
			Helper.Keystrokebackspace(1);
			screen.click(Loansplit.LoanAmount);
			screen.type("100000");
			screen.click(Loansplit.InterestType);
			Helper.Keystrokeenter(1);
			screen.click(Loansplit.FixedRateSelection);
			screen.click(Loansplit.RepaymentType);
			Helper.Keystrokeup(1);
			Helper.Keystrokeenter(1);
			screen.click(Loansplit.ReasonforIO);
			screen.click(Loansplit.ReasonTypeSelection);
			screen.click(Loansplit.InterestOnlyPeriod);
			Helper.Keystrokebackspace(2);
			Helper.Keystrokedelete(2);
			screen.type("5");
			screen.click(Loansplit.FixedIntPeriod);
			Helper.Keystrokebackspace(2);
			Helper.Keystrokedelete(2);
			screen.type("5");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
