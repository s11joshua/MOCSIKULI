
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class Securities {
	
	Pattern SecuritiesNavigationTab;
	Pattern AddressLine1;
	Pattern DefenceHousing;
	Pattern FloodAffectedArea;
	Pattern HeritageListed;
	Pattern HighDensity;
	Pattern LandArea;
	Pattern MineSubstance;
	Pattern MultipleOcc;
	Pattern OffThePlan;
	Pattern PostCodedpopup;
	Pattern PostCode;
	Pattern Suburb;
	Pattern TitleType;
	Pattern Zoning;

	public Securities(String Imagefolderlocation){
		
		SecuritiesNavigationTab = new Pattern(Imagefolderlocation + "Securities.PNG");
		AddressLine1 = new Pattern(Imagefolderlocation + "AddressLine1-TextBox.PNG");
		DefenceHousing = new Pattern(Imagefolderlocation + "DefenceHousing-Checkbox.PNG");
		FloodAffectedArea = new Pattern(Imagefolderlocation + "FloodAffectedArea-Checkbox.PNG");
		HeritageListed = new Pattern(Imagefolderlocation + "HeritageListed-Checkbox.PNG");
		HighDensity = new Pattern(Imagefolderlocation + "HighDensity-Checkbox.PNG");
		LandArea = new Pattern(Imagefolderlocation + "LandArea-Textbox.PNG");
		MineSubstance = new Pattern(Imagefolderlocation + "MineSubstance-Checkbox.PNG");
		MultipleOcc = new Pattern(Imagefolderlocation + "MultipleOcc-Checkbox.PNG");
		OffThePlan = new Pattern(Imagefolderlocation + "OffThePlan-Dropdown.PNG");
		PostCodedpopup = new Pattern(Imagefolderlocation + "PostCode-popup.PNG");
		PostCode = new Pattern(Imagefolderlocation + "PostCode-Textbox.PNG");
		Suburb = new Pattern(Imagefolderlocation + "Suburb-Textbox.PNG");
		TitleType = new Pattern(Imagefolderlocation + "TitleType-Dropdown.PNG");
		Zoning = new Pattern(Imagefolderlocation + "Zoning-Dropdown.PNG");

	}
	
	public static boolean CaptureSecurities(ClientInformation Scenario,Securities Collateral,Screen screen){
		try {
			screen.click(Collateral.SecuritiesNavigationTab);
			screen.click(Collateral.AddressLine1);
			screen.type("Unit 27/99-101 Anzac Avenue");
			screen.click(Collateral.PostCodedpopup);
			ClientInformation.SelectPostCode("NORTH RYDE","2113");
			screen.click(Collateral.HeritageListed);
			screen.type(Collateral.OffThePlan,"24112017");
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return true;
		}
	}

}
