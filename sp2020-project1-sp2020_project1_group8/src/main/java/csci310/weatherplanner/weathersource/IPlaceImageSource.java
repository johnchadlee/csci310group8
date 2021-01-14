package csci310.weatherplanner.weathersource;

import java.util.List;

public interface IPlaceImageSource {
	List<String> getImages(String loc);
}
