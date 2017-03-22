package Discovery;
import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Helper {
	static Log logger = LogFactory.getLog(Helper.class);
	public ScreenRecorder screenRecorder;
	
	public static void Keystrokebackspace(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	public static void Keystrokedelete(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_DELETE);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	public static void Keystroketab(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_TAB);
			App.pause(1);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	public static void Keystrokeenter(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_ENTER);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	public static void Keystrokedown(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_DOWN);
			App.pause(1);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	public static void Keystrokeup(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_UP);
			App.pause(1);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}
	
	public static void Keystrokespace(int repeatcount){
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_SPACE);
			App.pause(1);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	public static void ClearTextBoxandEnterValue(String value)
	{
		Screen screen = new Screen();
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_DELETE);
			counter = counter + 1;
		}
		while(counter <= 5);
		int counter1 = 1;
		do{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			counter1 = counter1 + 1;
		}
		while(counter1 <= 5);
		screen.type(value);
		Helper.Keystrokeenter(1);
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}
	
	public static void ClearTextBox(int RepeatCount)
	{
		
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_DELETE);
			counter = counter + 1;
		}
		while(counter <= RepeatCount);
		
		int counter1 = 1;
		do{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			counter1 = counter1 + 1;
		}
		while(counter1 <= RepeatCount);
		
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}
	
	public static void ClearTextBox(int RepeatCount, float time)
	{
		
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_DELETE);
			counter = counter + 1;
			App.pause((float)time);
		}
		while(counter <= RepeatCount);
		
		int counter1 = 1;
		do{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			counter1 = counter1 + 1;
			App.pause((float)time);
		}
		while(counter1 <= RepeatCount);
		
		} catch (AWTException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}
	
	public static boolean ForceKillApplication(String ApplicationName){
		//Tonto.exe
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + ApplicationName);
			logger.info("Application Successfully force Killed");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
	public static int CountIteratorItem(JSONArray Array){
		int counter = 0;
		Iterator<JSONObject> SplitDetails = Array.iterator();
		while (SplitDetails.hasNext()){
			JSONObject SplitValues = SplitDetails.next();
			counter ++;
		}
		return counter;
	}

	public static void ScreenDump(String FilePathinSikuliformat, String Screenshotname){
		Screen screen = new Screen();
		screen.capture().save(FilePathinSikuliformat, Screenshotname);
	}
	
	public static void ClickImageRight(Pattern Image) throws FindFailed{
		Screen screen = new Screen();
		screen.find(Image).right(10).click();
	}
	
	public static ArrayList<String> ReadFromTxtFile(String FilePath) throws FileNotFoundException {
		try {
			FileReader File = new FileReader(FilePath);
			BufferedReader TextData = new BufferedReader(File);
			String Line = null;
			ArrayList<String> records = new ArrayList<String>();
			while ((Line = TextData.readLine()) != null){
				records.add(Line);
			}
			TextData.close();
			return records;
			//WriteToTxtFile(records.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void WriteToTxtFile(String Content, String PathforFileName){
		File file = new File(PathforFileName);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Content);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean CreateFile(String path, String Filename){
		try {
			Files.createFile(Paths.get(path + Filename));
			logger.info("The following file '" + Filename +"' was created successfully in the follwoing directory, " + path);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public static boolean CreateDirectory(String RootFolder, String DirectoryName){
		try {
			Files.createDirectories(Paths.get(RootFolder + DirectoryName));
			logger.info("The following directory '" + DirectoryName +"' was created successfully in the root folder, " + RootFolder);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
	}
	
	public static boolean MoveFiles(String SourceFile, String TargetFile){
		try {
			Files.move(Paths.get(SourceFile), Paths.get(TargetFile));
			logger.info("The following file'" + SourceFile + "' was moved successfully to the following target folder: " + TargetFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public static boolean CopyFiles(String SourceFile, String TargetFile){
		try {
			Files.copy(Paths.get(SourceFile), Paths.get(TargetFile));
			logger.info("The following file'" + SourceFile + "' was copied successfully to the following target folder: " + TargetFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public static void CopyDiscoveryErrorLogtoExecutionFolder(String TargetFile){
		try {
			Files.copy(Paths.get(TestExecution.DiscoveryErroLogFile), Paths.get(TargetFile));
			logger.info("The Discovery Error log file has been successfully copied to the following target folder: " + TargetFile);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	public static void CopyAOLXMLFiletoExecutionFolder() {
	    File fl = new File(TestExecution.DiscoveryGeneratedXMLPath);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	  	if(choice != null){
	  		CopyFiles(choice.toString(),TestExecution.TestExecutionFolder + choice.getName());
		}
		else{
	    	logger.info("There are no Discovery generated XML file which was created after the test execution");
	    }
	}
	
	public static boolean ReadAllTextFilesInDirectory(String DirectoryPath){
	Path dir = FileSystems.getDefault().getPath(DirectoryPath);
	//List<JSONObject> list = new ArrayList<JSONObject>();
	//TestExecution Test = new TestExecution();
		try (DirectoryStream<Path> stream =
		     Files.newDirectoryStream(dir, "*.txt")) {
		    for (Path entry: stream) {
		    	//JSON.ReadTestData(entry.getFileName().toString());
		    	//list.add(JSON.ReadTestData(entry.getFileName().toString()));
		    	//Test.teststeps(DirectoryPath + entry.getFileName().toString());
		    	System.out.println(entry.getFileName());
		    }
		    return true;
		} catch (IOException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can // only be thrown by newDirectoryStream.
		    System.err.println(x);
		    return false;
		}
	}
	
	public static String GetConfigParameter(String ParameterKey){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:/TestFolder/Config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		return prop.getProperty(ParameterKey);
	}

	public static boolean Waitforelement(WebDriver driver, String Findby,String locator, int waittimeinsec){
		  WebDriverWait wait = new WebDriverWait(driver, waittimeinsec);
		  switch (Findby)
          {
              case "XPath":
            	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
                  return true;
              case "CssSelector":
            	  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
                  return true;
              case "Id":
            	  wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
                  return true;
              default:
                  logger.error("Invalid Findby string passed to Waitforelement method");
                  return false; 
          }
              
      }
	
	public static boolean MinimizeAllWindows(){
		try {
			Runtime.getRuntime().exec("cmd /c start MinimizeAllWindows.bat");
			logger.info("All windows in desktop have been minimized");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("Not able to minimize all windows");
			return false;
		}
		
	}
	
	public static void ScroolToView(WebDriver driver, WebElement Element){
		
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Element);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	
}
