import java.io.File;
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
import org.sikuli.basics.Debug;
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
	public String LogFolder;
	public String ExecutionFolder;
	public String TimeStamp;
	public static String TestExecutionFolder;
	
	public void InitializeTestFramework(){
		Settings.OcrTextSearch = true;
		Settings.OcrTextRead = true;
		Settings.setShowActions(true);
		//Debug.setDebugLevel(1);
		Settings.UserLogs = true;
		Settings.LogTime = true;
		
		
		
		TestEnvironment = "LendingSupportSIT";
		DiscoveryUserName = "santhony.replica";
		DiscoveryPassword = "choosey1!";
		
		TestDataFolderRoot = "C:\\DiscoveryAutomation\\TestData\\";
		//Helper.CreateDirectory("C:\\","DiscoveryAutomation\\TestExecution\\");
		LogFolder = "C:\\DiscoveryAutomation\\Logs\\";
		Debug.setLogFile(LogFolder + "Sikuli.log");
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
		logger.info("Entering Test Case: " + TestCaseName);
		TestExecutionFolder = TESTSETUP(TestCaseName);
		
		if (TestExecutionFolder != null){
			if (teststeps(TestExecutionFolder + TestCaseName + ".txt") != true){
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(false);
			}else{
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(true);
			}
		}else{
			logger.info("Existing Test Case: " + TestCaseName);
			assertTrue(false);
		}
	}	
	
	@ Test
	public void TestCase002() throws Exception{
	
		String TestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Entering Test Case: " + TestCaseName);
		TestExecutionFolder = TESTSETUP(TestCaseName);
		
		if (TestExecutionFolder != null){
			if (teststeps(TestExecutionFolder + TestCaseName + ".txt") != true){
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(false);
			}else{
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(true);
			}
		}else{
			logger.info("Existing Test Case: " + TestCaseName);
			assertTrue(false);
		}
	}
	
	@ Test
	public void TestCase003() throws Exception{
		
		
		String TestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Entering Test Case: " + TestCaseName);
		TestExecutionFolder = TESTSETUP(TestCaseName);
		
		if (TestExecutionFolder != null){
			if (teststeps(TestExecutionFolder + TestCaseName + ".txt") == true){
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(true);
			}else{
				Helper.CopyFiles(LogFolder + "Sikuli.log" , TestExecutionFolder + "Sikuli.log");
				logger.info("Existing Test Case: " + TestCaseName);
				assertTrue(false);
			}
		}else{
			logger.info("Existing Test Case: " + TestCaseName);
			assertTrue(false);
		}	
	}
	
	@After
	public void tearDown() throws Exception {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	public boolean teststeps(String testdata){
		
		JSONTestData = JSON.ReadTestData(testdata);
		
		if (LoginPage.LaunchDiscoveryApplicaiton("TestInstance") == false){return false;}
		if (LoginPage.LogintoDiscovery(JSONTestData) == false){return false;}
		if (DiscoveryHomePage.NavigatetoQualifyandAnalize() == false){return false;}
		if (QAHomePage.QuickQualify(JSONTestData) == false){return false;}
		if(ClientInformation.CaptureClientDetails(JSONTestData) == false){return false;}
		if (FundsRequired.CaptureTransaction(JSONTestData) == false){return false;}
		if (Securities.CaptureSecurities(JSONTestData) == false){return false;}
		if (LoanStructure.CaptureLoanStructure(JSONTestData) == false){return false;}
		if (QualifyLenders.ActionOnQulifyLenders(JSONTestData) == false){return false;}
		if (ScenarioSummary.SelectLenderandProduct(JSONTestData) == false){return false;}
		if (SaveScenario.SaveAsNewLead(JSONTestData) == false){return false;}
		if (ResponsibleLending.CaptureResponsibleLending(JSONTestData) == false){return false;}
		//if (Referrals.CaptureReferrals(JSONTestData) == false){return false;}
		if (SaveScenario.Save(JSONTestData) == false){return false;}
		if (Apply.CaptureTypeOfLodgement(JSONTestData) == false){return false;}
		if (Helper.ForceKillApplication("Tonto.exe") == false){return false;}
		return true;
	}
	
	public String TESTSETUP(String TestCaseName){
		
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + "Tonto.exe");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
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
