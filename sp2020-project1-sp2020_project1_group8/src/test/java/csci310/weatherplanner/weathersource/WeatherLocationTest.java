package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeatherLocationTest {
	private final String city = "Atlanta";
	private final String country = "US";
	private final String weather = "Clear";
	private final String weatherGraphicStatic = "/clear.jpg";
	private final String weatherGraphicAnimate = "/clear-animate.gif";
	private final int avgMinTemp = 30; 
	private final int avgMaxTemp = 50;
	private final int currentTemp = 45;
	private final TempUnit tempUnit = TempUnit.Celsius;
	private final int distance = 43;
	private final boolean isFavorite = true;
	
	private WeatherLocation testLocation;
	
	@Before
	public void createWeatherLocation(){
		testLocation = new WeatherLocation(city, country, weather, weatherGraphicStatic, weatherGraphicAnimate,
				avgMinTemp, avgMaxTemp, currentTemp, tempUnit, distance, isFavorite);
	}

	@Test
	public void testIsFavorite() {
		assertEquals(testLocation.isFavorite(), true);
	}

	@Test
	public void testSetFavorite() {
		testLocation.setFavorite(true);
		assertEquals(testLocation.isFavorite(), true);
		
		testLocation.setFavorite(false);
		assertEquals(testLocation.isFavorite(), false);
	}

	@Test
	public void testGetCity() {
		assertEquals(testLocation.getCity(), city);
	}

	@Test
	public void testGetCountry() {
		assertEquals(testLocation.getCountry(), country);
	}

	@Test
	public void testGetWeather() {
		assertEquals(testLocation.getWeather(), weather);
	}

	@Test
	public void testGetWeatherGraphicStatic() {
		assertEquals(testLocation.getWeatherGraphicStatic(), weatherGraphicStatic);
	}

	@Test
	public void testGetWeatherGraphicAnimate() {
		assertEquals(testLocation.getWeatherGraphicAnimate(), weatherGraphicAnimate);
	}

	@Test
	public void testGetAvgMinTemp() {
		assertEquals(testLocation.getAvgMinTemp(), avgMinTemp);
	}

	@Test
	public void testGetAvgMaxTemp() {
		assertEquals(testLocation.getAvgMaxTemp(), avgMaxTemp);
	}

	@Test
	public void testGetCurrentTemp() {
		assertEquals(testLocation.getCurrentTemp(), currentTemp);
	}

	@Test
	public void testGetTempUnit() {
		assertEquals(testLocation.getTempUnit(), tempUnit);
	}

	@Test
	public void testGetDistance() {
		assertEquals(testLocation.getDistance(), distance);
	}
	
	@Test
	public void testEquals() {
		WeatherLocation secondLocation = new WeatherLocation(city, country, weather, weatherGraphicStatic, weatherGraphicAnimate,
				avgMinTemp, avgMaxTemp, currentTemp, tempUnit, distance, isFavorite);
		
		assertEquals(secondLocation, testLocation);
	}
	
	@Test
	public void testNotEquals() {
		WeatherLocation secondLocation = new WeatherLocation(city, country, weather, weatherGraphicStatic, weatherGraphicAnimate,
				avgMinTemp, avgMaxTemp, currentTemp, tempUnit, 20, isFavorite);
		
		assertNotEquals(secondLocation, testLocation);
	}
}
