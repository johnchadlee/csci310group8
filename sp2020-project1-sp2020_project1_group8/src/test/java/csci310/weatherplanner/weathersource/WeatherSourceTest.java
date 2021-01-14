package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import csci310.weatherplanner.weathersource.mock.*;

/*
 * Note: Testing the WeatherSource involves making sure it properly utilizes its dependencies.
 *       When testing the WeatherSource, these dependencies are mocked, actual implementations
 *       of these dependencies will be given their own respective tests.
 */
public class WeatherSourceTest {
	private IForecastSource forecastSource;
	private IAdvancedWeatherSource advForecastSource;
	private IFavoritesManager favoritesManager;
	private IPlaceImageSource imageSource;
	
	private WeatherLocation forecastSourceCurrentWeather;
	private List<WeatherLocation> forecastSourceCurrentWeathers;
	private List<DailyForecast> forecastSourceForecast;
	
	private List<WeatherLocation> advForecastSourceLocations;
	private List<MonthTemp> advForecastSourceHistorical;
	
	private Set<String> favoritesManagerFavorites;
	private HttpSession favoritesManagerSession;
	
	private List<String> imageSourceImages;
	
	private WeatherSource testWeatherSource;
	
	@Before
	public void initMockData() {
		// ForecastSource mock data
		forecastSourceCurrentWeather = new WeatherLocation("Atlanta", "US", "Light rain", 
				"/light-rain.png", "/light-rain-animate.gif", 42, 63, 56, TempUnit.Fahrenheit, 512, false);
		
		forecastSourceCurrentWeathers = new ArrayList<WeatherLocation>();
		forecastSourceCurrentWeathers.add(new WeatherLocation("Atlanta", "US", "Light rain", "/light-rain.png", "/light-rain-animate.gif",
				42, 63, 56, TempUnit.Fahrenheit, 512, false));
		forecastSourceCurrentWeathers.add(new WeatherLocation("Los Angeles", "US", "Sunny", "/sunny.png", "/sunny-animate.gif",
				62, 83, 76, TempUnit.Fahrenheit, 3, false));
		forecastSourceCurrentWeathers.add(new WeatherLocation("New York", "US", "Snowing", "/snow.png", "/snow-animate.gif",
				25, 41, 30, TempUnit.Fahrenheit, 800, false));
		
		forecastSourceForecast = new ArrayList<DailyForecast>();
		forecastSourceForecast.add(new DailyForecast(new Date(Month.Mar, 5), "/sunny.png", 42, 67, TempUnit.Fahrenheit));
		forecastSourceForecast.add(new DailyForecast(new Date(Month.Mar, 6), "/light-rain.png", 54, 72, TempUnit.Fahrenheit));
		forecastSourceForecast.add(new DailyForecast(new Date(Month.Mar, 7), "/sunny.png", 51, 63, TempUnit.Fahrenheit));
		forecastSourceForecast.add(new DailyForecast(new Date(Month.Mar, 8), "/sunny.png", 60, 77, TempUnit.Fahrenheit));
		forecastSourceForecast.add(new DailyForecast(new Date(Month.Mar, 9), "/cloudy.png", 41, 62, TempUnit.Fahrenheit));
		
		// AdvForecastSource mock data
		advForecastSourceLocations = new ArrayList<WeatherLocation>();
		advForecastSourceLocations.add(new WeatherLocation("Atlanta", "US", "Light rain", "/light-rain.png", "/light-rain-animate.gif",
				42, 63, 56, TempUnit.Fahrenheit, 512, false));
		advForecastSourceLocations.add(new WeatherLocation("Los Angeles", "US", "Sunny", "/sunny.png", "/sunny-animate.gif",
				62, 83, 76, TempUnit.Fahrenheit, 3, false));
		advForecastSourceLocations.add(new WeatherLocation("New York", "US", "Snowing", "/snow.png", "/snow-animate.gif",
				25, 41, 30, TempUnit.Fahrenheit, 800, false));
		
		advForecastSourceHistorical = new ArrayList<MonthTemp>();
		advForecastSourceHistorical.add(new MonthTemp(Month.Jan, 34, 61));
		advForecastSourceHistorical.add(new MonthTemp(Month.Feb, 38, 65));
		advForecastSourceHistorical.add(new MonthTemp(Month.Mar, 43, 70));
		advForecastSourceHistorical.add(new MonthTemp(Month.Apr, 63, 81));
		advForecastSourceHistorical.add(new MonthTemp(Month.May, 75, 85));
		advForecastSourceHistorical.add(new MonthTemp(Month.Jun, 76, 89));
		advForecastSourceHistorical.add(new MonthTemp(Month.Jul, 78, 93));
		advForecastSourceHistorical.add(new MonthTemp(Month.Aug, 73, 84));
		advForecastSourceHistorical.add(new MonthTemp(Month.Sep, 64, 79));
		advForecastSourceHistorical.add(new MonthTemp(Month.Nov, 56, 65));
		advForecastSourceHistorical.add(new MonthTemp(Month.Dec, 31, 57));
		
		// FavoritesManager mock data
		favoritesManagerFavorites = new HashSet<String>();
		favoritesManagerFavorites.add("Atlanta_US");
		favoritesManagerFavorites.add("New York_US");
		
		imageSourceImages = new ArrayList<String>();
		imageSourceImages.add("https://en.wikipedia.org/wiki/Georgia_State_Capitol#/media/File:Georgia_State_Capitol,_Atlanta,_West_view_20160716_1.jpg");
		imageSourceImages.add("https://en.wikipedia.org/wiki/Piedmont_Park#/media/File:Midtown_HDR_Atlanta.jpg");
		imageSourceImages.add("https://en.wikipedia.org/wiki/World_of_Coca-Cola#/media/File:World_of_Coca_Cola_2015-04-09.jpg");
		
		favoritesManagerSession = new MockSession("abc123");
	}
	
	@Before
	public void setUp() {
		forecastSource = new ForecastMock(forecastSourceCurrentWeather, forecastSourceCurrentWeathers, forecastSourceForecast);;
		advForecastSource = new AdvancedWeatherMock(advForecastSourceLocations, advForecastSourceHistorical);
		favoritesManager = new FavoritesMock(favoritesManagerSession, favoritesManagerFavorites);
		imageSource = new PlaceImageMock(imageSourceImages);
		
		testWeatherSource = new WeatherSource(forecastSource, advForecastSource, favoritesManager, imageSource);
	}
	
	@Test
	public void testGetWeatherNoSession() {
		WeatherLocation result = testWeatherSource.getWeather(null, "Atlanta_US", TempUnit.Fahrenheit);
		assertNotNull(result);
		assertEquals(result.isFavorite(), favoritesManager.isFavorite(null, "Atlanta_US"));
		assertEquals(result, forecastSourceCurrentWeather);
	}
	
	@Test
	public void testGetWeatherNewSession() {
		MockSession newSession = new MockSession("321ABC");
		WeatherLocation result = testWeatherSource.getWeather(newSession, "Atlanta_US", TempUnit.Fahrenheit);
		assertNotNull(result);
		assertEquals(result.isFavorite(), favoritesManager.isFavorite(newSession, "Atlanta_US"));
		assertEquals(result, forecastSourceCurrentWeather);
	}
	
	@Test
	public void testGetWeather() {
		WeatherLocation result = testWeatherSource.getWeather(favoritesManagerSession, "Atlanta_US", TempUnit.Fahrenheit);
		assertNotNull(result);
		assertEquals(result.isFavorite(), favoritesManager.isFavorite(favoritesManagerSession, "Atlanta_US"));
		assertEquals(result, forecastSourceCurrentWeather);
	}

	@Test
	public void testGetLocationsByWeatherFilter() {
		WeatherFilter filter = new WeatherFilter(null, 0, 0, null, 0);
		List<WeatherLocation> locations = testWeatherSource.getLocations(favoritesManagerSession, filter, TempUnit.Fahrenheit);
		assertNotNull(locations);
		assertEquals(locations, advForecastSourceLocations);
	}
	
	@Test
	public void testGetLocationsByWeatherFilterCorrectFavorites() {
		WeatherFilter filter = new WeatherFilter(null, 0, 0, null, 0);
		List<WeatherLocation> locations = testWeatherSource.getLocations(favoritesManagerSession, filter, TempUnit.Fahrenheit);
		assertTrue(favoritesCorrect(locations));
	}
	
	@Test
	public void testGetWeatherByLocations() {
		List<String> locs = new ArrayList<String>();
		locs.add("Atlanta_US");
		locs.add("Los Angeles_US");
		locs.add("New York_US");
		
		List<WeatherLocation> locations = testWeatherSource.getLocations(favoritesManagerSession, locs, TempUnit.Fahrenheit);
		assertNotNull(locations);
		assertEquals(locations, forecastSourceCurrentWeathers);
	}
	
	@Test
	public void testGetWeatherByLocationsCorrectFavorites() {
		List<String> locs = new ArrayList<String>();
		locs.add("Atlanta_US");
		locs.add("Los Angeles_US");
		locs.add("New York_US");
		
		List<WeatherLocation> locations = testWeatherSource.getLocations(favoritesManagerSession, locs, TempUnit.Fahrenheit);
		assertTrue(favoritesCorrect(locations));
	}
	
	@Test
	public void testGetDetailedForecast() {
		DetailedForecast detailedForecast = testWeatherSource.getDetailedForecast(favoritesManagerSession, "Atlanta_US", TempUnit.Fahrenheit);
		assertNotNull(detailedForecast);
		assertEquals(detailedForecast.getWeatherLocation(), forecastSourceCurrentWeather);
		assertEquals(detailedForecast.getMonthTemps(), advForecastSourceHistorical);
		assertEquals(detailedForecast.getDailyForecasts(), forecastSourceForecast);
		assertEquals(detailedForecast.getPlaceImages(), imageSourceImages);
	}
	
	private boolean favoritesCorrect(List<WeatherLocation> locations) {
		if(locations == null) {
			return false;
		}
		
		for(WeatherLocation loc: locations) {
			if(!loc.isFavorite() == favoritesManager.isFavorite(favoritesManagerSession, loc.getCity() + "_" + loc.getCountry())) {
				return false;
			}
		}
		
		return true;
	}
}
