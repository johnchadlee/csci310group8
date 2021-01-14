package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MonthTempTest {
	private final Month month = Month.Feb;
	private final int avgLow = 45;
	private final int avgHigh = 79;
	
	private MonthTemp testMonthTemp;

	@Before
	public void createMonthTemp() {
		testMonthTemp = new MonthTemp(month, avgLow, avgHigh);
	}
	
	@Test
	public void testGetMonth() {
		assertEquals(testMonthTemp.getMonth(), month);
	}

	@Test
	public void testGetAvgLow() {
		assertEquals(testMonthTemp.getAvgLow(), avgLow);
	}

	@Test
	public void testGetAvgHigh() {
		assertEquals(testMonthTemp.getAvgHigh(), avgHigh);
	}

	@Test
	public void testEquals() {
		MonthTemp otherMonth = new MonthTemp(Month.Feb, 45, 79);
		assertEquals(otherMonth, testMonthTemp);
	}
	
	@Test
	public void testNotEquals() {
		MonthTemp otherMonth1 = new MonthTemp(Month.Mar, 45, 79);
		MonthTemp otherMonth2 = new MonthTemp(Month.Feb, 44, 79);
		MonthTemp otherMonth3 = new MonthTemp(Month.Feb, 45, 78);
		assertNotEquals(otherMonth1, testMonthTemp);
		assertNotEquals(otherMonth2, testMonthTemp);
		assertNotEquals(otherMonth3, testMonthTemp);
	}
}
