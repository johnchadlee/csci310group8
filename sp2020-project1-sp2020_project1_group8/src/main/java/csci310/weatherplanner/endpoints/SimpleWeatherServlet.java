package csci310.weatherplanner.endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Enums;
import com.google.gson.Gson;

import csci310.weatherplanner.weathersource.APIRequester;
import csci310.weatherplanner.weathersource.AerisAdvancedWeatherSource;
import csci310.weatherplanner.weathersource.AerisSimpleForecastSource;
import csci310.weatherplanner.weathersource.DailyForecast;
import csci310.weatherplanner.weathersource.Date;
import csci310.weatherplanner.weathersource.FavoritesManager;
import csci310.weatherplanner.weathersource.GooglePlacesAPI;
import csci310.weatherplanner.weathersource.IAdvancedWeatherSource;
import csci310.weatherplanner.weathersource.IFavoritesManager;
import csci310.weatherplanner.weathersource.IForecastSource;
import csci310.weatherplanner.weathersource.IPlaceImageSource;
import csci310.weatherplanner.weathersource.Month;
import csci310.weatherplanner.weathersource.MonthTemp;
import csci310.weatherplanner.weathersource.TempUnit;
import csci310.weatherplanner.weathersource.WeatherIcon;
import csci310.weatherplanner.weathersource.WeatherLocation;
import csci310.weatherplanner.weathersource.WeatherSource;
import csci310.weatherplanner.weathersource.mock.AdvancedWeatherMock;
import csci310.weatherplanner.weathersource.mock.FavoritesMock;
import csci310.weatherplanner.weathersource.mock.ForecastMock;
import csci310.weatherplanner.weathersource.mock.MockSession;
import csci310.weatherplanner.weathersource.mock.PlaceImageMock;

@WebServlet("/simpleweatherservlet")
public class SimpleWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Gson gson;
	private final WeatherSource weatherSource;
	private IForecastSource forecastSource;
	private IAdvancedWeatherSource advForecastSource;
	private IFavoritesManager favoritesManager;
	private IPlaceImageSource imageSource;
	
	public SimpleWeatherServlet() {
		super();
		forecastSource = new AerisSimpleForecastSource(new APIRequester("https://api.aerisapi.com"));
		advForecastSource = new AerisAdvancedWeatherSource(new APIRequester("https://api.aerisapi.com"));
		favoritesManager = FavoritesManager.getGlobalManager();
		imageSource = new GooglePlacesAPI(new APIRequester("https://maps.googleapis.com/maps/api/place"));
        gson = new Gson();        
		weatherSource = new WeatherSource(forecastSource, advForecastSource, favoritesManager, imageSource); 
    }
    
    public SimpleWeatherServlet(WeatherSource weatherSource) {
    	super();
    	gson = new Gson();
    	this.weatherSource = weatherSource;
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		if(!request.getParameterMap().containsKey("loc")) {
			response.setStatus(400);
			return;
		}
			
		String loc = request.getParameter("loc");
		
		TempUnit unit = Enums.getIfPresent(TempUnit.class, request.getParameter("unit")).orNull();
		if(unit == null)
		{
			response.setStatus(400);
			return;
		}
		
		
		WeatherLocation weather = weatherSource.getWeather(request.getSession(), loc, unit);
		System.out.println(weather);
	    out.print(gson.toJson(weather));
	    out.flush();
	}
}