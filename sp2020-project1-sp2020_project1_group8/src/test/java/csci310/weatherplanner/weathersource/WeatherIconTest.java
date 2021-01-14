package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherIconTest {

	@Test
	public void testFromWeatherCodeSunny() {
		assertEquals(WeatherIcon.Sunny, WeatherIcon.fromWeatherCode("::CL"));
		assertEquals(WeatherIcon.Sunny, WeatherIcon.fromWeatherCode("::FW"));
	}
	
	@Test
	public void testFromWeatherCodeCloudy() {
		assertEquals(WeatherIcon.Cloudy, WeatherIcon.fromWeatherCode("::SC"));
		assertEquals(WeatherIcon.Cloudy, WeatherIcon.fromWeatherCode("::BK"));
		assertEquals(WeatherIcon.Cloudy, WeatherIcon.fromWeatherCode("::OV"));
	}
	
	@Test
	public void testFromWeatherCodeRaining() {
		assertEquals(WeatherIcon.Raining, WeatherIcon.fromWeatherCode("X:X:BY"));
		assertEquals(WeatherIcon.Raining, WeatherIcon.fromWeatherCode("X:X:L"));
		assertEquals(WeatherIcon.Raining, WeatherIcon.fromWeatherCode("X:X:R"));
		assertEquals(WeatherIcon.Raining, WeatherIcon.fromWeatherCode("X:X:RW"));
		assertEquals(WeatherIcon.Raining, WeatherIcon.fromWeatherCode("X:X:RS"));
	}
	
	@Test
	public void testFromWeatherCodeStorming() {
		assertEquals(WeatherIcon.Storming, WeatherIcon.fromWeatherCode("X:X:T"));
	}
	
	@Test
	public void testFromWeatherCodeSnowing() {
		assertEquals(WeatherIcon.Snowing, WeatherIcon.fromWeatherCode("X:X:WM"));
		assertEquals(WeatherIcon.Snowing, WeatherIcon.fromWeatherCode("X:X:S"));
		assertEquals(WeatherIcon.Snowing, WeatherIcon.fromWeatherCode("X:X:SW"));
	}
	
	@Test
	public void testFromWeatherCodeInvalidCode() {
		boolean failed = false;
		try {
			WeatherIcon.fromWeatherCode("ABC");
		}
		catch(IllegalArgumentException iae) {
			failed = true;
		}
		assertTrue(failed);
	}
}
