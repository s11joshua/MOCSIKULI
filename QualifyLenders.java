import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class QualifyLenders {
	
	Pattern QualifyLendersNvaigation;
	Pattern QualifyLendersbutton;
	
	public QualifyLenders(String Imagefolderlocation){
		QualifyLendersNvaigation = new Pattern(Imagefolderlocation + "QualifyLenders.PNG" );
		QualifyLendersbutton = new Pattern(Imagefolderlocation + "QualifyLenderButton.PNG" );
		
	}

	public static boolean ActionOnQulifyLenders(QualifyLenders Qualifylenders,Screen screen)
	{
		try {
			screen.click(Qualifylenders.QualifyLendersNvaigation);
			screen.click(Qualifylenders.QualifyLendersbutton);
			return true;
		} catch (FindFailed e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
