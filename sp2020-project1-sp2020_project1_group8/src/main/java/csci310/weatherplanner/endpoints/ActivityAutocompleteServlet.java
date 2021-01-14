package csci310.weatherplanner.endpoints;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import csci310.weatherplanner.weathersource.LocationFinder;

public class ActivityAutocompleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private LocationFinder activitySource;
	private Gson gson;
	private String filePath;
	private File file;
	public ActivityAutocompleteServlet() {
		activitySource = new LocationFinder();
		gson = new Gson();
		filePath = "./activitylist.txt";
		file = new File(filePath);
		try {
			activitySource.setFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		if(!request.getParameterMap().containsKey("activity")) {
			response.setStatus(400);
			return;
		}
			
		String activity = request.getParameter("activity").toLowerCase();
		List<String> suggestions = activitySource.getAutocomplete(activity);
	    out.print(gson.toJson(suggestions));
	    out.flush();
	}
}
