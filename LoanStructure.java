import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LoanStructure {
	
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	static Pattern AddSplit;
	static Pattern AdjustLoan;
	static Pattern Amount;
	static Pattern FixedInterestPeriod;
	static Pattern ifOther;
	static Pattern IncludeCapitaliseLMIIn;
	static Pattern InterestOnlyPeriod;
	static Pattern InterestType;
	static Pattern Loan1;
	static Pattern Loan2;
	static Pattern Loan3;
	static Pattern Loan4;
	static Pattern Loan5;
	static Pattern LoanStructure;
	static Pattern LoanTerm;
	static Pattern ReasonforInterestOnly;
	static Pattern RepaymentType;
	static Pattern SplitLoanbutton;

	
	public LoanStructure(){
		new LoanStructure("C:\\Sikuli Images\\LoanStructure\\");
	}
	
	public LoanStructure(String Imagefolderlocation){
		
		AddSplit =  new Pattern(Imagefolderlocation + "AddSplit.PNG");
		AdjustLoan =  new Pattern(Imagefolderlocation + "AdjustLoan.PNG");
		Amount =  new Pattern(Imagefolderlocation + "Amount.PNG");
		FixedInterestPeriod =  new Pattern(Imagefolderlocation + "FixedInterestPeriod.PNG");
		ifOther =  new Pattern(Imagefolderlocation + "ifOther.PNG");
		IncludeCapitaliseLMIIn =  new Pattern(Imagefolderlocation + "IncludeCapitaliseLMIIn.PNG");
		InterestOnlyPeriod =  new Pattern(Imagefolderlocation + "InterestOnlyPeriod.PNG");
		InterestType =  new Pattern(Imagefolderlocation + "InterestType.PNG");
		Loan1 =  new Pattern(Imagefolderlocation + "Loan1.PNG");
		Loan2 =  new Pattern(Imagefolderlocation + "Loan2.PNG");
		Loan3 =  new Pattern(Imagefolderlocation + "Loan3.PNG");
		Loan4 =  new Pattern(Imagefolderlocation + "Loan4.PNG");
		Loan5 =  new Pattern(Imagefolderlocation + "Loan5.PNG");
		LoanStructure =  new Pattern(Imagefolderlocation + "LoanStructure.PNG");
		LoanTerm =  new Pattern(Imagefolderlocation + "LoanTerm.PNG");
		ReasonforInterestOnly =  new Pattern(Imagefolderlocation + "ReasonforInterestOnly.PNG");
		RepaymentType =  new Pattern(Imagefolderlocation + "RepaymentType.PNG");
		SplitLoanbutton =  new Pattern(Imagefolderlocation + "SplitLoanbutton.PNG");

		
	}
	
	public static boolean CaptureLoanStructure(JSONObject RawFile){
		JSONObject Loans = (JSONObject) RawFile.get("LoanStructure");
		JSONArray Loan = (JSONArray) Loans.get("Loan");
		JSONArray FundsRequired = (JSONArray) RawFile.get("FundsRequired");
		
		try {
			
			screen.click(LoanStructure);
			screen.wait(IncludeCapitaliseLMIIn,15);
			if (Loans.get("IncludeCapitalisedLMI")!= null && Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) > 0 && Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) <= FundsRequired.size()){
				if (Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) > 1){
					screen.find(IncludeCapitaliseLMIIn).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) -1);
					Helper.Keystrokeenter(1);
				}
			}else{
				System.out.println("Invalid Parameter Passed for IncludeCapitalisedLMI in LoanStructure");
				return false;
			}
			
			
			Iterator<JSONObject> LoanArray = Loan.iterator();
			Iterator<JSONObject> FundsRequiredArray = FundsRequired.iterator();
			
			while (FundsRequiredArray.hasNext() && LoanArray.hasNext()){
				
				JSONObject Transaction = FundsRequiredArray.next();
				JSONObject LoanDetail = LoanArray.next();				
				JSONArray SplitDetailsArray = (JSONArray) LoanDetail.get("SplitValues");
				Iterator<JSONObject> SplitDetails = SplitDetailsArray.iterator();
				
				while (SplitDetails.hasNext()){
					JSONObject SplitValues = SplitDetails.next();
					
					
				}
				
				
			}
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
