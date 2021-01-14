package csci310.weatherplanner.weathersource;
import java.util.List;

public interface ILocationFinder {
	List<String> getAutocomplete(String str);
	WeatherFilter getLocations(String location, String activity, int maxResults);
}
