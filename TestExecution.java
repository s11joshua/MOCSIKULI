package Discovery;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import org.sikuli.script.App;
import org.sikuli.script.Screen;

import Dynamics.DynamicsLeadsPage;
import Dynamics.DynamicsLoginPage;
import Dynamics.Selenium;

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
		new Selenium();
		new DynamicsLeadsPage();
		
	}
	
	@Before
	public void setUp() throws Exception {
		InitializeTestFramework();
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
	
	@Test
	public void TestCase005() throws Exception{
		//assertTrue(TestExecutor(new Object(){}.getClass().getEnclosingMethod().getName()));
		Dynamics.JDBCConnection.ConntectDB("suresh.anthony@mortgagechoice.com.au");
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
		//String TestCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("Test execution started for Test Case: " + TestCaseName);
		TestExecutionFolder = TestSetup(TestCaseName);
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
			if(Selenium.CreateQuicklead(JSONTestData) != true){
				return false;
			}
		}
		
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
		if (Referrals.CaptureReferrals(JSONTestData) == false){return false;}
		if (SaveScenario.Save(JSONTestData) == false){return false;}
		if (Apply.CaptureTypeOfLodgement(JSONTestData) == false){return false;}
		if (Helper.ForceKillApplication("Tonto.exe") == false){return false;}
		return true;
	}
	
	public String TestSetup(String TestCaseName){
		
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
