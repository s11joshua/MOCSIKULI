package FactFind;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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

public class PersonalDetails {
	static Log logger = LogFactory.getLog(PersonalDetails.class);
	public static WebDriver driver = null;
	
	public PersonalDetails(){
		
	}
	
	public PersonalDetails(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant1']")
	static WebElement Applicant1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant2']")
	static WebElement Applicant2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_salutation']")
	static WebElement Title;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='firstname']")
	static WebElement FirstName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='middlename']")
	static WebElement MiddleName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lastname']")
	static WebElement LastName;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='gendercode']")
	static WebElement Gender;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView1']/div[2]/div/div/fieldset/table/tbody/tr[2]/td[2]/div[2]/div/input")
	static WebElement DateOfbirth;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView2']/div[2]/div/div/fieldset/table/tbody/tr[2]/td[2]/div[2]/div/input")
	static WebElement DateOfbirthSpouse;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_residencystatus']")
	static WebElement ResidencyStatus;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='emailaddress1']")
	static WebElement Email;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mobilephone']")
	static WebElement Mobile;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='telephone2']")
	static WebElement HomePhone;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='telephone1']")
	static WebElement BusinessPhone;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_driverslicensenumber']")
	static WebElement DriversLicenceNumber;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_licenseltate']")
	static WebElement LicenceState;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView1']/div[2]/div/div/fieldset/table/tbody/tr[4]/td[3]/div[2]/div/input")
	static WebElement LicenceIssued;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView2']/div[2]/div/div/fieldset/table/tbody/tr[4]/td[3]/div[2]/div/input")
	static WebElement LicenceIssuedSpouse;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView1']/div[2]/div/div/fieldset/table/tbody/tr[4]/td[4]/div[2]/div/input")
	static WebElement LicenceExpiry;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView2']/div[2]/div/div/fieldset/table/tbody/tr[4]/td[4]/div[2]/div/input")
	static WebElement LicenceExpirySpouse;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='UpdateDetails1Btn']")
	static WebElement SaveMyDetails;
		
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddDependant']")
	static WebElement AddDependant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_age']")
	static WebElement AgeofDependant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='InsertDependentBtn']")
	static WebElement SaveDependent;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
		
	
	public static boolean CustomerPersonalDetails(){
		
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		String FirstCustomerFlag = "Yes";
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(NextButtonTopofthePage));			
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				
				if (FirstCustomerFlag.equals("Yes") || CustomerInformation.get("IsApplicant").equals("Yes")){
					
					if (FirstCustomerFlag.equals("Yes")){
						Applicant1.click();
						Thread.sleep(3000);
					} else if(CustomerInformation.get("IsApplicant").equals("Yes")) {
						Applicant2.click();
						Thread.sleep(3000);
					}
					
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("Title") != null && Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerNames").get("Title").toString()) >= 1){
						Title.click();
						Helper.Keystrokeup(9);
						Helper.Keystrokedown(Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerNames").get("Title").toString()));
					}
					
					if(JSON.GetTestData(CustomerInformation, "CustomerNames").get("MiddleName") != null){
						MiddleName.click();
						MiddleName.clear();
						MiddleName.sendKeys(JSON.GetTestData(CustomerInformation, "CustomerNames").get("MiddleName").toString());
					}
					
					if(CustomerInformation.get("Gender") != null){					
						if(CustomerInformation.get("Gender").equals("Male") || CustomerInformation.get("Gender").equals("Female")){
							Gender.click();
							Gender.sendKeys(CustomerInformation.get("Gender").toString());
							Helper.Keystrokeenter(1);
						}
						else{
							Gender.click();
							Gender.sendKeys("Unspecified");
							Helper.Keystrokeenter(1);
						}
					}
					
					if(CustomerInformation.get("DOB") != null){					
						if(FirstCustomerFlag.equals("Yes") ) {
							DateOfbirth.click();
							DateOfbirth.clear();
							DateOfbirth.sendKeys(CustomerInformation.get("DOB").toString());
						}
						else if(CustomerInformation.get("IsApplicant").equals("Yes")){
							DateOfbirthSpouse.click();
							DateOfbirthSpouse.clear();
							DateOfbirthSpouse.sendKeys(CustomerInformation.get("DOB").toString());
						}
					}
					
					if(CustomerInformation.get("ResidentialStatus") != null && Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) >= 1){
						if (Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) == 1 || 
								Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) == 2 || 
									Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) == 3){
							ResidencyStatus.click();
							Helper.Keystrokeup(3);
							Helper.Keystrokedown(2);
							Helper.Keystrokeenter(1);
						}else if(Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) == 4){
							ResidencyStatus.click();
							Helper.Keystrokeup(3);
							Helper.Keystrokedown(1);
							Helper.Keystrokeenter(1);
						}else if(Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) == 5){
							ResidencyStatus.click();
							Helper.Keystrokeup(3);
							Helper.Keystrokedown(3);
							Helper.Keystrokeenter(1);
						}
					
					}
				
					if(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("Mobile") != null){
						Mobile.click();
						Mobile.clear();
						Mobile.sendKeys(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("Mobile").toString());
					}
					
					if(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("HomePhone") != null){
						HomePhone.click();
						HomePhone.clear();
						HomePhone.sendKeys(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("HomePhone").toString());
					}
					
					if(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("BusinessPhone") != null){
						BusinessPhone.click();
						BusinessPhone.clear();
						BusinessPhone.sendKeys(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("BusinessPhone").toString());
					}
					
					JSONObject FactFind = JSON.GetTestData(CustomerInformation, "FactFind");
					
					if(JSON.GetTestData(FactFind, "DriversLicense").get("DriversLicenceNumber") != null){
						DriversLicenceNumber.click();
						DriversLicenceNumber.clear();
						DriversLicenceNumber.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("DriversLicenceNumber").toString());
					}
						
					if(JSON.GetTestData(FactFind, "DriversLicense").get("LicenseState") != null){
						LicenceState.click();
						LicenceState.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("LicenseState").toString());
						Helper.Keystrokeenter(1);
					}
					
					if(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceIssued") != null){
						if(FirstCustomerFlag.equals("Yes")){
							LicenceIssued.click();
							LicenceIssued.clear();
							LicenceIssued.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceIssued").toString());
						}else if(CustomerInformation.get("IsApplicant").equals("Yes")){
							LicenceIssuedSpouse.click();
							LicenceIssuedSpouse.clear();
							LicenceIssuedSpouse.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceIssued").toString());
						}
					}
					
					if(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceExpires") != null){
						if(FirstCustomerFlag.equals("Yes")){
							LicenceExpiry.click();
							LicenceExpiry.clear();
							LicenceExpiry.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceExpires").toString());
						}else if(CustomerInformation.get("IsApplicant").equals("Yes")){
							LicenceExpirySpouse.click();
							LicenceExpirySpouse.clear();
							LicenceExpirySpouse.sendKeys(JSON.GetTestData(FactFind, "DriversLicense").get("LicenceExpires").toString());
						}
					}
				
					Thread.sleep(3000);
					
					JSONArray DependantDOBArray = (JSONArray) JSON.GetTestData(CustomerInformation, "CustomerDependents").get("DependentsDOB");
					Iterator<String> DOBArray = DependantDOBArray.iterator();
					while (DOBArray.hasNext()){
						Helper.ScroolToView(driver, AddDependant);	
						AddDependant.click();
						Thread.sleep(2000);
						AgeofDependant.sendKeys(CalculateAge(DOBArray.next().toString()));// we have to pass the actual value of the child this is a dummy value.
						Helper.ScroolToView(driver, SaveDependent);
						SaveDependent.click();
						//screen.click(SaveDependantAge);
					}
					FirstCustomerFlag = "No";
				}
				
			}
			Applicant1.click();
			Thread.sleep(5000);
			Helper.ScroolToView(driver, SaveMyDetails);	
			SaveMyDetails.click();
			Thread.sleep(5000);
			Helper.ScroolToView(driver, NextButtonBottomofthePage);
			NextButtonBottomofthePage.click();
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindPersonalDetails");
			logger.info("FactFind Customer personal details entered successfully");
			return true;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error(e.toString());
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				return false;
			}
		
	}
	
	static String CalculateAge(String DOB){
		LocalDate today = LocalDate.now();
		
		String[] str_array = DOB.split("/");
		
		LocalDate birthday = LocalDate.of(Integer.parseInt(str_array[2]), Month.of(Integer.parseInt(str_array[1])), Integer.parseInt(str_array[0]));
		 
		Period p = Period.between(birthday, today);
		
		return Integer.toString(p.getYears());
	}
}
