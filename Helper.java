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
import java.util.ArrayList;
import java.util.Iterator;

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
	
	public static void WriteToTxtFile(String Content, String FilenametoWrite){
		//FilenametoWrite = "C:\\ResultsFolder\\filename.txt"
		File file = new File(FilenametoWrite);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.write(Content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
