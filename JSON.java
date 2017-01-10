import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSON {

	public static void ReadJSONFile(String FilePath){
		JSONParser Parser = new JSONParser();
			
		try {
			JSONObject JSONFile = (JSONObject) Parser.parse(new FileReader(FilePath));
			JSONObject jsonobject = (JSONObject) JSONFile.get("Customerinformation");
			JSONObject CustomerNames = (JSONObject) jsonobject.get("customerNames");
			System.out.println(CustomerNames);
			String CustomerFirstname = CustomerNames.get("FirstName").toString();
			String CustomerMiddleName = CustomerNames.get("MiddleName").toString();
			String CustomerLastName = CustomerNames.get("LastName").toString();
            System.out.println(CustomerFirstname);
            System.out.println(CustomerMiddleName);
            System.out.println(CustomerLastName);

            JSONObject CustomerAddress = (JSONObject) jsonobject.get("CustomerAddress");
            System.out.println(CustomerAddress);
            System.out.println(CustomerAddress.get("UnitNumber").toString());
            System.out.println(CustomerAddress.get("StreetNumber").toString());
            System.out.println(CustomerAddress.get("StreetName").toString());
            System.out.println(CustomerAddress.get("Suburb").toString());
            System.out.println(CustomerAddress.get("PostCode").toString());

            JSONObject CustomerDependents = (JSONObject) jsonobject.get("CustomerDependents");
            System.out.println(CustomerDependents);
            JSONArray DOBArray = (JSONArray) CustomerDependents.get("DependentsDOB");
            Iterator<String> iterator = DOBArray.iterator();
            while (iterator.hasNext()) {
            System.out.println(iterator.next());
            }
            
            //JSONObject CustomerIncome = (JSONObject) jsonobject.get("CustomerIncome");
            //System.out.println(CustomerIncome);
            //JSONArray CustomerIncomeArray = new JSONArray ();
            JSONArray CustomerIncomeArray = (JSONArray) jsonobject.get("CustomerIncome");
            Iterator<JSONObject> iterator1 = CustomerIncomeArray.iterator();
            System.out.println(CustomerIncomeArray.size());
           while (iterator1.hasNext()) {
        	   	JSONObject Income = iterator1.next();
            	System.out.println(Income.get("Amount").toString());
            	System.out.println(Income.get("Taxed").toString());
           //System.out.println(iterator1.next().toString());
           }
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject ReadTestData(String TestDataFilePath){
		JSONParser Parser = new JSONParser();
		
		try {
			JSONObject JSONFile = (JSONObject) Parser.parse(new FileReader(TestDataFilePath));
			return JSONFile;
		} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		
	}
	
	public static JSONObject GetTestData(JSONObject jsonobject,String Key){
		JSONObject ReturnValue = (JSONObject) jsonobject.get(Key);
		return ReturnValue;	
	}
}
