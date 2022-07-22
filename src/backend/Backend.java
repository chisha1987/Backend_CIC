package backend;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

/**
 * Back-end for the <a href=http://cic-challenge.eu-gb.mybluemix.net/challenge.html>CIC-Challenge</a>.
 * <p>
 * This program explicit implements the CO2 emissions of public buildings of San Francisco.
 * <p>
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
		
		String urlString = "https://data.sfgov.org/resource/pxac-sadh.json";
		
		boolean filterDepartment = true;
		boolean filterSource_type = true;
		
		String departmentFilterKey = "Public Health";
		String source_typeFilterKey = "Propane";
		
		
		//Only emissions greater than 0.0
		urlString = urlString + "?$where=emissions_mtco2e>0.0";
		
		//We need only department, source_type and emisions_mtco2e
		urlString = urlString + "&$select=department,source_type,emissions_mtco2e";
		
		//adding filter for department if needed
		if(filterDepartment)
		{
			departmentFilterKey = departmentFilterKey.replace(" ", "%20");
			urlString = urlString + "&department=" + departmentFilterKey;
		}
		
		//adding filter for source_type if needed
		if(filterSource_type)
		{
			source_typeFilterKey = source_typeFilterKey.replace(" ", "%20");
			urlString = urlString + "&source_type=" + source_typeFilterKey;
		}
			
		try {
			URL url = new URL(urlString);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();
			
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            }
            else
            {
            	String inline = "";
            	Scanner scan = new Scanner(url.openStream());
            	
            	while (scan.hasNext())
        		{
        			inline += scan.nextLine();
        		}
        		    
        		//Close the scanner
        		scan.close();
        		
        		JSONArray array = new JSONArray(inline);
        		
        		System.out.println(array.toString());
            	
            }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
	
		
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

	}
}
