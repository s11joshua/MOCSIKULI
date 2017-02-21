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

public class EmploymentDetails {

	static Log logger = LogFactory.getLog(EmploymentDetails.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	public static WebDriver driver = null;
	
	static Pattern AddressCannotbeFoundokButton;
	static Pattern CountrySearchPopUp;
	static Pattern CountryFilterforSearch;
	static Pattern SelectCountry;
	static Pattern SelectCountryPopup;
	static Pattern AddressLookUp;
	
	public EmploymentDetails(){
		new EmploymentDetails("C:\\Sikuli Images\\FactFind\\EmploymentDetails\\");
	}
	
	public EmploymentDetails(String Imagefolderlocation){
		AddressCannotbeFoundokButton = new Pattern (Imagefolderlocation + "AddressNotFoundokbutton.PNG");
		CountrySearchPopUp = new Pattern (Imagefolderlocation + "CountrySearch.PNG");
		SelectCountryPopup = new Pattern (Imagefolderlocation + "SelectCountryPopup.PNG");
		CountryFilterforSearch = new Pattern (Imagefolderlocation + "SearchCountryInPopup.PNG");
		SelectCountry = new Pattern (Imagefolderlocation + "SelectCountry.PNG");
		AddressLookUp = new Pattern (Imagefolderlocation + "AddressLookUp.PNG");
	}
	
	public EmploymentDetails(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant1']")
	static WebElement Applicant1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant2']")
	static WebElement Applicant2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddEmployment']")
	static WebElement AddEmployment;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_employername']")
	static WebElement EmployerName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_employmentrole']")
	static WebElement JobTitle;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_abnoracn']")
	static WebElement ABNACNNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_onprobation_0']")
	static WebElement OnProbation_No;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_onprobation_1']")
	static WebElement OnProbation_Yes;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_employmentcategory']")
	static WebElement EmploymentType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_employmentstatus']")
	static WebElement EmploymentStatus;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_contactperson']")
	static WebElement ContactPerson;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_contactphone']")
	static WebElement ContactPhone;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_grossincome']")
	static WebElement GrossIncome;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_payfrequency']")
	static WebElement PayFrequency;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='EmploymentFormView']/div[2]/div/div/fieldset[1]/table/tbody/tr[3]/td[3]/div[2]/div/input")
	static WebElement StartDate;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='EmploymentFormView']/div[2]/div/div/fieldset[1]/table/tbody/tr[3]/td[4]/div[2]/div/input")
	static WebElement EndDate;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_unitnumber']")
	static WebElement UnitNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streetnumber']")
	static WebElement StreetNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streetname']")
	static WebElement StreetName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_streettype']")
	static WebElement StreetType;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_poboxnumber']")
	static WebElement PostalDeliveryNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_suburb']")
	static WebElement Suburb;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_state']")
	static WebElement State;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_postcode']")
	static WebElement Postcode;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='EmploymentFormView']/div[2]/div/div/fieldset[2]/table/tbody/tr[3]/td[1]/div[2]/div[1]/div/button[2]")
	static WebElement SearchCountry;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Button1']")
	static WebElement SaveEmployment;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddIncome']")
	static WebElement AddOtherincome;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incometype']")
	static WebElement IncomeType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomegrossvalue']")
	static WebElement AmountBeforeTax;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomefrequency']")
	static WebElement IncomeFrequency;

	@FindBy(how = How.XPATH, using = ".//*[@id='InsertIncomeBtn']")
	static WebElement SaveOtherIncome;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
	
	public static boolean EnterEmploymentDetails(){
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		String FirstCustomerFlag = "Yes";
		try {
		
			driver = FactFindExecutor.driver;
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(AddEmployment));
			
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if (FirstCustomerFlag.equals("Yes") || CustomerInformation.get("IsApplicant").equals("Yes")){
					
					if (FirstCustomerFlag.equals("Yes")){
						Helper.ScroolToView(driver, Applicant1);
						Applicant1.click();
						Thread.sleep(3000);
					} else if(CustomerInformation.get("IsApplicant").equals("Yes")) {
						Helper.ScroolToView(driver, Applicant2);
						Applicant2.click();
						Thread.sleep(3000);
					}
					
					JSONArray CustomerEmploymentArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("EmploymentDetails");
					Iterator<JSONObject> Employment = CustomerEmploymentArray.iterator();
					JSONArray CustomerOtherIncomeArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("OtherIncome");
					Iterator<JSONObject> OtherIncome = CustomerOtherIncomeArray.iterator();
					
						while (Employment.hasNext()){
						JSONObject employment = Employment.next();
						Helper.ScroolToView(driver, AddEmployment);
						AddEmployment.click();
						Thread.sleep(3000);
						String AddressString = null;
						if(employment.get("Unitnumber") != null && employment.get("StreetNumber") != null
								&& employment.get("StreetName") != null && employment.get("StreetType") != null
								&& employment.get("Suburb") != null && employment.get("State") != null && employment.get("PostCode") != null){
							
								AddressString = employment.get("Unitnumber").toString() + " " + employment.get("StreetNumber").toString() 
													+ " " + employment.get("StreetName").toString()  + " " + employment.get("StreetType").toString()
													+ ", " + employment.get("Suburb").toString() + " " + employment.get("State").toString() + " " + employment.get("PostCode").toString();
						}else{
							AddressString = "                    ";
						}
						
						if(employment.get("CompanyName") != null){
							Helper.ScroolToView(driver, EmployerName);
							EmployerName.click();
							EmployerName.clear();
							EmployerName.sendKeys(employment.get("CompanyName").toString());
							Thread.sleep(1000);
						}
						if(employment.get("JobTitle") != null){
							Helper.ScroolToView(driver, JobTitle);
							JobTitle.click();
							JobTitle.clear();
							JobTitle.sendKeys(employment.get("JobTitle").toString());
							Thread.sleep(1000);
						}
						if(employment.get("ABN/ACN") != null){
							Helper.ScroolToView(driver, ABNACNNumber);
							ABNACNNumber.click();
							ABNACNNumber.clear();
							ABNACNNumber.sendKeys(employment.get("ABN/ACN").toString());
							Thread.sleep(1000);
						}
						if(employment.get("Probation") != null){
							if (employment.get("Probation").toString().equals("Yes")){
								OnProbation_Yes.click();
								Thread.sleep(1000);
							}else if(employment.get("Probation").toString().equals("No")){
								OnProbation_No.click();
								Thread.sleep(1000);
							}
						}
						if(employment.get("EmploymentType") != null){
							Helper.ScroolToView(driver, EmploymentType);
							EmploymentType.click();
							EmploymentType.sendKeys(employment.get("EmploymentType").toString());
							Helper.Keystrokeenter(1);
							Thread.sleep(1000);
						}
						if(employment.get("EmploymentStatus") != null){
							Helper.ScroolToView(driver, EmploymentStatus);
							EmploymentStatus.click();
							EmploymentStatus.sendKeys(employment.get("EmploymentStatus").toString());
							Helper.Keystrokeenter(1);
							Thread.sleep(1000);
						}
						if(employment.get("ContactPerson") != null){
							Helper.ScroolToView(driver, ContactPerson);
							ContactPerson.click();
							ContactPerson.clear();
							ContactPerson.sendKeys(employment.get("ContactPerson").toString());
							Thread.sleep(1000);
						}
						if(employment.get("ContactPhone") != null){
							Helper.ScroolToView(driver, ContactPhone);
							ContactPhone.click();
							ContactPhone.clear();
							ContactPhone.sendKeys(employment.get("ContactPhone").toString());
							Thread.sleep(1000);
						}
						if(employment.get("GrossIncome") != null){
							Helper.ScroolToView(driver, GrossIncome);
							GrossIncome.click();
							GrossIncome.clear();
							GrossIncome.sendKeys(employment.get("GrossIncome").toString());
							Thread.sleep(1000);
						}
						if(employment.get("PayFrequency") != null){
							Helper.ScroolToView(driver, PayFrequency);
							PayFrequency.click();
							PayFrequency.sendKeys(employment.get("PayFrequency").toString());
							Helper.Keystrokeenter(1);
							Thread.sleep(1000);
						}
						if(employment.get("StartDate") != null){
							Helper.ScroolToView(driver, StartDate);
							StartDate.click();
							StartDate.clear();
							StartDate.sendKeys(employment.get("StartDate").toString());
							Thread.sleep(1000);
						}
						if(employment.get("EndDate") != null){
							Helper.ScroolToView(driver, EndDate);
							EndDate.click();
							EndDate.clear();
							EndDate.sendKeys(employment.get("EndDate").toString());
							Thread.sleep(1000);
						}
						
						Helper.ScroolToView(driver, GrossIncome);
						screen.find(AddressLookUp).below(Offset[1]).click();
						screen.type(AddressString);
						Thread.sleep(3000);
					
						if (screen.exists(AddressCannotbeFoundokButton) != null){
							screen.click(AddressCannotbeFoundokButton);
							Thread.sleep(3000);
							if(employment.get("Unitnumber") != null){
								Helper.ScroolToView(driver, UnitNumber);
								UnitNumber.click();
								UnitNumber.clear();
								UnitNumber.sendKeys(employment.get("Unitnumber").toString());
								Thread.sleep(1000);
							}
							if(employment.get("StreetNumber") != null){
								Helper.ScroolToView(driver, StreetNumber);
								StreetNumber.click();
								StreetNumber.sendKeys(employment.get("StreetNumber").toString());
								Thread.sleep(1000);
							}
							if(employment.get("StreetName") != null){
								Helper.ScroolToView(driver, StreetName);
								StreetName.click();
								StreetName.clear();
								StreetName.sendKeys(employment.get("StreetName").toString());
								Thread.sleep(1000);
							}
							if(employment.get("StreetType") != null){
								Helper.ScroolToView(driver, StreetType);
								StreetType.click();
								StreetType.clear();
								StreetType.sendKeys(employment.get("StreetType").toString());
								Thread.sleep(1000);
							}
							
							if(employment.get("PostalDeliveryNo") != null){
								Helper.ScroolToView(driver, PostalDeliveryNumber);
								PostalDeliveryNumber.click();
								PostalDeliveryNumber.sendKeys(employment.get("PostalDeliveryNo").toString());
								Thread.sleep(1000);
							}
							if(employment.get("Suburb") != null){
								Helper.ScroolToView(driver, Suburb);
								Suburb.click();
								Suburb.sendKeys(employment.get("Suburb").toString());
								Thread.sleep(1000);
							}
							if(employment.get("State") != null){
								Helper.ScroolToView(driver, State);
								State.click();
								State.sendKeys(employment.get("State").toString());
								Thread.sleep(1000);
							}
							if(employment.get("PostCode") != null){
								Helper.ScroolToView(driver, Postcode);
								Postcode.click();
								Postcode.sendKeys(employment.get("PostCode").toString());
								Thread.sleep(1000);
							}
							if(employment.get("Country") != null){
								Helper.ScroolToView(driver, SearchCountry);
								SearchCountry.click();
								Thread.sleep(3000);
								screen.click(CountrySearchPopUp);
								screen.type(employment.get("Country").toString());
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
						
						Helper.ScroolToView(driver, EmployerName);//this is to view what has been entered.
						Thread.sleep(2000);
						Helper.ScroolToView(driver, SaveEmployment);
						SaveEmployment.click();
						Thread.sleep(2000);					
					}
				
					while (OtherIncome.hasNext()){
						
						JSONObject Otherincome = OtherIncome.next();
						Helper.ScroolToView(driver, AddOtherincome);
						AddOtherincome.click();
						Thread.sleep(2000);
						
						if(Otherincome.get("IncomeType") != null){
							Helper.ScroolToView(driver, IncomeType);
							IncomeType.click();
							IncomeType.sendKeys(Otherincome.get("IncomeType").toString());
							Thread.sleep(1000);
						}
						if(Otherincome.get("Amount") != null){
							Helper.ScroolToView(driver, AmountBeforeTax);
							AmountBeforeTax.click();
							AmountBeforeTax.clear();
							AmountBeforeTax.sendKeys(Otherincome.get("Amount").toString());
							Thread.sleep(1000);
						}
						if(Otherincome.get("PayFrequency") != null){
							Helper.ScroolToView(driver, IncomeFrequency);
							IncomeFrequency.click();
							IncomeFrequency.sendKeys(Otherincome.get("PayFrequency").toString());
							Thread.sleep(1000);
						}
						
						Helper.ScroolToView(driver, AddOtherincome);
						Thread.sleep(1000);
						Helper.ScroolToView(driver, SaveOtherIncome);
						SaveOtherIncome.click();
						Thread.sleep(2000);	
					}
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFind EmploymentDetails");
				FirstCustomerFlag = "No";
			}
			
		}
		Helper.ScroolToView(driver, NextButtonBottomofthePage);
		NextButtonBottomofthePage.click();
		logger.info("FactFind Employment details entered successfully");
		return true;
		}catch (InterruptedException | FindFailed e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
		
}
