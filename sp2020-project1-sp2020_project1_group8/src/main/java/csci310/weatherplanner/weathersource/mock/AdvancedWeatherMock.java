package csci310.weatherplanner.weathersource.mock;

import java.util.ArrayList;
import java.util.List;
import csci310.weatherplanner.weathersource.*;

public class AdvancedWeatherMock implements IAdvancedWeatherSource{
	private final List<WeatherLocation> locations;
	private final List<MonthTemp> historical;
	
	
	public AdvancedWeatherMock(List<WeatherLocation> locations, List<MonthTemp> historical) {
		this.locations = locations;
		this.historical = historical;
	}
	
	@Override
	public List<WeatherLocation> getLocations(WeatherFilter filter, TempUnit unit) {
		return locations;
	}

	@Override
	public List<MonthTemp> getHistoricalWeather(String loc, TempUnit unit) {
		return historical;
	}

}
