package FactFind;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Discovery.Helper;
import Discovery.JSON;
import Discovery.TestExecution;

public class AddressDetails {
	
	static Log logger = LogFactory.getLog(AddressDetails.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	public static WebDriver driver = null;
	
	
	static Pattern AddressCannotbeFound;
	static Pattern AddressCannotbeFoundokButton;
	static Pattern CountrySearchPopUp;
	static Pattern SelectCountryPopup;
	static Pattern AddressLookUp;
	
	public AddressDetails(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	public AddressDetails(){
		new AddressDetails("C:\\Sikuli Images\\FactFind\\Address\\");
	}
	public AddressDetails(String Imagefolderlocation){
		AddressCannotbeFound = new Pattern (Imagefolderlocation + "AddressCannotbeFound.PNG");
		AddressCannotbeFoundokButton = new Pattern (Imagefolderlocation + "AddressNotFoundokbutton.PNG");
		CountrySearchPopUp = new Pattern (Imagefolderlocation + "CountrySearch.PNG");
		SelectCountryPopup = new Pattern (Imagefolderlocation + "SelectCountryPopup.PNG");
		AddressLookUp = new Pattern (Imagefolderlocation + "AddressLookUp.PNG");
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddAddress']")
	static WebElement AddAddress;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant1']")
	static WebElement Applicant1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant2']")
	static WebElement Applicant2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='addressPredictiveSearchBox']")
	static WebElement AddressLookup;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='addPromptModal']/div/div/div[3]/button")
	static WebElement PopupOkButton;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_unitnumber']")
	static WebElement UnitNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_houseorpropertynumber']")
	static WebElement StreetNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streetname']")
	static WebElement StreetName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streettype']")
	static WebElement StreetType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_postaldeliverytype']")
	static WebElement PostalDeliveryType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_poboxnumber']")
	static WebElement PostalDeliveryNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_suburb']")
	static WebElement Suburb;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_stateorprovince']")
	static WebElement State;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_postalcode']")
	static WebElement Postcode;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='AddressFormView']/div[2]/div/div/fieldset[2]/table/tbody/tr[3]/td[2]/div[2]/div[1]/div/button[2]")
	static WebElement CountrySearch;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_countryid_lookupmodal']/div/section/div/div/div[2]/div[2]/div[1]/div/input")
	static WebElement PopupCountrySearch;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_countryid_lookupmodal']/div/section/div/div/div[3]/button[2]")
	static WebElement PopupCountrySelect;	
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_livingarrangement']")
	static WebElement LivingArrangement;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_addresstype']")
	static WebElement AddressType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_addressformat']")
	static WebElement AddressFormat;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='AddressFormView']/div[2]/div/div/fieldset[3]/table/tbody/tr[1]/td[3]/div[2]/div/input")
	static WebElement MoveInDate;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='AddressFormView']/div[2]/div/div/fieldset[3]/table/tbody/tr[1]/td[4]/div[2]/div/input")
	static WebElement MoveOutDate;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_isprimaryaddress_0']")
	static WebElement IsPrincipalResidence_No;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_isprimaryaddress_1']")
	static WebElement IsPrincipalResidence_Yes;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_isprincipalmailing_0']")
	static WebElement IsPrincipalMailing_No;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_isprincipalmailing_1']")
	static WebElement IsPrincipalMailing_Yes;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ismailingsameasresidentialaddress_0']")
	static WebElement MailingAsSameAsResidential_No;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ismailingsameasresidentialaddress_1']")
	static WebElement MailingAsSameAsResidential_Yes;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertAddBtn']")
	static WebElement SaveandAddAddresstoList;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
	
	public static boolean EnterAddressDetails(){
		
		driver = FactFindExecutor.driver;
		
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		String FirstCustomerFlag = "Yes";
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(AddAddress));			
			
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				
				if (FirstCustomerFlag.equals("Yes") || CustomerInformation.get("IsApplicant").equals("Yes")){
					if (FirstCustomerFlag.equals("Yes")){
						Applicant1.click();
						Thread.sleep(3000);
						FirstCustomerFlag = "No";
					} else if(CustomerInformation.get("IsApplicant").equals("Yes")) {
						Applicant2.click();
						Thread.sleep(3000);
					}
					
					JSONArray CustomerAdressArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("Address");
					Iterator<JSONObject> CustomerAddress = CustomerAdressArray.iterator();
					while (CustomerAddress.hasNext()){
						
						JSONObject Address = CustomerAddress.next();
						AddAddress.click();
						Thread.sleep(3000);
						String AddressString = null;
						if(Address.get("Unitnumber") != null && Address.get("StreetNumber") != null
								&& Address.get("StreetName") != null && Address.get("StreetType") != null
								&& Address.get("Suburb") != null && Address.get("State") != null && Address.get("PostCode") != null){
							
								AddressString = Address.get("Unitnumber").toString() + " " + Address.get("StreetNumber").toString() 
													+ " " + Address.get("StreetName").toString()  + " " + Address.get("StreetType").toString()
													+ ", " + Address.get("Suburb").toString() + " " + Address.get("State").toString() + " " + Address.get("PostCode").toString();
						}else{
							AddressString = "                    ";
						}
						
						screen.find(AddressLookUp).below(Offset[1]).click();
						screen.type(AddressString);
						Thread.sleep(3000);
						
						if (screen.exists(AddressCannotbeFoundokButton) != null){
							screen.click(AddressCannotbeFoundokButton);
							Thread.sleep(3000);
							if(Address.get("Unitnumber") != null){
								Helper.ScroolToView(driver, UnitNumber);
								UnitNumber.click();
								UnitNumber.sendKeys(Address.get("Unitnumber").toString());
								Thread.sleep(1000);
							}
							if(Address.get("StreetNumber") != null){
								Helper.ScroolToView(driver, StreetNumber);
								StreetNumber.click();
								StreetNumber.sendKeys(Address.get("StreetNumber").toString());
								Thread.sleep(1000);
							}
							if(Address.get("StreetName") != null){
								Helper.ScroolToView(driver, StreetName);
								StreetName.click();
								StreetName.sendKeys(Address.get("StreetName").toString());
								Thread.sleep(1000);
							}
							if(Address.get("StreetType") != null){
								Helper.ScroolToView(driver, StreetType);
								StreetType.click();
								StreetType.sendKeys(Address.get("StreetType").toString());
								Thread.sleep(1000);
							}
							if(Address.get("PostalDeliveryType") != null){
								Helper.ScroolToView(driver, PostalDeliveryType);
								PostalDeliveryType.click();
								PostalDeliveryType.sendKeys(Address.get("PostalDeliveryType").toString());
								Thread.sleep(1000);
							}
							if(Address.get("PostalDeliveryNo") != null){
								Helper.ScroolToView(driver, PostalDeliveryNumber);
								PostalDeliveryNumber.click();
								PostalDeliveryNumber.sendKeys(Address.get("PostalDeliveryNo").toString());
								Thread.sleep(1000);
							}
							if(Address.get("Suburb") != null){
								Helper.ScroolToView(driver, Suburb);
								Suburb.click();
								Suburb.sendKeys(Address.get("Suburb").toString());
								Thread.sleep(1000);
							}
							if(Address.get("State") != null){
								Helper.ScroolToView(driver, State);
								State.click();
								State.sendKeys(Address.get("State").toString());
								Thread.sleep(1000);
							}
							if(Address.get("PostCode") != null){
								Helper.ScroolToView(driver, Postcode);
								Postcode.click();
								Postcode.sendKeys(Address.get("PostCode").toString());
								Thread.sleep(1000);
							}
							if(Address.get("Country") != null){
								Helper.ScroolToView(driver, CountrySearch);
								CountrySearch.click();
								Thread.sleep(3000);
								screen.click(CountrySearchPopUp);
								screen.type(Address.get("Country").toString());
								Helper.Keystrokeenter(1);
								Thread.sleep(1000);
								Helper.Keystroketab(5);
								Helper.Keystrokeenter(1);
								screen.click(SelectCountryPopup);
								Thread.sleep(2000);
							}
							
						}else{
							screen.find(AddressLookUp).below(Offset[2]).doubleClick();
							Thread.sleep(4000);
						}
						
						if(Address.get("AddressType") != null && Integer.parseInt(Address.get("AddressType").toString()) >= 1){
							Helper.ScroolToView(driver, AddressType);
							AddressType.click();
							Helper.Keystrokedown(Integer.parseInt(Address.get("AddressType").toString()));
							Helper.Keystrokeenter(1);
						}
						
						if(Address.get("AddressFormat") != null && Integer.parseInt(Address.get("AddressFormat").toString()) >= 1){
							Helper.ScroolToView(driver, AddressFormat);
							AddressFormat.click();
							Helper.Keystrokedown(Integer.parseInt(Address.get("AddressFormat").toString()));
							Helper.Keystrokeenter(1);
						}
						
						if(Address.get("LivingArrangement") != null && Integer.parseInt(Address.get("LivingArrangement").toString()) >= 1){
							Helper.ScroolToView(driver, LivingArrangement);
							LivingArrangement.click();
							Helper.Keystrokedown(Integer.parseInt(Address.get("LivingArrangement").toString()));
							Helper.Keystrokeenter(1);
						}
						if(Address.get("DateMovedIn") != null){
							Helper.ScroolToView(driver, MoveInDate);
							MoveInDate.click();
							Helper.ClearTextBox(10, (float)0.3);
							MoveInDate.sendKeys(Address.get("DateMovedIn").toString());
							Thread.sleep(1000);
						}
						if(Address.get("DateMovedOut") != null){
							Helper.ScroolToView(driver, MoveOutDate);
							MoveOutDate.click();
							Helper.ClearTextBox(10, (float)0.3);
							MoveOutDate.sendKeys(Address.get("DateMovedOut").toString());
							Thread.sleep(1000);
						}						
						if(Address.get("IsPrincipalResidence") != null){
							if (Address.get("IsPrincipalResidence").toString().equals("Yes")){
								IsPrincipalResidence_Yes.click();
								Thread.sleep(1000);
							}else if(Address.get("IsPrincipalResidence").toString().equals("No")){
								IsPrincipalResidence_No.click();
								Thread.sleep(1000);
							}
						}
						if(Address.get("IsPrincipalMailing") != null){
							if (Address.get("IsPrincipalMailing").toString().equals("Yes")){
								IsPrincipalMailing_Yes.click();
								Thread.sleep(1000);
							}else if(Address.get("IsPrincipalMailing").toString().equals("No")){
								IsPrincipalMailing_No.click();
								Thread.sleep(1000);
							}
						}						
						if(Address.get("MailingAddressSame") != null){
							if (Address.get("MailingAddressSame").toString().equals("Yes")){
								MailingAsSameAsResidential_Yes.click();
								Thread.sleep(1000);
							}else if(Address.get("MailingAddressSame").toString().equals("No")){
								MailingAsSameAsResidential_No.click();
								Thread.sleep(1000);
							}
						}
						
						SaveandAddAddresstoList.click();
						Thread.sleep(5000);
					}
					
				}
			}
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFind AddressDetails");
			NextButtonBottomofthePage.click();
			logger.info("FactFind Customer address details entered successfully");
			return true;
		} catch (InterruptedException | FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
	}
}
