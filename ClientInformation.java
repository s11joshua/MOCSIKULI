
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ClientInformation {
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	
	static Pattern qashortcut;
	static Pattern clientsearch;
	static Pattern quickqualify;

	static Pattern ScenarioSave;
	static Pattern ScenarioLoanConsultant;
	static Pattern ScenarioLoanOkbutton;
	static Pattern ScenarioLeadSourceMajor;
	static Pattern ClientInformationsavedsuccessfully;
	
	static Pattern titlelabel;
	static Pattern AddExistingCustomer;
	static Pattern ExistingCustomerSurname;
	static Pattern ExistingCustomerFirstname;
	static Pattern ExistingCustomerPostcode;
	static Pattern FindClientOKbutton;
	static Pattern AddNewCustomer;
	static Pattern ClientTypeRole;
	static Pattern ResidencyStatus;
	static Pattern TitleandFirstName;
	static Pattern LastName;
	static Pattern DateOfBirth;
	static Pattern PreferredContactMethod;
	static Pattern BusPhone;
	static Pattern HomePhone;
	static Pattern MobilePhone;
	static Pattern EmailId;
	static Pattern Addressline1;
	static Pattern SelectRegion;
	static Pattern popupSuburb;
	static Pattern popupPostcode;
	static Pattern popupOK;
	static Pattern MoveInDate;
	static Pattern NumberOfChildern;
	static Pattern AgesButton;
	static Pattern BoardwithRealtives;
	static Pattern NonAplicantSpouse;
	static Pattern CheckedNonAplicantSpouse;
	static Pattern NumberofDependentsNonApplicantSpouse;
	static Pattern FirstChildAge;
	static Pattern ChildAgeOkbutton;
	static Pattern HouseholdOfApplicant;
	static Pattern LivingExpenseseditbutton;
	static Pattern HouseHoldBasicExpenses;
	static Pattern HouseHoldGymMemberships;
	static Pattern HouseHoldMotorVehicles;
	static Pattern HouseHoldChildCare;
	static Pattern HouseHoldPrivateSchoolFees;
	static Pattern HouseHoldOther;
	static Pattern HouseholdCreditAssessmentNote;
	static Pattern HousholdDetailsSave;
	static Pattern WarningSavebutton;
	static Pattern PrimaryEmployment;
	static Pattern EmploymentType;
	static Pattern Probation;
	static Pattern IncomeDeletebutton;
	static Pattern IncomeADDbutton;
	static Pattern IncomeGroupdropdown;
	static Pattern IncomeTypedropdown;
	static Pattern SourceOfProposedRentalIncome;
	static Pattern IncomeAmount;
	static Pattern IncomeFrequencydropdown;
	static Pattern IncomeTaxeddropdown;
	static Pattern IncomeOKbutton;
	static Pattern CommitmentDeletebutton;
	static Pattern CommitmentAddbutton;
	static Pattern CommitmentGroup;
	static Pattern CommitmentType;
	static Pattern CommitmentwillbeCleared;
	static Pattern CommitmentTaxDeductible;
	static Pattern CommitmentRepaymentAmount;
	static Pattern CommitmentFrequency;
	static Pattern CommitmentLender;
	static Pattern CommitmentBalance;
	static Pattern CommitmentLimit;
	static Pattern CommitmentRemaingPITerm;
	static Pattern CommitmentRemaingPITermFrequency;
	static Pattern CommitmentRepaymentType;
	static Pattern CommitmentInterestRate;
	static Pattern CommitmentOKbutton;
	
	public ClientInformation(){
		new ClientInformation("C:\\Sikuli Images\\Client_Information\\");
	}
	
	public ClientInformation(String Imagefolderlocation)
	{	
	
		ScenarioSave = new Pattern (Imagefolderlocation + "ScenarioSave.PNG");
		ScenarioLoanConsultant = new Pattern (Imagefolderlocation + "ScenarioLoanConsultant.PNG");
		ScenarioLoanOkbutton = new Pattern (Imagefolderlocation + "ScenarioDetailsOkbutton.PNG");
		ScenarioLeadSourceMajor = new Pattern (Imagefolderlocation + "ScenarioMCLeadSourceMajor.PNG");
		ClientInformationsavedsuccessfully = new Pattern (Imagefolderlocation + "ScenarioSavedSucessfullyPopup.PNG");
		
		titlelabel = new Pattern(Imagefolderlocation + "Tilte Label.PNG");
		AddExistingCustomer = new Pattern (Imagefolderlocation + "FindExistingCustomer.PNG");
		ExistingCustomerSurname = new Pattern (Imagefolderlocation + "ExistingCustomer_Surname.PNG");
		ExistingCustomerFirstname = new Pattern (Imagefolderlocation + "ExistingCustomer_Firstname.PNG");
		ExistingCustomerPostcode = new Pattern (Imagefolderlocation + "ExistingCustomer_Postcode.PNG");
		FindClientOKbutton = new Pattern (Imagefolderlocation + "FindClientOKbutton.PNG");
		AddNewCustomer = new Pattern (Imagefolderlocation + "AdNewCustomer.PNG");
		ClientTypeRole = new Pattern (Imagefolderlocation + "CustomerTypeRole.PNG");
		ResidencyStatus = new Pattern (Imagefolderlocation + "ResidencyStatus.PNG");
		TitleandFirstName = new Pattern (Imagefolderlocation + "Title&FirstName.PNG");
		LastName = new Pattern(Imagefolderlocation + "Last Name.PNG");
		DateOfBirth = new Pattern(Imagefolderlocation + "Date of Birth.PNG");
		PreferredContactMethod = new Pattern(Imagefolderlocation + "PreferedContactMethod.PNG");
		BusPhone = new Pattern(Imagefolderlocation + "Bus Phone.PNG");
		HomePhone = new Pattern(Imagefolderlocation + "Home Phone.PNG");
		MobilePhone = new Pattern(Imagefolderlocation + "Mobile.PNG");
		EmailId = new Pattern(Imagefolderlocation + "Email.PNG");
		Addressline1 = new Pattern(Imagefolderlocation + "Address.PNG");
		SelectRegion = new Pattern(Imagefolderlocation + "SelectRegionPostcode.PNG");
		popupSuburb = new Pattern(Imagefolderlocation + "Regionsuburb-popuptab.PNG");
		popupPostcode = new Pattern(Imagefolderlocation + "Regionpostcode-popuptab.PNG");
		popupOK = new Pattern(Imagefolderlocation + "Regionok-popuptab.PNG");
		MoveInDate = new Pattern(Imagefolderlocation + "MoveInDate.PNG");
		NumberOfChildern = new Pattern(Imagefolderlocation + "Number of Childern.PNG");
		AgesButton = new Pattern(Imagefolderlocation + "Ages.PNG");
		BoardwithRealtives = new Pattern(Imagefolderlocation + "BoardwithRelatives.PNG");
		NonAplicantSpouse = new Pattern(Imagefolderlocation + "NonApplicantSpouse.PNG");
		CheckedNonAplicantSpouse = new Pattern(Imagefolderlocation + "CheckedNonApplicantSpouse.PNG");
		NumberofDependentsNonApplicantSpouse =  new Pattern(Imagefolderlocation + "NumberOfDependentsNonSpouseAdults.PNG");
		FirstChildAge = new Pattern(Imagefolderlocation + "ChildAges.PNG");
		ChildAgeOkbutton = new Pattern(Imagefolderlocation + "ChildAgeOKbutton.PNG");
		HouseholdOfApplicant = new Pattern(Imagefolderlocation + "HouseholdOfApplicant.PNG");
		LivingExpenseseditbutton = new Pattern(Imagefolderlocation + "LivingExpenseseditbutton.PNG");
		HouseHoldBasicExpenses = new Pattern(Imagefolderlocation + "HouseHoldOfApplicant.PNG");
		HouseHoldGymMemberships = new Pattern(Imagefolderlocation + "HouseHoldGymMemberships.PNG");
		HouseHoldMotorVehicles = new Pattern(Imagefolderlocation + "HouseHoldMotorVehicles.PNG");
		HouseHoldChildCare = new Pattern(Imagefolderlocation + "HouseHoldChildCare.PNG");;
		HouseHoldPrivateSchoolFees = new Pattern(Imagefolderlocation + "HouseHoldPrivateSchoolFees.PNG");;
		HouseHoldOther = new Pattern(Imagefolderlocation + "HouseHoldOther.PNG");;
		HouseholdCreditAssessmentNote = new Pattern(Imagefolderlocation + "HouseholdCreditAssessmentNote.PNG");;
		HousholdDetailsSave = new Pattern(Imagefolderlocation + "HouseholddetailspopupSavebutton.PNG");
		WarningSavebutton = new Pattern(Imagefolderlocation + "WarningSaveButton.PNG");
		PrimaryEmployment  = new Pattern(Imagefolderlocation + "PrimaryEmployment.PNG");
		EmploymentType  = new Pattern(Imagefolderlocation + "EmploymentType.PNG");
		Probation  = new Pattern(Imagefolderlocation + "Probation.PNG");
		IncomeDeletebutton = new Pattern(Imagefolderlocation + "IncomeDeletebutton.PNG");
		IncomeADDbutton = new Pattern(Imagefolderlocation + "IncomeAddbutton.PNG");
		IncomeGroupdropdown = new Pattern (Imagefolderlocation + "IncomeGroup.PNG");
		IncomeTypedropdown = new Pattern (Imagefolderlocation + "IncomeType.PNG");
		SourceOfProposedRentalIncome = new Pattern (Imagefolderlocation + "SourceOfProposedRentalIncome.PNG");
		IncomeAmount = new Pattern (Imagefolderlocation + "IncomeAmount.PNG");
		IncomeFrequencydropdown = new Pattern (Imagefolderlocation + "IncomeFrequency.PNG");
		IncomeTaxeddropdown = new Pattern (Imagefolderlocation + "IncomeTaxed.PNG");
		IncomeOKbutton = new Pattern (Imagefolderlocation + "IncomeOkbutton.PNG");
		CommitmentDeletebutton = new Pattern (Imagefolderlocation + "CommitmentDeletebutton.PNG");
		CommitmentAddbutton = new Pattern (Imagefolderlocation + "CommitmentAddbutton.PNG");
		CommitmentGroup =  new Pattern(Imagefolderlocation + "CommitmentGroup.PNG");
		CommitmentType =  new Pattern(Imagefolderlocation + "CommitmentType.PNG");
		CommitmentwillbeCleared =  new Pattern(Imagefolderlocation + "CommitmentwillbeCleared.PNG");
		CommitmentTaxDeductible =  new Pattern(Imagefolderlocation + "CommitmentTaxDeductible.PNG");
		CommitmentRepaymentAmount =  new Pattern(Imagefolderlocation + "CommitmentRepaymentAmount.PNG");
		CommitmentFrequency =  new Pattern(Imagefolderlocation + "CommitmentFrequency.PNG");
		CommitmentLender =  new Pattern(Imagefolderlocation + "CommitmentLender.PNG");
		CommitmentBalance =  new Pattern(Imagefolderlocation + "CommitmentBalance.PNG");
		CommitmentLimit =  new Pattern(Imagefolderlocation + "CommitmentLimit.PNG");
		CommitmentRemaingPITerm =  new Pattern(Imagefolderlocation + "CommitmentRemaingPITerm.PNG");
		CommitmentRemaingPITermFrequency =  new Pattern(Imagefolderlocation + "CommitmentRemaingPITermFrequency.PNG");
		CommitmentRepaymentType =  new Pattern(Imagefolderlocation + "CommitmentRepaymentType.PNG");
		CommitmentInterestRate =  new Pattern(Imagefolderlocation + "CommitmentInterestRate.PNG");
		CommitmentOKbutton =  new Pattern(Imagefolderlocation + "CommitmentOKbutton.PNG");

	}
	
	public static boolean CaptureClientDetails(JSONObject RawFile){
		
		int counter = 0;
		JSONArray CustomerInformation_Array = (JSONArray) RawFile.get("Customerinformation");
		
		try {
			
			App.focus("Qualifier Analyser");
			screen.wait(titlelabel,30);
			Iterator<JSONObject> CustomerInformationArray = CustomerInformation_Array.iterator();
			
			while (CustomerInformationArray.hasNext()){
				JSONObject CustomerInformation = CustomerInformationArray.next();
				//System.out.println(CustomerInformation.toString());
								
				if (CustomerInformation.get("NewCustomer").toString().equals("Yes")){
					counter++;
					if (counter > 1) {
					screen.click(AddNewCustomer);
					}
					
					screen.wait(titlelabel,30);
					
					if (counter >= 1 && CustomerInformation.get("CustomerType") != null && CustomerInformation.get("CustomerType").toString().equals("Guarantor")){
						screen.click(ClientTypeRole);
						Helper.Keystrokedown(1);
					}
					if ((Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) >= 1) && (Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) <= 5)){
						if(Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString()) > 1) {
							screen.find(ResidencyStatus).right(Offset[1]).click();
							Helper.Keystrokedown(Integer.parseInt(CustomerInformation.get("ResidentialStatus").toString())-1);
							Helper.Keystrokeenter(1);
						}
					}else return false;
					
					screen.find(TitleandFirstName).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerNames").get("Title").toString()));
					Helper.Keystrokeenter(1);
					screen.find(TitleandFirstName).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString());
					screen.find(LastName).right(Offset[1]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString());
					screen.find(DateOfBirth).right(Offset[1]).click();
					screen.type(CustomerInformation.get("DOB").toString());
					screen.find(PreferredContactMethod).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(CustomerInformation.get("PreferredContactMethod").toString()));
					Helper.Keystrokeenter(1);
					screen.find(BusPhone).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("BusinessPhone").toString());
					screen.find(HomePhone).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("HomePhone").toString());
					screen.find(MobilePhone).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("Mobile").toString());
					screen.find(EmailId).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerContactDetails").get("EmailId").toString());
					screen.find(Addressline1).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerAddress").get("AddressLine1").toString());
					if (JSON.GetTestData(CustomerInformation, "CustomerAddress").get("AddressLine2") != null){
						Helper.Keystroketab(1);
						screen.type(JSON.GetTestData(CustomerInformation, "CustomerAddress").get("AddressLine2").toString());
					}
					screen.click(SelectRegion);
					if (SelectPostCode(JSON.GetTestData(CustomerInformation, "CustomerAddress").get("Suburb").toString(),JSON.GetTestData(CustomerInformation, "CustomerAddress").get("PostCode").toString()) != true){
						return false;
					}
					screen.find(MoveInDate).right(Offset[4]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerAddress").get("MoveInDate").toString());
					if (IncomeExpenses(CustomerInformation) != true){
						return false;
					}
					
					
				} else if(CustomerInformation.get("NewCustomer").toString().equals("No")){
					counter++;
					screen.click(AddExistingCustomer);
					screen.wait(ExistingCustomerSurname,30);
					screen.find(ExistingCustomerSurname).below(10).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("LastName").toString());
					screen.find(ExistingCustomerFirstname).below(10).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerNames").get("FirstName").toString());
					screen.click(FindClientOKbutton);
					screen.waitVanish(FindClientOKbutton);
					
				}
			}
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean SaveScenario(){
		
		try {
			App.pause(3);
			screen.click(ScenarioSave);
			App.pause(3);
			App.focus("Sceanrio Details");
			screen.click(ScenarioLoanConsultant);//Family & Friends
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			screen.click(ScenarioLeadSourceMajor);//Family & Friends
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			Helper.Keystrokedown(1);
			Helper.Keystroketab(1);
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			App.pause(2);
			screen.click(ScenarioLoanOkbutton);
			App.pause(10);
			return true;
		} catch (FindFailed e) {e.printStackTrace();
			return false;
		}

	}
	
	public static boolean SelectPostCode(String Suburb, String Postcode){
		Screen screen = new Screen();
		try{
			App.pause(3);
			App.focus("Region/Suburb/PostCode Query");
			screen.click(popupSuburb);
			screen.type(Suburb);
			Helper.Keystroketab(1);
			screen.type(Postcode);
			screen.click(popupOK);
			screen.waitVanish(popupOK,30);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean IncomeExpenses(JSONObject CustomerInformation){
		try {
			
			if (JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NumberOfDependents") != null){
				if(Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NumberOfDependents").toString()) >= 1){
					screen.find(NumberOfChildern).right(Offset[2]).click();
					screen.type(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NumberOfDependents").toString());
					screen.click(AgesButton);
					App.focus("Child Ages");
					screen.click(FirstChildAge);
					JSONArray DependantDOBArray = (JSONArray) JSON.GetTestData(CustomerInformation, "CustomerDependents").get("DependentsDOB");
					Iterator<String> DOBArray = DependantDOBArray.iterator();
					while (DOBArray.hasNext()){
						screen.type(DOBArray.next().toString());
						Helper.Keystroketab(2);
					}
					screen.click(ChildAgeOkbutton);
					screen.waitVanish(ChildAgeOkbutton,30);
				}
			}
			
			if(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("BoardwithRelatives") != null){
				if(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("BoardwithRelatives").toString().equals("Yes")){
				screen.click(BoardwithRealtives);
				App.pause(1);
				}
			}
			
			if (JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NonAplicantSpouse") != null){
				if(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NonAplicantSpouse").toString().equals("Yes")){
					screen.find(NonAplicantSpouse).right(Offset[1]).click();
				}
			}
			
			if (JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NumberofDependentsNonApplicantSpouse") != null){
				screen.find(NumberofDependentsNonApplicantSpouse).right(Offset[2]).click();
				screen.type(JSON.GetTestData(CustomerInformation, "CustomerDependents").get("NumberofDependentsNonApplicantSpouse").toString());
			}
			
			if (HouseHoldDetails(CustomerInformation) != true){
				return false;
			}
			if(Income(CustomerInformation) != true){
				return false;
			}
			if(FinancialCommitments(CustomerInformation) != true){
				return false;
			}
			
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean HouseHoldDetails(JSONObject CustomerInformation){
		
		try {
			
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("HouseholdOfApplicant") != null && Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("HouseholdOfApplicant").toString()) >= 1){
				if (Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("HouseholdOfApplicant").toString()) > 1){
					screen.find(HouseholdOfApplicant).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("HouseholdOfApplicant").toString()) - 1);
					Helper.Keystrokeenter(1);
				}
			}else{
				System.out.println("Invalid Parameter passed for HouseholdOfApplicant in Customerinformation-CustomerLivingExpenses-HouseholdOfApplicant");
				return false;
			}
			
			screen.click(LivingExpenseseditbutton);
			App.focus("Household Details");
			
			if (JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("BasicExpenses") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("BasicExpenses").toString()) > 1){
				screen.find(HouseHoldBasicExpenses).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("BasicExpenses").toString());
				Helper.Keystrokeenter(1);
			}
				
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Gym-OtherMembeships") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Gym-OtherMembeships").toString()) > 1){
				screen.find(HouseHoldGymMemberships).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Gym-OtherMembeships").toString());
				Helper.Keystrokeenter(1);
			}
			
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("AdditionalMotorVehicles") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("AdditionalMotorVehicles").toString()) > 1){
				screen.find(HouseHoldMotorVehicles).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("AdditionalMotorVehicles").toString());
				Helper.Keystrokeenter(1);
			}
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("ChildCare") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("ChildCare").toString()) > 1){
				screen.find(HouseHoldChildCare).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("ChildCare").toString());
				Helper.Keystrokeenter(1);
			}
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("PrivateSchoolFees") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("PrivateSchoolFees").toString()) > 1){
				screen.find(HouseHoldPrivateSchoolFees).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("PrivateSchoolFees").toString());
				Helper.Keystrokeenter(1);
			}
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Other") != null && Double.parseDouble(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Other").toString()) > 1){
				screen.find(HouseHoldOther).right(Offset[5]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("Other").toString());
				Helper.Keystrokeenter(1);
			}
			if(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("CreditAssementNotes") != null){
				screen.find(HouseholdCreditAssessmentNote).below(Offset[2]).click();
				Helper.ClearTextBoxandEnterValue(JSON.GetTestData(CustomerInformation, "CustomerLivingExpenses").get("CreditAssementNotes").toString());
				Helper.Keystrokeenter(1);
			}
			
			screen.click(HousholdDetailsSave);
						
			if (screen.exists(WarningSavebutton) != null){
				screen.click(WarningSavebutton);
			}
			
			screen.waitVanish(HousholdDetailsSave);
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static boolean Income(JSONObject CustomerInformation){
		try {
			//Clearing the default income section.
		
			int Counter = 1;
			while(Counter <= 4){
				screen.find(IncomeDeletebutton).left(Offset[2]).click();
				App.pause(1);
				Counter ++;
			}			
			
			JSONArray IncomeArray = (JSONArray) CustomerInformation.get("CustomerIncome");
			Iterator<JSONObject> Income = IncomeArray.iterator();
			if (Integer.parseInt(CustomerInformation.get("PrimaryEmployment").toString()) > 1){
				screen.find(PrimaryEmployment).right(Offset[1]).click();
				Helper.Keystrokedown(Integer.parseInt(CustomerInformation.get("PrimaryEmployment").toString())-1);
				Helper.Keystrokeenter(1);
			}
			if (Integer.parseInt(CustomerInformation.get("EmploymentType").toString()) > 1){
				screen.click(EmploymentType);
				Helper.Keystrokedown(Integer.parseInt(CustomerInformation.get("EmploymentType").toString())-1);
				Helper.Keystrokeenter(1);
			}
			if (CustomerInformation.get("Probation") != null && CustomerInformation.get("Probation").toString().equals("Check")){
				screen.click(Probation);
			}
			
			while(Income.hasNext()){
				screen.find(IncomeADDbutton).left(10).click();
				JSONObject Incomevalues = Income.next();
				App.focus("Income");
				
				if (Integer.parseInt(Incomevalues.get("IncomeGroup").toString()) >= 1){
					screen.find(IncomeGroupdropdown).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(Incomevalues.get("IncomeGroup").toString()));
					Helper.Keystrokeenter(1);					
				}
				if (Integer.parseInt(Incomevalues.get("IncomeType").toString()) >= 1){
					screen.find(IncomeTypedropdown).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(Incomevalues.get("IncomeType").toString()));
					Helper.Keystrokeenter(1);
				}
				if (Integer.parseInt(Incomevalues.get("IncomeGroup").toString()) == 1 && Integer.parseInt(Incomevalues.get("IncomeType").toString()) == 4){
					if(Incomevalues.get("SourceOfProposedRentalIncome") != null){
						screen.find(SourceOfProposedRentalIncome).right(Offset[2]).click();
						Helper.Keystrokedown(Integer.parseInt(Incomevalues.get("SourceOfProposedRentalIncome").toString()));
					}
					else{
						System.out.println("Invalid Parameter passed for SourceOfProposedRentalIncome");
					}
				}
				if (Integer.parseInt(Incomevalues.get("Amount").toString()) > 0 ){
					screen.find(IncomeAmount).right(Offset[1]).click();
					Helper.ClearTextBoxandEnterValue((Incomevalues.get("Amount").toString()));
					Helper.Keystrokeenter(1);
				}
				if (Integer.parseInt(Incomevalues.get("Frequency").toString()) > 1 ){
					screen.find(IncomeFrequencydropdown).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(Incomevalues.get("Frequency").toString()) - 1);
					Helper.Keystrokeenter(1);
				}
				if (Integer.parseInt(Incomevalues.get("Taxed").toString()) > 1 ){
					screen.find(IncomeTaxeddropdown).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(Incomevalues.get("Taxed").toString()) - 1);
					Helper.Keystrokeenter(1);
				}
				screen.click(IncomeOKbutton);
				screen.waitVanish(IncomeOKbutton);
			}
			
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean FinancialCommitments(JSONObject CustomerInformation){
		try {
			
			//Clearing the default commitment section.
			
			int Counter = 1;
			while(Counter <= 7){
				screen.find(CommitmentDeletebutton).above(Offset[1]).click();
				App.pause(1);
				Counter ++;
			}	
						
			JSONArray CommitmentArray = (JSONArray) CustomerInformation.get("CustomerFinancialCommitments");
			Iterator<JSONObject> Commitment = CommitmentArray.iterator();
			while(Commitment.hasNext()){
				screen.find(CommitmentAddbutton).left(10).click();
				JSONObject Commitmentvalues = Commitment.next();
				App.focus("Financial Commitments");
				App.pause(2);
				
				if (((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4) >= -3) && ((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4) < 0)){
					screen.find(CommitmentGroup).right(Offset[1]).click();
					Helper.Keystrokeup(Math.abs(Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4));
					Helper.Keystrokeenter(1);
				}else if (((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4) > 0) && ((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4) <= 4)){
					screen.find(CommitmentGroup).right(Offset[1]).click();
					Helper.Keystrokedown(Math.abs(Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString()) - 4));
					Helper.Keystrokeenter(1);
				}
				if (Integer.parseInt(Commitmentvalues.get("CommitmentType").toString()) >= 1 ){
					screen.find(CommitmentType).right(Offset[1]).click();
					Helper.Keystrokedown(Integer.parseInt(Commitmentvalues.get("CommitmentType").toString()));
					Helper.Keystrokeenter(1);
				}
				if ((Commitmentvalues.get("WillbeCleared") != null) && (Commitmentvalues.get("WillbeCleared").toString().equals("Yes"))){
					screen.click(CommitmentwillbeCleared);
				}
				if ((Commitmentvalues.get("TaxDeductable") != null) && (Commitmentvalues.get("TaxDeductable").toString().equals("Yes"))){
					screen.click(CommitmentTaxDeductible);
				}
				if ((Commitmentvalues.get("RepaymentAmount") != null) && (Integer.parseInt(Commitmentvalues.get("RepaymentAmount").toString()) > 0)){
					screen.find(CommitmentRepaymentAmount).right(Offset[2]).click();
					Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("RepaymentAmount").toString());					
				}
				if ((Commitmentvalues.get("Frequency") != null) && (Integer.parseInt(Commitmentvalues.get("Frequency").toString()) > 1)){
					screen.find(CommitmentFrequency).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(Commitmentvalues.get("Frequency").toString()) - 1);
					Helper.Keystrokeenter(1);
				}
				if ((Commitmentvalues.get("Lender") != null) && (Integer.parseInt(Commitmentvalues.get("Lender").toString()) > 0)){
					screen.find(CommitmentLender).right(Offset[2]).click();
					Helper.Keystrokedown(Integer.parseInt(Commitmentvalues.get("Lender").toString()));
					Helper.Keystrokeenter(1);
				}
				if ((Commitmentvalues.get("Balance") != null) && (Integer.parseInt(Commitmentvalues.get("Balance").toString()) > 0)){
					screen.find(CommitmentBalance).right(Offset[2]).click();
					Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("Balance").toString());					
				}
				if ((Commitmentvalues.get("Limit") != null) && (Integer.parseInt(Commitmentvalues.get("Limit").toString()) > 0)){
					screen.find(CommitmentLimit).right(Offset[2]).click();
					Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("Limit").toString());					
				}				
				if (((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString())) == 1) || ((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString())) == 4) || ((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString())) == 8)){
					if ((Commitmentvalues.get("RemainingP&ITerm") != null) && (Integer.parseInt(Commitmentvalues.get("RemainingP&ITerm").toString()) > 0)){
						screen.find(CommitmentRemaingPITerm).right(Offset[2]).click();
						Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("RemainingP&ITerm").toString());					
					}
					if ((Commitmentvalues.get("RemainingP&ITermFrequency") != null) && (Integer.parseInt(Commitmentvalues.get("RemainingP&ITermFrequency").toString()) > 0)){
						screen.click(CommitmentRemaingPITermFrequency);
						Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("RemainingP&ITermFrequency").toString());					
					}
					if ((Integer.parseInt(Commitmentvalues.get("CommitmentGroup").toString())) == 4){ 
						if ((Commitmentvalues.get("RepaymentType") != null) && (Integer.parseInt(Commitmentvalues.get("RepaymentType").toString()) >0)){
							screen.find(CommitmentRepaymentType).right(Offset[2]).click();
							Helper.Keystrokedown(Integer.parseInt(Commitmentvalues.get("RepaymentType").toString()));
							Helper.Keystrokeenter(1);
						}
						if ((Double.parseDouble(Commitmentvalues.get("Interestrate").toString()) >0) && (Commitmentvalues.get("Interestrate") != null)){
							screen.find(CommitmentInterestRate).right(Offset[2]).click();
							Helper.ClearTextBoxandEnterValue(Commitmentvalues.get("Interestrate").toString());
						}
					}
					
				}
				screen.click(CommitmentOKbutton);
				screen.waitVanish(CommitmentOKbutton);
			}
			
			return true;
			
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
}
