package csci310.weatherplanner.weathersource.mock;

import java.util.Map;

import csci310.weatherplanner.weathersource.IExternalAPI;

public class MockExternalAPI implements IExternalAPI {
	private final Map<String, String> endpointMap;
	
	public MockExternalAPI(Map<String, String> endpointMap) {
		this.endpointMap = endpointMap;
	}
	
	@Override
	public String request(String request) {
		String endpoint = request.substring(0, request.indexOf("?"));
		return endpointMap.get(endpoint);
	}

}
