import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
public class FundsRequired {
	
	static Log logger = LogFactory.getLog(FundsRequired.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	static Pattern FundsRequiredSection;
	static Pattern AddNewTransaction;
	
	static Pattern TransactionType;
	static Pattern UseOfFunds;
	static Pattern LoanPurpose;
	static Pattern PropertyType;
	static Pattern Location;
	static Pattern State;
	static Pattern LinkSecurity;
	
	static Pattern PurchasePrice;
	static Pattern FHO;
	
	static Pattern FundsRequested;
	static Pattern LoanBalance;
	static Pattern ReasonforCashOut;
	static Pattern LoanLimit;
	static Pattern NumberofDebts;
	
	static Pattern Ownerbuilder;
	static Pattern LandValue;
	static Pattern ConstructionCosts;
	static Pattern ThisLoanIs;
	static Pattern ExistingLMI;
	
	static Pattern LoanIncreaseAmount;
	static Pattern ReasonforLoanIncrease;
	static Pattern SecuredLimt;
	static Pattern LMIPremiumCredit;
	
	static Pattern RefinanceAmount;
	static Pattern CashOut;
	static Pattern ConsolidationAmount;
	
	static Pattern Bridging;
	static Pattern Refinance;
	static Pattern SecurityValue;
	static Pattern CapitalisedInterest;	
	static Pattern SellingCostCashOut;
	static Pattern BridgingExistingLMI;
	
	static Pattern TotalCost;
	static Pattern TotalContribution;
	
	static Pattern FullDOC;
	static Pattern CapitaliseLMI;
		
	public FundsRequired(){
		new FundsRequired("C:\\Sikuli Images\\FundsRequired\\");
	}
	
	public FundsRequired(String Imagefolderlocation){
		
		FundsRequiredSection = new Pattern(Imagefolderlocation + "FundsRequired.PNG");
		AddNewTransaction = new Pattern(Imagefolderlocation + "AddNewTransaction");
		Bridging =  new Pattern(Imagefolderlocation + "Bridging.PNG");
		BridgingExistingLMI  =  new Pattern(Imagefolderlocation + "BridgingExistingLMI.PNG");
		CapitalisedInterest =  new Pattern(Imagefolderlocation + "BridgingCapitalisedInterest.PNG");
		LoanBalance  =  new Pattern(Imagefolderlocation + "LoanBalance.PNG");
		SecurityValue =  new Pattern(Imagefolderlocation + "BridgingSecurityValue.PNG");
		SellingCostCashOut =  new Pattern(Imagefolderlocation + "BridgingSellingCostCashOut.PNG");
		ExistingLMI =  new Pattern(Imagefolderlocation + "ExistingLMI.PNG");
		FHO =  new Pattern(Imagefolderlocation + "FHO.PNG");
		LinkSecurity =  new Pattern(Imagefolderlocation + "LinkSecurity.PNG");
		LoanPurpose =  new Pattern(Imagefolderlocation + "LoanPurpose.PNG");
		Location =  new Pattern(Imagefolderlocation + "Location.PNG");
		PropertyType =  new Pattern(Imagefolderlocation + "PropertyType.PNG");
		PurchasePrice =  new Pattern(Imagefolderlocation + "PurchasePrice.PNG");
		Refinance =  new Pattern(Imagefolderlocation + "Refinance.PNG");
		State =  new Pattern(Imagefolderlocation + "State.PNG");
		TransactionType =  new Pattern(Imagefolderlocation + "TransactionType.PNG");
		UseOfFunds = new Pattern(Imagefolderlocation + "UseOfFunds.PNG");
		FundsRequested = new Pattern(Imagefolderlocation + "FundsRequested.PNG");
		ReasonforCashOut = new Pattern(Imagefolderlocation + "ReasonforCashOut.PNG");
		LoanLimit = new Pattern(Imagefolderlocation + "LoanLimit.PNG");
		NumberofDebts = new Pattern(Imagefolderlocation + "NumberofDebts.PNG");
		Ownerbuilder = new Pattern(Imagefolderlocation + "OwnerBuilder.PNG");
		LandValue = new Pattern(Imagefolderlocation + "LandValue.PNG");
		ConstructionCosts = new Pattern(Imagefolderlocation + "ConstructionCost.PNG");
		ThisLoanIs = new Pattern(Imagefolderlocation + "ThisLoanIs.PNG");
		LoanIncreaseAmount =  new Pattern(Imagefolderlocation + "LoanIncreaseAmount.PNG");
		ReasonforLoanIncrease =  new Pattern(Imagefolderlocation + "ReasonforLoanIncrease.PNG");
		SecuredLimt =  new Pattern(Imagefolderlocation + "SecuredLimt.PNG");
		LMIPremiumCredit =  new Pattern(Imagefolderlocation + "LMIPremiumCredit.PNG");
		RefinanceAmount =  new Pattern(Imagefolderlocation + "RefinanceAmount.PNG");
		CashOut =  new Pattern(Imagefolderlocation + "CashOut.PNG");
		ConsolidationAmount =  new Pattern(Imagefolderlocation + "ConsolidationAmount.PNG");
		TotalCost =  new Pattern(Imagefolderlocation + "TotalCost.PNG");
		TotalContribution =  new Pattern(Imagefolderlocation + "TotalContribution.PNG");
		FullDOC = new Pattern(Imagefolderlocation + "FullDoc.PNG");
		CapitaliseLMI = new Pattern(Imagefolderlocation + "CapitaliseLMI.PNG");
		
	}
	
	public static boolean CaptureTransaction(JSONObject RawFile){
		logger.debug("Entering Capture Transaction");
		int counter = 0;
		JSONArray FundsRequired_Array = (JSONArray) RawFile.get("FundsRequired");
		Iterator<JSONObject> FundsRequiredArray = FundsRequired_Array.iterator();
		
		//JSONObject FundsRequiredValues = (JSONObject) RawFile.get("FundsRequired");
		try {
			screen.click(FundsRequiredSection);
			while (FundsRequiredArray.hasNext()){
				counter++;
					if (counter > 1) {
						screen.click(AddNewTransaction);
					}
				JSONObject FundsRequiredValues = FundsRequiredArray.next();
				if ((FundsRequiredValues.get("TransactionType") != null) && (Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) >= 1) && (Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) <= 5)){
					if (Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) > 1){
						screen.find(TransactionType).right(Offset[1]).click();
						Helper.Keystrokedown(Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) - 1);
						Helper.Keystrokeenter(1);
					}
				}else{
					logger.error("Invalid Parameter Passed for transaction type");
					return false;
				}
				
				if ((FundsRequiredValues.get("UseofFunds") != null) && (Integer.parseInt(FundsRequiredValues.get("UseofFunds").toString()) > 0)){
					
					if ((Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) > 1)){
						if (Integer.parseInt(FundsRequiredValues.get("UseofFunds").toString()) > 1){
							screen.find(UseOfFunds).right(Offset[1]).click();
							Helper.Keystrokedown(Integer.parseInt(FundsRequiredValues.get("UseofFunds").toString()) - 1);
							Helper.Keystrokeenter(1);
						}
					}
					else if (Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) == 1){ 
						if (Integer.parseInt(FundsRequiredValues.get("UseofFunds").toString()) != 4){
							screen.find(UseOfFunds).right(Offset[1]).click();
							Helper.Keystrokeup(Math.abs(4 - Integer.parseInt(FundsRequiredValues.get("UseofFunds").toString())));
							Helper.Keystrokeenter(1);
						}
					}			
				}else{
					logger.error("Invalid Parameter Passed for UseofFunds");
					return false;
				}
	
				if ((FundsRequiredValues.get("LoanPurpose") != null) && (Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) >= 1) && (Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) <= 4)){
					if (Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) != 3){
						
						if ((Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) - 3) > 0){
							screen.find(LoanPurpose).right(Offset[1]).click();
							Helper.Keystrokedown(Math.abs(Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) - 3));
							Helper.Keystrokeenter(1);
						}
						else if ((Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) - 3) < 0){
							screen.find(LoanPurpose).right(Offset[1]).click();
							Helper.Keystrokeup(Math.abs(Integer.parseInt(FundsRequiredValues.get("LoanPurpose").toString()) - 3));
							Helper.Keystrokeenter(1);
						}			
					}
				}else{
					logger.error("Invalid Parameter Passed for LoanPurpose");
					return false;
				}
				
				if ((FundsRequiredValues.get("PropertyType") != null) && (Integer.parseInt(FundsRequiredValues.get("PropertyType").toString()) >= 1)){
					
					if ((Integer.parseInt(FundsRequiredValues.get("PropertyType").toString()) - 4) > 0){
						screen.find(PropertyType).right(Offset[1]).click();
						Helper.Keystrokedown(Math.abs(Integer.parseInt(FundsRequiredValues.get("PropertyType").toString()) - 4));
						Helper.Keystrokeenter(1);
					}
					else if ((Integer.parseInt(FundsRequiredValues.get("PropertyType").toString()) - 4) < 0){
						screen.find(PropertyType).right(Offset[1]).click();
						Helper.Keystrokeup(Math.abs(Integer.parseInt(FundsRequiredValues.get("PropertyType").toString()) - 4));
						Helper.Keystrokeenter(1);
					}
				}else{
					logger.error("Invalid Parameter Passed for PropertyType");
					return false;
				}
				
				if ((FundsRequiredValues.get("Location") != null) && (Integer.parseInt(FundsRequiredValues.get("Location").toString()) >= 1)){
					screen.find(Location).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(FundsRequiredValues.get("Location").toString()));
					Helper.Keystrokeenter(1);
					
				}else{
					logger.error("Invalid Parameter Passed for Location");
					return false;
				}
				
				if ((FundsRequiredValues.get("State") != null) && (Integer.parseInt(FundsRequiredValues.get("State").toString()) >= 1)){
					if ((Integer.parseInt(FundsRequiredValues.get("State").toString()) - 5) > 0){
						screen.click(State);
						Helper.Keystrokedown(Math.abs(Integer.parseInt(FundsRequiredValues.get("State").toString()) - 5));
						Helper.Keystrokeenter(1);
					}
					else if ((Integer.parseInt(FundsRequiredValues.get("State").toString()) - 5) < 0){
						screen.click(State);
						Helper.Keystrokeup(Math.abs(Integer.parseInt(FundsRequiredValues.get("State").toString()) - 5));
						Helper.Keystrokeenter(1);
					}
				}else{
					logger.error("Invalid Parameter Passed for state");
					return false;
				}
				
				if ( FundsRequiredValues.get("LinkSecurity") != null && FundsRequiredValues.get("LinkSecurity").toString().equals("Uncheck")){
					screen.click(LinkSecurity);
				}
				
				if (AdditionalValuesFundsRequired(FundsRequiredValues) != true){
					return false;
				}
				
				if(Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) == 1 || Integer.parseInt(FundsRequiredValues.get("TransactionType").toString()) == 3){
					if (JSON.GetTestData(FundsRequiredValues, "Bridging").get("Bridging").toString().equals("Check")){
						if (Bridging(JSON.GetTestData(FundsRequiredValues, "Bridging")) != true){
							return false;
						}
					}
				}
				
				if (FundsRequiredValues.get("TotalCost") != null && Double.parseDouble(FundsRequiredValues.get("TotalCost").toString()) > 0 ){
					screen.find(TotalCost).right(Offset[1]).click();
					Helper.ClearTextBox(10,(float) 0.3);
					Helper.ClearTextBoxandEnterValue(FundsRequiredValues.get("TotalCost").toString());
					Helper.Keystrokeenter(1);
				}
				
				if (FundsRequiredValues.get("TotalContribution") != null && Double.parseDouble(FundsRequiredValues.get("TotalContribution").toString()) > 0 ){
					screen.find(TotalContribution).right(Offset[1]).click();
					Helper.ClearTextBox(10,(float) 0.3);
					Helper.ClearTextBoxandEnterValue(FundsRequiredValues.get("TotalContribution").toString());
					Helper.Keystrokeenter(1);
				}
				
				if (JSON.GetTestData(RawFile, "LVRCalculation").get("LowDoc") != null && JSON.GetTestData(RawFile, "LVRCalculation").get("LowDoc").toString().equals("Yes")){
					screen.click(FullDOC);
					Helper.Keystrokeup(1);
					Helper.Keystrokeenter(1);
				}
				if (JSON.GetTestData(RawFile, "LVRCalculation").get("CapitalisedLMI") != null && JSON.GetTestData(RawFile, "LVRCalculation").get("CapitalisedLMI").toString().equals("Uncheck")){
					screen.click(CapitaliseLMI);
				}
			}
			
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FundsRequired");
			logger.info("Transactions were created successfully");
			Helper.WriteToTxtFile("Transactions were created successfully", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
		
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
	
	public static boolean AdditionalValuesFundsRequired(JSONObject FundsRequiredPayLoad){
		
		switch(Integer.parseInt(FundsRequiredPayLoad.get("TransactionType").toString())){
		case 1:
			return NewPurchase(FundsRequiredPayLoad);
		case 2:
			return NewLoanPersonalUse(FundsRequiredPayLoad);
		case 3:
			return Construction(FundsRequiredPayLoad);
		case 4:
			return Increaseexistinglender(FundsRequiredPayLoad);
		case 5:
			return Refinance(FundsRequiredPayLoad);
		default:
			logger.error("Invalid parameter passed for Transaction Type.");
			return false;
			
		}
		
		
	}
	
	public static boolean NewPurchase(JSONObject FundsRequiredPayLoad){
		logger.debug("Entering transaction New Purchase");
		JSONObject NewPurchase = (JSONObject) FundsRequiredPayLoad.get("NewPurchase");
		
		try {
			if (NewPurchase.get("FHO").toString().equals("Yes")){
				screen.find(FHO).right(Offset[1]).click();
				Helper.Keystrokedown(1);
				Helper.Keystrokeenter(1);
			}
			if (NewPurchase.get("PurchasePrice") != null && Double.parseDouble(NewPurchase.get("PurchasePrice").toString()) > 0){
				screen.find(PurchasePrice).right(Offset[3]).click();
				Helper.ClearTextBoxandEnterValue(NewPurchase.get("PurchasePrice").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for Purchase Price.");
				return false;
			}
			
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction NewPurchase");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static boolean NewLoanPersonalUse(JSONObject FundsRequiredPayLoad){
		logger.debug("Entering transaction New loan personal use");
		JSONObject NewLoanPersonalUse = (JSONObject) FundsRequiredPayLoad.get("NewPersonalLoanUse");
		
		try {
			if (NewLoanPersonalUse.get("FundsRequested") != null){
				screen.find(FundsRequested).right(Offset[3]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(NewLoanPersonalUse.get("FundsRequested").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for new loan personal use, funds requested field");
				return false;
			}
			if (NewLoanPersonalUse.get("ReasonforCashOut") != null && Integer.parseInt(NewLoanPersonalUse.get("ReasonforCashOut").toString()) > 0){
				screen.find(ReasonforCashOut).right(Offset[3]).click();
				Helper.Keystrokedown(Integer.parseInt((NewLoanPersonalUse.get("ReasonforCashOut").toString())));
				Helper.Keystrokeenter(1);
			}
			if (NewLoanPersonalUse.get("SecurityValue")!= null){
				screen.find(SecurityValue).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(NewLoanPersonalUse.get("SecurityValue").toString());
			}
			if (NewLoanPersonalUse.get("LoanBalance")!= null){
				screen.find(LoanBalance).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(NewLoanPersonalUse.get("LoanBalance").toString());
			}
			if (NewLoanPersonalUse.get("LoanLimit") != null){
				screen.find(LoanLimit).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(NewLoanPersonalUse.get("LoanLimit").toString());
			}
			if (NewLoanPersonalUse.get("NumberofDebts") != null && Integer.parseInt(NewLoanPersonalUse.get("NumberofDebts").toString()) == 1){
				screen.find(NumberofDebts).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(NewLoanPersonalUse.get("NumberofDebts").toString());
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction NewLoanPersonalUse");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static boolean Bridging(JSONObject BridgingValues){
		logger.debug("Entering bridging section");
		try {
			
			screen.click(Bridging);
			
			if (BridgingValues.get("BridgingExistingLMI").toString().equals("Check")){
				Helper.Keystroketab(2);
				screen.click(BridgingExistingLMI);
				screen.find(BridgingExistingLMI).right(Offset[4]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(BridgingValues.get("BridgingExistingLMIvalue").toString());
			}else if (BridgingValues.get("Refinance").toString().equals("Uncheck")){
				screen.click(Refinance);
			}
			
			if (BridgingValues.get("BridgingSecurityValue") != null){
				screen.find(SecurityValue).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(BridgingValues.get("BridgingSecurityValue").toString());
			}
			if (BridgingValues.get("BridgingLoanBalance") != null){
				Helper.Keystroketab(1);
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(BridgingValues.get("BridgingLoanBalance").toString());
			}
			if (BridgingValues.get("BridgingSellingCostsCashOut") != null){
				screen.find(SellingCostCashOut).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(BridgingValues.get("BridgingSellingCostsCashOut").toString());
			}
			if (BridgingValues.get("BridgingCapitalisedinterest") != null){
				screen.find(CapitalisedInterest).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(BridgingValues.get("BridgingCapitalisedinterest").toString());
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction Bridging");
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
	
	public static boolean Construction(JSONObject FundsRequiredPayLoad){
		logger.debug("Entering Construction transaction section");
		JSONObject Construction = (JSONObject) FundsRequiredPayLoad.get("Construction");
		try {
			if (Construction.get("FHO").toString().equals("Yes")){
				screen.find(FHO).right(Offset[1]).click();
				Helper.Keystrokedown(1);
				Helper.Keystrokeenter(1);
			}
			if (Construction.get("Ownerbuilder").toString().equals("Check")){
				screen.click(Ownerbuilder);
			}
			if (Construction.get("LandValue") != null && Double.parseDouble(Construction.get("LandValue").toString()) > 0){
				screen.find(LandValue).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Construction.get("LandValue").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid Parameter passed for LandValue!");
				return false;
			}
			if (Construction.get("ConstructionCosts") != null && Double.parseDouble(Construction.get("ConstructionCosts").toString()) > 0){
				screen.find(ConstructionCosts).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Construction.get("ConstructionCosts").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid Parameter passed for ConstructionCost!");
				return false;
			}
			if (FundsRequiredPayLoad.get("UseofFunds").toString().equals("2")){
				if (Construction.get("ThisLoanIs") != null && Integer.parseInt(Construction.get("ThisLoanIs").toString()) > 0){
					screen.find(ThisLoanIs).right(Offset[2]).click();
					if (Integer.parseInt(Construction.get("ThisLoanIs").toString()) < 2){
						Helper.Keystrokeup(1);
						Helper.Keystrokeenter(1);
					}else if (Integer.parseInt(Construction.get("ThisLoanIs").toString()) > 2){
						Helper.Keystrokedown(1);
						Helper.Keystrokeenter(1);
					}
				}	
			
				if (Construction.get("LoanBalance") != null && Double.parseDouble(Construction.get("LoanBalance").toString()) > 0){
					screen.find(LoanBalance).right(Offset[2]).click();
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Construction.get("LoanBalance").toString());
					Helper.Keystrokeenter(1);
				}
				
				if (Construction.get("ExistingLMI").toString().equals("Check")){
					screen.click(ExistingLMI);
					screen.find(ExistingLMI).right(Offset[4]).click();
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Construction.get("ExistingLMIvalue").toString());
					Helper.Keystrokeenter(1);
				}
			}
			if (Construction.get("LoanLimit") != null && Double.parseDouble(Construction.get("LoanLimit").toString()) > 0){
				if (Construction.get("LoanBalance") != Construction.get("LoanLimit")){
					screen.find(LoanLimit).right(Offset[2]).click();
					Helper.ClearTextBox(15);
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Construction.get("LoanLimit").toString());
					Helper.Keystrokeenter(1);
				}
				
			}
			
		Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction Construction");	
		return true;
		
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static boolean Increaseexistinglender(JSONObject FundsRequiredPayLoad){
		logger.debug("Entering Increase existing lender transaction type section.");
		JSONObject Increaseexistinglender = (JSONObject) FundsRequiredPayLoad.get("Increaseexistinglender");
		try {
			if (Increaseexistinglender.get("LoanIncreaseAmount") != null && Double.parseDouble(Increaseexistinglender.get("LoanIncreaseAmount").toString()) > 0){
				screen.find(LoanIncreaseAmount).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("LoanIncreaseAmount").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for LoanIncreaseAmount");
				return false;
			}
			if (Increaseexistinglender.get("ReasonforIncrease") != null && Integer.parseInt(Increaseexistinglender.get("ReasonforIncrease").toString()) > 0){
				screen.find(ReasonforLoanIncrease).right(Offset[2]).click();
				Helper.Keystrokedown(Integer.parseInt((Increaseexistinglender.get("ReasonforIncrease").toString())));
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for ReasonforIncrease");
				return false;
			}
			if (Increaseexistinglender.get("LoanBalance") != null && Double.parseDouble(Increaseexistinglender.get("LoanBalance").toString()) > 0){
				screen.find(LoanBalance).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("LoanBalance").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for LoanBalance");
				return false;
			}
			if (Increaseexistinglender.get("LoanLimit") != null && Double.parseDouble(Increaseexistinglender.get("LoanLimit").toString()) > 0){
				screen.find(LoanLimit).right(Offset[2]).click();
				Helper.ClearTextBox(15);
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("LoanLimit").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for LoanLimit");
				return false;
			}
			if (Increaseexistinglender.get("SecurityValue") != null && Double.parseDouble(Increaseexistinglender.get("SecurityValue").toString()) > 0){
				screen.find(SecurityValue).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("SecurityValue").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for SecurityValue");
				return false;
			}
			if (Increaseexistinglender.get("SecuredLimt") != null && Double.parseDouble(Increaseexistinglender.get("SecuredLimt").toString()) > 0){
				screen.find(SecuredLimt).right(Offset[2]).click();
				Helper.ClearTextBox(15);
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("SecuredLimt").toString());
				Helper.Keystrokeenter(1);
			}
			
			if(Increaseexistinglender.get("ExistingLMI") != null && Increaseexistinglender.get("ExistingLMI").toString().equals("Check")){
				screen.click(ExistingLMI);
				if (Increaseexistinglender.get("LMIPremiumCredit") != null && Double.parseDouble(Increaseexistinglender.get("LMIPremiumCredit").toString()) > 0){
					screen.find(LMIPremiumCredit).right(Offset[2]).click();
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("LMIPremiumCredit").toString());
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid parameter passed for LMIPremiumCredit");
					return false;
				} 
			}
			if (FundsRequiredPayLoad.get("UseofFunds").toString().equals("1")){
				if (Increaseexistinglender.get("NumberofDebts") != null && Integer.parseInt(Increaseexistinglender.get("NumberofDebts").toString()) > 0){
					screen.find(NumberofDebts).right(Offset[2]).click();
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Increaseexistinglender.get("NumberofDebts").toString());
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid parameter passed for NumberofDebts");
					return false;
				}
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction Increaseexistinglender");
			return true;
		}catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
	
	public static boolean Refinance(JSONObject FundsRequiredPayLoad){
		logger.debug("Entering Refinance transaction type section.");
		
		JSONObject Refinance = (JSONObject) FundsRequiredPayLoad.get("Refinance");
		try{
			if (Refinance.get("RefinanceAmount") != null && Double.parseDouble(Refinance.get("RefinanceAmount").toString()) > 0){
				screen.find(RefinanceAmount).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Refinance.get("RefinanceAmount").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for RefinanceAmount");
				return false;
			}
			if (Refinance.get("CashOut") != null && Double.parseDouble(Refinance.get("CashOut").toString()) > 0){
				screen.find(CashOut).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Refinance.get("CashOut").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for CashOut");
				return false;
			}
			if (Refinance.get("ReasonforCashOut") != null && Integer.parseInt(Refinance.get("ReasonforCashOut").toString()) > 0){
				screen.find(ReasonforCashOut).right(Offset[3]).click();
				Helper.Keystrokedown(Integer.parseInt((Refinance.get("ReasonforCashOut").toString())));
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for ReasonforCashOut");
				return false;
			}
			if (Refinance.get("ConsolidationAmount") != null && Double.parseDouble(Refinance.get("ConsolidationAmount").toString()) > 0){
				screen.find(ConsolidationAmount).right(Offset[3]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Refinance.get("ConsolidationAmount").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for ConsolidationAmount");
				return false;
			}
			if (FundsRequiredPayLoad.get("UseofFunds").toString().equals("2")){
				if (Refinance.get("NumberofDebts") != null && Integer.parseInt(Refinance.get("NumberofDebts").toString()) > 0){
					screen.find(NumberofDebts).right(Offset[2]).click();
					Helper.ClearTextBox(10,(float) 0.2);
					Helper.ClearTextBoxandEnterValue(Refinance.get("NumberofDebts").toString());
					Helper.Keystrokeenter(1);
				}else{
					logger.error("Invalid parameter passed for NumberofDebts");
					return false;
				}
			}
			if (Refinance.get("SecurityValue") != null && Double.parseDouble(Refinance.get("SecurityValue").toString()) > 0){
				screen.find(SecurityValue).right(Offset[2]).click();
				Helper.ClearTextBox(10,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Refinance.get("SecurityValue").toString());
				Helper.Keystrokeenter(1);
			}else{
				logger.error("Invalid parameter passed for SecurityValue");
				return false;
			}
			if (Refinance.get("SecuredLimt") != null && Double.parseDouble(Refinance.get("SecuredLimt").toString()) > 0){
				screen.find(SecuredLimt).right(Offset[2]).click();
				Helper.ClearTextBox(15,(float) 0.2);
				Helper.ClearTextBoxandEnterValue(Refinance.get("SecuredLimt").toString());
				Helper.Keystrokeenter(1);
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Transaction Refinance");
			return true;
			
		}catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
		
	}
}
	
	