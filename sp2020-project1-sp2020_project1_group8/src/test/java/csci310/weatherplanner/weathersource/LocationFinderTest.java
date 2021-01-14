package csci310.weatherplanner.weathersource;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LocationFinderTest {
	private LocationFinder locationfinder;
	
	@Before
	public void setUp() {
		locationfinder = new LocationFinder();
	}
	
	@Test
	public void TestsetFile() throws FileNotFoundException {
		List<String> act_list = new ArrayList<String>();
        
		File file = new File ("./activitylist.txt");
		locationfinder.setFile(file);
		
		act_list = LocationFinder.active_list;
		assertNotNull(act_list); //Test for true
	}
	
	@Test 
	public void TestgetLocations() {
		File file = new File ("./activitylist.txt");
		try {
			locationfinder.setFile(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String activity = "frisbee";
		int maxResults = 1;
		String loc = "Los Angeles";
		WeatherFilter wf = locationfinder.getLocations(loc, activity, maxResults);
		int tempM = wf.getTempMax();
		int tempm = wf.getTempMin();
		int max = 79;
		int min = 41;
		assertEquals(tempM, max);
		assertEquals(tempm, min);
	}
	@Test
	public void TestgetAutocomplete(){
		File file = new File ("./activitylist.txt");
		try {
			locationfinder.setFile(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word = "danc";
		List<String> words = locationfinder.getAutocomplete(word);
		String expected = "dancing";
		assertNotNull(words);
		String response = words.get(0);
		assertEquals(expected,response);
	}
	@Test 
	public void TestInvalidString() {
		File file = new File ("./activitylist.txt");
		try {
			locationfinder.setFile(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word = "123";
		List<String> words = locationfinder.getAutocomplete(word);
		List<String> emptyTest = new ArrayList<String>();
		assertEquals(emptyTest, words);
	}
}

