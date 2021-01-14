package csci310.weatherplanner.weathersource;

public class WeatherLocation {
	private final String city;
	private final String country;
	private final String weather;
	private final String weatherGraphicStatic;
	private final String weatherGraphicAnimate;
	private final int avgMinTemp;
	private final int avgMaxTemp;
	private final int currentTemp;
	private final TempUnit tempUnit;
	private final int distance;
	private boolean isFavorite;
	
	public WeatherLocation(String city, String country, String weather, String weatherGraphicStatic,
			String weatherGraphicAnimate, int avgMinTemp, int avgMaxTemp, int currentTemp, TempUnit tempUnit,
			int distance, boolean isFavorite) {
		this.city = city;
		this.country = country;
		this.weather = weather;
		this.weatherGraphicStatic = weatherGraphicStatic;
		this.weatherGraphicAnimate = weatherGraphicAnimate;
		this.avgMinTemp = avgMinTemp;
		this.avgMaxTemp = avgMaxTemp;
		this.currentTemp = currentTemp;
		this.tempUnit = tempUnit;
		this.distance = distance;
		
		this.isFavorite = isFavorite;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getWeather() {
		return weather;
	}

	public String getWeatherGraphicStatic() {
		return weatherGraphicStatic;
	}

	public String getWeatherGraphicAnimate() {
		return weatherGraphicAnimate;
	}

	public int getAvgMinTemp() {
		return avgMinTemp;
	}

	public int getAvgMaxTemp() {
		return avgMaxTemp;
	}

	public int getCurrentTemp() {
		return currentTemp;
	}

	public TempUnit getTempUnit() {
		return tempUnit;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return "WeatherLocation [city=" + city + ", country=" + country + ", weather=" + weather
				+ ", weatherGraphicStatic=" + weatherGraphicStatic + ", weatherGraphicAnimate=" + weatherGraphicAnimate
				+ ", avgMinTemp=" + avgMinTemp + ", avgMaxTemp=" + avgMaxTemp + ", currentTemp=" + currentTemp
				+ ", tempUnit=" + tempUnit + ", distance=" + distance + ", isFavorite=" + isFavorite + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherLocation other = (WeatherLocation) obj;
		if (avgMaxTemp != other.avgMaxTemp)
			return false;
		if (avgMinTemp != other.avgMinTemp)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (currentTemp != other.currentTemp)
			return false;
		if (distance != other.distance)
			return false;
		if (isFavorite != other.isFavorite)
			return false;
		if (tempUnit != other.tempUnit)
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		if (weatherGraphicAnimate == null) {
			if (other.weatherGraphicAnimate != null)
				return false;
		} else if (!weatherGraphicAnimate.equals(other.weatherGraphicAnimate))
			return false;
		if (weatherGraphicStatic == null) {
			if (other.weatherGraphicStatic != null)
				return false;
		} else if (!weatherGraphicStatic.equals(other.weatherGraphicStatic))
			return false;
		return true;
	}
}
