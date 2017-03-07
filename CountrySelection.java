package FactFind;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Discovery.Helper;
import Discovery.TestExecution;

public class CountrySelection {
	
	static Log logger = LogFactory.getLog(CountrySelection.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	
	public static Pattern AddressCannotbeFoundokButton;
	public static Pattern AddressLookUp;
	public static Pattern EmployeAddressLookUp;
	public static Pattern AssetAddressLookUp;
	
	static Pattern CountrySearchPopUp;
	static Pattern CountryFilterforSearch;
	static Pattern SelectCountry;
	static Pattern SelectCountryPopup;
	
	public CountrySelection(){
		new CountrySelection(TestExecution.PatternRootFolderlocation+"FactFind\\CountrySelection\\");
	}
	
	public CountrySelection(String Imagefolderlocation){
		AddressCannotbeFoundokButton = new Pattern (Imagefolderlocation + "AddressNotFoundokbutton.PNG");
		AddressLookUp = new Pattern (Imagefolderlocation + "AddressLookUp.PNG");
		EmployeAddressLookUp = new Pattern (Imagefolderlocation + "EmployeAddressLookup.PNG");
		AssetAddressLookUp = new Pattern (Imagefolderlocation + "AssetAddressLookup.PNG");
		
		CountrySearchPopUp = new Pattern (Imagefolderlocation + "CountrySearch.PNG");
		CountryFilterforSearch = new Pattern (Imagefolderlocation + "SearchCountryInPopup.PNG");
		SelectCountry = new Pattern (Imagefolderlocation + "SelectCountry.PNG");
		SelectCountryPopup = new Pattern (Imagefolderlocation + "SelectCountryPopup.PNG");
		
	}
	
	public static boolean SelectCountry(String CountryName){
		
		try {
			Thread.sleep(3000);
			screen.click(CountrySearchPopUp);
			screen.type(CountryName);
			Thread.sleep(1000);
			screen.click(CountryFilterforSearch);
			Thread.sleep(1000);
			screen.find(SelectCountry).below(Offset[1]).click();
			screen.click(SelectCountryPopup);
			screen.waitVanish(SelectCountryPopup,10);
			Thread.sleep(2000);
			return true;
		} catch (InterruptedException | FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
	}
}
