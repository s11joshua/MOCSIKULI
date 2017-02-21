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
import Discovery.TestExecution;

public class FactFindLogin {
	static Log logger = LogFactory.getLog(FactFindLogin.class);
	static int Offset[] = {0,10,50,100,200,500,1000};
	static Screen screen = new Screen();
	static String RedemtionID = null;	
		
	static Pattern AlreadyTaken;
	static Pattern InvalidID;
	static Pattern Signin;
	public static Pattern NextButton;
	
	public FactFindLogin(){
		new FactFindLogin("C:\\Sikuli Images\\FactFind\\Login\\");
	}
	
	public FactFindLogin(String Imagefolderlocation)
	{
		AlreadyTaken = new Pattern (Imagefolderlocation + "AlreadyTaken.PNG");
		InvalidID = new Pattern (Imagefolderlocation + "InvalidInvitationCode.PNG");
		Signin = new Pattern (Imagefolderlocation + "SignIn.PNG");
		NextButton = new Pattern (Imagefolderlocation + "NextButton.PNG");
	}
	
	public FactFindLogin(WebDriver BrowserType){
			PageFactory.initElements(BrowserType, this);
	}
		
		@FindBy(how = How.XPATH, using = ".//*[@id='content']/ul/li[1]/a")
		static WebElement TABSignIn;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='content']/ul/li[2]/a")
		static WebElement TABRedeemInvitation;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='Username']")
		static WebElement TXTUserName;
			
		@FindBy(how = How.XPATH, using = ".//*[@id='Password']")
		static WebElement TXTPassword;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='ConfirmPassword']")
		static WebElement TXTConfirmPassword;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='InvitationCode']")
		static WebElement TXTInvitationCode;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='submit-signin-local']")
		static WebElement BTNSignIn;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='submit-redeem-invitation']")
		static WebElement BTNRedeem;
		
		@FindBy(how = How.XPATH, using = ".//*[@id='submit-signup-local']")
		static WebElement BTNRegister;
			
		public static boolean LoginFactFindFirstTime(String UserName) {
						
			RedemtionID = Dynamics.JDBCConnection.GetRedemtionID(UserName);
			
				try {					
					if (RedemtionID != null){
						TABRedeemInvitation.click();
						TXTInvitationCode.sendKeys(RedemtionID);
						BTNRedeem.click();
						if(screen.exists(InvalidID,20) != null){
							if (Login(UserName,"choosey1!") != true){
								logger.info("Fact Find Login failed");
								Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
								return false;
							}else{
								return true;
							}
						}
						TXTUserName.isEnabled();
						TXTUserName.sendKeys(UserName);
						TXTPassword.sendKeys("choosey1!");
						TXTConfirmPassword.sendKeys("choosey1!");
						BTNRegister.click();
						if(screen.exists(AlreadyTaken,20) != null){
							if (Login(UserName,"choosey1!") != true){
								logger.info("Fact Find Login failed");
								Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
								return false;
							}else{
								return true;
							}
						}
						screen.wait(NextButton,30).click();
						screen.click(NextButton);
						logger.info("Fact Find Login Completed Successfully");
						Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindLogin");
						return true;
					}else{
						logger.info("Fact Find Login failed");
						Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
						return false;
					}
					
				} catch (FindFailed e) {
					e.printStackTrace();
					logger.info("Fact Find Login failed");
					logger.error(e.toString());
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
					return false;
				}	
							
		}
		
		public static boolean Login(String UserName,String Password) {
			
			try {
				TABSignIn.click();
				TXTUserName.isEnabled();
				TXTUserName.sendKeys(UserName);
				TXTPassword.sendKeys(Password);
				Thread.sleep(5000);
				screen.click(Signin);
				screen.wait(NextButton,30).click();
				logger.info("Fact Find Login Completed Successfully");
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "FactFindLogin");
				return true;
			} catch (InterruptedException | FindFailed e) {
				e.printStackTrace();
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				logger.error(e.toString());
				return false;
			}
			
		}
}
