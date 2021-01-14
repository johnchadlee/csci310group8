package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DetailedForecastTest {
	private WeatherLocation weatherLocation;
	private List<MonthTemp> monthTemps;
	private List<DailyForecast> dailyForecasts;
	private List<String> placeImages;
	
	private DetailedForecast testDetailedForecast;
	
	@Before
	public void setUp() throws Exception {
		// Note: The fields/contents of these objects is irrelevant as
		// 		 the getters are meant to return the same object not an 
		//       object with the same contents
		
		weatherLocation = new WeatherLocation(null, null, null, null, null, 0, 0, 0, null, 0, false);
		monthTemps = new ArrayList<MonthTemp>();
		dailyForecasts = new ArrayList<DailyForecast>();
		placeImages = new ArrayList<String>();
		
		testDetailedForecast = new DetailedForecast(weatherLocation, monthTemps, dailyForecasts, placeImages);
	}

	@Test
	public void testGetWeatherLocation() {
		assertEquals(testDetailedForecast.getWeatherLocation(), weatherLocation);
	}

	@Test
	public void testGetMonthTemps() {
		assertEquals(testDetailedForecast.getMonthTemps(), monthTemps);
	}

	@Test
	public void testGetDailyForecasts() {
		assertEquals(testDetailedForecast.getDailyForecasts(), dailyForecasts);
	}

	@Test
	public void testGetPlaceImages() {
		assertEquals(testDetailedForecast.getPlaceImages(), placeImages);
	}
}
