import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.sikuli.script.App;
import org.sikuli.script.Screen;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.sikuli.basics.Settings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExecution {
	
	public JSONObject JSONTestData;
	public Screen screen;
	public String TestEnvironment;
	public Object[] ObjectReferencesArray = new Object[50];
	
	public void InitializeTestExecution(){
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		JSONTestData = JSON.ReadTestData("C:\\Users\\santhony\\Desktop\\TestJSONFile.txt");
		screen = new Screen();
		TestEnvironment = "SIT Server";
		new ClientInformation();
		new FundsRequired();
		new Securities();
		ObjectReferencesArray[1] = new DiscoveryHomePage("C:\\Sikuli Images\\DiscoveryHomePage\\");
		ObjectReferencesArray[2] = new QAHomePage("C:\\Sikuli Images\\QA Homepage\\");
		//ObjectReferencesArray[3] = new ClientInformation("C:\\Sikuli Images\\Client_Information\\");
		//ObjectReferencesArray[4] = new FundsRequired("C:\\Sikuli Images\\FundsRequired\\");
		//ObjectReferencesArray[5] = new Securities("C:\\Sikuli Images\\Securities\\");
		ObjectReferencesArray[6] = new LoanSplit("C:\\Sikuli Images\\LoanStructure\\");
		ObjectReferencesArray[7] = new QualifyLenders("C:\\Sikuli Images\\QualifyLenders\\");
		ObjectReferencesArray[8] = new ScenarioSummary("C:\\Sikuli Images\\ScenarioSummary\\");
		ObjectReferencesArray[9] = new ResponsibleLending("C:\\Sikuli Images\\ResponsibleLending\\");
		ObjectReferencesArray[10] = new Referrals("C:\\Sikuli Images\\Referrals\\");
		ObjectReferencesArray[11] = new Apply("C:\\Sikuli Images\\Apply\\");
	}
	
	@Before
	public void setUp() throws Exception {
		InitializeTestExecution();
		Helper.ReadFromTxtFile("C:\\ResultsFolder\\DynamicsTestResult2016-12-19_07-30-00-AM.txt");
	}
	
	@ Test
	public void Testcase01() throws Exception{
		
		String FirstName = "TestCase01";
		String LastName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		//assertTrue (LoginPage.LaunchDiscoveryApplicaiton("TestInstance"));
		//assertTrue (LoginPage.LogintoDiscovery(screen,"joanne.gocher","choosey1!",TestEnvironment));
		//assertTrue (DiscoveryHomePage.NavigatetoQualifyandAnalize((DiscoveryHomePage) ObjectReferencesArray[1], screen));
		//assertTrue (QAHomePage.QuickQualify((QAHomePage) ObjectReferencesArray[2] , screen, FirstName));
		App.focus("Qualifier Analyser");
		//assertTrue(ClientInformation.CaptureClientDetails(JSONTestData));
		//assertTrue (FundsRequired.CaptureTransaction(JSONTestData));
		assertTrue (Securities.CaptureSecurities(JSONTestData));
		//assertTrue (LoanSplit.CaptureLoanSplits((LoanSplit)ObjectReferencesArray[6], screen));
		//assertTrue (QualifyLenders.ActionOnQulifyLenders((QualifyLenders) ObjectReferencesArray[7], screen));
		//assertTrue (ScenarioSummary.SelectLenderandProduct((ScenarioSummary) ObjectReferencesArray[8],screen));
		//assertTrue (ResponsibleLending.captureResponsibleLending((ResponsibleLending) ObjectReferencesArray[9], screen));
		//assertTrue (Referrals.CaptureReferrals((Referrals) ObjectReferencesArray[10], screen));
		//assertTrue (Apply.CaptureTypeOfLodgement((Apply) ObjectReferencesArray[11], screen));
		//assertTrue (ClientInformation.SaveScenario());
		//assertTrue (Apply.SubmitApplication((Apply) ObjectReferencesArray[11], screen));
		//assertTrue (Helper.ForceKillApplication("Tonto.exe"));
		
		//App.focus("Qualifier Analyser");
		//assertTrue (FundsRequired.CaptureTransaction((FundsRequired) ObjectReferencesArray[4], screen));
	}
	
	//@ Test
	public void Testcase02() throws Exception{
	
		String FirstName = "TestCase02";
		String LastName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				
		//assertTrue (LoginPage.LaunchDiscoveryApplicaiton("TestInstance"));
		//assertTrue (LoginPage.LogintoDiscovery(screen,"john.bilous","choosey1!",TestEnvironment));
		//assertTrue (DiscoveryHomePage.NavigatetoQualifyandAnalize((DiscoveryHomePage) ObjectReferencesArray[1], screen));
		//assertTrue (QAHomePage.QuickQualify((QAHomePage) ObjectReferencesArray[2] , screen, FirstName));
		//assertTrue (ClientInformation.CaptureClientInformation((ClientInformation)ObjectReferencesArray[3] ,screen, FirstName, LastName));
		//assertTrue (FundsRequired.CaptureTransaction());
		//assertTrue (Securities.CaptureSecurities((ClientInformation)ObjectReferencesArray[3],(Securities)ObjectReferencesArray[5], screen));
		assertTrue (LoanSplit.CaptureLoanSplits((LoanSplit)ObjectReferencesArray[6], screen));
		assertTrue (QualifyLenders.ActionOnQulifyLenders((QualifyLenders) ObjectReferencesArray[7], screen));
		assertTrue (ScenarioSummary.SelectLenderandProduct((ScenarioSummary) ObjectReferencesArray[8],screen));
		assertTrue (ResponsibleLending.captureResponsibleLending((ResponsibleLending) ObjectReferencesArray[9], screen));
		assertTrue (Referrals.CaptureReferrals((Referrals) ObjectReferencesArray[10], screen));
		assertTrue (Apply.CaptureTypeOfLodgement((Apply) ObjectReferencesArray[11], screen));
		assertTrue (ClientInformation.SaveScenario());
		assertTrue (Apply.SubmitApplication((Apply) ObjectReferencesArray[11], screen));
		assertTrue (Helper.ForceKillApplication("Tonto.exe"));

		//App.focus("Qualifier Analyser");
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
