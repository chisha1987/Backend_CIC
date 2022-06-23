package backend;

import java.io.IOException;
import org.json.*;


public class Backend {

	
	public static void main(String[] args) {
		
		JSONObject jsonObj = new JSONObject();;
		try {
			jsonObj = JSONReader.readJsonFromUrl("https://data.sfgov.org/resource/pxac-sadh.json");
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println(jsonObj.toString());
		
		
		/*
		JSONObject json = new JSONObject();
		json.append("key", "test");
		System.out.println(json.toString());
		*/
	}

}
