import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class QualifyLenders {
	
	public static int Offset[] = {0,10,50,100,200,500,1000};
	public static Screen screen = new Screen();
	static Pattern QualifyLendersNvaigation;
	static Pattern QualifyLendersbutton;
	
	public QualifyLenders(){
		new QualifyLenders("C:\\Sikuli Images\\QualifyLenders\\");
	}
	
	public QualifyLenders(String Imagefolderlocation){
		QualifyLendersNvaigation = new Pattern(Imagefolderlocation + "QualifyLenders.PNG" );
		QualifyLendersbutton = new Pattern(Imagefolderlocation + "QualifyLenderButton.PNG" );
		
	}

	public static boolean ActionOnQulifyLenders()
	{
		try {
			screen.click(QualifyLendersNvaigation);
			screen.click(QualifyLendersbutton);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
