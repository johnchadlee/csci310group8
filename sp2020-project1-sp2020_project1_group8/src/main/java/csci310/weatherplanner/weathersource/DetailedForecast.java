package csci310.weatherplanner.weathersource;

import java.util.List;

public class DetailedForecast {
	private final WeatherLocation weatherLocation;
	private final List<MonthTemp> monthTemps;
	private final List<DailyForecast> dailyForecasts;
	private final List<String> placeImages;
	
	public DetailedForecast(WeatherLocation weatherLocation, List<MonthTemp> monthTemps,
			List<DailyForecast> dailyForecasts, List<String> placeImages) {
		this.weatherLocation = weatherLocation;
		this.monthTemps = monthTemps;
		this.dailyForecasts = dailyForecasts;
		this.placeImages = placeImages;
	}
	
	public WeatherLocation getWeatherLocation() {
		return weatherLocation;
	}
	
	public List<MonthTemp> getMonthTemps() {
		return monthTemps;
	}
	
	public List<DailyForecast> getDailyForecasts() {
		return dailyForecasts;
	}
	
	public List<String> getPlaceImages() {
		return placeImages;
	}
	
	
}
