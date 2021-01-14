package csci310.weatherplanner.weathersource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import csci310.weatherplanner.endpoints.LocationsByActivityServlet;
import csci310.weatherplanner.endpoints.mock.MockRequest;
import csci310.weatherplanner.endpoints.mock.MockResponse;
import csci310.weatherplanner.weathersource.mock.AdvancedWeatherMock;
import csci310.weatherplanner.weathersource.mock.FavoritesMock;
import csci310.weatherplanner.weathersource.mock.ForecastMock;
import csci310.weatherplanner.weathersource.mock.MockSession;
import csci310.weatherplanner.weathersource.mock.PlaceImageMock;

@WebServlet("/locationsbyactivity")
public class LocationsByActivityServletTest {
	private HttpSession session;
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
	
	private LocationsByActivityServlet servlet;

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
		session = new MockSession("123ABC");
		
		servlet = new LocationsByActivityServlet(testWeatherSource);
	}
	
	@Test
	public void testSuccessfulGet() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {"Aquarium"});
		parameters.put("loc", new String[] {"Atlanta,us"});
		parameters.put("results", new String[] {"10"});
		parameters.put("unit", new String[] {"Fahrenheit"});
		
		HttpServletRequest request = new MockRequest(session, new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		
		String expected = "[{\"city\":\"Atlanta\",\"country\":\"US\",\"weather\":\"Light rain\",\"weatherGraphicStatic\":\"/light-rain.png\",\"weatherGraphicAnimate\":\"/light-rain-animate.gif\",\"avgMinTemp\":42,\"avgMaxTemp\":63,\"currentTemp\":56,\"tempUnit\":\"Fahrenheit\",\"distance\":512,\"isFavorite\":false},{\"city\":\"Los Angeles\",\"country\":\"US\",\"weather\":\"Sunny\",\"weatherGraphicStatic\":\"/sunny.png\",\"weatherGraphicAnimate\":\"/sunny-animate.gif\",\"avgMinTemp\":62,\"avgMaxTemp\":83,\"currentTemp\":76,\"tempUnit\":\"Fahrenheit\",\"distance\":3,\"isFavorite\":false},{\"city\":\"New York\",\"country\":\"US\",\"weather\":\"Snowing\",\"weatherGraphicStatic\":\"/snow.png\",\"weatherGraphicAnimate\":\"/snow-animate.gif\",\"avgMinTemp\":25,\"avgMaxTemp\":41,\"currentTemp\":30,\"tempUnit\":\"Fahrenheit\",\"distance\":800,\"isFavorite\":false}]";
		
		assertEquals(expected, response.getOutput());
	}
	
	
	@Test
	public void testMissingParameters() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {"Aquarium"});
		parameters.put("loc", new String[] {"Atlanta,us"});
		// missing paramters
//		parameters.put("results", new String[] {"10"});
		parameters.put("unit", new String[] {"Fahrenheit"});
		HttpServletRequest request = new MockRequest(session, new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		
		int expected = 400;
		
		assertEquals(expected, response.getStatus());
	}
	
	@Test
	public void testInvalidParameters() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {"Aquarium"});
		parameters.put("loc", new String[] {"Atlanta,us"});
		// invalid parameter
		parameters.put("results", new String[] {"-1"});
		parameters.put("unit", new String[] {"Fahrenheit"});
		
		HttpServletRequest request = new MockRequest(session, new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		int expected = 400;
			
		assertEquals(expected, response.getStatus());
	}
}
