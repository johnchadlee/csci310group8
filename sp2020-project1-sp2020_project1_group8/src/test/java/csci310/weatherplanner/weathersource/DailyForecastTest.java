package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DailyForecastTest {
	private final Date date = new Date(Month.Apr, 5);
	private final String weatherIcon = "/clear.png";
	private final int low = 50;
	private final int high = 80;
	private final TempUnit tempUnit = TempUnit.Fahrenheit;
	
	private DailyForecast testDailyForecast;

	@Before
	public void createTestDailyForecast() {
		testDailyForecast = new DailyForecast(date, weatherIcon, low, high, tempUnit);
	}
	
	@Test
	public void testGetDate() {
		assertEquals(testDailyForecast.getDate(), date);
	}

	@Test
	public void testGetWeatherIcon() {
		assertEquals(testDailyForecast.getWeatherIcon(), weatherIcon);
	}

	@Test
	public void testGetLow() {
		assertEquals(testDailyForecast.getLow(), low);
	}

	@Test
	public void testGetHigh() {
		assertEquals(testDailyForecast.getHigh(), high);
	}

	@Test
	public void testGetTempUnit() {
		assertEquals(testDailyForecast.getTempUnit(), tempUnit);
	}
	
	@Test
	public void testEquals() {
		DailyForecast other = new DailyForecast(date, weatherIcon, low, high, tempUnit);
		assertEquals(testDailyForecast, other);
	}
	
	@Test
	public void testNotEquals() {
		DailyForecast other = new DailyForecast(date, weatherIcon, low, high + 1, tempUnit);
		assertNotEquals(testDailyForecast, other);
	}
}
