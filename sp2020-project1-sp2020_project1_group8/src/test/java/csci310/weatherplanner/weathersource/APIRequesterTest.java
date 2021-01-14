package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class APIRequesterTest {
	APIRequester testAPIRequester;

	@Before
	public void setUp() throws Exception {
		// Accesses test json url
		testAPIRequester = new APIRequester("https://jsonplaceholder.typicode.com");
	}

	@Test
	public void testRequestValid() {
		String response = testAPIRequester.request("/todos/1");
		String expected = "{  \"userId\": 1,  \"id\": 1,  \"title\": \"delectus aut autem\",  \"completed\": false}";
		assertEquals(expected, response);
	}
	
	@Test
	public void testRequestInvalidBase() {
		testAPIRequester = new APIRequester("https://website.doesnotexist");
		String response = testAPIRequester.request("/resource");
		assertNull(response);
	}

	@Test
	public void testRequestInvalidEndpoint() {
		String response = testAPIRequester.request("/doesnotexist");
		assertNull(response);
	}
}
