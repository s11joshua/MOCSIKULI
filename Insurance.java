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
import org.sikuli.script.Screen;
import Discovery.Helper;
import Discovery.JSON;
import Discovery.TestExecution;

public class Insurance {

	static Log logger = LogFactory.getLog(Insurance.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static WebDriver driver = null;
	
	public Insurance(){
		
	}
	
	public Insurance(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant1']")
	static WebElement Applicant1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Applicant2']")
	static WebElement Applicant2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_life']")
	static WebElement LifeInsurance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_lifeamount']")
	static WebElement LifeInsuranceAmount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_lifeprovider']")
	static WebElement LifeInsuranceprovider;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_disability']")
	static WebElement DisabilityInsurance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_disabilityamount']")
	static WebElement DisabilityInsuranceAmount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_disabilityprovider']")
	static WebElement DisabilityInsuranceProvider;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_income']")
	static WebElement IncomeInsurance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomeamount']")
	static WebElement IncomeInsuranceAmount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_incomeprovider']")
	static WebElement IncomeInsuranceProvider;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_trauma']")
	static WebElement TraumaInsurance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_traumaamount']")
	static WebElement TraumaInsuranceAmount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_traumaprovider']")
	static WebElement TraumaInsuranceProvider;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='UpdateDetailsBtn']")
	static WebElement SaveInsuranceDetailsforApplicant1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='UpdateDetails2Btn']")
	static WebElement SaveInsuranceDetailsforApplicant2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='Submit']")
	static WebElement FinishandSubmit;

	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
	
	
	public static boolean EnterinsuranceDetails(){
		driver = FactFindExecutor.driver;
		
		int MaxCustomerreached = 0;
		JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
		Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
		
		String FirstCustomerFlag = "Yes";
		try {
		
			driver = FactFindExecutor.driver;
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(LifeInsurance));
			
			while (CustomerInformationArray.hasNext()){
				if (MaxCustomerreached >= 2){
					break;
				}
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if ((FirstCustomerFlag.equals("Yes") || CustomerInformation.get("IsApplicant").equals("Yes")) && CustomerInformation.get("CustomerType").equals("Individual")){
					
					if (FirstCustomerFlag.equals("Yes")){
						Helper.ScroolToView(driver, Applicant1);
						Applicant1.click();
						Thread.sleep(3000);
						MaxCustomerreached ++;
					} else if(CustomerInformation.get("IsApplicant").equals("Yes")) {
						Helper.ScroolToView(driver, Applicant2);
						Applicant2.click();
						Thread.sleep(3000);
						MaxCustomerreached ++;
					}
					
					JSONObject CustomerInsurance = (JSONObject) JSON.GetTestData(CustomerInformation, "FactFind").get("Insurance");
					
					if (CustomerInsurance.get("Life") != null){
						String LifeInsuranceList[] = CustomerInsurance.get("Life").toString().split("\\|");
						if(!LifeInsuranceList[0].equalsIgnoreCase("null")){
							LifeInsurance.click();
							LifeInsurance.sendKeys(LifeInsuranceList[0].toString());
							Thread.sleep(500);
						}
						if(!LifeInsuranceList[1].equalsIgnoreCase("null")){
							LifeInsuranceAmount.click();
							LifeInsuranceAmount.clear();
							LifeInsuranceAmount.sendKeys(LifeInsuranceList[1].toString());
							Thread.sleep(500);
						}
						if(!LifeInsuranceList[2].equalsIgnoreCase("null")){
							LifeInsuranceprovider.click();
							LifeInsuranceprovider.clear();
							LifeInsuranceprovider.sendKeys(LifeInsuranceList[2].toString());
							Thread.sleep(500);
						}
					}
					
								
					if (CustomerInsurance.get("Disability") != null){
						String DisabilityInsuranceList[] = CustomerInsurance.get("Disability").toString().split("\\|");
						
						if(!DisabilityInsuranceList[0].equalsIgnoreCase("null")){
							DisabilityInsurance.click();
							DisabilityInsurance.sendKeys(DisabilityInsuranceList[0].toString());
							Thread.sleep(500);
						}
						if(!DisabilityInsuranceList[1].equalsIgnoreCase("null")){
							DisabilityInsuranceAmount.click();
							DisabilityInsuranceAmount.clear();
							DisabilityInsuranceAmount.sendKeys(DisabilityInsuranceList[1].toString());
							Thread.sleep(500);
						}
						if(!DisabilityInsuranceList[2].equalsIgnoreCase("null")){
							DisabilityInsuranceProvider.click();
							DisabilityInsuranceProvider.clear();
							DisabilityInsuranceProvider.sendKeys(DisabilityInsuranceList[2].toString());
							Thread.sleep(500);
						}
					}
					
					if (CustomerInsurance.get("Income") != null){
						String IncomeInsuranceList[] = CustomerInsurance.get("Income").toString().split("\\|");
						
						if(!IncomeInsuranceList[0].equalsIgnoreCase("null")){
							IncomeInsurance.click();
							IncomeInsurance.sendKeys(IncomeInsuranceList[0] .toString());
							Thread.sleep(500);
						}
						if(!IncomeInsuranceList[1].equalsIgnoreCase("null")){
							IncomeInsuranceAmount.click();
							IncomeInsuranceAmount.clear();
							IncomeInsuranceAmount.sendKeys(IncomeInsuranceList[1] .toString());
							Thread.sleep(500);
						}
						if(!IncomeInsuranceList[2].equalsIgnoreCase("null")){
							IncomeInsuranceProvider.click();
							IncomeInsuranceProvider.clear();
							IncomeInsuranceProvider.sendKeys(IncomeInsuranceList[2].toString());
							Thread.sleep(500);
						}
					}
					
					if (CustomerInsurance.get("Trauma") != null){
						String TraumaInsuranceList[] = CustomerInsurance.get("Trauma").toString().split("\\|");
						
						if(!TraumaInsuranceList[0].equalsIgnoreCase("null")){
							TraumaInsurance.click();
							TraumaInsurance.sendKeys(TraumaInsuranceList[0].toString());
							Thread.sleep(500);
						}
						if(!TraumaInsuranceList[1].equalsIgnoreCase("null")){
							TraumaInsuranceAmount.click();
							TraumaInsuranceAmount.clear();
							TraumaInsuranceAmount.sendKeys(TraumaInsuranceList[1].toString());
							Thread.sleep(500);
						}
						if(!TraumaInsuranceList[2].equalsIgnoreCase("null")){
							TraumaInsuranceProvider.click();
							TraumaInsuranceProvider.clear();
							TraumaInsuranceProvider.sendKeys(TraumaInsuranceList[2].toString());
							Thread.sleep(500);
						}
					}
					
					if (FirstCustomerFlag.equals("Yes")){
						Thread.sleep(500);
						Helper.ScroolToView(driver, SaveInsuranceDetailsforApplicant1);
						SaveInsuranceDetailsforApplicant1.click();	
					} else if(CustomerInformation.get("IsApplicant").equals("Yes")) {
						Thread.sleep(500);
						Helper.ScroolToView(driver, SaveInsuranceDetailsforApplicant2);
						SaveInsuranceDetailsforApplicant2.click();
					}
					
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindInsuranceDetails");
					FirstCustomerFlag = "No";
				}
				
			}
		
		logger.info("FactFind Insurance details entered successfully");
		Thread.sleep(5000);
		return true;
		}catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	
	}
}
