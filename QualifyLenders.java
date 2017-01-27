import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class QualifyLenders {
	
	static Log logger = LogFactory.getLog(QualifyLenders.class);
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	static Pattern QualifyLendersNvaigation;
	static Pattern QualifyLendersbutton;
	static Pattern Display;
	static Pattern SortBy;
	static Pattern SuccessfulOnly;
	static Pattern Qualifiedsymbol;
	
	public QualifyLenders(){
		new QualifyLenders("C:\\Sikuli Images\\QualifyLenders\\");
	}
	
	public QualifyLenders(String Imagefolderlocation){
		QualifyLendersNvaigation = new Pattern(Imagefolderlocation + "QualifyLenders.PNG" );
		QualifyLendersbutton = new Pattern(Imagefolderlocation + "QualifyLenderButton.PNG" );
		Display = new Pattern(Imagefolderlocation + "Display.PNG" );
		SortBy = new Pattern(Imagefolderlocation + "SortByPNG.PNG" );
		SuccessfulOnly = new Pattern(Imagefolderlocation + "SuccessfulOnly.PNG" );
		Qualifiedsymbol = new Pattern(Imagefolderlocation + "QualifiedLoan.PNG" );
	}

	public static boolean ActionOnQulifyLenders()
	{
		try {
			screen.click(QualifyLendersNvaigation);
			screen.click(QualifyLendersbutton);
			App.pause(1);
			screen.find(SortBy).right(Offset[3]).click();
			Helper.Keystrokedown(3);
			Helper.Keystrokeenter(1);
			screen.click(SuccessfulOnly);
			screen.find(Display).right(Offset[3]).click();
			Helper.Keystrokeup(2);
			Helper.Keystrokeenter(1);
			
			if (screen.exists(Qualifiedsymbol) == null){
				System.out.println("The loan application was not successful, because it was not qualified by any lenders.");
				Helper.WriteToTxtFile("The loan application was not successful, because it was not qualified by any lenders.", TestExecution.TestExecutionFolder + "logs.txt");
				return false;
			}else{
				Helper.ScreenDump(TestExecution.TestExecutionFolder, "QualifyLender");
				Helper.WriteToTxtFile("Qualify lenders successful", TestExecution.TestExecutionFolder + "logs.txt");
				return true;
			}
			
		} catch (FindFailed e) {
			e.printStackTrace();
			Helper.WriteToTxtFile(e.toString(), TestExecution.TestExecutionFolder + "logs.txt");
			Helper.ScreenDump(TestExecution.TestExecutionFolder, "Error");
			return false;
		}
	}
	
}
