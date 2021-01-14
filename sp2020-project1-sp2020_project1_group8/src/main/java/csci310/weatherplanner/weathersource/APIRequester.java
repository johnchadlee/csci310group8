package csci310.weatherplanner.weathersource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class APIRequester implements IExternalAPI{
	private final String baseUrl;
	
	public APIRequester(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Override
	public String request(String request) {
		URL url;
		
		try {
			url = new URL(baseUrl + request);
			System.out.println(url);
		} catch (MalformedURLException e1) {
			return null;
		}
		
		try {
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuilder response = new StringBuilder();
			String line;
			
			while ((line = in.readLine()) != null) 
			    response.append(line);
			
			in.close();
			
			return response.toString();
		} catch (IOException e) {
			return null;
		}
	}
}
