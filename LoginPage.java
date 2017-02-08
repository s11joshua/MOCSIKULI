package Discovery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class LoginPage {
	
	static Log logger = LogFactory.getLog(LoginPage.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	static Pattern username;
	static Pattern password;
	static Pattern environmentdropdown;
	static Pattern login;
	
		public LoginPage(){
			new LoginPage("C:\\Sikuli Images\\Login\\");
		}
	
		public LoginPage(String Imagefolderlocation)
		{
			username = new Pattern(Imagefolderlocation + "Username.PNG");
			password = new Pattern(Imagefolderlocation + "Password.PNG");
			environmentdropdown = new Pattern(Imagefolderlocation + "EnvironmentDropdown.PNG");
			login = new Pattern(Imagefolderlocation + "Login.PNG");
				
		}
		
		public static boolean LaunchDiscoveryApplicaiton()
		{	
			Helper Config = new Helper();
			String DiscoveryAppLocation = Config.GetConfigParameter("DiscoveryInstance").toString();
			logger.info("Launching the discovery application");
			App.open(DiscoveryAppLocation);
			App.pause(5);
			App.focus("Discovery");
			return true;
			
			/*if (DiscoveryAppLocation == "SysTest"){
					logger.info("Launching the discovery application");
					App.open("C:\\Program Files\\mortgage choice\\DiscoverySysTest\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					return true;
			}
			else if (DiscoveryAppLocation == "Test"){
					logger.info("Launching the discovery application");
					App.open("C:\\Program Files (x86)\\Mortgage Choice\\Discovery\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					return true;
			}
			else
			{
				logger.error("Invalid instance of the test environment set in the Config file for Discovery ");	
				return false;
			}*/
			
		}
		
		
		public static boolean LogintoDiscovery(JSONObject RawFile)
		{	
			
			if (LaunchDiscoveryApplicaiton() == false){
				return false;
			}
			
			logger.info("Logging into Discovery");
			Helper Config = new Helper();
			String Discoveryusername = null;
			String Discoverypassword = null;
			String TestEnvironment = null;
			
			JSONObject LoginDetails = JSON.GetTestData(RawFile, "UserDetails");
			JSONObject EnvironmentDetails = JSON.GetTestData(RawFile, "EnvironmentDetails");
						
			if (LoginDetails.get("DiscoveryUsername") == null){
				Discoveryusername = Config.GetConfigParameter("DiscoveryUsername");
			}else{
				Discoveryusername = LoginDetails.get("DiscoveryUsername").toString();
			}
			
			if (LoginDetails.get("DiscoveryPassword") == null){
				Discoverypassword = Config.GetConfigParameter("DiscoveryPassword");
			}else{
				Discoverypassword = LoginDetails.get("DiscoveryPassword").toString();
			}
			
			if (LoginDetails.get("DiscoveryDatabase") == null){
				TestEnvironment = Config.GetConfigParameter("DiscoveryDatabase");
			}else{			
				TestEnvironment = EnvironmentDetails.get("DiscoveryDatabase").toString();
			}
				
			try
			{
				screen.wait(username,60);
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Discoverylaunch");
				screen.click(username);
				Helper.Keystrokebackspace(30);
				Helper.Keystrokedelete(30);
				Helper.ClearTextBox(50);
				Helper.ClearTextBoxandEnterValue(Discoveryusername);
				screen.click(password);
				Helper.ClearTextBox(50);
				Helper.ClearTextBoxandEnterValue(Discoverypassword);
				screen.click(environmentdropdown);
				Helper.ClearTextBox(40);
				screen.type(TestEnvironment);
				Helper.Keystrokeenter(1);
				screen.click(login);
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "DiscoveryLogin");
				logger.info("Login was succecssful");
				Helper.WriteToTxtFile("Login was succecssful", TestExecution.TestExecutionFolder + "logs.txt");
				return true;
			}
			catch (FindFailed e){
				e.printStackTrace();
				logger.error(e.toString());
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				return false;
			}
		}
}
