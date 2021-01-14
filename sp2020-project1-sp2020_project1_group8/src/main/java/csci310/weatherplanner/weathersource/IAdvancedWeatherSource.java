package csci310.weatherplanner.weathersource;

import java.util.List;

public interface IAdvancedWeatherSource {
	List<WeatherLocation> getLocations(WeatherFilter filter, TempUnit unit);
	List<MonthTemp> getHistoricalWeather(String loc, TempUnit unit);
}
