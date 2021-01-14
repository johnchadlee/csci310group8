package csci310.weatherplanner.util;

public class UnitConversion {
	public static int fToC(int temp) {
		return ((temp - 32) * 5) / 9;
	}
	
	/*
	 * Converts a pair of latitude and longitude coordinates into a distance in miles
	 * Formula from: https://www.geeksforgeeks.org/program-distance-two-points-earth/
	 */
	public static int degDist(double lat1, double long1, double lat2, double long2) {
		lat1 = Math.toRadians(lat1);
		long1 = Math.toRadians(long1);
		lat2 = Math.toRadians(lat2);
		long2 = Math.toRadians(long2);
		
		// Haversine formula
		double latD = lat2 - lat1; 
		double longD = long2 - long1;
		double a = 2 * Math.asin(
				Math.sqrt(
						Math.pow(Math.sin(latD / 2), 2) 
						+ Math.cos(lat1) 
						* Math.cos(lat2) 
						* Math.pow(Math.sin(longD / 2),2)
						)
				);
		
		double r = 3956; 
		
        return (int)(a * r);
	}
}
