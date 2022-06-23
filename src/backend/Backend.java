package backend;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;

/**
 * Back-end for the <a href=http://cic-challenge.eu-gb.mybluemix.net/challenge.html>CIC-Challenge</a>.
 * <p>
 * This program explicit implements the CO2 emissions of public buildings of San Francisco.
 * <p>
 * Due to time constrains, the filter function is very crude and needs change of the source code to see the different outcome.  
 * 
 * @author Meisl Ulrich
 *
 */
public class Backend {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<JSONObject> jsonObj = new ArrayList<JSONObject>();
		ArrayList<JSONObject> outputObj = new ArrayList<JSONObject>();
		
		//crude Filter
		String crudeFilterKey = "";
		//crudeFilterKey = "d"; // department
		crudeFilterKey = "s"; //source
	
		
		String filterValue = "";
		
		filterValue = "Electric";
		
		/*
		 * departments
		Municipal Transportation Agency
		Asian Art Museum
		Animal Care and Control
		Adult Probation Department
		Arts Commission
		Assessor-Recorder
		Board of Supervisors
		City Attorney
		Community College District
		Convention Facilities Department
		Controller’s Office
		Planning
		County of SF Superior Court (Trial Courts)
		Central Shops
		Civil Service Commission
		Child Support Services
		District Attorney
		Building Inspection
		Emergency Management
		Human Resources
		Public Health
		
		 * source_type
		B100
		Diesel
		CNG
		Propane
		B20
		B5
		Gasoline
		Natural Gas
		Electric
		Steam
		
		*/
		
		
		try {
			jsonObj = readJsonFromUrl("https://data.sfgov.org/resource/pxac-sadh.json");
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(crudeFilterKey == "d")
		{
			for(JSONObject j : jsonObj)
			{
				if(j.getString("department").compareTo(filterValue) == 0)
				{
					outputObj.add(j);
				}
			}
		}
		else if(crudeFilterKey == "s")
		{
			for(JSONObject j : jsonObj)
			{
				if(j.getString("source_type").compareTo(filterValue) == 0)
				{
					outputObj.add(j);
				}
			}
		}
		else
		{
			outputObj = jsonObj;
		}
		
		for(JSONObject j : outputObj)
		{
			System.out.println(j.toString());
		}
		
		//System.out.println(outputObj.size());
		//System.out.println(jsonObj.toString());
		//System.out.println(outputObj.toString());
	}
	
	/**
	 * Reads the <code>JSONObjects</code> from the Data given by an <code>URL</code>.
	 * <p>
	 * All Datasets with 0 CO2 emission will be removed.	
	 * 
	 * @param url The <code>URL</code> of the data to be read
	 * @return ArrayList containing the <code>JSONObjects</code>
	 * @throws IOException
	 * @throws JSONException
	 */
	 static ArrayList<JSONObject> readJsonFromUrl(String url) throws IOException, JSONException {
			
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
