package csci310.weatherplanner.weathersource;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TrieNodeTest {
	private LocationFinder lf;
	private Trie t;
	private Gson gson;
	public static List<String> active_list = null;

	//= new ArrayList<String>();

	@Before
	public void initMockData() {
        gson = new Gson();        
		lf = new LocationFinder();
		File file = new File ("./activitylist.txt");
		try {
			lf.setFile(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t = new Trie(); 
		ArrayList<String> mock_activities = new ArrayList<String>((
				Arrays.asList(
				"paddleboarding",
				"dancing",
				"loitering",
				"camping",
				"apple picking",
				"snowboarding",
				"skydiving",
				"surfing",
				"ice skating",
				"sking",
				"frisbee",
				"jockey",
				"running",
				"wakeboarding",
				"swimming",
				"basketball",
				"scuba diving",
				"cooking",
				"zoo",
				"napping",
				"soccer",
				"fishing",
				"kayaking",
				"hiking",
				"football",
				"picnic",
				"eating",
				"tennis",
				"museum",
				"cricket",
				"gym",
				"skateboarding"))); 
        for(int i=0; i<mock_activities.size(); i++) {
        	t.addWord(mock_activities.get(i));
        }
        
	}
	
	@Test
	public void testSuccessfulSearchForWord() {
		String word = "kayak";
		boolean test = t.SearchForWord(word);
		boolean expected = true;
		assertEquals(expected, test);
	}
	@Test
	public void testUnsuccessfulSearchForWord() {
		String word = "dfady";
		boolean test = t.SearchForWord(word);
		boolean expected = false;
		assertEquals(expected, test);
	}
	@Test
	public void testSuccessfulAutoCompleteSingle() {

		String word = "s";
		List<String> outputList = t.autocomplete(word);
		
		String actualString = gson.toJson(outputList);
		//should navigate through all possibilities of s that match mock dictionary
		String expected = "[\"s\",\"sn\",\"sno\",\"snow\",\"snowb\",\"snowbo\",\"snowboa\",\"snowboar\",\"snowboard\",\"snowboardi\",\"snowboardin\",\"snowboarding\",\"sk\",\"sky\",\"skyd\",\"skydi\",\"skydiv\",\"skydivi\",\"skydivin\",\"skydiving\",\"ski\",\"skin\",\"sking\",\"ska\",\"skat\",\"skate\",\"skateb\",\"skatebo\",\"skateboa\",\"skateboar\",\"skateboard\",\"skateboardi\",\"skateboardin\",\"skateboarding\",\"su\",\"sur\",\"surf\",\"surfi\",\"surfin\",\"surfing\",\"sw\",\"swi\",\"swim\",\"swimm\",\"swimmi\",\"swimmin\",\"swimming\",\"sc\",\"scu\",\"scub\",\"scuba\",\"scuba \",\"scuba d\",\"scuba di\",\"scuba div\",\"scuba divi\",\"scuba divin\",\"scuba diving\",\"so\",\"soc\",\"socc\",\"socce\",\"soccer\"]";
		assertEquals(expected, actualString);
	}
	@Test
	public void testUnsuccessfulAutoCompleteSingle() {
		String word = "xyz";
		List<String> outputList = t.autocomplete(word);
		
		String actualString = gson.toJson(outputList);
		// Should not recurse at all and return empty string
		String expected = "[]";
		assertEquals(expected, actualString);
	}

}
