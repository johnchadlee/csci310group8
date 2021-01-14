package csci310.weatherplanner.weathersource;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface IFavoritesManager {
	List<String> getFavorites(HttpSession session);
	void labelFavorites(HttpSession session, List<WeatherLocation> locations);
	void labelFavorites(HttpSession session, WeatherLocation... locations);
	boolean isFavorite(HttpSession session, String loc);
	boolean addFavorite(HttpSession session, String loc);
	boolean removeFavorite(HttpSession session, String loc);
}
