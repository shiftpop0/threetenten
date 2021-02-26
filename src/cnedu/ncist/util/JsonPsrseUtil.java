package cnedu.ncist.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonPsrseUtil {
	
	 public static JSONArray toJsonArray(List<Map<String, String>> data){
	        JSONArray array = new JSONArray();
	        for (Map<String, String> rowItem : data) {
				JSONObject jsonObject = new JSONObject();
				for (Entry<String, String> entry : rowItem.entrySet()) {
					jsonObject.put(entry.getKey(), entry.getValue());
				}
				array.add(jsonObject);
			}
	        
			return array;

	 }
}

