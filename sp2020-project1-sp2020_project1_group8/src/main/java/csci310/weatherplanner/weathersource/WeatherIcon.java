package csci310.weatherplanner.weathersource;

public enum WeatherIcon {
	Sunny("/icons/static/day.svg", "/icons/animated/day.svg"),
	Cloudy("/icons/static/cloudy-day-1.svg", "/icons/animated/cloudy-day-1.svg"),
	Raining("/icons/static/rainy-1.svg", "/icons/animated/rainy-1.svg"),
	Storming("/icons/static/thunder.svg", "/icons/animated/thunder.svg"),
	Snowing("/icons/static/snowy-1.svg", "/icons/animated/snowy-1.svg");
	
	private final String staticPath;
	private final String animatePath;
	WeatherIcon(String staticPath, String animatePath){
		this.staticPath = staticPath;
		this.animatePath = animatePath;
	}
	
	public String getStaticPath() {
		return staticPath;
	}

	public String getAnimatePath() {
		return animatePath;
	}

	/*
	 * Returns a WeatherIcon given a weather code as specified at
	 * https://www.aerisweather.com/support/docs/api/reference/weather-codes/
	 */
	public static WeatherIcon fromWeatherCode(String code) {
		String[] elements = code.split(":");
		if(elements.length != 3)
			throw new IllegalArgumentException("Invalid weather code!");
		// String coverage = elements[0];
		// String intensity = elements[1];
		String weather = elements[2];
		
		if(weather.equals("CL") || weather.equals("FW"))
			return Sunny;
		
		if(weather.equals("SC") || weather.equals("BK") || weather.equals("OV"))
			return Cloudy;
		
		if(weather.equals("BY") || weather.equals("L") || weather.equals("R") || weather.equals("RW") || weather.equals("RS"))
			return Raining;
		
		if(weather.equals("T"))
			return Storming;
		
		if(weather.equals("SI") || weather.equals("WM") || weather.equals("S") || weather.equals("SW"))
			return Snowing;
		
		return Cloudy;
	}
}
