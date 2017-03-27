package Discovery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import Dynamics.DynamicsExecutor;
import Dynamics.DynamicsHomepage;
import Dynamics.DynamicsLeadsPage;

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
import org.sikuli.script.App;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestExecution {
	
	static Properties prop = new Properties();
	
	
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
	public static long TestExecutionStartTime;
	public static String DiscoveryGeneratedXMLPath;
	public static String TestExecutionFolder;
	public static String DiscoveryErroLogFile;
	
	public void InitializeTestFramework(){
		
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		//Debug.setDebugLevel(1);
		Settings.UserLogs = true;
		Settings.LogTime = true;
		
		Helper config = new Helper();
		
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
		if(config.GetConfigParameter("MinimizeAllWindow").equals("Yes")){
			Helper.MinimizeAllWindows();
		}
		
		if (config.GetConfigParameter("MinimizeAllWindow").equals("Yes")){
			Helper.MinimizeAllWindows();
		}
		
		PatternRootFolderlocation = config.GetConfigParameter("PatternRootFolder");
		DiscoveryErroLogFile = config.GetConfigParameter("DiscoveryErrorLog");
		TestDataFolderRoot = config.GetConfigParameter("WQAAutomationFolderPath")+"TestData\\";
		DiscoveryGeneratedXMLPath = config.GetConfigParameter("DiscoveryGeneratedXMLPath");
		
		LogFolder = config.GetConfigParameter("WQAAutomationFolderPath")+"Logs\\";
		Debug.setLogFile(LogFolder + "Sikuli.log");
		RootFolder = config.GetConfigParameter("WQAAutomationFolderPath")+"TestExecution\\";
	    TestFolder =  new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
	    Helper.CreateDirectory(RootFolder,TestFolder);
	    ExecutionFolder = RootFolder + TestFolder +"\\";
		
	    TestExecutionStartTime = System.currentTimeMillis();
	    System.out.println(TestExecutionStartTime);
	    
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
		new DynamicsExecutor();
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
		logger.info("Starting Test case Initalization");
		InitializeTestFramework();
		logger.info("Test case Initalization Completed successfully");
	}
	
	//@Test
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
	
	@Test
		public void TestCase006() throws Exception{
			assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));
		}
	
	@After
	public void tearDown() throws Exception {
		logger.info("Starting Tear Down Operation");
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		Helper.CopyDiscoveryErrorLogtoExecutionFolder(TestExecutionFolder+"DiscoveryErrorLog.txt");
		Helper.CopyAOLXMLFiletoExecutionFolder();
		logger.info("Tear Down Operation Completed successfully");
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
		if (JSON.GetTestData(JSONTestData, "Apply").get("UpdateApplyOnline") != null && (!JSON.GetTestData(JSONTestData, "Apply").get("UpdateApplyOnline").equals("Yes"))){
			if (JSON.GetTestData(JSONTestData, "LeadDetails").get("LeadOrigination").equals("Dynamics")){
				if(DynamicsExecutor.CreateQuicklead() != true){
					return false;
				}
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
		
		
			if (JSON.GetTestData(JSONTestData, "LeadDetails").get("LeadOrigination").equals("Discovery")){
				if (JSON.GetTestData(JSONTestData, "LeadDetails").get("SendFactFindInvitaion").equals("Yes")){
					if(DiscoveryReplication.StartRepliationfromDiscoveryHomePae() != true){
						return false;
					}
					if(DynamicsExecutor.LogintoDynamics() != true){
						System.out.println("test the logic after login to dynamics");
						return false;
					}
					if(DynamicsHomepage.SearchOpportunity() != true){
						return false;
					}
					if(DynamicsHomepage.SendFactFindInvite() != true){
						return false;
					}
					if (JSON.GetTestData(JSONTestData, "LeadDetails").get("SendFactFindInvitaion").equals("Yes")){
						if(FactFindExecutor.FillFactFind() != true){
							return false;
						}
					}
				}
				
				App.focus("Qualifier Analyser");
				if (Apply.UpdateApplyOnline() == false){return false;}
			}		
			return true;
		}else{
			if (LoginPage.LogintoDiscovery(JSONTestData) == false){return false;}
			if (DiscoveryHomePage.FindExistingScenario() == false){return false;}
			if (Apply.UpdateApplyOnline() == false){return false;}
			return true;
		}
		//if (Helper.ForceKillApplication("Tonto.exe") == false){return false;}
		
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
