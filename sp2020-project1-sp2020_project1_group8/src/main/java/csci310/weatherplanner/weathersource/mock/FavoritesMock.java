package csci310.weatherplanner.weathersource.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import csci310.weatherplanner.weathersource.*;

public class FavoritesMock implements IFavoritesManager{
	private final Set<String> mockStore;
	private final HttpSession validSession;
	
	public FavoritesMock(HttpSession validSession, Set<String> favoriteLocations) {
		this.validSession = validSession;
		mockStore = favoriteLocations;
	}
	
	@Override
	public List<String> getFavorites(HttpSession session) {
		if(session != validSession)
			return new ArrayList<String>();
		
		return new ArrayList<String>(mockStore);
	}

	@Override
	public void labelFavorites(HttpSession session, List<WeatherLocation> locations) {
		labelFavorites(session, locations.toArray(new WeatherLocation[locations.size()]));
	}

	@Override
	public void labelFavorites(HttpSession session, WeatherLocation... locations) {	
		if(session != validSession)
			return;
		
		for(WeatherLocation location: locations) {
			String locId = location.getCity() + "_" + location.getCountry();
			if(mockStore.contains(locId)) {
				location.setFavorite(true);
			}
		}
	}

	@Override
	public boolean isFavorite(HttpSession session, String loc) {
		if(session != validSession)
			return false;
		
		return mockStore.contains(loc);
	}

	@Override
	public boolean addFavorite(HttpSession session, String loc) {
		if(session != validSession)
			return false;
		
		return mockStore.add(loc);
	}

	@Override
	public boolean removeFavorite(HttpSession session, String loc) {
		if(session != validSession)
			return false;
		
		return mockStore.remove(loc);
	}

}
