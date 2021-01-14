package csci310.weatherplanner.weathersource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import csci310.weatherplanner.util.UnitConversion;
import csci310.weatherplanner.weathersource.mock.MockExternalAPI;

public class AerisAdvancedWeatherSource implements IAdvancedWeatherSource {
	private final static String clientId = "rLHlJuTq0vq2Q2Y7xPn0Q";
	private final static String clientSecret = "fK4BUjGftMkWajEzVAsCXX2VRqzGo9yhcmxo6pYg";
	private final static String locationsUrl = "/observations/search?format=json&client_id=" + clientId + "&client_secret=" + clientSecret;
	private final static String normalsUrl = "/normals/%s?format=json&client_id=" + clientId + "&client_secret=" + clientSecret;
	private final static String placesUrl = "/places/closest?format=json&client_id=" + clientId + "&client_secret=" + clientSecret;
	
	private final IExternalAPI aeris;
	private final JsonParser parser;
	private final Map<String, List<String>> normalsCache;
	
	public AerisAdvancedWeatherSource(IExternalAPI aeris) {
		this.aeris = aeris;
		parser = new JsonParser();
		normalsCache = new HashMap<String, List<String>>();
	}
	
	@Override
	public List<WeatherLocation> getLocations(WeatherFilter filter, TempUnit unit) {
		String urlStr = createLocationsUrl(filter, unit);
		
		String response = aeris.request(urlStr);
		
		JsonObject root = parser.parse(response).getAsJsonObject();
		
		JsonObject locRoot = getPlace(filter.getLocation()).get("response").getAsJsonArray().get(0).getAsJsonObject().get("loc").getAsJsonObject();
		double lat = locRoot.get("lat").getAsDouble();
		double lon = locRoot.get("long").getAsDouble();
		
		JsonArray locations = root.get("response").getAsJsonArray();
		List<WeatherLocation> result = new ArrayList<WeatherLocation>();
		
		for(JsonElement element: locations) {
			JsonObject loc = element.getAsJsonObject();
			
			JsonObject place = loc.get("place").getAsJsonObject();
			String city = place.get("city").getAsString();
			String country = place.get("country").getAsString();
			
			JsonObject weather = loc.get("ob").getAsJsonObject();
			String weatherDisc = weather.get("weather").getAsString();
			int currentTemp = unit == TempUnit.Celsius ? weather.get("tempC").getAsInt() : weather.get("tempF").getAsInt();
			
			
			String normalLoc = (city.replace(' ', '+').replace('/', '+')  + "," + country);

			JsonObject normalsRoot = parser.parse(getNormals(normalLoc)).getAsJsonObject();
			
			JsonObject period = null;
			JsonObject temp = null;
			try {
				period = normalsRoot.get("response").getAsJsonObject().get("periods").getAsJsonArray().get(0).getAsJsonObject();
			}
			catch(IllegalStateException ise){ }
			
			if(period != null)
				temp = period.get("temp").getAsJsonObject();
			
			int avgLow = -1000;
			int avgHigh = -1000;
			
			if(temp != null) {
				avgLow = (int) (unit == TempUnit.Celsius ? temp.get("minC").getAsDouble() : temp.get("minF").getAsDouble());
				avgHigh = (int) (unit == TempUnit.Celsius ? temp.get("maxC").getAsDouble() : temp.get("maxF").getAsDouble());
				
			}
			
			double locLat = loc.get("loc").getAsJsonObject().get("lat").getAsDouble();
			double locLong = loc.get("loc").getAsJsonObject().get("long").getAsDouble();
			
			int distance = UnitConversion.degDist(lat, lon, locLat, locLong);
			
			WeatherIcon icon = WeatherIcon.fromWeatherCode(weather.get("weatherPrimaryCoded").getAsString());
			
			result.add(new WeatherLocation(city, country, weatherDisc, icon.getStaticPath(), icon.getAnimatePath(), avgLow, avgHigh, currentTemp, unit, distance, false));
			
			if(result.size() >= filter.getMaxResults())
				break;
		}
		return result;
	}
	
	private String getNormals(String loc) {
		String url = String.format(normalsUrl, loc) + "&filter=monthly";
		String response = aeris.request(url);
		return response;
	}
	
	private String createLocationsUrl(WeatherFilter filter, TempUnit unit) {
		StringBuilder params = new StringBuilder(locationsUrl);
		
		params.append(String.format("&p=%s", filter.getLocation()));
		params.append(String.format("&limit=%d", filter.getMaxResults()));
		
		if(unit == TempUnit.Fahrenheit)
			params.append(String.format("&query=temp:%d:%d", UnitConversion.fToC(filter.getTempMin()), UnitConversion.fToC(filter.getTempMax())));
		else
			params.append(String.format("&query=temp:%d:%d", filter.getTempMin(), filter.getTempMax()));
		
		switch(filter.getPrecipType()) {
		case Rainy:
			params.append("&filter=wxrain,allstations");
			break;
		case Snowing:
			params.append("&filter=wxsnow,allstations");
			break;
		case Storming:
			params.append("&filter=wxrain,allstations");
			break;
		default:
			params.append("&filter=allstations");
			break;
		}
		
		return params.toString();
	}

	private JsonObject getPlace(String name) {
		String url = placesUrl;
		url += String.format("&p=%s", name);
		String response = aeris.request(url);
		return parser.parse(response).getAsJsonObject();
	}
	
	@Override
	public List<MonthTemp> getHistoricalWeather(String loc, TempUnit unit) {
		List<String> normalsResponses = getNormalsResponses(loc);
		List<MonthTemp> historical = new ArrayList<MonthTemp>();
		
		for(int i = 0; i < 12; i++) {
			JsonObject root = parser.parse(normalsResponses.get(i)).getAsJsonObject();
			JsonObject period = root.get("response").getAsJsonObject().get("periods").getAsJsonArray().get(0).getAsJsonObject();
			JsonObject temp = period.get("temp").getAsJsonObject();
			Month month = Month.values()[i];
			int avgLow = (int) (unit == TempUnit.Celsius ? temp.get("minC").getAsDouble() : temp.get("minF").getAsDouble());
			int avgHigh = (int) (unit == TempUnit.Celsius ? temp.get("maxC").getAsDouble() : temp.get("maxF").getAsDouble());
			historical.add(new MonthTemp(month, avgLow, avgHigh));
		}
		
		return historical;
	}
	
	private List<String> getNormalsResponses(String loc){
		if(normalsCache.containsKey(loc))
			return normalsCache.get(loc);
		
		final Map<Integer, String> requests = new HashMap<Integer, String>();
		for(int i = 1; i <= 12; i++) {
			requests.put(i, String.format(normalsUrl, loc) + String.format("&filter=monthly&from=2019/%02d/01", i));
		}
		
		final String[] results = new String[12];
		
		requests.keySet().parallelStream().forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer month) {
				results[month - 1] = aeris.request(requests.get(month));
			}
		});
		
		List<String> normals = Arrays.asList(results);
		normalsCache.put(loc, normals);
		return Arrays.asList(results);
	}
}
