package Discovery;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class ScenarioSummary {
	
	static Log logger = LogFactory.getLog(ScenarioSummary.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern BranchNetworkAvailability;
	static Pattern CompetitiveInterestRate;
	static Pattern CustomerLenderPreference;
	static Pattern FeeStructure;
	static Pattern GeneralBankingFacilities;
	static Pattern LenderCreditPolicy;
	static Pattern LenderServiceLevels;
	static Pattern Loan1_1;
	static Pattern Loan1_2;
	static Pattern Loan1_3;
	static Pattern Loan2_1;
	static Pattern Loan2_2;
	static Pattern Loan2_3;
	static Pattern Loan3_1;
	static Pattern Loan3_2;
	static Pattern Loan3_3;
	static Pattern Loan4_1;
	static Pattern Loan4_2;
	static Pattern Loan4_3;
	static Pattern Loan5_1;
	static Pattern Loan5_2;
	static Pattern Loan5_3;
	static Pattern OverallLoanCostSavings;
	static Pattern RepaymentFrequency;
	static Pattern RepaymentMethodsAvailability;
	static Pattern ScenarioSummary;
	static Pattern SelectLenderDropdown;
	static Pattern SelectProduct;
	static Pattern InitialPeriod;
	static Pattern LoanTerm;
	static Pattern LoanScroll;

	public ScenarioSummary(){
		new ScenarioSummary(TestExecution.PatternRootFolderlocation+"ScenarioSummary\\");
		//new LoanStructure("C:\\Sikuli Images\\LoanStructure\\");
	}
	
	public ScenarioSummary(String Imagefolderlocation){
		
		SelectLenderDropdown =  new Pattern(Imagefolderlocation + "SelectLenderDropdown.PNG");
		BranchNetworkAvailability =  new Pattern(Imagefolderlocation + "BranchNetworkAvailability.PNG");
		CompetitiveInterestRate =  new Pattern(Imagefolderlocation + "CompetitiveInterestRate.PNG");
		CustomerLenderPreference =  new Pattern(Imagefolderlocation + "CustomerLenderPreference.PNG");
		FeeStructure =  new Pattern(Imagefolderlocation + "FeeStructure.PNG");
		GeneralBankingFacilities =  new Pattern(Imagefolderlocation + "GeneralBankingFacilities.PNG");
		LenderCreditPolicy =  new Pattern(Imagefolderlocation + "LenderCreditPolicy.PNG");
		LenderServiceLevels =  new Pattern(Imagefolderlocation + "LenderServiceLevels.PNG");
		OverallLoanCostSavings =  new Pattern(Imagefolderlocation + "OverallLoanCostSavings.PNG");
		RepaymentFrequency =  new Pattern(Imagefolderlocation + "RepaymentFrequency.PNG");
		RepaymentMethodsAvailability =  new Pattern(Imagefolderlocation + "RepaymentMethodsAvailability.PNG");
		ScenarioSummary =  new Pattern(Imagefolderlocation + "ScenarioSummary.PNG");
		Loan1_1 =  new Pattern(Imagefolderlocation + "Loan1_1.PNG");
		Loan1_2 =  new Pattern(Imagefolderlocation + "Loan1_2.PNG");
		Loan1_3 =  new Pattern(Imagefolderlocation + "Loan1_3.PNG");
		Loan2_1 =  new Pattern(Imagefolderlocation + "Loan2_1.PNG");
		Loan2_2 =  new Pattern(Imagefolderlocation + "Loan2_2.PNG");
		Loan2_3 =  new Pattern(Imagefolderlocation + "Loan2_3.PNG");
		Loan3_1 =  new Pattern(Imagefolderlocation + "Loan3_1.PNG");
		Loan3_2 =  new Pattern(Imagefolderlocation + "Loan3_2.PNG");
		Loan3_3 =  new Pattern(Imagefolderlocation + "Loan3_3.PNG");
		Loan4_1 =  new Pattern(Imagefolderlocation + "Loan4_1.PNG");
		Loan4_2 =  new Pattern(Imagefolderlocation + "Loan4_2.PNG");
		Loan4_3 =  new Pattern(Imagefolderlocation + "Loan4_3.PNG");
		Loan5_1 =  new Pattern(Imagefolderlocation + "Loan5_1.PNG");
		Loan5_2 =  new Pattern(Imagefolderlocation + "Loan5_2.PNG");
		Loan5_3 =  new Pattern(Imagefolderlocation + "Loan5_3.PNG");		
		SelectProduct =  new Pattern(Imagefolderlocation + "SelectProduct.PNG");
		LoanScroll =  new Pattern(Imagefolderlocation + "LoanScroll.PNG");

	}
	
	public static boolean SelectLenderandProduct(JSONObject RawFile){
		
		logger.debug("Entering select lender and product section");
		JSONObject Summary = (JSONObject) RawFile.get("ScenarioSummary");
		Pattern SplitArray[] = {LoanScroll,Loan1_1,Loan1_2,Loan1_3,Loan2_1,Loan2_2,Loan2_3,Loan3_1,Loan3_2,Loan3_3,Loan4_1,Loan4_2,Loan4_3,Loan5_1,Loan5_2,Loan5_3};
		Pattern NonSplitLoanArray[] = {LoanStructure.Loan1,LoanStructure.Loan2,LoanStructure.Loan3,LoanStructure.Loan4,LoanStructure.Loan5};
		JSONObject Loans = (JSONObject) RawFile.get("LoanStructure");
		JSONArray Loan = (JSONArray) Loans.get("Loan");
		JSONArray FundsRequired = (JSONArray) RawFile.get("FundsRequired");
		
		try {
			
			screen.click(ScenarioSummary);
			screen.wait(SelectLenderDropdown,15);
			screen.find(SelectLenderDropdown).right(Offset[2]).click();
			Helper.Keystrokedown(1);
			App.pause(2);
			Helper.Keystrokeenter(1);
			App.pause(1);
			
			if (Summary.get("BranchNetworkAvailability") != null && Summary.get("BranchNetworkAvailability").toString().equals("Check")){
				screen.click(BranchNetworkAvailability);
			}
			if (Summary.get("CompetitiveInterestRate") != null && Summary.get("CompetitiveInterestRate").toString().equals("Check")){
				screen.click(CompetitiveInterestRate);
			}
			if (Summary.get("CustomerLenderPreference") != null && Summary.get("CustomerLenderPreference").toString().equals("Check")){
				screen.click(CustomerLenderPreference);
			}
			if (Summary.get("FeeStructure") != null && Summary.get("FeeStructure").toString().equals("Check")){
				screen.click(FeeStructure);
			}
			if (Summary.get("GeneralBankingFacilities") != null && Summary.get("GeneralBankingFacilities").toString().equals("Check")){
				screen.click(GeneralBankingFacilities);
			}
			if (Summary.get("LenderCreditPolicy") != null && Summary.get("LenderCreditPolicy").toString().equals("Check")){
				screen.click(LenderCreditPolicy);
			}
			if (Summary.get("LenderServiceLevels") != null && Summary.get("LenderServiceLevels").toString().equals("Check")){
				screen.click(LenderServiceLevels);
			}
			if (Summary.get("OverallLoanCostSavings") != null && Summary.get("OverallLoanCostSavings").toString().equals("Check")){
				screen.click(OverallLoanCostSavings);
			}
			if (Summary.get("RepaymentFrequency") != null && Summary.get("RepaymentFrequency").toString().equals("Check")){
				screen.click(RepaymentFrequency);
			}			
			
			Iterator<JSONObject> LoanArray = Loan.iterator();
			Iterator<JSONObject> FundsRequiredArray = FundsRequired.iterator();
			int LoanCounter = 1;
			int NonSplitloanCounter = 0;
			int TotalLoanCounter = 1;
			while (FundsRequiredArray.hasNext() && LoanArray.hasNext()){
				int counter = 0; 
				JSONObject Transaction = FundsRequiredArray.next();
				JSONObject LoanDetail = LoanArray.next();				
				JSONArray SplitDetailsArray = (JSONArray) LoanDetail.get("SplitValues");
				int Count = Helper.CountIteratorItem(SplitDetailsArray);
				Iterator<JSONObject> SplitDetails = SplitDetailsArray.iterator();
				while (SplitDetails.hasNext()){
					JSONObject SplitValues = SplitDetails.next();
					if (TotalLoanCounter == 7 || TotalLoanCounter == 13){
						screen.click(SplitArray[0]);
						screen.click(SplitArray[0]);
						screen.click(SplitArray[0]);
					}
					if (Count > 1){
						screen.click(SplitArray[LoanCounter + counter]);
					}else{
						screen.click(NonSplitLoanArray[NonSplitloanCounter]);
					}
					screen.click(SelectProduct);
					Helper.Keystrokedown(1);
					Helper.Keystrokeenter(1);
					
					counter ++;
					TotalLoanCounter ++;
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "ScenarioSummary");
				}
				LoanCounter = LoanCounter + 3;
				NonSplitloanCounter ++;
				logger.info("Scenario Summary section was successful");
				Helper.WriteToTxtFile("Scenario Summary was successful", TestExecution.TestExecutionFolder + "logs.txt");
			}
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
}
