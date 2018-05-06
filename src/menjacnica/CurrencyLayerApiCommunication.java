
package menjacnica;


import java.util.LinkedList;
import java.util.Map;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyLayerApiCommunication {

	
	
	
	
	public static LinkedList<Zemlja> getCountries() {

		LinkedList<Zemlja> countries = new LinkedList<Zemlja>();

		String url = "http://free.currencyconverterapi.com/api/v3/countries";

		try {
			String zemlje = URLConnection.getContent(url);
			JsonParser par = new JsonParser();
			Gson g = new GsonBuilder().create();
			JsonObject obj = par.parse(zemlje).getAsJsonObject().getAsJsonObject("results");
			for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
				Zemlja c = g.fromJson(entry.getValue(), Zemlja.class);
				countries.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countries;

	}

}