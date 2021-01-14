package csci310.weatherplanner.weathersource;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class GooglePlacesAPI implements IPlaceImageSource{
	private String findPlaceFromText;
	private String details;
	private IExternalAPI places;
	private JsonParser jp;
	String photourl;
	String APIkeyurl;
	
	
	public GooglePlacesAPI(IExternalAPI places) {
		findPlaceFromText = "/findplacefromtext/json?";
		details = "/details/json?";
		photourl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
		APIkeyurl = "&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots";
		this.places = places;
		jp = new JsonParser();
	}
	
	@Override
	public List<String> getImages(String loc) {
		// TODO Auto-generated method stub
		String correctLoc = "";
		for (int i = 0; i < loc.length(); i++) {
			if(loc.charAt(i) == ' ') {
				correctLoc = correctLoc + '%';
			}
			else {
				correctLoc = correctLoc + loc.charAt(i);
			}
		}
		String parameters = "input=" + correctLoc + "&inputtype=textquery&fields=place_id&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots";
		System.out.println(findPlaceFromText+parameters);
		String response = places.request(findPlaceFromText+parameters);
		//response = "{\"candidates\": [{\"place_id\": \"ChIJRW_5f4bHwoARGRQF0lNThzA\"} ],\"status\": \"OK\"}";
		JsonObject root = jp.parse(response).getAsJsonObject();
		/*JsonArray candidates = root.getAsJsonArray();
		if(candidates.size() == 0)
			return null;*/
		JsonArray obj2 = root.get("candidates").getAsJsonArray();
		String place_id = obj2.get(0).getAsJsonObject().get("place_id").getAsString();
		parameters = "place_id="+ place_id + "&fields=photos&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots";
		response = places.request(details+parameters);
		System.out.println(response);
		//response = "{\"html_attributions\":[],\"result\":{\"photos\":[{\"height\":442,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/104494000466617447205\\\">Manju Gupta</a>\"],\"photo_reference\":\"CmRaAAAA1fsR_kR1O4Q6z_iorF3w4HPiX6S5G9tRG7GHMFDHuciEOY-ocECemZTJvRt8ltR5965WY0-KySVOIe_KtmflXUB3OyPU82RMAWOY-LYXarxDGaqKVai--bqKFA9hJVvDEhCMQR0C3FoWk6W96GECowFeGhT_c2hKrLuPyiDJmhURlNnuOWmBeg\",\"width\":707},{\"height\":485,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/108862076113400282635\\\">J.F. Traveler</a>\"],\"photo_reference\":\"CmRaAAAAWxdfxqyqcarqfHpoxJJ_vIirUsszzcAjzTTFoCGFTcW16clLzQcmwigtK4XkdRfDIbuf_ExhMLadl11H2nev30DeanVnM3VPHpZw2nrpHSpcWJ30ryw6yXZbIMuyvieQEhDv77cDGpdKh5LQ8nZY6hjIGhSZCM_V3Ton36SvRUbs9nZ-pQHZKw\",\"width\":896},{\"height\":416,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/115055200036247424884\\\">Rosie crockett</a>\"],\"photo_reference\":\"CmRaAAAAs8ty_N75dCPAluccNZrSYHFBLSwg-lrdlQwjkVnTGVsexQ9XaoXE_bIQQeyHw6RT-eh7xK8INBaaxU5acIHb8K3X_s9VGiCCpEUBoG_hnm1wqZvMRHpl84VQ4cTyNKs7EhDID45BpB8kicPr4lGNd02UGhTHVnbxLHr8uPdJ_0EDYzTvgWV36A\",\"width\":620},{\"height\":2988,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/113686060862392794258\\\">theycallmecarl</a>\"],\"photo_reference\":\"CmRaAAAAwRZXZXNgr2sF4YJKNTbQOn6wIBgM-sXFS5H0QUAWGTntrEVYjquBN1wYpiJM1S_VxA5DVO3mh2TNOikPMWsPyYTefpRRDCcRLcSS39BwLY3fB_-W1MiOMg-wVLDYceaYEhDZiKEIxxulN3sVL3k51gTdGhSfYCunxJXpPJucw8cq1ze7uDUSeA\",\"width\":5312},{\"height\":3072,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/110185226650349094791\\\">Alex Harm</a>\"],\"photo_reference\":\"CmRaAAAA9PKuUI07JCJAcsIpjqpC-i36qWkIqB4Rzx-Xw6vQFajBT7jSn0KkerbQDE42f8AtbnC6wCHpGOXRFRHuRJw1FXxMSuXqWdpssqmq-1vGFySLWZFG0PcjDAeoP2a2pyvOEhBIqRBLxaQK2mQ9GkPT00xnGhRSbT6aoxMOEjvXGuS1ZnYMlWT2-Q\",\"width\":4608},{\"height\":5312,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/103675347621144765049\\\">Celma De Brito</a>\"],\"photo_reference\":\"CmRaAAAAmzXd37PKOWmRoRJO9Q51mOKu1UrMsxb2dzfndz0yyGpV-nTnnjsim42z3x0KBUdzSg1_928258AqB-Ju5EvfFu_OJnQYuBUt1tsuwWxfRKku2ZmQJr-lCVSrVzEonJ_2EhCJoyKyIAK7nDWMtXqOfkz8GhQKrTeTFNGYG-r7HpKRnzXdRt0c-w\",\"width\":2988},{\"height\":2304,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/114050664419084842910\\\">Marc Garcia</a>\"],\"photo_reference\":\"CmRaAAAArVgdmoXEagaMSLL5NlV3iVoOW17_kD2Y9D4FOh0RZkdhGn6a_Z1Q0F5qbJgqVt20cBF2XgUPDgvTXPwZI2bsJHw6ehb_xvsCaw83J8aXeHAx2erfPYOMwDvx-dFxO5joEhC4EfySYxIlzURQp3pp86bwGhRdtvH88OOLkrBPRTjCauLWfbH3lg\",\"width\":4608},{\"height\":2184,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/103984083753284971383\\\">Melissa Soon</a>\"],\"photo_reference\":\"CmRaAAAAUQ8Pv-ZVNrEtcgt85iSuOY6xXuYfSWpDhf4NPxp1OPn22PNUovr3UOPkH5Foi6h0IGkzpAVAh-4UO-D9BH7VAO77CUPmkqBCJ8xP87eLeOeN1MaD6jICisDSPZn-55VREhCae8VG5ljm-lYMwQamn1U8GhTNYW7zECx2NMlIBjh-GtCianiRPQ\",\"width\":4608},{\"height\":4032,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/100495420965546386737\\\">George Laurie</a>\"],\"photo_reference\":\"CmRaAAAAoSU8f7UJ7kq81pSgqrpom7wJvtBpkhIg41997cYDW5ZN5ComY_AKj0hsgQG9MqMJb7wkUyF724FN9PF8L9U7G57zTbeYdCtB9todcFYeZLY50-Jw4ftbIvZd1iONyToXEhD3yLHR1wP0tKFZNj6SO2gHGhQ5jOzSVuh0v7rEbeCf75VmsiAqXw\",\"width\":3024},{\"height\":640,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/104310465765551061088\\\">Paola Vacca</a>\"],\"photo_reference\":\"CmRaAAAA7vWdIdCxXoHqw6xFnTN-GfpK50jCapQ6AKGtUVFK9BhPg7fRasGy2ZcXdV7eramO_rGMny7EbL-_y1YrkQpQQdf-A96MeB4zd3EVOdvvHC7n_1Fdx92fRnSRlUD1LrDAEhDTQJkC_XhmRtjq9jilfroNGhTHNcGAlR-pfFv8bfzLmuz_EtKk8Q\",\"width\":1280}]},\"status\":\"OK\"}";
		root = jp.parse(response).getAsJsonObject();
		JsonObject arr = root.get("result").getAsJsonObject();
		JsonArray result = arr.get("photos").getAsJsonArray();
		String photo1 = result.get(0).getAsJsonObject().get("photo_reference").getAsString();
		String photo2 = result.get(1).getAsJsonObject().get("photo_reference").getAsString();
		String photo3 = result.get(2).getAsJsonObject().get("photo_reference").getAsString();
		photo1 = photourl + photo1 + APIkeyurl;
		photo2 = photourl + photo2 + APIkeyurl;
		photo3 = photourl + photo3 + APIkeyurl;
		ArrayList<String> results = new ArrayList<String>();
		results.add(photo1);
		results.add(photo2);
		results.add(photo3);
		return results;
	}
	

}
