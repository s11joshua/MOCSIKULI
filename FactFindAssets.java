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

public class FactFindAssets {
	
	static Log logger = LogFactory.getLog(FactFindAssets.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static WebDriver driver = null;
	
	static Pattern AddressLookUp;
	static Pattern AddressCannotbeFoundokButton;
	static Pattern CountrySearchPopUp;
	static Pattern CountryFilterforSearch;
	static Pattern SelectCountry;
	static Pattern SelectCountryPopup;
	static Pattern SuperHeldBy1;
	static Pattern SuperHeldBy2;
	
	public FactFindAssets(){
		new FactFindAssets("C:\\Sikuli Images\\FactFind\\Assets\\");
	}
	public FactFindAssets(String Imagefolderlocation){
		AddressLookUp = new Pattern (Imagefolderlocation + "ExistingPropertyAddressLookup.PNG");
		AddressCannotbeFoundokButton = new Pattern (Imagefolderlocation + "AddressNotFoundokbutton.PNG");
		CountrySearchPopUp = new Pattern (Imagefolderlocation + "CountrySearch.PNG");
		SelectCountryPopup = new Pattern (Imagefolderlocation + "SelectCountryPopup.PNG");
		CountryFilterforSearch = new Pattern (Imagefolderlocation + "SearchCountryInPopup.PNG");
		SelectCountry = new Pattern (Imagefolderlocation + "SelectCountry.PNG");
		SuperHeldBy1 = new Pattern (Imagefolderlocation + "HeldBy1stPerson.PNG");
		SuperHeldBy2 = new Pattern (Imagefolderlocation + "HeldBy2ndPerson.PNG");
	}
	public FactFindAssets(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddProperty']")
	static WebElement AddProperty;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_unitnumber']")
	static WebElement UnitNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streetnumber']")
	static WebElement StreetNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_addressline1']")
	static WebElement StreetName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streettype']")
	static WebElement StreetType;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_poboxnumber']")
	static WebElement PostalDeliveryNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_suburb']")
	static WebElement Suburb;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_state']")
	static WebElement State;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_postalcode']")
	static WebElement Postcode;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ReEstateFormView']/div[2]/div/div/fieldset[2]/table/tbody/tr[3]/td[1]/div[2]/div[1]/div/button[2]")
	static WebElement SearchCountry;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_realestateassettype']")
	static WebElement RealEstateAssetType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_assetvalue']")
	static WebElement PropertyValue;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomegrossvalue']")
	static WebElement RentalIncome;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomefrequency']")
	static WebElement RentalFrequency;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_totalliabilityvalue']")
	static WebElement LoanBalance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_bank']")
	static WebElement LenderName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_lender']")
	static WebElement LenderNameOther;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant1percentageownership']")
	static WebElement Applicant1Ownership;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant2percentageownership']")
	static WebElement Applicant2Ownership;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertRealEstateBtn']")
	static WebElement SavePropertyDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddSavingsAccount']")
	static WebElement AddSavingsAccount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_savingsacctbank']")
	static WebElement SavingsAccountInstitution;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_otherbank']")
	static WebElement SavingsAccountInstitutionOther;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_savingsacctamount']")
	static WebElement SavingsAccountBalance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant1savingsownership']")
	static WebElement Applicant1SavingsAccountOwnership;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant2savingsownership']")
	static WebElement Applicant2SavingsAccountOwnership;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertSavingsAcctBtn']")
	static WebElement SaveSavingsAccountDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddMotorVehicle']")
	static WebElement AddMotorVehicle;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_motorvehiclemake']")
	static WebElement MotorVehicleMake;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_motorvehicleyear']")
	static WebElement MotorVehicleYear;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_motorvehiclevalue']")
	static WebElement MotorVehicleValue;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_financed_0']")
	static WebElement Financed_No;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_financed_1']")
	static WebElement Financed_Yes;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_0']")
	static WebElement OwnedByOwner1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_1']")
	static WebElement OwnedByOwner2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertMVehicleBtn']")
	static WebElement SaveMotorVehicleDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddSuperannuation']")
	static WebElement AddSuperAnnuation;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_superannuationfund']")
	static WebElement SuperFund;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_othersuperannuationfund']")
	static WebElement SuperFundOther;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_amount']")
	static WebElement SuperAccountBalance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_0']")
	static WebElement HeldByOwner1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_1']")
	static WebElement HeldByOwner2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Button2']")
	static WebElement SaveSupperDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddAsset']")
	static WebElement AddOtherAssets;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_assettype']")
	static WebElement OtherAssetType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_otherassetvalue']")
	static WebElement OtherAssetValue;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant1otherasset']")
	static WebElement Applicant1OwnershipPercentage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant2otherasset']")
	static WebElement Applicant2OwnershipPercentage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertOtherAssetsBtn']")
	static WebElement SaveOtherAssetDetails;

	public static boolean EnterAssetDetails(){
		driver = FactFindExecutor.driver;
					
		if (AddProperty() == false){return false;}
		if (AddSavingsAccount() == false){return false;}
		if (AddMotorVehicle() == false){return false;}
		if (AddSuperAnnuation() == false){return false;}
		if (OtherAssets() == false){return false;}
		
		Helper.ScroolToView(driver, NextButtonTopofthePage);	
		NextButtonTopofthePage.click();
		
		return true;
		
	}
	
	
	static boolean AddProperty(){
		
		
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){
						
					
					JSONArray ExistingPropertyArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("ExistingProperty");
					Iterator<JSONObject> ExistingProperty = ExistingPropertyArray.iterator();
	
					while (ExistingProperty.hasNext()){
						JSONObject Existingproperty = ExistingProperty.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddProperty));
						AddProperty.click();
						Thread.sleep(2000);
						String AddressString = null;
						
						if(Existingproperty.get("Unitnumber") != null && Existingproperty.get("StreetNumber") != null
								&& Existingproperty.get("StreetName") != null && Existingproperty.get("StreetType") != null
								&& Existingproperty.get("Suburb") != null && Existingproperty.get("State") != null && Existingproperty.get("PostCode") != null){
							
								AddressString = Existingproperty.get("Unitnumber").toString() + " " + Existingproperty.get("StreetNumber").toString() 
													+ " " + Existingproperty.get("StreetName").toString()  + " " + Existingproperty.get("StreetType").toString()
													+ ", " + Existingproperty.get("Suburb").toString() + " " + Existingproperty.get("State").toString() + " " + Existingproperty.get("PostCode").toString();
						}else{
							AddressString = "                    ";
						}
						
						screen.find(AddressLookUp).below(Offset[1]).click();
						screen.type(AddressString);
						Thread.sleep(3000);
						if (screen.exists(AddressCannotbeFoundokButton) != null){
							screen.click(AddressCannotbeFoundokButton);
							Thread.sleep(3000);
							if(Existingproperty.get("Unitnumber") != null){
								Helper.ScroolToView(driver, UnitNumber);
								UnitNumber.click();
								UnitNumber.clear();
								UnitNumber.sendKeys(Existingproperty.get("Unitnumber").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("StreetNumber") != null){
								Helper.ScroolToView(driver, StreetNumber);
								StreetNumber.click();
								StreetNumber.clear();
								StreetNumber.sendKeys(Existingproperty.get("StreetNumber").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("StreetName") != null){
								Helper.ScroolToView(driver, StreetName);
								StreetName.click();
								StreetName.clear();
								StreetName.sendKeys(Existingproperty.get("StreetName").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("StreetType") != null){
								Helper.ScroolToView(driver, StreetType);
								StreetType.click();
								StreetType.clear();
								StreetType.sendKeys(Existingproperty.get("StreetType").toString());
								Thread.sleep(500);
							}
							
							if(Existingproperty.get("PostalDeliveryNo") != null){
								Helper.ScroolToView(driver, PostalDeliveryNumber);
								PostalDeliveryNumber.click();
								PostalDeliveryNumber.sendKeys(Existingproperty.get("PostalDeliveryNo").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("Suburb") != null){
								Helper.ScroolToView(driver, Suburb);
								Suburb.click();
								Suburb.sendKeys(Existingproperty.get("Suburb").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("State") != null){
								Helper.ScroolToView(driver, State);
								State.click();
								State.sendKeys(Existingproperty.get("State").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("PostCode") != null){
								Helper.ScroolToView(driver, Postcode);
								Postcode.click();
								Postcode.sendKeys(Existingproperty.get("PostCode").toString());
								Thread.sleep(500);
							}
							if(Existingproperty.get("Country") != null){
								Helper.ScroolToView(driver, SearchCountry);
								SearchCountry.click();
								Thread.sleep(3000);
								screen.click(CountrySearchPopUp);
								screen.type(Existingproperty.get("Country").toString());
								Thread.sleep(1000);
								screen.click(CountryFilterforSearch);
								Thread.sleep(1000);
								screen.find(CountryFilterforSearch).below(Offset[1]).click();
								//Helper.Keystroketab(4);
								//Helper.Keystrokeenter(1);
								screen.click(SelectCountryPopup);
								screen.waitVanish(SelectCountryPopup,15);
							}
							
						}else{
							screen.find(AddressLookUp).below(Offset[2]).doubleClick();
							Thread.sleep(4000);
						}
						
						if(Existingproperty.get("AssetType") != null){
							Helper.ScroolToView(driver, RealEstateAssetType);
							RealEstateAssetType.click();
							RealEstateAssetType.sendKeys(Existingproperty.get("AssetType").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("PropertyValue") != null){
							Helper.ScroolToView(driver, PropertyValue);
							PropertyValue.click();
							PropertyValue.clear();
							PropertyValue.sendKeys(Existingproperty.get("PropertyValue").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("RentalIncome") != null){
							Helper.ScroolToView(driver, RentalIncome);
							RentalIncome.click();
							RentalIncome.clear();
							RentalIncome.sendKeys(Existingproperty.get("RentalIncome").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("RentalFrequency") != null){
							Helper.ScroolToView(driver, RentalFrequency);
							RentalFrequency.click();
							RentalFrequency.sendKeys(Existingproperty.get("RentalFrequency").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("LoanBalance") != null){
							Helper.ScroolToView(driver, LoanBalance);
							LoanBalance.click();
							LoanBalance.clear();
							LoanBalance.sendKeys(Existingproperty.get("LoanBalance").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("LenderName") != null){
							Helper.ScroolToView(driver, LenderName);
							LenderName.click();
							LenderName.sendKeys(Existingproperty.get("LenderName").toString());
							Thread.sleep(500);
						}
						if(Existingproperty.get("LenderNameOther") != null){
							Helper.ScroolToView(driver, LenderNameOther);
							LenderNameOther.click();
							LenderNameOther.clear();
							LenderNameOther.sendKeys(Existingproperty.get("LenderNameOther").toString());
							Thread.sleep(500);
						}
						
						if(Existingproperty.get("OwnershipPercentage") != null){
							double PrimaryOwnership = Double.parseDouble(Existingproperty.get("OwnershipPercentage").toString());
							double SecondaryOwnership = 100 - PrimaryOwnership;
							Applicant1Ownership.click();
							Applicant1Ownership.clear();
							Applicant1Ownership.sendKeys(Double.toString(PrimaryOwnership));
							Applicant2Ownership.click();
							Applicant2Ownership.clear();
							Applicant2Ownership.sendKeys(Double.toString(SecondaryOwnership));
							Thread.sleep(500);
						}else{
							Applicant1Ownership.click();
							Applicant1Ownership.clear();
							Applicant1Ownership.sendKeys(Integer.toString(0));
							Applicant2Ownership.click();
							Applicant2Ownership.clear();
							Applicant2Ownership.sendKeys(Double.toString(100));
							Thread.sleep(500);
						}
						
						SavePropertyDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddProperty);
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindAssetsPropertyDetails");
			logger.info("FactFind Property details under asset has been successfuly entered");
			return true;
			
		} catch (InterruptedException | FindFailed | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}

	static boolean AddSavingsAccount(){
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){
					
					JSONArray SavingsAccountArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("SavingsAccount");
					Iterator<JSONObject> SavingsAccount = SavingsAccountArray.iterator();
	
					while (SavingsAccount.hasNext()){
						JSONObject Savingsaccount = SavingsAccount.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddSavingsAccount));
						Helper.ScroolToView(driver, AddSavingsAccount);
						AddSavingsAccount.click();
						Thread.sleep(2000);
						if(Savingsaccount.get("FinancialInstitution") != null){
							Helper.ScroolToView(driver, SavingsAccountInstitution);
							SavingsAccountInstitution.click();
							SavingsAccountInstitution.sendKeys(Savingsaccount.get("FinancialInstitution").toString());
							Thread.sleep(500);
						}
						if(Savingsaccount.get("FinancialInstitutionOther") != null){
							Helper.ScroolToView(driver, SavingsAccountInstitutionOther);
							SavingsAccountInstitutionOther.click();
							SavingsAccountInstitutionOther.clear();
							SavingsAccountInstitutionOther.sendKeys(Savingsaccount.get("FinancialInstitutionOther").toString());
							Thread.sleep(500);
						}
						if(Savingsaccount.get("AccountBalance") != null){
							Helper.ScroolToView(driver, SavingsAccountBalance);
							SavingsAccountBalance.click();
							SavingsAccountBalance.clear();
							SavingsAccountBalance.sendKeys(Savingsaccount.get("AccountBalance").toString());
							Thread.sleep(500);
						}
						if(Savingsaccount.get("OwnershipPercentage") != null){
							double PrimaryOwnership = Double.parseDouble(Savingsaccount.get("OwnershipPercentage").toString());
							double SecondaryOwnership = 100 - PrimaryOwnership;
							Applicant1SavingsAccountOwnership.click();
							Applicant1SavingsAccountOwnership.clear();
							Applicant1SavingsAccountOwnership.sendKeys(Double.toString(PrimaryOwnership));
							Applicant2SavingsAccountOwnership.click();
							Applicant2SavingsAccountOwnership.clear();
							Applicant2SavingsAccountOwnership.sendKeys(Double.toString(SecondaryOwnership));
							Thread.sleep(500);
						}else{
							Applicant1SavingsAccountOwnership.click();
							Applicant1SavingsAccountOwnership.clear();
							Applicant1SavingsAccountOwnership.sendKeys(Integer.toString(0));
							Applicant2SavingsAccountOwnership.click();
							Applicant2SavingsAccountOwnership.clear();
							Applicant2SavingsAccountOwnership.sendKeys(Double.toString(100));
							Thread.sleep(500);
						}
						
						SaveSavingsAccountDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddProperty);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindSavingsAccDetails");
			logger.info("FactFind Savings account details under asset has been successfuly entered");
			return true;
			
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	static boolean AddMotorVehicle(){
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){
					
					JSONArray MotorVehicleArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("MotorVechileDetails");
					Iterator<JSONObject> MotorVehicle = MotorVehicleArray.iterator();
	
					while (MotorVehicle.hasNext()){
						JSONObject Motorvehicle = MotorVehicle.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddMotorVehicle));
						Helper.ScroolToView(driver, AddMotorVehicle);
						AddMotorVehicle.click();
						Thread.sleep(2000);
						if(Motorvehicle.get("MotorVehicleMake") != null){
							Helper.ScroolToView(driver, MotorVehicleMake);
							MotorVehicleMake.click();
							MotorVehicleMake.clear();
							MotorVehicleMake.sendKeys(Motorvehicle.get("MotorVehicleMake").toString());
							Thread.sleep(500);
						}
						if(Motorvehicle.get("MotorVehicleYear") != null){
							Helper.ScroolToView(driver, MotorVehicleYear);
							MotorVehicleYear.click();
							MotorVehicleYear.clear();
							MotorVehicleYear.sendKeys(Motorvehicle.get("MotorVehicleYear").toString());
							Thread.sleep(500);
						}
						if(Motorvehicle.get("MotorVehicleValue") != null){
							Helper.ScroolToView(driver, MotorVehicleValue);
							MotorVehicleValue.click();
							MotorVehicleValue.clear();
							MotorVehicleValue.sendKeys(Motorvehicle.get("MotorVehicleValue").toString());
							Thread.sleep(500);
						}
						if(Motorvehicle.get("Financed") != null){
							if(Motorvehicle.get("Financed").toString().equals("Yes")){
								Financed_Yes.click();
							}else{
								Financed_No.click();
							}
						}
						if(Motorvehicle.get("OwnedBy") != null){
							if(Motorvehicle.get("OwnedBy").toString().equals("1")){
								OwnedByOwner1.click();
							}else if(Motorvehicle.get("OwnedBy").toString().equals("2")){
								OwnedByOwner2.click();
							}
						}
						
						SaveMotorVehicleDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddSavingsAccount);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindMotorVehicleDetails");
			logger.info("FactFind Motor Vehicle  details under asset has been successfuly entered");
			return true;
			
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	static boolean AddSuperAnnuation(){
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){
					
					JSONArray SuperAnnuationArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("SuperAnnuation");
					Iterator<JSONObject> SuperAnnuation = SuperAnnuationArray.iterator();
	
					while (SuperAnnuation.hasNext()){
						JSONObject Superannuation = SuperAnnuation.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddSuperAnnuation));
						Helper.ScroolToView(driver, AddSuperAnnuation);
						AddSuperAnnuation.click();
						Thread.sleep(2000);
						if(Superannuation.get("SuperannuationFund") != null){
							Helper.ScroolToView(driver,SuperFund );
							SuperFund.click();
							SuperFund.sendKeys(Superannuation.get("SuperannuationFund").toString());
							Thread.sleep(500);
						}
						if(Superannuation.get("SuperannuationFundOther") != null){
							Helper.ScroolToView(driver, SuperFundOther);
							SuperFundOther.click();
							SuperFundOther.clear();
							SuperFundOther.sendKeys(Superannuation.get("SuperannuationFundOther").toString());
							Thread.sleep(1000);
						}
						if(Superannuation.get("AccountBalance") != null){
							Helper.ScroolToView(driver, SuperAccountBalance);
							SuperAccountBalance.click();
							SuperAccountBalance.clear();
							SuperAccountBalance.sendKeys(Superannuation.get("AccountBalance").toString());
							Thread.sleep(1000);
						}
						if(Superannuation.get("HeldBy") != null){
							if(Superannuation.get("HeldBy").toString().equals("2")){
								Helper.ScroolToView(driver, HeldByOwner2);
								screen.click(SuperHeldBy2);
							}else if(Superannuation.get("HeldBy").toString().equals("1")){
								Helper.ScroolToView(driver, HeldByOwner1);
								screen.find(SuperHeldBy1).below(Offset[1]).click();
							}
						}
						
						SaveSupperDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddMotorVehicle);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindSupperDetails");
			logger.info("FactFind superannuation details under asset has been successfuly entered");
			return true;
			
		} catch (InterruptedException | NullPointerException | FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	static boolean OtherAssets(){
		
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){
					
					JSONArray OtherAssetsArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("OtherAssets");
					Iterator<JSONObject> OtherAssets = OtherAssetsArray.iterator();
	
					while (OtherAssets.hasNext()){
						JSONObject Othersssets = OtherAssets.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddOtherAssets));
						Helper.ScroolToView(driver, AddOtherAssets);
						AddOtherAssets.click();
						Thread.sleep(2000);
						if(Othersssets.get("AssetType") != null){
							Helper.ScroolToView(driver,OtherAssetType);
							OtherAssetType.click();
							OtherAssetType.sendKeys(Othersssets.get("AssetType").toString());
							Thread.sleep(500);
						}
						if(Othersssets.get("Value") != null){
							Helper.ScroolToView(driver, OtherAssetValue);
							OtherAssetValue.click();
							OtherAssetValue.clear();
							OtherAssetValue.sendKeys(Othersssets.get("Value").toString());
							Thread.sleep(500);
						}
						if(Othersssets.get("OwnershipPercentage") != null){
							double PrimaryOwnership = Double.parseDouble(Othersssets.get("OwnershipPercentage").toString());
							double SecondaryOwnership = 100 - PrimaryOwnership;
							Applicant1OwnershipPercentage.click();
							Applicant1OwnershipPercentage.clear();
							Applicant1OwnershipPercentage.sendKeys(Double.toString(PrimaryOwnership));
							Applicant2OwnershipPercentage.click();
							Applicant2OwnershipPercentage.clear();
							Applicant2OwnershipPercentage.sendKeys(Double.toString(SecondaryOwnership));
							Thread.sleep(500);
						}else{
							Applicant1OwnershipPercentage.click();
							Applicant1OwnershipPercentage.clear();
							Applicant1OwnershipPercentage.sendKeys(Integer.toString(0));
							Applicant2OwnershipPercentage.click();
							Applicant2OwnershipPercentage.clear();
							Applicant2OwnershipPercentage.sendKeys(Double.toString(100));
							Thread.sleep(500);
						}
						
						SaveOtherAssetDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddMotorVehicle);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindOtherAssets");
			logger.info("FactFind other Assets details under asset has been successfuly entered");
			Thread.sleep(5000);
			return true;
			
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
}
