package backend;

import java.io.*;

import org.json.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONReader {
	
	public static ArrayList<JSONObject> readJsonFromUrl(String url) throws IOException, JSONException {
		
		ArrayList<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
		String[] keys = new String[] {"department", "source_type", "emissions_mtco2e"};
		
	    InputStream in = new URL(url).openStream();
	   
	    Scanner scan = new Scanner(in);
	    String jsonText = scan.useDelimiter("\\Z").next();
	    
	    jsonText = jsonText.replace('[', ' ');
	    jsonText = jsonText.replace(']', ' ');
	    
	    scan.close();
	    
	    scan = new Scanner(jsonText);
	    
	    
	    String line;
	    JSONObject jsonTest;
	    Double co2e;
	    
	    while (scan.hasNextLine())
	    {
	    	line = scan.nextLine().substring(1);
	    	jsonTest = new JSONObject(line);
	    	co2e = Double.parseDouble(jsonTest.getString("emissions_mtco2e"));
	    	
	    	
	    	if(co2e > 0.0)
		    {
		    	jsonObjectList.add(new JSONObject(jsonTest, keys));
		    }
	    }
	    
	    scan.close();
	    
	    
	    return jsonObjectList;
	}
}
