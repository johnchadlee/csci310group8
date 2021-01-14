package csci310.weatherplanner.weathersource;

import java.util.List;

public interface IForecastSource {
	WeatherLocation getCurrentWeather(String loc, TempUnit unit);
	List<WeatherLocation> getCurrentWeather(List<String> locs, TempUnit unit);
	List<DailyForecast> getForecast(String loc, TempUnit unit);
}
