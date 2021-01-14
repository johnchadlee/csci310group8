package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTest {
	private int day = 10;
	private Month month = Month.Mar;
	
	private Date testDate;
	
	@Before
	public void createTestDate() {
		testDate = new Date(month, day);
	}
	
	@Test
	public void testGetMonth() {
		assertEquals(testDate.getMonth(), month);
	}

	@Test
	public void testGetDay() {
		assertEquals(testDate.getDay(), day);
	}
	
	
	@Test
	public void testEquals() {
		Date other = new Date(month, day);
		assertEquals(testDate, other);
	}
	
	@Test
	public void testNotEquals() {
		Date other = new Date(month, day + 1);
		assertNotEquals(testDate, other);
	}
}
