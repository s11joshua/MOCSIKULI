import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import org.sikuli.script.App;
import org.sikuli.script.Screen;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.sikuli.basics.Settings;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExecution {
	static Log logger = LogFactory.getLog(TestExecution.class);
	
	public JSONObject JSONTestData;
	public String TestEnvironment;
	public String DiscoveryUserName;
	public String DiscoveryPassword;
	public String TestDataFolderRoot;
	public String TestFolder;
	public String RootFolder;
	public String ExecutionFolder;
	public String TimeStamp;
	public static String TestExecutionFolder;
	
	public void InitializeTestFramework(){
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		
		TestEnvironment = "LendingSupportSIT";
		DiscoveryUserName = "santhony.replica";
		DiscoveryPassword = "choosey1!";
		
		TestDataFolderRoot = "C:\\DiscoveryAutomation\\TestData\\";
		//Helper.CreateDirectory("C:\\","DiscoveryAutomation\\TestExecution\\");
	    RootFolder = "C:\\DiscoveryAutomation\\TestExecution\\";
	    TestFolder =  new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	    Helper.CreateDirectory(RootFolder,TestFolder);
	    ExecutionFolder = RootFolder + TestFolder +"\\";
				
		new LoginPage();
		new DiscoveryHomePage();
		new QAHomePage();
		new ClientInformation();
		new FundsRequired();
		new Securities();
		new LoanStructure();
		new QualifyLenders();
		new SaveScenario();
		new ScenarioSummary();
		new ResponsibleLending();
		new Referrals();
		new Apply();
	}
	
	@Before
	public void setUp() throws Exception {
		InitializeTestFramework();
	}
	
	@ Test
	public void TestCase001() throws Exception{
		
		String TestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestExecutionFolder = TESTSETUP(TestCaseName);
		assertTrue(teststeps(TestExecutionFolder + TestCaseName + ".txt"));
	}	
	
	@ Test
	public void TestCase002() throws Exception{
		String TestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		TestExecutionFolder = TESTSETUP(TestCaseName);
		assertTrue(teststeps(TestExecutionFolder + TestCaseName + ".txt"));
	}
	
	@After
	public void tearDown() throws Exception {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean teststeps(String testdata){
		
		JSONTestData = JSON.ReadTestData(testdata);
		if (JSONTestData == null){
			Helper.WriteToTxtFile("Not able to locate the JSON data file in the test data directory",  TestExecutionFolder + "logs.txt");
			return false;
		}
		
		assertTrue (LoginPage.LaunchDiscoveryApplicaiton("TestInstance"));
		assertTrue (LoginPage.LogintoDiscovery(JSONTestData));
		assertTrue (DiscoveryHomePage.NavigatetoQualifyandAnalize());
		assertTrue (QAHomePage.QuickQualify(JSONTestData));
		assertTrue (ClientInformation.CaptureClientDetails(JSONTestData));
		assertTrue (FundsRequired.CaptureTransaction(JSONTestData));
		assertTrue (Securities.CaptureSecurities(JSONTestData));
		assertTrue (LoanStructure.CaptureLoanStructure(JSONTestData));
		assertTrue (QualifyLenders.ActionOnQulifyLenders());
		assertTrue (SaveScenario.SaveAsNewLead(JSONTestData));
		assertTrue (ScenarioSummary.SelectLenderandProduct(JSONTestData));
		assertTrue (ResponsibleLending.CaptureResponsibleLending(JSONTestData));
		//assertTrue (Referrals.CaptureReferrals(JSONTestData));
		//assertTrue (Apply.CaptureTypeOfLodgement(JSONTestData));
		assertTrue (SaveScenario.Save(JSONTestData));
		assertTrue (Helper.ForceKillApplication("Tonto.exe"));
		return true;
	}
	
	public String TESTSETUP(String TestCaseName){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		TimeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		String CurrentTestFolderName =  TestCaseName + "_" + TimeStamp;
		Helper.CreateDirectory(ExecutionFolder,CurrentTestFolderName);
		String CurrentTestFolerPath = ExecutionFolder + CurrentTestFolderName + "\\";
		Helper.WriteToTxtFile("ExecutionFolderCreated",  CurrentTestFolerPath + "logs.txt");
		Helper.CopyFiles(TestDataFolderRoot + TestCaseName + ".txt" , CurrentTestFolerPath + TestCaseName + ".txt");
		Helper.MoveFiles(TestDataFolderRoot + TestCaseName + ".txt" , TestDataFolderRoot + "Archive\\" + TestCaseName + "_" + TimeStamp + ".txt");
		return CurrentTestFolerPath;
	}
	
}
