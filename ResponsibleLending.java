package Discovery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ResponsibleLending {
	
	static Log logger = LogFactory.getLog(ResponsibleLending.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern ExpectedChanges;
	static Pattern FinancialCircumstancesYes;
	static Pattern FinancialCircumstancesNo;
	static Pattern InsuranceAssistanceMCFP;
	static Pattern ProvideALIQuote;
	static Pattern NoActionrequiredInsurance;
	static Pattern LoanComparisonCompletedYes;
	static Pattern LoanComparisonCompletedNo;
	static Pattern ManageExpectedChanges;
	static Pattern MeassuresAcceptable;
	static Pattern NoActionRequired;
	static Pattern PreferredLoanRepayment;
	static Pattern PreferredLoanTerm;
	static Pattern ReasonComparisonNotCompleted;
	static Pattern RefinanceAppication;
	static Pattern RefinanceReason;
	static Pattern RefinanceReasonOther;
	static Pattern ResponsibleLending;
	static Pattern SelfManagedSuperFund;
	static Pattern TabPosition;
	static Pattern CustomerCanMeetObligation;
	static Pattern ExistingRelationshipWithFA;
	static Pattern CustomerHasSufficientInsuranceCover;
	
	public ResponsibleLending(){
		new ResponsibleLending(TestExecution.PatternRootFolderlocation+"ResponsibleLending\\");
	}
	
	public ResponsibleLending(String Imagefolderlocation){
		ExpectedChanges = new Pattern(Imagefolderlocation + "ExpectedChanges.PNG");
		FinancialCircumstancesYes = new Pattern(Imagefolderlocation + "FinancialCircumstances.PNG");
		FinancialCircumstancesNo = new Pattern(Imagefolderlocation + "FinancialCircumstancesNo.PNG");
		InsuranceAssistanceMCFP = new Pattern(Imagefolderlocation + "InsuranceAssistanceMCFP.PNG");
		ProvideALIQuote = new Pattern(Imagefolderlocation + "ProvideALIQuote.PNG");
		NoActionrequiredInsurance = new Pattern(Imagefolderlocation + "NoActionrequiredInsurance.PNG");
		LoanComparisonCompletedYes =  new Pattern(Imagefolderlocation + "LoanComparisonCompletedYes.PNG");
		LoanComparisonCompletedNo = new Pattern(Imagefolderlocation + "NoActionrequiredInsuranceNo.PNG");
		ManageExpectedChanges =  new Pattern(Imagefolderlocation + "ManageExpectedChanges.PNG");
		MeassuresAcceptable =  new Pattern(Imagefolderlocation + "MeassuresAcceptable.PNG");
		NoActionRequired =  new Pattern(Imagefolderlocation + "NoActionRequired.PNG");
		PreferredLoanRepayment =  new Pattern(Imagefolderlocation + "PreferredLoanRepayment.PNG");
		PreferredLoanTerm =  new Pattern(Imagefolderlocation + "PreferredLoanTerm.PNG");
		ReasonComparisonNotCompleted =  new Pattern(Imagefolderlocation + "ReasonComparisonNotCompleted.PNG");
		RefinanceAppication =  new Pattern(Imagefolderlocation + "RefinanceAppication.PNG");
		RefinanceReason =  new Pattern(Imagefolderlocation + "RefinanceReason.PNG");
		RefinanceReasonOther =  new Pattern(Imagefolderlocation + "RefinanceReasonOther.PNG");
		ResponsibleLending =  new Pattern(Imagefolderlocation + "ResponsibleLending.PNG");
		SelfManagedSuperFund =  new Pattern(Imagefolderlocation + "SelfManagedSuperFund.PNG");
		TabPosition =  new Pattern(Imagefolderlocation + "TabPosition.PNG");
		CustomerCanMeetObligation =  new Pattern(Imagefolderlocation + "CustomerCanMeetObligation.PNG");
		ExistingRelationshipWithFA =  new Pattern(Imagefolderlocation + "ExistingRelationshipWithFA.PNG");
		CustomerHasSufficientInsuranceCover =  new Pattern(Imagefolderlocation + "CustomerHasSufficientInsuranceCover.PNG");


	}
	
	public static boolean CaptureResponsibleLending(JSONObject RawFile){
		logger.debug("Entering Capture Responsible Lending section");
		JSONObject ResponsibleLendingValues = (JSONObject) RawFile.get("ResponsibleLending");
		
		try {
			App.pause(2);
			screen.click(ResponsibleLending);
			screen.wait(PreferredLoanTerm,15);
			
			if(ResponsibleLendingValues.get("PreferredLoanterm") != null){
				if(Integer.parseInt(ResponsibleLendingValues.get("PreferredLoanterm").toString()) >=1 ){
					screen.find(PreferredLoanTerm).right(Offset[1]).click();
					Helper.ClearTextBoxandEnterValue(ResponsibleLendingValues.get("PreferredLoanterm").toString());
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid Parameter Passed for PreferredLoanterm in ResponsibleLending-PreferredLoanterm");
					return false;
				}
			}
			if(ResponsibleLendingValues.get("PreferredLoanRepayment") != null){
				if(Double.parseDouble(ResponsibleLendingValues.get("PreferredLoanRepayment").toString()) >= 1 ){
					screen.find(PreferredLoanRepayment).right(Offset[1]).click();
					Helper.ClearTextBoxandEnterValue(ResponsibleLendingValues.get("PreferredLoanRepayment").toString());
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid Parameter Passed for PreferredLoanRepayment in ResponsibleLending-PreferredLoanRepayment");
					return false;
				}
			}
			
			if(ResponsibleLendingValues.get("ChangesFinancialCircumstances") != null && ResponsibleLendingValues.get("ChangesFinancialCircumstances").toString().equals("Yes")){
				screen.click(FinancialCircumstancesYes);
				JSONObject ChangesExpected = JSON.GetTestData(ResponsibleLendingValues, "ChangesExpected");
				if(ChangesExpected.get("TemporaryDecreaseInDisposableIncome") != null && ChangesExpected.get("TemporaryDecreaseInDisposableIncome").toString().equals("Check")){
					screen.click(ExpectedChanges);
				}
				if(ChangesExpected.get("PermanaetDecreaseInDisposableIncome") != null && ChangesExpected.get("PermanaetDecreaseInDisposableIncome").toString().equals("Check")){
					screen.click(ExpectedChanges);
					screen.click(ExpectedChanges);
					Helper.Keystroketab(1);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ChangesExpected.get("AnticipatedLagreExpenditure") != null && ChangesExpected.get("AnticipatedLagreExpenditure").toString().equals("Check")){
					screen.click(ExpectedChanges);
					screen.click(ExpectedChanges);
					Helper.Keystroketab(2);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ChangesExpected.get("Other") != null && ChangesExpected.get("Other").toString().equals("Check")){
					screen.click(ExpectedChanges);
					screen.click(ExpectedChanges);
					Helper.Keystroketab(3);
					screen.find(TabPosition).above(Offset[1]).click();
				}

				JSONObject ExpectedChanges = JSON.GetTestData(ResponsibleLendingValues, "ManageChanges");
				
				if(ExpectedChanges.get("MeassuresAcceptable") != null && ExpectedChanges.get("MeassuresAcceptable").toString().equals("No")){
					screen.click(MeassuresAcceptable);
					logger.error("This application will not be successful, due to foreseeable changesto their financial circumstances that may impact the loan repayments.");
					return false;
				}
				
				if(ExpectedChanges.get("SecuringAdditionalIncome") != null && ExpectedChanges.get("SecuringAdditionalIncome").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
				}
				if(ExpectedChanges.get("UsingSavings") != null && ExpectedChanges.get("UsingSavings").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(1);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("FinancialDetailsReflect") != null && ExpectedChanges.get("FinancialDetailsReflect").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(2);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("ReducingExpenditure") != null && ExpectedChanges.get("ReducingExpenditure").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(3);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("SaleSharesInvestment") != null && ExpectedChanges.get("SaleSharesInvestment").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(4);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("PayoutforSuperannuation") != null && ExpectedChanges.get("PayoutforSuperannuation").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(5);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("DownSizing") != null && ExpectedChanges.get("DownSizing").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(6);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(ExpectedChanges.get("Other") != null && ExpectedChanges.get("Other").toString().equals("Check")){
					screen.click(ManageExpectedChanges);
					screen.click(ManageExpectedChanges);
					Helper.Keystroketab(7);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				
			}else if(ResponsibleLendingValues.get("ChangesFinancialCircumstances") != null && ResponsibleLendingValues.get("ChangesFinancialCircumstances").toString().equals("No")){
				screen.click(FinancialCircumstancesNo);
			}
			
			JSONObject Insurance = JSON.GetTestData(ResponsibleLendingValues, "PersonalInsurance");
			if(Insurance.get("MCFPAdviser") != null && Insurance.get("MCFPAdviser").toString().equals("Check")){
				screen.click(InsuranceAssistanceMCFP);
			}else if(Insurance.get("ProvideALIQuote") != null && Insurance.get("ProvideALIQuote").toString().equals("Check")){
				screen.click(ProvideALIQuote);
			}else if(JSON.GetTestData(Insurance, "NoActionRequired").get("ActionRequired") != null && JSON.GetTestData(Insurance, "NoActionRequired").get("ActionRequired").toString().equals("Check")){
				screen.click(NoActionrequiredInsurance);
				if(JSON.GetTestData(Insurance, "NoActionRequired").get("CustomerCanMeettheObligations") != null && JSON.GetTestData(Insurance, "NoActionRequired").get("CustomerCanMeettheObligations").toString().equals("Check")){
					screen.click(CustomerCanMeetObligation);
				}else if(JSON.GetTestData(Insurance, "NoActionRequired").get("ExistingRelationshipwithFA") != null && JSON.GetTestData(Insurance, "NoActionRequired").get("ExistingRelationshipwithFA").toString().equals("Check")){
					screen.click(ExistingRelationshipWithFA);
				}else if(JSON.GetTestData(Insurance, "NoActionRequired").get("SufficientInsuranceCover") != null && JSON.GetTestData(Insurance, "NoActionRequired").get("SufficientInsuranceCover").toString().equals("Check")){
					screen.click(CustomerHasSufficientInsuranceCover);
				}else{
					logger.error("Invalid parameter passed for NoActionRequired sub values in ResponsibleLending-PersonalInsurance-NoActionRequired");
					return false;
				}
			}
			
			JSONObject RefinanceValues = JSON.GetTestData(ResponsibleLendingValues, "RefinaceApplication");
			
			if(RefinanceValues.get("Refinance") != null && RefinanceValues.get("Refinance").toString().equals("Yes")){
				
				screen.click(RefinanceAppication);
				
				if(RefinanceValues.get("ConvenienceFlexibility") != null && RefinanceValues.get("ConvenienceFlexibility").toString().equals("Check")){
					screen.click(RefinanceReason);
				}
				if(RefinanceValues.get("ConsolidatingRestructuring") != null && RefinanceValues.get("ConsolidatingRestructuring").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(1);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(RefinanceValues.get("Disatisfaction") != null && RefinanceValues.get("Disatisfaction").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(2);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(RefinanceValues.get("CompetitivePricing") != null && RefinanceValues.get("CompetitivePricing").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(3);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(RefinanceValues.get("SpecificFeaturesProducts") != null && RefinanceValues.get("SpecificFeaturesProducts").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(4);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(RefinanceValues.get("UnabletoGetCredit") != null && RefinanceValues.get("UnabletoGetCredit").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(5);
					screen.find(TabPosition).above(Offset[1]).click();
				}
				if(RefinanceValues.get("Other") != null && RefinanceValues.get("Other").toString().equals("Check")){
					screen.click(RefinanceReason);
					screen.click(RefinanceReason);
					Helper.Keystroketab(6);
					screen.find(TabPosition).above(Offset[1]).click();
					
					if(RefinanceValues.get("OtherReason") != null){
						screen.find(RefinanceReasonOther).right(Offset[1]).click();
						Helper.ClearTextBoxandEnterValue(RefinanceValues.get("OtherReason").toString());
					}
				}
				if(RefinanceValues.get("LoanCostComparison") != null && RefinanceValues.get("LoanCostComparison").toString().equals("No")){
					screen.click(LoanComparisonCompletedYes);
					screen.click(LoanComparisonCompletedNo);
					if(RefinanceValues.get("ReasonLoanCostComparison") != null && Integer.parseInt(RefinanceValues.get("ReasonLoanCostComparison").toString()) >= 1 && Integer.parseInt(RefinanceValues.get("ReasonLoanCostComparison").toString()) <= 2){
						screen.find(ReasonComparisonNotCompleted).below(Offset[1]).click();
						Helper.Keystrokedown(Integer.parseInt(RefinanceValues.get("ReasonLoanCostComparison").toString()));
						Helper.Keystrokeenter(1);
					}
				}
				if(RefinanceValues.get("LoanCostComparison") != null && RefinanceValues.get("LoanCostComparison").toString().equals("Yes")){
					screen.click(LoanComparisonCompletedYes);
				}
				
			}
			
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "CaptureResponsibleLending");
			logger.info("Responsible Lending section completed successfully");
			Helper.WriteToTxtFile("Responsible Lending Successful", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
	
}
