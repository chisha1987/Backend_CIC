package backend;

import java.io.*;

import org.json.*;

import java.net.URL;
import java.util.Scanner;

public class JSONReader {
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream in = new URL(url).openStream();
	   
	    Scanner scan = new Scanner(in);
	    String jsonText = scan.useDelimiter("\\Z").next();
	    jsonText = jsonText.replace('[', ' ');
	    jsonText = jsonText.replace(']', ' ');
	    //jsonText = jsonText.replace(' ', '_');
	    scan.close();
	    
	    scan = new Scanner(jsonText);
	    
	    String jj = scan.nextLine().substring(1);
	    
	    System.out.println(jj);
	    
	    scan.close();
	    
	    //System.out.println(jsonText);
	    
	    return new JSONObject(jsonText);
	}
}
