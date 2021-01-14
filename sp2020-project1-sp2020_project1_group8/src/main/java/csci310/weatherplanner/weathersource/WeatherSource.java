package csci310.weatherplanner.weathersource;

import java.util.List;

import javax.servlet.http.HttpSession;

public class WeatherSource {
	private final IForecastSource forecastSource;
	private final IAdvancedWeatherSource advForecastSource;
	private final IFavoritesManager favoritesManager;
	private final IPlaceImageSource imageSource;
	
	public WeatherSource(IForecastSource forecastSource, IAdvancedWeatherSource advForecastSource,
			IFavoritesManager favoritesManager, IPlaceImageSource imageSource) {
		this.forecastSource = forecastSource;
		this.advForecastSource = advForecastSource;
		this.favoritesManager = favoritesManager;
		this.imageSource = imageSource;
	}
	
	public WeatherLocation getWeather(HttpSession session, String loc, TempUnit unit) {
		WeatherLocation location = forecastSource.getCurrentWeather(loc, unit);
		favoritesManager.labelFavorites(session, location);
		return forecastSource.getCurrentWeather(loc, unit);
	}
	
	public List<WeatherLocation> getLocations(HttpSession session, WeatherFilter filter, TempUnit unit){
		List<WeatherLocation> locations  = advForecastSource.getLocations(filter, unit);
		favoritesManager.labelFavorites(session, locations);
		return locations;
	}
	
	public List<WeatherLocation> getLocations(HttpSession session, List<String> locs, TempUnit unit){
		List<WeatherLocation> locations = forecastSource.getCurrentWeather(locs, unit);
		favoritesManager.labelFavorites(session, locations);
		return locations;
	}
	
	public DetailedForecast getDetailedForecast(HttpSession session, String loc, TempUnit unit) {
		WeatherLocation current = forecastSource.getCurrentWeather(loc, unit);
		favoritesManager.labelFavorites(session, current);
		List<MonthTemp> historic = advForecastSource.getHistoricalWeather(loc, unit);
		List<DailyForecast> daily = forecastSource.getForecast(loc, unit);
		List<String> placeImages = imageSource.getImages(loc);
		
		return new DetailedForecast(current, historic, daily, placeImages);
	}
}
