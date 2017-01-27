import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import org.json.JSONException;
import org.json.XML;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XMLParser {
	 
	static Log logger = LogFactory.getLog(XMLParser.class);
  

	public static org.json.JSONObject ReturnXMLFileContentinJSONFormat(String XMLFilePath){
		
	File inputFile = new File(XMLFilePath);
 
	try {
		
		  byte[] bytes = Files.readAllBytes(inputFile.toPath());
		  String JSON = new String(bytes,"UTF-8");
		  System.out.println(XML.toJSONObject(JSON));
		  return XML.toJSONObject(JSON);
		  
	      
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	      return null;
	  } catch (IOException e) {
	      e.printStackTrace();
	      return null;
	  } catch (JSONException e) {
	  	  e.printStackTrace();
	   	  return null;
	  }
	      
  }
   
}

