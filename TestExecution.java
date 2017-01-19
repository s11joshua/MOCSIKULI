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
	public String DiscoveryUserName;
	public String DiscoveryPassword;
	public Object[] ObjectReferencesArray = new Object[50];
	
	public void InitializeTestExecution(){
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		
		JSONTestData = JSON.ReadTestData("C:\\Users\\santhony\\Desktop\\TestJSONFile.txt");
		TestEnvironment = "LendingSupportSIT";
		DiscoveryUserName = "santhony.replica";
		DiscoveryPassword = "choosey1!";
		
		screen = new Screen();
		new LoginPage();
		new DiscoveryHomePage();
		new QAHomePage();
		new ClientInformation();
		new FundsRequired();
		new Securities();
		new LoanStructure();
		new QualifyLenders();
		new ScenarioSummary();
		new ResponsibleLending();
		new Referrals();
		new Apply();
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
		//assertTrue (LoginPage.LogintoDiscovery(DiscoveryUserName,DiscoveryPassword,TestEnvironment));
		//assertTrue (DiscoveryHomePage.NavigatetoQualifyandAnalize());
		//assertTrue (QAHomePage.QuickQualify(JSONTestData));
		App.focus("Qualifier Analyser");
		//assertTrue(ClientInformation.CaptureClientDetails(JSONTestData));
		//assertTrue (FundsRequired.CaptureTransaction(JSONTestData));
		//assertTrue (Securities.CaptureSecurities(JSONTestData));
		//assertTrue (LoanStructure.CaptureLoanStructure(JSONTestData));
		//assertTrue (QualifyLenders.ActionOnQulifyLenders());
		//assertTrue (ClientInformation.SaveScenario());
		//assertTrue (ScenarioSummary.SelectLenderandProduct(JSONTestData));
		assertTrue (ResponsibleLending.CaptureResponsibleLending(JSONTestData));
		//assertTrue (Referrals.CaptureReferrals());
		//assertTrue (Apply.CaptureTypeOfLodgement());		
		//assertTrue (Apply.SubmitApplication());
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
		//assertTrue (LoanStructure.CaptureLoanSplits((LoanSplit)ObjectReferencesArray[6], screen));
		//assertTrue (QualifyLenders.ActionOnQulifyLenders((QualifyLenders) ObjectReferencesArray[7], screen));
		//assertTrue (ScenarioSummary.SelectLenderandProduct((ScenarioSummary) ObjectReferencesArray[8],screen));
		//assertTrue (ResponsibleLending.captureResponsibleLending((ResponsibleLending) ObjectReferencesArray[9], screen));
		//assertTrue (Referrals.CaptureReferrals((Referrals) ObjectReferencesArray[10], screen));
		//assertTrue (Apply.CaptureTypeOfLodgement((Apply) ObjectReferencesArray[11], screen));
		//assertTrue (ClientInformation.SaveScenario());
		//assertTrue (Apply.SubmitApplication((Apply) ObjectReferencesArray[11], screen));
		//assertTrue (Helper.ForceKillApplication("Tonto.exe"));

		//App.focus("Qualifier Analyser");
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
