package cucumber;

import java.util.HashMap;
import java.util.Map;

public class IdMap {
	private static Map<String, String> ID_MAP;
	
	public static Map<String, String> getGlobalIdMap(){
		if(ID_MAP == null) {
			initMap();
		}
		
		return ID_MAP;
	}
	
	private static void initMap() {
		ID_MAP = new HashMap<String, String>();
		ID_MAP.put("mainSearchBox", "locationInput");
		ID_MAP.put("navBar", "nav");
		ID_MAP.put("mainSearchButton", "mainSearchBtn");
	}
}
