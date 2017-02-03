package Discovery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Securities {
	
	static Log logger = LogFactory.getLog(Securities.class);
	public static Screen screen = new Screen();
	public static int Offset[] = {0,10,50,100,200,500,1000};
	
	static Pattern AddSecurity;
	static Pattern Securities;
	static Pattern AddressLine1;
	static Pattern SelectRegion;
	static Pattern Lender;
	static Pattern HeritageListed;
	static Pattern FloodAffected;
	static Pattern DefenceHousing;
	static Pattern MineSubsidence;
	static Pattern HighDensity;
	static Pattern MultipleOccupancy;
	static Pattern OffthePLanCompletion;
	static Pattern Zoning;
	static Pattern LandFloorArea;
	static Pattern Sqm;
	static Pattern Hectares;
	static Pattern TitleType;
	static Pattern Security1;
	static Pattern Security2;
	static Pattern Security3;
	static Pattern Security4;
	static Pattern Security5;
	static Pattern Security6;
	static Pattern Security7;
	static Pattern Security8;
	static Pattern Security9;
	static Pattern Security10;
		
	public Securities(){
		new Securities("C:\\Sikuli Images\\Securities\\");
	}
	
	public Securities(String Imagefolderlocation){
		
		AddressLine1 = new Pattern(Imagefolderlocation + "Address.PNG");
		AddSecurity = new Pattern(Imagefolderlocation + "AddSecurity.PNG");
		DefenceHousing = new Pattern(Imagefolderlocation + "DefenceHousing.PNG");
		FloodAffected = new Pattern(Imagefolderlocation + "FloodAffected.PNG");
		Hectares = new Pattern(Imagefolderlocation + "Hectares.PNG");
		HeritageListed = new Pattern(Imagefolderlocation + "HeritageListed.PNG");
		HighDensity = new Pattern(Imagefolderlocation + "HighDensity.PNG");
		LandFloorArea = new Pattern(Imagefolderlocation + "LandFloorArea.PNG");
		Lender = new Pattern(Imagefolderlocation + "Lender.PNG");
		MineSubsidence = new Pattern(Imagefolderlocation + "MineSubsidence.PNG");
		MultipleOccupancy = new Pattern(Imagefolderlocation + "MultipleOccupancy.PNG");
		OffthePLanCompletion = new Pattern(Imagefolderlocation + "OffthePLanCompletion.PNG");
		Zoning = new Pattern(Imagefolderlocation + "Zoning.PNG");
		SelectRegion =  new Pattern(Imagefolderlocation + "PostCode-popup.PNG");
		Securities =  new Pattern(Imagefolderlocation + "Securities.PNG");
		Sqm =  new Pattern(Imagefolderlocation + "Sqm.PNG");
		TitleType = new Pattern(Imagefolderlocation + "TitleType.PNG");
		Security1 = new Pattern(Imagefolderlocation + "Security1.PNG");
		Security2 = new Pattern(Imagefolderlocation + "Security2.PNG");
		Security3 = new Pattern(Imagefolderlocation + "Security3.PNG");
		Security4 = new Pattern(Imagefolderlocation + "Security4.PNG");
		Security5 = new Pattern(Imagefolderlocation + "Security5.PNG");
		Security6 = new Pattern(Imagefolderlocation + "Security6.PNG");
		Security7 = new Pattern(Imagefolderlocation + "Security7.PNG");
		Security8 = new Pattern(Imagefolderlocation + "Security8.PNG");
		Security9 = new Pattern(Imagefolderlocation + "Security9.PNG");
		Security10 = new Pattern(Imagefolderlocation + "Security10.PNG");
	}
	
	public static boolean CaptureSecurities(JSONObject RawFile){
		
		logger.debug("Entering Capture Securities section");
		int counter = 0;
		JSONObject Security = (JSONObject) RawFile.get("Securities");
		JSONArray Securities_Array = (JSONArray) Security.get("SecurityDetails");
		Iterator<JSONObject> SecuritiesArray = Securities_Array.iterator();
		JSONArray FundsRequired_Array = (JSONArray) RawFile.get("FundsRequired");
		Iterator<JSONObject> FundsRequiredArray = FundsRequired_Array.iterator();
		Pattern Securitytab[] = {Security1,Security2,Security3,Security4,Security5,Security6,Security7,Security8,Security9,Security10};
		
		try {
			
			screen.click(Securities);
			while (SecuritiesArray.hasNext() && FundsRequiredArray.hasNext()){
				
				JSONObject SecuritiesDetails = SecuritiesArray.next();
				JSONObject FundsRequiredValues = FundsRequiredArray.next();
				screen.wait(AddressLine1,15);
				screen.click(Securitytab[counter]);
				counter++;
				
				if (SecuritiesDetails.get("AddressLine1") != null){
					screen.find(AddressLine1).right(Offset[4]).click();
					screen.type(SecuritiesDetails.get("AddressLine1").toString());
					if (SecuritiesDetails.get("AddressLine2") != null){
						Helper.Keystroketab(1);
						screen.type(SecuritiesDetails.get("AddressLine2").toString());
					}
					screen.click(SelectRegion);
					if (ClientInformation.SelectPostCode(SecuritiesDetails.get("Suburb").toString(),SecuritiesDetails.get("PostCode").toString()) != true){
						return false;
					}
				}
				
				if (FundsRequiredValues.get("TransactionType").toString().equals("2")){
					JSONObject NewPersonalLoan = JSON.GetTestData(FundsRequiredValues, "NewPersonalLoanUse");
					if (Double.parseDouble(NewPersonalLoan.get("LoanBalance").toString()) > 0){
						screen.find(Lender).right(Offset[4]).click();
						Helper.Keystrokedown(Integer.parseInt(SecuritiesDetails.get("Lender").toString()));
						Helper.Keystrokeenter(1);
					}
					
				}else if(FundsRequiredValues.get("TransactionType").toString().equals("4") || FundsRequiredValues.get("TransactionType").toString().equals("5")){
					if (SecuritiesDetails.get("Lender") != null && Integer.parseInt(SecuritiesDetails.get("Lender").toString()) >0){
						screen.find(Lender).right(Offset[4]).click();
						Helper.Keystrokedown(Integer.parseInt(SecuritiesDetails.get("Lender").toString()));
						Helper.Keystrokeenter(1);
					}else{
						logger.error("Invalid Parameter passed for Lender in securities section");
					}
					
				}
				
				if (SecuritiesDetails.get("HeritageListed") != null && SecuritiesDetails.get("HeritageListed").toString().equals("Check")){
						screen.click(HeritageListed);				
				}
				if (SecuritiesDetails.get("FloodAffected") != null && SecuritiesDetails.get("FloodAffected").toString().equals("Check")){
					screen.click(FloodAffected);				
				}
				if (SecuritiesDetails.get("DefenceHousing") != null && SecuritiesDetails.get("DefenceHousing").toString().equals("Check")){
					screen.click(DefenceHousing);				
				}
				if (SecuritiesDetails.get("MineSubsidence") != null && SecuritiesDetails.get("MineSubsidence").toString().equals("Check")){
					screen.click(MineSubsidence);				
				}
				if (SecuritiesDetails.get("HighDensity") != null && SecuritiesDetails.get("HighDensity").toString().equals("Check")){
					screen.click(HighDensity);				
				}
				if (SecuritiesDetails.get("MultipleOccupancy") != null && SecuritiesDetails.get("MultipleOccupancy").toString().equals("Check")){
					screen.click(MultipleOccupancy);				
				}
				
				if (SecuritiesDetails.get("OffthePlanCompletionDate") != null){
					SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");
					screen.find(OffthePLanCompletion).right(Offset[2]).click();
					date.parse(SecuritiesDetails.get("OffthePlanCompletionDate").toString());
					screen.type(SecuritiesDetails.get("OffthePlanCompletionDate").toString());
				}
				
				if (SecuritiesDetails.get("TitleType") != null && Integer.parseInt(SecuritiesDetails.get("TitleType").toString()) > 0 && Integer.parseInt(SecuritiesDetails.get("TitleType").toString()) <= 13){
					if (Integer.parseInt(SecuritiesDetails.get("TitleType").toString()) < 13){
						screen.find(TitleType).right(Offset[2]).click();
						Helper.Keystrokeup(13 - Integer.parseInt(SecuritiesDetails.get("TitleType").toString()));
						Helper.Keystrokeenter(1);
					}
				}
				
				if (SecuritiesDetails.get("Zoning") != null && Integer.parseInt(SecuritiesDetails.get("Zoning").toString()) > 0 && Integer.parseInt(SecuritiesDetails.get("Zoning").toString()) <= 5){
					screen.find(Zoning).right(Offset[2]).click();
					if((Integer.parseInt(SecuritiesDetails.get("Zoning").toString()) - 3) > 0){
						Helper.Keystrokedown(Integer.parseInt(SecuritiesDetails.get("Zoning").toString()) - 3);
						Helper.Keystrokeenter(1);
					}else{
						Helper.Keystrokeup(Math.abs(Integer.parseInt(SecuritiesDetails.get("Zoning").toString()) - 3));
						Helper.Keystrokeenter(1);
					}
				}
				
				if (SecuritiesDetails.get("LandFloorArea") != null && Double.parseDouble(SecuritiesDetails.get("LandFloorArea").toString()) > 0){
					screen.find(LandFloorArea).right(Offset[2]).click();
					screen.type(SecuritiesDetails.get("LandFloorArea").toString());
					if (SecuritiesDetails.get("Squaremeter") != null && SecuritiesDetails.get("Squaremeter").toString().equals("Yes")){
						screen.click(Sqm);
					}else if (SecuritiesDetails.get("Hectares") != null && SecuritiesDetails.get("Hectares").toString().equals("Yes")){
						screen.click(Hectares);
					}else{
						logger.error("Invalid parameter for the land-floor size unit");
						return false;
					}
					
				}
			
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Securities");
			}
			
			logger.info("Securities Created successfully");
			Helper.WriteToTxtFile("Securities Created successfully", TestExecution.TestExecutionFolder + "logs.txt");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}

}
