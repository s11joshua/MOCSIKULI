package Discovery;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSON {
	static Log logger = LogFactory.getLog(JSON.class);
	public static JSONObject ReadTestData(String TestDataFilePath){
		JSONParser Parser = new JSONParser();
		
		try {
			JSONObject JSONFile = (JSONObject) Parser.parse(new FileReader(TestDataFilePath));
			return JSONFile;
		} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error(e.toString());
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.toString());
				return null;
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error(e.toString());
				return null;
			}
		
	}
	
	public static JSONObject GetTestData(JSONObject jsonobject,String Key){
		JSONObject ReturnValue = (JSONObject) jsonobject.get(Key);
		return ReturnValue;	
	}
}
