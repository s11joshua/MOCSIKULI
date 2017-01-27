import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LoanStructure {
	
	static Log logger = LogFactory.getLog(LoanStructure.class);
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
		Pattern Loantab[] = {Loan1,Loan2,Loan3,Loan4,Loan5};
		try {
			
			screen.click(LoanStructure);
			App.pause(2);
			screen.wait(Loan1,15);
			
			if (JSON.GetTestData(RawFile, "LVRCalculation").get("CapitalisedLMI") != null){	
				if(!JSON.GetTestData(RawFile, "LVRCalculation").get("CapitalisedLMI").toString().equals("Uncheck")){
					if (Loans.get("IncludeCapitalisedLMI")!= null && Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) > 0 && Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) <= FundsRequired.size()){
						if (Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) > 1){
							screen.find(IncludeCapitaliseLMIIn).right(Offset[2]).click();
							Helper.Keystrokedown(Integer.parseInt(Loans.get("IncludeCapitalisedLMI").toString()) -1);
							Helper.Keystrokeenter(1);
							App.pause(2);
						}
					}else{
						System.out.println("Invalid Parameter Passed for IncludeCapitalisedLMI in LoanStructure");
						Helper.WriteToTxtFile("Invalid Parameter Passed for IncludeCapitalisedLMI in LoanStructure", TestExecution.TestExecutionFolder + "logs.txt");
						return false;
					}
				}
			}
			Iterator<JSONObject> LoanArray = Loan.iterator();
			Iterator<JSONObject> FundsRequiredArray = FundsRequired.iterator();
			int LoanCounter = 0;
			while (FundsRequiredArray.hasNext() && LoanArray.hasNext()){
				if (LoanCounter > 0){
					screen.click(Loantab[LoanCounter]);
				}
				LoanCounter ++;
				JSONObject Transaction = FundsRequiredArray.next();
				JSONObject LoanDetail = LoanArray.next();				
				JSONArray SplitDetailsArray = (JSONArray) LoanDetail.get("SplitValues");
				Iterator<JSONObject> SplitDetails = SplitDetailsArray.iterator();
				
				int counter = 0; 
				boolean SplitFlag = true;
				boolean FixedInterestMonthFlag = false;
				boolean InterestOnlyMonthFlag = false;
				int currentinteresttype = 2;
				int currentrepaymenttype = 2;
				int currentreasonforinterestonly = 0;
				
				while (SplitDetails.hasNext()){
					counter ++;
					JSONObject SplitValues = SplitDetails.next();
					if (counter > 1){
						if (SplitFlag == true){
							screen.click(SplitLoanbutton);
							App.pause(1);
							SplitFlag = false;
						} else {
							screen.click(AddSplit);
							App.pause(1);
						}
					}
				
					if (counter > 1 && SplitValues.get("SplitAmount") != null && Double.parseDouble(SplitValues.get("SplitAmount").toString()) > 0){
						screen.find(Amount).right(Offset[1]).click();
						Helper.ClearTextBoxandEnterValue(SplitValues.get("SplitAmount").toString());
						Helper.Keystrokeenter(1);
					}
					
					if (SplitValues.get("InterestType") != null){
						if (SplitValues.get("InterestType").toString().equals("Fixed") || SplitValues.get("InterestType").toString().equals("Variable") || SplitValues.get("InterestType").toString().equals("1") || SplitValues.get("InterestType").toString().equals("2")){
							if(SplitValues.get("InterestType").toString().equals("Fixed") || SplitValues.get("InterestType").toString().equals("1")){
								int Inputinteresttype = 1;
								if ((Inputinteresttype - currentinteresttype) != 0){
									App.pause(2);
									screen.find(InterestType).right(Offset[3]).click();
									App.pause(2);
									if ((Inputinteresttype - currentinteresttype) < 0){
										Helper.Keystrokeup(1);
									}else{
										Helper.Keystrokedown(1);
									}
									Helper.Keystrokeenter(1);
									currentinteresttype = 1;
							}
							}else{
								int Inputinteresttype = 2;
 								if ((Inputinteresttype - currentinteresttype) != 0){
 									App.pause(2);
									screen.find(InterestType).right(Offset[4]).click();
									App.pause(2);
 									if ((Inputinteresttype - currentinteresttype) < 0){
 										Helper.Keystrokedown(1);
 									}else{
 										Helper.Keystrokeup(1);
 									}
 									Helper.Keystrokeenter(1);
									currentinteresttype = 2;
								}
							}	
						}
						else{
							System.out.println("Invalid parameter passed for InterestType in LoanStructure-loan-splitvalues");
							Helper.WriteToTxtFile("Invalid parameter passed for InterestType in LoanStructure-loan-splitvalues", TestExecution.TestExecutionFolder + "logs.txt");
							return false;
						}
					}else{
						System.out.println("null value passed for InterestType in LoanStructure-loan-splitvalues");
						Helper.WriteToTxtFile("Null value passed for InterestType in LoanStructure-loan-splitvalues", TestExecution.TestExecutionFolder + "logs.txt");
						return false;
					}
					
					if (SplitValues.get("RepaymentType") != null && Integer.parseInt(SplitValues.get("RepaymentType").toString()) >= 1 && Integer.parseInt(SplitValues.get("RepaymentType").toString()) <= 5){
						int inputpaymenttype = Integer.parseInt(SplitValues.get("RepaymentType").toString());
						if (currentrepaymenttype - inputpaymenttype != 0){
							screen.wait(RepaymentType,15);
							screen.find(RepaymentType).right(Offset[4]).click();
							if ((currentrepaymenttype - inputpaymenttype) > 0){
								Helper.Keystrokeup(Math.abs(currentrepaymenttype - inputpaymenttype));
							}else{
								Helper.Keystrokedown(Math.abs(currentrepaymenttype - inputpaymenttype));
							}
							currentrepaymenttype = inputpaymenttype;
							Helper.Keystrokeenter(1);
						}						
					}else{
						System.out.println("Invalid Parameter passed for RepaymentType in LoanStructure-loan-splitvalues");
						Helper.WriteToTxtFile("Invalid Parameter passed for RepaymentType in LoanStructure-loan-splitvalues", TestExecution.TestExecutionFolder + "logs.txt");
						return false;
					}
					
					if (SplitValues.get("RepaymentType").toString().equals("1")){
						if (SplitValues.get("ReasonforInterestOnly") != null && Integer.parseInt(SplitValues.get("ReasonforInterestOnly").toString()) >= 1){
							int Inputreasonforinterestonly = Integer.parseInt(SplitValues.get("ReasonforInterestOnly").toString());
							if ((currentreasonforinterestonly-Inputreasonforinterestonly) != 0){
								screen.find(ReasonforInterestOnly).right(Offset[4]).click();
								if ((currentreasonforinterestonly - Inputreasonforinterestonly) > 0){
									Helper.Keystrokeup(Math.abs(currentreasonforinterestonly - Inputreasonforinterestonly));
								}else{
									Helper.Keystrokedown(Math.abs(currentreasonforinterestonly - Inputreasonforinterestonly));
								}
								currentreasonforinterestonly = Inputreasonforinterestonly;
								Helper.Keystrokeenter(1);
							}
						}else{
							System.out.println("Invalid Parameter passed for ReasonforInterestOnly in LoanStructure-loan-splitvalues");
							Helper.WriteToTxtFile("Invalid Parameter passed for ReasonforInterestOnly in LoanStructure-loan-splitvalues", TestExecution.TestExecutionFolder + "logs.txt");
							return false;
						}
						
						if (Integer.parseInt(SplitValues.get("ReasonforInterestOnly").toString()) == 5){
							if(SplitValues.get("OtherReason") != null ){
								screen.find(ifOther).right(Offset[4]).click();
								Helper.ClearTextBoxandEnterValue(SplitValues.get("OtherReason").toString());
								Helper.Keystrokeenter(1);
							}else{
								System.out.println("Invalid Parameter passed for OtherReason in LoanStructure-loan-splitvalues");
								return false;
							}	
						}
					}
					
					if(SplitValues.get("TermInYears") != null && Integer.parseInt(SplitValues.get("TermInYears").toString()) >= 1 && Integer.parseInt(SplitValues.get("TermInYears").toString()) <= 45){
						screen.find(LoanTerm).right(Offset[4]).click();					Helper.ClearTextBox(5);
						Helper.ClearTextBoxandEnterValue(SplitValues.get("TermInYears").toString());
						Helper.Keystrokeenter(1);
					}else{
						System.out.println("Invalid Parameter passed for TermInYears in LoanStructure-loan-splitvalues");
						Helper.WriteToTxtFile("Invalid Parameter passed for TermInYears in LoanStructure-loan-splitvalues", TestExecution.TestExecutionFolder + "logs.txt");
						return false;
					}
					
					screen.find(InterestOnlyPeriod).right(Offset[3]).click();
					Helper.ClearTextBox(5);				if (SplitValues.get("RepaymentType").toString().equals("1") || SplitValues.get("RepaymentType").toString().equals("3") || SplitValues.get("RepaymentType").toString().equals("4")){
						if (SplitValues.get("InterestOnlyPeriod") != null && SplitValues.get("InterestOnlyPeriod").toString().substring(SplitValues.get("InterestOnlyPeriod").toString().length() -1, SplitValues.get("InterestOnlyPeriod").toString().length()).equals("M")){
							screen.find(InterestOnlyPeriod).right(Offset[3]).click();
							Helper.ClearTextBoxandEnterValue(SplitValues.get("InterestOnlyPeriod").toString().substring(0,(SplitValues.get("InterestOnlyPeriod").toString().length() -1)));
							Helper.Keystrokeenter(1);
							Helper.Keystroketab(1);
							Helper.Keystrokedown(1);
							Helper.Keystrokeenter(1);
							InterestOnlyMonthFlag = true;
						}else if (SplitValues.get("InterestOnlyPeriod") != null && SplitValues.get("InterestOnlyPeriod").toString().substring(SplitValues.get("InterestOnlyPeriod").toString().length() -1, SplitValues.get("InterestOnlyPeriod").toString().length()).equals("Y")){
							screen.find(InterestOnlyPeriod).right(Offset[3]).click();
							Helper.ClearTextBoxandEnterValue(SplitValues.get("InterestOnlyPeriod").toString().substring(0,(SplitValues.get("InterestOnlyPeriod").toString().length() -1)));
							Helper.Keystrokeenter(1);
							if(InterestOnlyMonthFlag = true){
								Helper.Keystroketab(1);
								Helper.Keystrokeup(1);
								Helper.Keystrokeenter(1);
								InterestOnlyMonthFlag = false;
							}
						}else {
							System.out.println("Invalid Parameter passed for Interest Only Period in LoanStructure-loan-InterestOnlyPeriod");
							System.out.println("The String should always end with a 'Y' or 'M' if the payment type is 'Intereset Only', 'LOC' or 'Intereset In Advance'.");
							Helper.WriteToTxtFile("Invalid Parameter passed for Interest Only Period in LoanStructure-loan-InterestOnlyPeriod", TestExecution.TestExecutionFolder + "logs.txt");
							Helper.WriteToTxtFile("The String should always end with a 'Y' or 'M' if the payment type is 'Intereset Only', 'LOC' or 'Intereset In Advance'.", TestExecution.TestExecutionFolder + "logs.txt");
							return false;
						}
					}
					
					screen.find(FixedInterestPeriod).right(Offset[3]).click();
					Helper.ClearTextBox(5);
					if(SplitValues.get("InterestType").toString().equals("Fixed") || SplitValues.get("InterestType").toString().equals("1")){
						if (SplitValues.get("FixedInterestPeriod") != null && SplitValues.get("FixedInterestPeriod").toString().substring(SplitValues.get("FixedInterestPeriod").toString().length() -1, SplitValues.get("FixedInterestPeriod").toString().length()).equals("M")){
							screen.find(FixedInterestPeriod).right(Offset[3]).click();
							Helper.ClearTextBoxandEnterValue(SplitValues.get("FixedInterestPeriod").toString().substring(0,(SplitValues.get("FixedInterestPeriod").toString().length() -1)));
							Helper.Keystrokeenter(1);
							Helper.Keystroketab(1);
							Helper.Keystrokedown(1);
							Helper.Keystrokeenter(1);
							FixedInterestMonthFlag = true;
						}else if (SplitValues.get("FixedInterestPeriod") != null &&  SplitValues.get("FixedInterestPeriod").toString().substring(SplitValues.get("FixedInterestPeriod").toString().length() -1, SplitValues.get("FixedInterestPeriod").toString().length()).equals("Y")){
							screen.find(FixedInterestPeriod).right(Offset[3]).click();
							Helper.ClearTextBoxandEnterValue(SplitValues.get("FixedInterestPeriod").toString().substring(0,(SplitValues.get("FixedInterestPeriod").toString().length() -1)));
							Helper.Keystrokeenter(1);
							if (FixedInterestMonthFlag == true){
								Helper.Keystroketab(1);
								Helper.Keystrokeup(1);
								Helper.Keystrokeenter(1);
								FixedInterestMonthFlag = false;
							}
						}else {
							System.out.println("Invalid Parameter passed for Fixed Interest Period in LoanStructure-loan-FixedInterestPeriod");
							System.out.println("The String should always end with a 'Y' or 'M' if the interest type is 'Fixed'");
							Helper.WriteToTxtFile("Invalid Parameter passed for Fixed Interest Period in LoanStructure-loan-FixedInterestPeriod", TestExecution.TestExecutionFolder + "logs.txt");
							Helper.WriteToTxtFile("The String should always end with a 'Y' or 'M' if the interest type is 'Fixed'", TestExecution.TestExecutionFolder + "logs.txt");
							return false;
						}
					}
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "LoanStructure");
				}
				Helper.WriteToTxtFile("Loan Structure created successfully", TestExecution.TestExecutionFolder + "logs.txt");
			}
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
}
