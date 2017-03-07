package Discovery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import Dynamics.DynamicsLeadsPage;
import Dynamics.Selenium;
import FactFind.AddressDetails;
import FactFind.CountrySelection;
import FactFind.EmploymentDetails;
import FactFind.FactFindAssets;
import FactFind.FactFindExecutor;
import FactFind.FactFindLogin;
import FactFind.Insurance;
import FactFind.Liabilities;
import FactFind.PersonalDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExecution {
	
	static Log logger = LogFactory.getLog(TestExecution.class);
	public static JSONObject JSONTestData;
	public static String PatternRootFolderlocation; 
	public String TestEnvironment;
	public String DiscoveryUserName;
	public String DiscoveryPassword;
	public String TestDataFolderRoot;
	public String TestFolder;
	public String RootFolder;
	public String LogFolder;
	public String ExecutionFolder;
	public String TimeStamp;
	public static String TestExecutionFolder;
	
	public void InitializeTestFramework(){
		Helper Config = new Helper();
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		//Debug.setDebugLevel(1);
		Settings.UserLogs = true;
		Settings.LogTime = true;
				
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
			Runtime.getRuntime().exec("taskkill /F /IM " + "geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM " + "IEDriverServer.exe");
			Runtime.getRuntime().exec("taskkill /F /IM " + "chromedriver.exe");
			
			logger.info("Killed All open instance of Tonto and browser drivers");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		if (Config.GetConfigParameter("MinimizeAllWindow").equals("Yes")){
			Helper.MinimizeAllWindows();
		}
		
		PatternRootFolderlocation = Config.GetConfigParameter("PatternRootFolder");
		
		TestDataFolderRoot = Config.GetConfigParameter("WQAAutomationFolderPath")+"TestData\\";
		
		LogFolder = Config.GetConfigParameter("WQAAutomationFolderPath")+"Logs\\";
		Debug.setLogFile(LogFolder + "Sikuli.log");
		RootFolder = Config.GetConfigParameter("WQAAutomationFolderPath")+"TestExecution\\";
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
		new Selenium();
		new DynamicsLeadsPage();
		new FactFindLogin();
		new PersonalDetails();
		new FactFindAssets();
		new Liabilities();
		new Insurance();
		new CountrySelection();
	}
	
	@Before
	public void setUp() throws Exception {
		InitializeTestFramework();
	}
	
	@Test
	public void TestCase001() throws Exception{
		assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));
	}	
	
	//@Test
	public void TestCase002() throws Exception{
		assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));	
	}
	
	//@Test
	public void TestCase003() throws Exception{
		assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));		
	}
	
	//@Test
	public void TestCase004() throws Exception{
		assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));		
	}
	
	//@Test
	public void TestCase005() throws Exception{
		assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));
	}
	
	//@After
	public void tearDown() throws Exception {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	public boolean TestExecutor(String TestCaseName){
		logger.info("Test execution started for Test Case: " + TestCaseName);
		TestExecutionFolder = TestFolderSetup(TestCaseName);
		VideoRecorderclass Record = new VideoRecorderclass();
		Record.startRecording(TestExecutionFolder);
		if (TestExecutionFolder != null){
			if (TestSteps(TestExecutionFolder + TestCaseName + ".txt") != true){
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Test execution aborted for " + TestCaseName);
				Record.stopRecording();
				return false;
			}else{
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Test execution sucessfully completed for : " + TestCaseName);
				Record.stopRecording();
				return true;
			}
		}else{
			logger.info("Test execution aborted for: " + TestCaseName);
			Record.stopRecording();
			return false;
		}
	}
	
	public boolean TestSteps(String testdata){
		
		JSONTestData = JSON.ReadTestData(testdata);
		if (JSON.GetTestData(JSONTestData, "LeadDetails").get("LeadOrigination").equals("Dynamics")){
			/*if(Selenium.CreateQuicklead() != true){
				return false;
			}*/
			if (JSON.GetTestData(JSONTestData, "LeadDetails").get("SendFactFindInvitaion").equals("Yes")){
				if(FactFindExecutor.FillFactFind() != true){
					return false;
				}
			}
		}
		
		if (LoginPage.LogintoDiscovery(JSONTestData) == false){return false;}
		if (DiscoveryHomePage.NavigatetoQualifyandAnalize() == false){return false;}
		if (QAHomePage.QuickQualify(JSONTestData) == false){return false;}
		if (ClientInformation.CaptureClientDetails(JSONTestData) == false){return false;}
		if (FundsRequired.CaptureTransaction(JSONTestData) == false){return false;}
		if (Securities.CaptureSecurities(JSONTestData) == false){return false;}
		if (LoanStructure.CaptureLoanStructure(JSONTestData) == false){return false;}
		if (QualifyLenders.ActionOnQulifyLenders(JSONTestData) == false){return false;}
		if (ScenarioSummary.SelectLenderandProduct(JSONTestData) == false){return false;}
		if (SaveScenario.SaveAsNewLead(JSONTestData) == false){return false;}
		if (ResponsibleLending.CaptureResponsibleLending(JSONTestData) == false){return false;}
		if (Referrals.CaptureReferrals(JSONTestData) == false){return false;}
		if (SaveScenario.Save(JSONTestData) == false){return false;}
		if (Apply.CaptureTypeOfLodgement(JSONTestData) == false){return false;}
		if (Helper.ForceKillApplication("Tonto.exe") == false){return false;}
		return true;
	}
	
	public String TestFolderSetup(String TestCaseName){
		
		TestExecutionFolder = null;
		
		File f = new File(TestDataFolderRoot + TestCaseName + ".txt" );
		
		if(f.exists() && !f.isDirectory()) { 
			TimeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
			String CurrentTestFolderName =  TestCaseName + "_" + TimeStamp;
			Helper.CreateDirectory(ExecutionFolder,CurrentTestFolderName);
			String CurrentTestFolerPath = ExecutionFolder + CurrentTestFolderName + "\\";
			Helper.WriteToTxtFile("ExecutionFolderCreated",  CurrentTestFolerPath + "logs.txt");
			Helper.CopyFiles(TestDataFolderRoot + TestCaseName + ".txt" , CurrentTestFolerPath + TestCaseName + ".txt");
			Helper.MoveFiles(TestDataFolderRoot + TestCaseName + ".txt" , TestDataFolderRoot + "Archive\\" + TestCaseName + "_" + TimeStamp + ".txt");
			return CurrentTestFolerPath;	
		}else{
			return null;
		}
	
	}	
	
}
