package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeatherFilterTest {
	private final String loc = "Atlanta, GA";
	private final int tempMax = 70;
	private final int tempMin = 50;
	private PrecipType precipType = PrecipType.Clear;
	private final int maxResults = 10;
	
	private WeatherFilter testWeatherFilter;
	
	@Before
	public void setUp() {
		testWeatherFilter = new WeatherFilter(loc, tempMax, tempMin, precipType, maxResults);
	}

	@Test
	public void testGetPrecipType() {
		assertEquals(testWeatherFilter.getPrecipType(), precipType);
	}
	
	@Test
	public void testGetLocation() {
		assertEquals(testWeatherFilter.getLocation(), loc);
	}

	@Test
	public void testGetTempMax() {
		assertEquals(testWeatherFilter.getTempMax(), tempMax);
	}

	@Test
	public void testGetTempMin() {
		assertEquals(testWeatherFilter.getTempMin(), tempMin);
	}

	@Test
	public void testGetMaxResults() {
		assertEquals(testWeatherFilter.getMaxResults(), maxResults);
	}

}
