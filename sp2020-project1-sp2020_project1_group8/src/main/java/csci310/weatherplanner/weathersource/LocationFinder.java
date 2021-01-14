package csci310.weatherplanner.weathersource;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LocationFinder implements ILocationFinder{
	private static HashMap<String, ArrayList<Integer> > ActivityList;//Stores Activities and their according temps
	private static TrieNode root;
	public static List<String> active_list;
	public static int size;
	public LocationFinder() {
		ActivityList = new HashMap<String, ArrayList<Integer> >();
		char c = ' ';
		root = new TrieNode(c);
		size = 0;
		active_list = new ArrayList<String>();
	}
	// Edit from John
	public List<String> getAutocomplete (String str){
		if (str.isEmpty()) {
			List<String> empty = new ArrayList<String>();
			return empty;
		}
        Trie t = new Trie();  
        //Add all activities into active_list
        for(int i=0; i<active_list.size(); i++) {
        	t.addWord(active_list.get(i));
        }        
		List<String> act_list= t.autocomplete(str);
		List<String> end_points = new ArrayList<String>();
		int j = 0;
		while(!act_list.isEmpty()) {
			if(active_list.contains(act_list.get(j))) {
				end_points.add(act_list.get(j));
				act_list.remove(j);
			}
			else {
				act_list.remove(j);
			}
		}
		return end_points;
	}

	public WeatherFilter getLocations(String location,String activity, int maxResults) {
		if(activity == null || !ActivityList.containsKey(activity) ) {
			return null;
		}
		//Activity temperature range needed
		ArrayList<Integer> temp = ActivityList.get(activity);//Use buffer array
		int tempMin = temp.get(0);
		int tempMax = temp.get(1);
		String loc = location;
		PrecipType precipType = PrecipType.Clear;
		int max = maxResults;
		//Find locations based on activity
		WeatherFilter wf = new WeatherFilter(loc,tempMax, tempMin, precipType, max);
		return wf;
	}
	public void setFile(File file) throws FileNotFoundException {
		ActivityList = new HashMap<String, ArrayList<Integer> > ();
		Scanner sc = new Scanner(file); 
		
		while(sc.hasNextLine()) {
			String act = sc.next();
			act = act.replace("-", " ");
			String temp = sc.next();
			int temp_lo = Integer.parseInt(temp);
			temp = sc.next();
			int temp_hi = Integer.parseInt(temp);
			ArrayList<Integer> temp_range = new ArrayList<Integer>();
			temp_range.add(0,temp_lo);
			temp_range.add(1,temp_hi);
			ActivityList.put(act, temp_range);
		}
		active_list = new ArrayList<String>(ActivityList.keySet() );//List of activities
		sc.close();
		}

}
