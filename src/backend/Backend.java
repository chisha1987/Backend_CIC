package backend;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;

import org.json.*;


public class Backend {

	
	public static void main(String[] args) {
		ArrayList<JSONObject> jsonObj = new ArrayList<JSONObject>();
		ArrayList<JSONObject> outputObj = new ArrayList<JSONObject>();
		
		/*
		Scanner filterScan = new Scanner(System.in);
		String filterKey = "";
		String filterValue = "";
		
		if(filterScan.hasNextLine())
		{
			filterKey = filterScan.nextLine();
			if(filterScan.hasNextLine())
			{
				filterValue = filterScan.nextLine();
			}
		}
		filterScan.close();
		
		
		System.out.println(filterKey);
		
		System.out.println(filterValue);
		*/
		
		//crude Filter
		
		String crudeFilterKey = "";
		crudeFilterKey = "d"; // department
		//crudeFilterKey = "s"; //source
	
		
		String filterValue = "";
		
		filterValue = "Municipal Transportation Agency";
		
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
			jsonObj = JSONReader.readJsonFromUrl("https://data.sfgov.org/resource/pxac-sadh.json");
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
		
		System.out.println(outputObj.size());
		
		//System.out.println(jsonObj.toString());
		System.out.println(outputObj.toString());
	
	}

}
