package csci310.weatherplanner.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitConversionTest {

	@Test
	public void testFToC() {
		// Correct answers from google's unit conversion tool
		assertEquals(UnitConversion.fToC(32), 0);
		assertEquals(UnitConversion.fToC(-10), -23);
		assertEquals(UnitConversion.fToC(50), 10);
		assertEquals(UnitConversion.fToC(75), 23);
	}

	@Test
	public void testDegDist() {
		// Correct answers NOAA Latitude/Longitude Distance Calculator
		assertEquals(UnitConversion.degDist(50, 80, 20, 30), 3413);
		assertEquals(UnitConversion.degDist(-18, 45, 82, 30), 6922);
		assertEquals(UnitConversion.degDist(10, 30, 10, 30), 0);
	}

}
