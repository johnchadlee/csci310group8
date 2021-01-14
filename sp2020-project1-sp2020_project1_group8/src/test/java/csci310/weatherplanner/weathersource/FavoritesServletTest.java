package csci310.weatherplanner.weathersource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import csci310.weatherplanner.endpoints.FavoritesServlet;
import csci310.weatherplanner.endpoints.mock.MockRequest;
import csci310.weatherplanner.endpoints.mock.MockResponse;
import csci310.weatherplanner.weathersource.mock.FavoritesMock;
import csci310.weatherplanner.weathersource.mock.MockSession;

public class FavoritesServletTest {
	private IFavoritesManager favoritesManager;
	private HashSet<String> favoritesManagerFavorites;
	private HttpSession favoritesManagerSession;
	private FavoritesServlet servlet;

	@Before
	public void initMockData() {
		favoritesManagerFavorites = new HashSet<String>();
		favoritesManagerFavorites.add("Atlanta_US");
		favoritesManagerFavorites.add("New York_US");
		favoritesManagerSession = new MockSession("321abc");
	}
	
	@Before
	public void setUp() {
		favoritesManager = new FavoritesMock(favoritesManagerSession, favoritesManagerFavorites);
		servlet = new FavoritesServlet(favoritesManager);
	}
	
	@Test
	public void testSuccessfulGet() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();

		HttpServletRequest request = new MockRequest(favoritesManagerSession, new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doGet(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		String expected = "[\"Atlanta_US\",\"New York_US\"]";
		
		assertEquals(expected, response.getOutput());
	}
	@Test
	public void testSuccessfulPost() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		String city = ("Los Angeles_US");
		HttpServletRequest request = new MockRequest(favoritesManagerSession, new HashMap<String, Object>(), parameters, city);
		MockResponse response = new MockResponse();
		
		try {
			servlet.doPost(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		String expected = "[\"Atlanta_US\",\"New York_US\",\"Los Angeles_US\"]";
		assertEquals(expected, response.getOutput());
	}
	@Test
	public void testSuccessfulDelete() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		String city = ("New York_US");
		HttpServletRequest request = new MockRequest(favoritesManagerSession, new HashMap<String, Object>(), parameters, city);
		MockResponse response = new MockResponse();
		
		try {
			servlet.doDelete(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		String expected = "[\"Atlanta_US\"]";
		assertEquals(expected, response.getOutput());
	}
	@Test
	public void testMissingBody() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		HttpServletRequest request = new MockRequest(favoritesManagerSession, new HashMap<String, Object>(), parameters, "");
		MockResponse response = new MockResponse();
		
		try {
			servlet.doPost(request, response);
		} catch (ServletException e) {
			fail("ServletException thrown!");
		} catch (IOException e) {
			fail("IOException thrown!");
		}
		int expected = 400;
		assertEquals(expected, response.getStatus());
	}
}
