package FactFind;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import Discovery.Helper;

public class PersonalDetails {
	static Log logger = LogFactory.getLog(PersonalDetails.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static String RedemtionID = null;
	public static WebDriver driver = null;
	
	static Pattern SavePersonalDetails;
	static Pattern SaveDependantAge;
	
	public PersonalDetails(){
		new PersonalDetails("C:\\Sikuli Images\\FactFind\\PersonalDetails\\");
	}
	public PersonalDetails(String Imagefolderlocation){
		SavePersonalDetails = new Pattern (Imagefolderlocation + "SavePersonalDetails.PNG");
		SaveDependantAge = new Pattern (Imagefolderlocation + "SaveandAddtoList.PNG");
	}
	public PersonalDetails(WebDriver BrowserType){
		PageFactory.initElements(BrowserType, this);
	}
	
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
	
	@FindBy(how = How.XPATH, using = ".//*[@id='ContactFormView1']/div[2]/div/div/fieldset/table/tbody/tr[4]/td[4]/div[2]/div/input")
	static WebElement LicenceExpiry;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='lnkAddDependant']")
	static WebElement AddDependant;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mc_age']")
	static WebElement AgeofDependant;
	
	public static boolean CustomerPersonalDetails(){
				
	try {	
			screen.wait(FactFindLogin.NextButton,30);
			Title.click();
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			Gender.click();
			Helper.Keystrokedown(1);
			Helper.Keystrokeenter(1);
			DateOfbirth.sendKeys("11/04/1983");
			LicenceIssued.sendKeys("11/04/2000");
			LicenceExpiry.sendKeys("11/04/2025");
			Helper.ScroolToView(driver, AddDependant);
			AddDependant.click();
			Thread.sleep(2000);
			AgeofDependant.sendKeys("2");
			screen.click(SaveDependantAge);
			screen.click(FactFindLogin.NextButton);
			
			return true;
			
		} catch (FindFailed | InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
	}
		
}
