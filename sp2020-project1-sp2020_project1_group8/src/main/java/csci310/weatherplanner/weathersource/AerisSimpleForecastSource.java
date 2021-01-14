package csci310.weatherplanner.weathersource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class AerisSimpleForecastSource implements IForecastSource {
	private final static String clientId = "rLHlJuTq0vq2Q2Y7xPn0Q";
	private final static String clientSecret = "fK4BUjGftMkWajEzVAsCXX2VRqzGo9yhcmxo6pYg";
	private final static String locationsUrl = "/observations/closest?format=json&client_id=" + clientId + "&client_secret=" + clientSecret;
	private final static String forecastUrl = "/forecasts/%s?format=json&client_id=" + clientId + "&client_secret=" + clientSecret;

	
	private IExternalAPI aeris;
	private JsonParser parser;
	
	public AerisSimpleForecastSource(IExternalAPI aeris) {
		this.aeris = aeris;
		parser = new JsonParser();
	}

	@Override
	public WeatherLocation getCurrentWeather(String loc, TempUnit unit) {
		String url = locationsUrl + String.format("&p=%s", loc);
		String response = aeris.request(url);
		if(response == null)
			return null;
		
		JsonObject root = parser.parse(response).getAsJsonObject();
		JsonArray locations = root.get("response").getAsJsonArray();
		
		if(locations.size() == 0)
			return null;
		
		JsonObject location = locations.get(0).getAsJsonObject();
		
		JsonObject place = location.get("place").getAsJsonObject();
		String city = place.get("city").getAsString();
		String country = place.get("country").getAsString();
		
		JsonObject weather = location.get("ob").getAsJsonObject();
		String weatherDisc = weather.get("weather").getAsString();
		int currentTemp = (int) (unit == TempUnit.Celsius ? weather.get("tempC").getAsDouble() : weather.get("tempF").getAsDouble());
		int avgHigh = -1;
		int avgLow = -1;
		
		double locLat = location.get("loc").getAsJsonObject().get("lat").getAsDouble();
		double locLong = location.get("loc").getAsJsonObject().get("long").getAsDouble();
		
		WeatherIcon icon = WeatherIcon.fromWeatherCode(weather.get("weatherCoded").getAsString());
		
	    return new WeatherLocation(city, country, weatherDisc, icon.getStaticPath(), 
	    		icon.getAnimatePath(), avgLow, avgHigh, currentTemp, unit, 0, false);
	}

	@Override
	public List<WeatherLocation> getCurrentWeather(List<String> locs, TempUnit unit) {
		List<WeatherLocation> results = new ArrayList<WeatherLocation>();
		
		for(String loc: locs)
			results.add(getCurrentWeather(loc, unit));
		
		return results;
	}

	@Override
	public List<DailyForecast> getForecast(String loc, TempUnit unit) {
		String url = String.format(forecastUrl, loc);
		String result = aeris.request(url);
		
		if(result == null)
			return null;
		
		JsonObject root = parser.parse(result).getAsJsonObject();
		JsonArray response = root.get("response").getAsJsonArray();
		
		if(response.size() == 0)
			return null;
		
		JsonArray forecast = response.get(0).getAsJsonObject().get("periods").getAsJsonArray();
		
		List<DailyForecast> dailyForecast = new ArrayList<DailyForecast>();
		
		for(JsonElement element: forecast) {
			JsonObject day = element.getAsJsonObject();
			DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date javaDate = null;
			try {
				javaDate = isoFormat.parse(day.get("validTime").getAsString().substring(0, 19));
			} catch (ParseException e) { }
			
			csci310.weatherplanner.weathersource.Date date = 
					new csci310.weatherplanner.weathersource.Date(Month.values()[javaDate.getMonth()], javaDate.getDay());
			
			String weatherIcon = WeatherIcon.fromWeatherCode(day.get("weatherPrimaryCoded").getAsString()).getStaticPath();
			
			int low = unit == TempUnit.Celsius ? day.get("minTempC").getAsInt() : day.get("minTempF").getAsInt();
			int high = unit == TempUnit.Celsius ? day.get("maxTempC").getAsInt() : day.get("maxTempF").getAsInt();
			
			dailyForecast.add(new DailyForecast(date, weatherIcon, low, high, unit));
		}
		
		
		return dailyForecast.subList(0, 5);
	}
}
