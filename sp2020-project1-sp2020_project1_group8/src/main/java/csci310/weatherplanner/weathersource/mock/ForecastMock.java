package csci310.weatherplanner.weathersource.mock;
import java.util.ArrayList;
import java.util.List;

import csci310.weatherplanner.weathersource.*;

public class ForecastMock implements IForecastSource{
	private final WeatherLocation currentWeather;
	private final List<WeatherLocation> currentWeathers;
	private final List<DailyForecast> forecast;
	
	public ForecastMock(WeatherLocation currentWeather, List<WeatherLocation> currentWeathers, List<DailyForecast> forecast) {
		this.currentWeather = currentWeather;
		this.currentWeathers = currentWeathers;
		this.forecast = forecast;
	}
	
	@Override
	public WeatherLocation getCurrentWeather(String loc, TempUnit unit) {
		return currentWeather;
		//return new WeatherLocation("Atlanta", "US", "Light rain", "/light-rain.png", "/light-rain-animate.gif",
		//		42, 63, 56, unit, 512, false);
	}

	@Override
	public List<WeatherLocation> getCurrentWeather(List<String> locs, TempUnit unit) {
		return currentWeathers;
	}

	@Override
	public List<DailyForecast> getForecast(String loc, TempUnit unit) {
		return forecast;
	}

}
