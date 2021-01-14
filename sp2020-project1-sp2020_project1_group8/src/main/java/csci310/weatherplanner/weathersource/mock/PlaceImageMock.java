package csci310.weatherplanner.weathersource.mock;

import java.util.ArrayList;
import java.util.List;

import csci310.weatherplanner.weathersource.*;

public class PlaceImageMock implements IPlaceImageSource{
	private final List<String> images;
	
	public PlaceImageMock(List<String> images) {
		this.images = images;
	}
	
	@Override
	public List<String> getImages(String loc) {
		return images;
	}
}
