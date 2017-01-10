import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class LoginPage {
	Pattern username;
	Pattern password;
	Pattern environmentdropdown;
	Pattern login;
	

		public LoginPage()
		{
			username = new Pattern("C:\\Sikuli Images\\Login\\Username.PNG");
			password = new Pattern("C:\\Sikuli Images\\Login\\Password.PNG");
			environmentdropdown = new Pattern("C:\\Sikuli Images\\Login\\EnvironmentDropdown.PNG");
			login = new Pattern("C:\\Sikuli Images\\Login\\Login.PNG");
				
		}
		
		public static boolean LaunchDiscoveryApplicaiton(String DiscoveryAppLocation)
		{
			if (DiscoveryAppLocation == "TestInstance"){
					App.open("C:\\Program Files\\mortgage choice\\DiscoverySysTest\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					return true;
			}
			else if (DiscoveryAppLocation == "ProductionInstance"){
					App.open("C:\\Program Files (x86)\\Mortgage Choice\\Discovery\\bin\\Discovery.exe");
					App.pause(3);
					App.focus("Discovery");
					return true;
			}
			else
			{
					return false;
			}
			
		}
		
		public static boolean LogintoDiscovery(Screen screen, String username, String password, String TestEnvironment)
		{
			LoginPage Login = new LoginPage();

			try
			{
			screen.wait(Login.username,30);
			screen.click(Login.username);
			Helper.Keystrokebackspace(30);
			Helper.Keystrokedelete(30);
			screen.type(username);
			screen.click(Login.password);
			screen.type(password);
			screen.click(Login.environmentdropdown);
			screen.type(TestEnvironment);
			Helper.Keystrokeenter(1);
			screen.click(Login.login);
			return true;
			}
			catch (FindFailed e){
				e.printStackTrace();
				return false;
			}
		}
}
