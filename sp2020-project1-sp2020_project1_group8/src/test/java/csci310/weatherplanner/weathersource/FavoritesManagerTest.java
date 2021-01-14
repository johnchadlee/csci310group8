package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import csci310.weatherplanner.weathersource.mock.MockSession;

public class FavoritesManagerTest {
	private FavoritesManager favoritesManager;
	private HttpSession session1;
	private HttpSession session2;
	
	@Before
	public void setUp() {
		session1 = new MockSession("1");
		session2 = new MockSession("2");
		
		favoritesManager = new FavoritesManager();
	}
	
	@Test
	public void testGetFavorites() {
		List<String> favoriteLocations = new ArrayList<String>();
		favoriteLocations.add("Atlanta_US");
		favoriteLocations.add("New York City_US");
		
		for(String loc: favoriteLocations)
			favoritesManager.addFavorite(session1, loc);
		
		List<String> result = favoritesManager.getFavorites(session1);
		
		assertNotNull(result);
		assertEquals(favoriteLocations, result);
	}
	
	@Test
	public void testGetFavoritesNullSession() {
		List<String> favoriteLocations = new ArrayList<String>();
		favoriteLocations.add("Atlanta_US");
		favoriteLocations.add("New York City_US");
		
		for(String loc: favoriteLocations)
			favoritesManager.addFavorite(session1, loc);
		
		List<String> result = favoritesManager.getFavorites(null);
		
		// Should return an empty list
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testLabelFavorites() {
		List<WeatherLocation> locations = new ArrayList<WeatherLocation>();
		locations.add(new WeatherLocation("Atlanta", "US", "Light rain", "/light-rain.png", "/light-rain-animate.gif",
				42, 63, 56, TempUnit.Fahrenheit, 512, false));
		locations.add(new WeatherLocation("Los Angeles", "US", "Sunny", "/sunny.png", "/sunny-animate.gif",
				62, 83, 76, TempUnit.Fahrenheit, 3, false));
		locations.add(new WeatherLocation("New York", "US", "Snowing", "/snow.png", "/snow-animate.gif",
				25, 41, 30, TempUnit.Fahrenheit, 800, false));
		
		
		favoritesManager.addFavorite(session1, "Atlanta_US");
		favoritesManager.addFavorite(session1, "New York_US");
		
		
		favoritesManager.labelFavorites(session1, locations);
		
		// Atlanta is a favorite
		assertTrue(locations.get(0).isFavorite());
		
		// Los Angeles is not a favorite
		assertFalse(locations.get(1).isFavorite());
		
		// New York is a favorite
		assertTrue(locations.get(2).isFavorite());
	}

	@Test
	public void testIsFavoriteTrue() {
		favoritesManager.addFavorite(session1, "Atlanta_US");
		
		assertTrue(favoritesManager.isFavorite(session1, "Atlanta_US"));
	}
	
	@Test
	public void testIsFavoriteFalse() {
		assertFalse(favoritesManager.isFavorite(session1, "Atlanta_US"));
	}
	
	public void testIsFavoriteNullSession() {
		favoritesManager.addFavorite(session1, "Atlanta_US");
		
		assertFalse(favoritesManager.isFavorite(null, "Atlanta_US"));
	}

	@Test
	public void testAddFavorite() {
		assertFalse(favoritesManager.isFavorite(session1, "Atlanta_US"));
		assertFalse(favoritesManager.isFavorite(session1, "New York_US"));
		
		assertFalse(favoritesManager.isFavorite(session2, "Atlanta_US"));
		assertFalse(favoritesManager.isFavorite(session2, "Los Angeles_US"));
		
		favoritesManager.addFavorite(session1, "Atlanta_US");
		favoritesManager.addFavorite(session1, "New York_US");
		
		favoritesManager.addFavorite(session2, "Atlanta_US");
		favoritesManager.addFavorite(session2, "Los Angeles_US");
		
		assertTrue(favoritesManager.isFavorite(session1, "Atlanta_US"));
		assertTrue(favoritesManager.isFavorite(session1, "New York_US"));
		
		assertTrue(favoritesManager.isFavorite(session2, "Atlanta_US"));
		assertTrue(favoritesManager.isFavorite(session2, "Los Angeles_US"));
	}
	
	@Test
	public void testAddFavoriteTrue() {
		// Adding a favorite is idempotent
		assertTrue(favoritesManager.addFavorite(session1, "Atlanta_US"));
		assertTrue(favoritesManager.addFavorite(session1, "Atlanta_US"));
	}
	
	@Test
	public void testAddFavoriteNullSession() {		
		assertFalse(favoritesManager.addFavorite(null, "Atlanta_US"));
	}
	
	@Test
	public void testAddFavoriteNullLocation() {		
		assertFalse(favoritesManager.addFavorite(session1, null));
	}

	@Test
	public void testRemoveFavorite() {
		favoritesManager.addFavorite(session1, "Atlanta_US");
		favoritesManager.addFavorite(session1, "New York_US");
		
		favoritesManager.removeFavorite(session1, "Atlanta_US");
		favoritesManager.removeFavorite(session2, "New York_US");
		
		assertFalse(favoritesManager.isFavorite(session1, "Atlanta_US"));
		assertTrue(favoritesManager.isFavorite(session1, "New York_US"));
	}
	
	@Test
	public void testRemoveFavoriteTrue() {
		favoritesManager.addFavorite(session1, "Atlanta_US");
		
		// Removing a favorite is idempotent
		assertTrue(favoritesManager.removeFavorite(session1, "Atlanta_US"));
		assertTrue(favoritesManager.removeFavorite(session1, "Atlanta_US"));
	}
	
	@Test
	public void testRemoveFavoriteNullSession() {
		favoritesManager.addFavorite(session1, "Atlanta_US");

		assertFalse(favoritesManager.removeFavorite(null, "Atlanta_US"));
	}
	
	@Test
	public void testRemoveFavoriteNullLocation() {
		favoritesManager.addFavorite(session1, "Atlanta_US");

		assertFalse(favoritesManager.removeFavorite(session1, null));
	}
}
