import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class LoginPage {
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
		
		public static boolean LaunchDiscoveryApplicaiton(String DiscoveryAppLocation)
		{
			if (DiscoveryAppLocation == "TestInstance"){
					App.open("C:\\Program Files\\mortgage choice\\DiscoverySysTest\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "Discoverylaunch");
					return true;
			}
			else if (DiscoveryAppLocation == "ProductionInstance"){
					App.open("C:\\Program Files (x86)\\Mortgage Choice\\Discovery\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					Helper.ScreenDump(TestExecution.TestExecutionFolder, "Discoverylaunch");
					return true;
			}
			else
			{
				Helper.WriteToTxtFile("Invalid parameter passed for Instance of Discovery ", TestExecution.TestExecutionFolder + "logs.txt");	
				return false;
			}
			
		}
		
		
		public static boolean LogintoDiscovery(JSONObject RawFile)
		{	
			JSONObject LoginDetails = JSON.GetTestData(RawFile, "UserDetails");
			JSONObject EnvironmentDetails = JSON.GetTestData(RawFile, "EnvironmentDetails");
			
			String Discoveryusername = LoginDetails.get("DiscoveryUsername").toString();
			String Discoverypassword = LoginDetails.get("DiscoveryPassword").toString();
			String TestEnvironment = EnvironmentDetails.get("DiscoveryDatabase").toString();
			
			try
			{
				screen.wait(username,30);
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
				return true;
			}
			catch (FindFailed e){
				e.printStackTrace();
				Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
				return false;
			}
		}
}
