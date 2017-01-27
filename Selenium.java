
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
public class Selenium {

 public static void main(String[] args) {
	  WebDriver driver = new InternetExplorerDriver();
	  driver.get("http://only-testing-blog.blogspot.in");
	  String i = driver.getCurrentUrl();
	  System.out.println(i);
	  //driver.close();
	}
}
