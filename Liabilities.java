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
import Discovery.SaveScenario;
import Discovery.TestExecution;

public class Liabilities {

	static Log logger = LogFactory.getLog(Liabilities.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static WebDriver driver = null;
	static boolean JointClient;
	
	public Liabilities(){
		
	}
	
	public Liabilities(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextButton']")
	static WebElement NextButtonBottomofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='NextBtn']")
	static WebElement NextButtonTopofthePage;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddCreditCard']")
	static WebElement AddCreditCard;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_creditcardbank']")
	static WebElement CreditCardProviderName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_creditcardotherbank']")
	static WebElement CreditCardProviderNameOther;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_creditcardamountowning']")
	static WebElement CreditCardCurrentBalance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_creditcardlimit']")
	static WebElement CreditCardLimit;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_0']")
	static WebElement CreditCardOwnedBy1stApplicant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_ownsby_1']")
	static WebElement CreditCardOwnedBy2ndApplicant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='CreditCardBtn']")
	static WebElement SaveCreditCardDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddLoan']")
	static WebElement AddLoanDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_liabilitytype']")
	static WebElement DebitType;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_bank']")
	static WebElement FinancialInstitutionName;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_otherbank']")
	static WebElement FinancialInstitutionNameOther;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_totalbalance']")
	static WebElement LoanCurrentBalance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_totallimit']")
	static WebElement LoanLimit;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_repaymentperiod']")
	static WebElement RepaymentPeriod;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_repaymentamount']")
	static WebElement RepaymentAmount;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_consolidatedebt']")
	static WebElement ConsolidateDebt;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant1liability']")
	static WebElement Ownership1stApplicant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_applicant2liability']")
	static WebElement Ownership2ndApplicant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='OtherLoansBtn']")
	static WebElement SaveLoanDetails;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_groceries']")
	static WebElement Groceries;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_homeutilitiesandrates']")
	static WebElement HomeUtilitiesandRates;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_telephoneandinternet']")
	static WebElement TelephoneandInternet;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_investmentpropertyutilitiesandrates']")
	static WebElement InvestmentUtilitiesandRates;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_transport']")
	static WebElement Transport;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_medicalandhealth']")
	static WebElement MedicalandHealth;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_recreationandentertainment']")
	static WebElement RecreationandEntertainment;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_paytv']")
	static WebElement PayTv;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_education']")
	static WebElement Education;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_clothingandpersonalcare']")
	static WebElement ClothingandPersonalCare;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_childcare']")
	static WebElement ChildCare;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_insurance']")
	static WebElement Insurance;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ExpenseBtn']")
	static WebElement SaveExpenses;
	
	public static boolean EnterLiabilities(){
		driver = FactFindExecutor.driver;		
		JointClient = FactFindExecutor.IsJointClient(TestExecution.JSONTestData);
		
		if (CreditCard() == false){return false;}
		if (LoanDetails() == false){return false;}
		if (Expenses() == false){return false;}
		return true;
	}
	
	static boolean CreditCard(){
		
		
			try {
				
				JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
				Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
				
				while (CustomerInformationArray.hasNext()){
					
					JSONObject CustomerInformation = CustomerInformationArray.next();
					if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").equals("Individual")){
						
						JSONArray CreditCardArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("CreditCard");
						Iterator<JSONObject> CreditCard = CreditCardArray.iterator();
		
						while (CreditCard.hasNext()){
							JSONObject Creditcard = CreditCard.next();
							
							Thread.sleep(2000);
							WebDriverWait wait = new WebDriverWait(driver, 30);
							wait.until(ExpectedConditions.elementToBeClickable(AddCreditCard));
							Helper.ScroolToView(driver, AddCreditCard);
							AddCreditCard.click();
							Thread.sleep(2000);
							if(Creditcard.get("CreditCardProvider") != null){
								Helper.ScroolToView(driver,CreditCardProviderName);
								CreditCardProviderName.click();
								CreditCardProviderName.sendKeys(Creditcard.get("CreditCardProvider").toString());
								Thread.sleep(500);
							}
							if(Creditcard.get("Other") != null){
								Helper.ScroolToView(driver, CreditCardProviderNameOther);
								CreditCardProviderNameOther.click();
								CreditCardProviderNameOther.clear();
								CreditCardProviderNameOther.sendKeys(Creditcard.get("Other").toString());
								Thread.sleep(500);
							}
							if(Creditcard.get("CurrentBalance") != null){
								Helper.ScroolToView(driver, CreditCardCurrentBalance);
								CreditCardCurrentBalance.click();
								CreditCardCurrentBalance.clear();
								CreditCardCurrentBalance.sendKeys(Creditcard.get("CurrentBalance").toString());
								Thread.sleep(500);
							}
							if(Creditcard.get("Limit") != null){
								Helper.ScroolToView(driver, CreditCardLimit);
								CreditCardLimit.click();
								CreditCardLimit.clear();
								CreditCardLimit.sendKeys(Creditcard.get("Limit").toString());
								Thread.sleep(500);
							}
							if(JointClient){
								if(Creditcard.get("HeldBy") != null){
									if(Creditcard.get("HeldBy").toString().equals("2")){
										Helper.ScroolToView(driver, CreditCardOwnedBy2ndApplicant);
										CreditCardOwnedBy2ndApplicant.click();
									}else if(Creditcard.get("HeldBy").toString().equals("1")){
										Helper.ScroolToView(driver, CreditCardOwnedBy1stApplicant);
										CreditCardOwnedBy1stApplicant.click();
									}
								}
							}
							SaveCreditCardDetails.click();
							Thread.sleep(2000);
						}
					}
				}
				
				Helper.ScroolToView(driver, AddCreditCard);		
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindLiabilities");
				logger.info("FactFind credit card details under liabilities has been successfully entered");
				return true;
				
			} catch (InterruptedException | NullPointerException e) {
				e.printStackTrace();
				logger.error(e.toString());
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				return false;
			}

	}

	static boolean LoanDetails(){
	
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes") && CustomerInformation.get("CustomerType").equals("Individual")){
					
					JSONArray LoansArray = (JSONArray) JSON.GetTestData(CustomerInformation, "FactFind").get("Loans");
					Iterator<JSONObject> Loans = LoansArray.iterator();
	
					while (Loans.hasNext()){
						JSONObject loans = Loans.next();
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(AddLoanDetails));
						Helper.ScroolToView(driver, AddLoanDetails);
												
						String Debittype = null;
						if(loans.get("DebitType") != null){
							AddLoanDetails.click();
							Thread.sleep(2000);
							Helper.ScroolToView(driver,DebitType);
							DebitType.click();
							Debittype = loans.get("DebitType").toString();
							DebitType.sendKeys(Debittype);
							Thread.sleep(1000);
						}else{
							break;
						}
						
						if (Debittype.equals("Other") || Debittype.equals("Personal Loan")
							|| Debittype.equals("Lease") || Debittype.equals("Hire Purchase")){
							if(loans.get("FinancialInstitution") != null){
								Helper.ScroolToView(driver, FinancialInstitutionName);
								FinancialInstitutionName.click();
								FinancialInstitutionName.sendKeys(loans.get("FinancialInstitution").toString());
								Thread.sleep(500);
							}
						}
						if (Debittype.equals("Other") || Debittype.equals("Personal Loan")
								|| Debittype.equals("Lease") || Debittype.equals("Hire Purchase")){
							if(loans.get("FinancialInstitutionOther") != null){
								Helper.ScroolToView(driver, FinancialInstitutionNameOther);
								FinancialInstitutionNameOther.click();
								FinancialInstitutionNameOther.clear();
								FinancialInstitutionNameOther.sendKeys(loans.get("FinancialInstitutionOther").toString());
								Thread.sleep(500);
							}
						}
						
						if (!Debittype.equals("Commercial Liabilities") && !Debittype.equals("Commercial Liabilities")){
							if(loans.get("CurrentBalance") != null){
								Helper.ScroolToView(driver, LoanCurrentBalance);
								LoanCurrentBalance.click();
								LoanCurrentBalance.clear();
								LoanCurrentBalance.sendKeys(loans.get("CurrentBalance").toString());
								Thread.sleep(1000);
							}
						}
						
						if (Debittype.equals("Other") || Debittype.equals("Personal Loan")
								|| Debittype.equals("Commercial Liabilities") || Debittype.equals("Contingent Liabilities")){	
							if(loans.get("Limit") != null){
								Helper.ScroolToView(driver, LoanLimit);
								LoanLimit.click();
								LoanLimit.clear();
								LoanLimit.sendKeys(loans.get("Limit").toString());
								Thread.sleep(1000);
							}
						}
						
						if (Debittype.equals("Other") || Debittype.equals("Personal Loan")
								|| Debittype.equals("Line Of Credit") || Debittype.equals("Maitanance Payment")){
							if(loans.get("RepaymentPeriod") != null){
								Helper.ScroolToView(driver, RepaymentPeriod);
								RepaymentPeriod.click();
								RepaymentPeriod.sendKeys(loans.get("RepaymentPeriod").toString());
								Thread.sleep(1000);
							}							
							if(loans.get("RepaymentAmount") != null){
								Helper.ScroolToView(driver, RepaymentAmount);
								RepaymentAmount.click();
								RepaymentAmount.clear();
								RepaymentAmount.sendKeys(loans.get("RepaymentAmount").toString());
								Thread.sleep(1000);
							}
						}
						if(loans.get("ConsolidateDebt") != null && loans.get("ConsolidateDebt").toString().equals("Yes")){
							Helper.ScroolToView(driver, ConsolidateDebt);
							ConsolidateDebt.click();
							Thread.sleep(1000);
						}
						if(loans.get("OwnershipPercentage") != null){
							double PrimaryOwnership = Double.parseDouble(loans.get("OwnershipPercentage").toString());
							double SecondaryOwnership = 100 - PrimaryOwnership;
							Ownership1stApplicant.click();
							Ownership1stApplicant.clear();
							Ownership1stApplicant.sendKeys(Double.toString(PrimaryOwnership));
							if(JointClient){
								Ownership2ndApplicant.click();
								Ownership2ndApplicant.clear();
								Ownership2ndApplicant.sendKeys(Double.toString(SecondaryOwnership));
							}
							Thread.sleep(1000);
						}else{
							Ownership1stApplicant.click();
							Ownership1stApplicant.clear();
							Ownership1stApplicant.sendKeys(Integer.toString(0));
							if(JointClient){
								Ownership2ndApplicant.click();
								Ownership2ndApplicant.clear();
								Ownership2ndApplicant.sendKeys(Double.toString(100));
							}
							Thread.sleep(1000);
						}
						SaveLoanDetails.click();
						Thread.sleep(2000);
					}
				}
			}
			
			Helper.ScroolToView(driver, AddLoanDetails);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindLoanDetail");
			logger.info("FactFind loan details under liabilities has been successfully entered");
			return true;
			
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}

	static boolean Expenses(){
		try {
			
			JSONArray CustomerInformation_Array = (JSONArray) TestExecution.JSONTestData.get("Customerinformation");
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			String MonthlyExpensesEntered = null;
			//Monthly Expenses will be entered only once and then exit
			while (CustomerInformationArray.hasNext() && MonthlyExpensesEntered == null){
				
				JSONObject CustomerInformation = CustomerInformationArray.next();
				JSONObject Monthlyexpenses = (JSONObject) JSON.GetTestData(CustomerInformation, "FactFind").get("MonthlyExpenses");
				if(Monthlyexpenses == null){
					break;
				}
				
				if(CustomerInformation.get("IsApplicant").toString().equals("Yes")){										
						
						Thread.sleep(2000);
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(Groceries));
						Helper.ScroolToView(driver, Groceries);
						
						if(Monthlyexpenses.get("Groceries") != null){
							Helper.ScroolToView(driver, Groceries);
							Groceries.click();
							Groceries.clear();
							Groceries.sendKeys(Monthlyexpenses.get("Groceries").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("HomeUtilitiesandRates") != null){
							Helper.ScroolToView(driver, HomeUtilitiesandRates);
							HomeUtilitiesandRates.click();
							HomeUtilitiesandRates.clear();
							HomeUtilitiesandRates.sendKeys(Monthlyexpenses.get("HomeUtilitiesandRates").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Telephone&Internet") != null){
							Helper.ScroolToView(driver, TelephoneandInternet);
							TelephoneandInternet.click();
							TelephoneandInternet.clear();
							TelephoneandInternet.sendKeys(Monthlyexpenses.get("Telephone&Internet").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("InvestmentPropertyUtilities&Rates") != null){
							Helper.ScroolToView(driver, InvestmentUtilitiesandRates);
							InvestmentUtilitiesandRates.click();
							InvestmentUtilitiesandRates.clear();
							InvestmentUtilitiesandRates.sendKeys(Monthlyexpenses.get("InvestmentPropertyUtilities&Rates").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Transport") != null){
							Helper.ScroolToView(driver, Transport);
							Transport.click();
							Transport.clear();
							Transport.sendKeys(Monthlyexpenses.get("Transport").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Medical&Health") != null){
							Helper.ScroolToView(driver, MedicalandHealth);
							MedicalandHealth.click();
							MedicalandHealth.clear();
							MedicalandHealth.sendKeys(Monthlyexpenses.get("Medical&Health").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Recreation&Entertainment") != null){
							Helper.ScroolToView(driver, RecreationandEntertainment);
							RecreationandEntertainment.click();
							RecreationandEntertainment.clear();
							RecreationandEntertainment.sendKeys(Monthlyexpenses.get("Recreation&Entertainment").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("PayTV") != null){
							Helper.ScroolToView(driver, PayTv);
							PayTv.click();
							PayTv.clear();
							PayTv.sendKeys(Monthlyexpenses.get("PayTV").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Education") != null){
							Helper.ScroolToView(driver, Education);
							Education.click();
							Education.clear();
							Education.sendKeys(Monthlyexpenses.get("Education").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Clothing&PersonalCare") != null){
							Helper.ScroolToView(driver, ClothingandPersonalCare);
							ClothingandPersonalCare.click();
							ClothingandPersonalCare.clear();
							ClothingandPersonalCare.sendKeys(Monthlyexpenses.get("Clothing&PersonalCare").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("ChildCare") != null){
							Helper.ScroolToView(driver, ChildCare);
							ChildCare.click();
							ChildCare.clear();
							ChildCare.sendKeys(Monthlyexpenses.get("ChildCare").toString());
							Thread.sleep(1000);
						}
						
						if(Monthlyexpenses.get("Insurance") != null){
							Helper.ScroolToView(driver, Insurance);
							Insurance.click();
							Insurance.clear();
							Insurance.sendKeys(Monthlyexpenses.get("Insurance").toString());
							Thread.sleep(1000);
						}
						
						Helper.ScroolToView(driver, SaveExpenses);
						SaveExpenses.click();
						Thread.sleep(2000);
						MonthlyExpensesEntered = "Entered";
					
				}
			}
			
			Helper.ScroolToView(driver, Groceries);		
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindMonthlyExpenses");
			NextButtonBottomofthePage.click();
			logger.info("FactFind monthly expenses for liabilities has been successfully entered");
			return true;
			
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}

	}
}
