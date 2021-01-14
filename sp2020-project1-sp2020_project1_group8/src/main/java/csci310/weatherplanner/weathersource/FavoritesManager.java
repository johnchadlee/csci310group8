package csci310.weatherplanner.weathersource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

public class FavoritesManager implements IFavoritesManager{
	private static FavoritesManager globalManager;
	
	private final Map<String, SortedSet<String>> favoriteStore;
	
	public FavoritesManager() {
		favoriteStore = new HashMap<String, SortedSet<String>>();
	}
	
	public static FavoritesManager getGlobalManager() {
		if(globalManager == null) {
			globalManager = new FavoritesManager();
		}
		
		return globalManager;
	}
	
	@Override
	public List<String> getFavorites(HttpSession session) {	
		if(session == null || !favoriteStore.containsKey(session.getId()))
			return new ArrayList<String>();
		
		return new ArrayList<String>(favoriteStore.get(session.getId()));
	}

	@Override
	public void labelFavorites(HttpSession session, List<WeatherLocation> locations) {
		if(locations == null)
			return;
		
		labelFavorites(session, locations.toArray(new WeatherLocation[locations.size()]));
		
	}

	@Override
	public void labelFavorites(HttpSession session, WeatherLocation... locations) {
		if(session == null || locations == null)
			return;
		
		if(!favoriteStore.containsKey(session.getId()))
			return;
		
		Set<String> favorites = favoriteStore.get(session.getId());
		
		for(WeatherLocation loc: locations)
			loc.setFavorite(favorites.contains(loc.getCity() + "," + loc.getCountry()));
	}

	@Override
	public boolean isFavorite(HttpSession session, String loc) {
		if(!favoriteStore.containsKey(session.getId()))
			return false;
		
		return favoriteStore.get(session.getId()).contains(loc);
	}

	@Override
	public boolean addFavorite(HttpSession session, String loc) {
		if(session == null || loc == null)
			return false;
		
		if(!favoriteStore.containsKey(session.getId()))
			favoriteStore.put(session.getId(), new TreeSet<String>());
		
		favoriteStore.get(session.getId()).add(loc);
		
		return true;
	}

	@Override
	public boolean removeFavorite(HttpSession session, String loc) {
		if(session == null || loc == null)
			return false;
		
		if(!favoriteStore.containsKey(session.getId()))
			favoriteStore.put(session.getId(), new TreeSet<String>());
		
		favoriteStore.get(session.getId()).remove(loc);
		
		return true;
	}

}
