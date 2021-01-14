package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.BeforeClass;
import org.junit.Test;

import csci310.weatherplanner.endpoints.ActivityAutocompleteServlet;
import csci310.weatherplanner.endpoints.mock.MockRequest;
import csci310.weatherplanner.endpoints.mock.MockResponse;
import csci310.weatherplanner.weathersource.mock.MockSession;

public class ActivityAutocompleteServletTest {
	private static ActivityAutocompleteServlet servlet;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		servlet = new ActivityAutocompleteServlet();
	}

	@Test
	public void testSuccessfulSuggestions() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {"S"});
		
		HttpServletRequest request = new MockRequest(new MockSession("ABC"), new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		
		String expected = "[\"snowboarding\",\"skydiving\",\"sking\",\"skateboarding\",\"surfing\",\"swimming\",\"scuba diving\",\"soccer\"]";
		assertEquals(expected, response.getOutput());
	}
	
	@Test
	public void noSuggestions() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {"xxx"});
		
		HttpServletRequest request = new MockRequest(new MockSession("ABC"), new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		
		String expected = "[]";
		assertEquals(expected, response.getOutput());
	}
	
	@Test 
	public void emptyInputTest() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("activity", new String[] {""});
		
		HttpServletRequest request = new MockRequest(new MockSession("ABC"), new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		
		String expected = "[]";
		assertEquals(expected, response.getOutput());
	}
	@Test 
	public void missingParameterTest() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		
		HttpServletRequest request = new MockRequest(new MockSession("ABC"), new HashMap<String, Object>(), parameters, "");
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
