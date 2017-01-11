
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Securities {
	
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
		
	public Securities(){
		new Securities("C:\\Sikuli Images\\Securities\\Securities\\");
	}
	
	public Securities(String Imagefolderlocation){
		
		AddressLine1 =  new Pattern(Imagefolderlocation + "Address.PNG");
		AddSecurity =  new Pattern(Imagefolderlocation + "AddSecurity.PNG");
		DefenceHousing =  new Pattern(Imagefolderlocation + "DefenceHousing.PNG");
		FloodAffected =  new Pattern(Imagefolderlocation + "FloodAffected.PNG");
		Hectares =  new Pattern(Imagefolderlocation + "Hectares.PNG");
		HeritageListed =  new Pattern(Imagefolderlocation + "HeritageListed.PNG");
		HighDensity =  new Pattern(Imagefolderlocation + "HighDensity.PNG");
		LandFloorArea =  new Pattern(Imagefolderlocation + "LandFloorArea.PNG");
		Lender =  new Pattern(Imagefolderlocation + "Lender.PNG");
		MineSubsidence =  new Pattern(Imagefolderlocation + "MineSubsidence.PNG");
		MultipleOccupancy =  new Pattern(Imagefolderlocation + "MultipleOccupancy.PNG");
		OffthePLanCompletion =  new Pattern(Imagefolderlocation + "OffthePLanCompletion.PNG");
		SelectRegion =  new Pattern(Imagefolderlocation + "PostCode-popup.PNG");
		Securities =  new Pattern(Imagefolderlocation + "Securities.PNG");
		Sqm =  new Pattern(Imagefolderlocation + "Sqm.PNG");
		TitleType = new Pattern(Imagefolderlocation + "TitleType.PNG");
		Security1 = new Pattern(Imagefolderlocation + "Security1.PNG");
		Security2 = new Pattern(Imagefolderlocation + "Security2.PNG");
		Security3 = new Pattern(Imagefolderlocation + "Security3.PNG");
		Security4 = new Pattern(Imagefolderlocation + "Security4.PNG");
		Security5 = new Pattern(Imagefolderlocation + "Security5.PNG");
	}
	
	public static boolean CaptureSecurities(JSONObject RawFile){
		int counter = 0;
		JSONObject Security = (JSONObject) RawFile.get("Securities");
		JSONArray Securities_Array = (JSONArray) Security.get("SecurityDetails");
		Iterator<JSONObject> SecuritiesArray = Securities_Array.iterator();
		JSONArray FundsRequired_Array = (JSONArray) RawFile.get("FundsRequired");
		Iterator<JSONObject> FundsRequiredArray = FundsRequired_Array.iterator();
		Pattern Securitytab[] = {Security1,Security2,Security3,Security4,Security5};
		
		try {
			
			screen.click(Securities);
			while (SecuritiesArray.hasNext() && FundsRequiredArray.hasNext()){
				
				JSONObject SecuritiesDetails = SecuritiesArray.next();
				JSONObject FundsRequiredValues = FundsRequiredArray.next();
				
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
						System.out.println("Invalid Parameter passed for Lender in securities section");
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
				
			}
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}

}
