import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Helper {
	
	public static void Keystrokebackspace(int repeatcount)
	{	
		try {Robot robot = new Robot();
		int counter = 1;
		do{
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			counter = counter + 1;
		}
		while(counter <= repeatcount);
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
			
		} catch (AWTException e1) {e1.printStackTrace();}
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
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
		
		} catch (AWTException e1) {e1.printStackTrace();}
		
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
		
		} catch (AWTException e1) {e1.printStackTrace();}
		
	}
	
	public static boolean ForceKillApplication(String ApplicationName){
		//Tonto.exe
		try {
			Runtime.getRuntime().exec("taskkill /F /IM " + ApplicationName);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
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
		//screen.capture().save("C:\\Sikuli Images\\", "TestFile");
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
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean CreateDirectory(String RootFolder, String DirectoryName){
		try {
			Files.createDirectories(Paths.get(RootFolder + DirectoryName));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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
	
}
