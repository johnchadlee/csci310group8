package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import csci310.weatherplanner.weathersource.mock.MockExternalAPI;

public class AerisAdvancedWeatherSourceTest {
	private static AerisAdvancedWeatherSource testSource;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Map<String, String> endpointMap = new HashMap<String, String>();
		
		// Results based on real responses collected from the aerisAPI
		endpointMap.put("/observations/search", "{\"success\":true,\"error\":null,\"response\":[{\"id\":\"KCCB\",\"loc\":{\"long\":-117.68333333333,\"lat\":34.116666666667},\"place\":{\"name\":\"upland\",\"city\":\"upland\",\"state\":\"ca\",\"country\":\"us\"},\"profile\":{\"tz\":\"America\\/Los_Angeles\",\"tzname\":\"PDT\",\"tzoffset\":-25200,\"isDST\":true,\"elevM\":440,\"elevFT\":1444},\"obTimestamp\":1584054900,\"obDateTime\":\"2020-03-12T16:15:00-07:00\",\"ob\":{\"type\":\"station\",\"timestamp\":1584054900,\"dateTimeISO\":\"2020-03-12T16:15:00-07:00\",\"recTimestamp\":1584055158,\"recDateTimeISO\":\"2020-03-12T16:19:18-07:00\",\"tempC\":10,\"tempF\":50,\"dewpointC\":10,\"dewpointF\":50,\"humidity\":100,\"pressureMB\":1011,\"pressureIN\":29.85,\"spressureMB\":959,\"spressureIN\":28.32,\"altimeterMB\":1010,\"altimeterIN\":29.84,\"windKTS\":7,\"windKPH\":13,\"windMPH\":8,\"windSpeedKTS\":7,\"windSpeedKPH\":13,\"windSpeedMPH\":8,\"windDirDEG\":330,\"windDir\":\"NNW\",\"windGustKTS\":null,\"windGustKPH\":null,\"windGustMPH\":null,\"flightRule\":\"IFR\",\"visibilityKM\":3.21868,\"visibilityMI\":2,\"weather\":\"Cloudy with Heavy Rain\",\"weatherShort\":\"Heavy Rain\",\"weatherCoded\":\":H:R,::OV\",\"weatherPrimary\":\"Heavy Rain\",\"weatherPrimaryCoded\":\":H:R\",\"cloudsCoded\":\"OV\",\"icon\":\"rain.png\",\"heatindexC\":10,\"heatindexF\":50,\"windchillC\":10,\"windchillF\":50,\"feelslikeC\":10,\"feelslikeF\":50,\"isDay\":true,\"sunrise\":1584021838,\"sunriseISO\":\"2020-03-12T07:03:58-07:00\",\"sunset\":1584064591,\"sunsetISO\":\"2020-03-12T18:56:31-07:00\",\"snowDepthCM\":null,\"snowDepthIN\":null,\"precipMM\":0,\"precipIN\":0,\"solradWM2\":121,\"solradMethod\":\"estimated\",\"ceilingFT\":3400,\"ceilingM\":1036.32,\"light\":32,\"uvi\":null,\"QC\":\"O\",\"QCcode\":10,\"trustFactor\":100,\"sky\":100},\"raw\":\"METAR KCCB 122315Z AUTO 33007KT 2SM +RA SCT006 BKN034 OVC041 10\\/10 A2984 RMK AO2\"},{\"id\":\"KFKS\",\"loc\":{\"long\":-86.2,\"lat\":44.633333333333},\"place\":{\"name\":\"frankfort\",\"city\":\"frankfort\",\"state\":\"mi\",\"country\":\"us\"},\"profile\":{\"tz\":\"America\\/Detroit\",\"tzname\":\"EDT\",\"tzoffset\":-14400,\"isDST\":true,\"elevM\":193,\"elevFT\":633},\"obTimestamp\":1584054900,\"obDateTime\":\"2020-03-12T19:15:00-04:00\",\"ob\":{\"type\":\"station\",\"timestamp\":1584054900,\"dateTimeISO\":\"2020-03-12T19:15:00-04:00\",\"recTimestamp\":1584055158,\"recDateTimeISO\":\"2020-03-12T19:19:18-04:00\",\"tempC\":8.6,\"tempF\":47,\"dewpointC\":1.9,\"dewpointF\":35,\"humidity\":63,\"pressureMB\":1000,\"pressureIN\":29.53,\"spressureMB\":977,\"spressureIN\":28.85,\"altimeterMB\":1000,\"altimeterIN\":29.52,\"windKTS\":13,\"windKPH\":24,\"windMPH\":15,\"windSpeedKTS\":13,\"windSpeedKPH\":24,\"windSpeedMPH\":15,\"windDirDEG\":160,\"windDir\":\"SSE\",\"windGustKTS\":16,\"windGustKPH\":30,\"windGustMPH\":18,\"flightRule\":\"VFR\",\"visibilityKM\":16.0934,\"visibilityMI\":10,\"weather\":\"Cloudy with Light Rain\",\"weatherShort\":\"Light Rain\",\"weatherCoded\":\":L:R,::OV\",\"weatherPrimary\":\"Light Rain\",\"weatherPrimaryCoded\":\":L:R\",\"cloudsCoded\":\"OV\",\"icon\":\"rain.png\",\"heatindexC\":8.3,\"heatindexF\":47,\"windchillC\":5,\"windchillF\":41,\"feelslikeC\":5,\"feelslikeF\":41,\"isDay\":true,\"sunrise\":1584014467,\"sunriseISO\":\"2020-03-12T08:01:07-04:00\",\"sunset\":1584056853,\"sunsetISO\":\"2020-03-12T19:47:33-04:00\",\"snowDepthCM\":null,\"snowDepthIN\":null,\"precipMM\":null,\"precipIN\":null,\"solradWM2\":14,\"solradMethod\":\"estimated\",\"ceilingFT\":6500,\"ceilingM\":1981.2,\"light\":6,\"uvi\":null,\"QC\":\"O\",\"QCcode\":10,\"trustFactor\":100,\"sky\":100},\"raw\":\"METAR KFKS 122315Z AUTO 16013G16KT 10SM -RA SCT032 BKN065 OVC100 09\\/02 A2952 RMK AO2 T00860019\"},{\"id\":\"KLOM\",\"loc\":{\"long\":-75.266666666667,\"lat\":40.133333333333},\"place\":{\"name\":\"philly wings f\",\"city\":\"philly wings f\",\"state\":\"pa\",\"country\":\"us\"},\"profile\":{\"tz\":\"America\\/New_York\",\"tzname\":\"EDT\",\"tzoffset\":-14400,\"isDST\":true,\"elevM\":92,\"elevFT\":302},\"obTimestamp\":1584054900,\"obDateTime\":\"2020-03-12T19:15:00-04:00\",\"ob\":{\"type\":\"station\",\"timestamp\":1584054900,\"dateTimeISO\":\"2020-03-12T19:15:00-04:00\",\"recTimestamp\":1584055360,\"recDateTimeISO\":\"2020-03-12T19:22:40-04:00\",\"tempC\":9.9,\"tempF\":50,\"dewpointC\":7,\"dewpointF\":45,\"humidity\":82,\"pressureMB\":1019,\"pressureIN\":30.09,\"spressureMB\":1008,\"spressureIN\":29.75,\"altimeterMB\":1019,\"altimeterIN\":30.08,\"windKTS\":10,\"windKPH\":19,\"windMPH\":12,\"windSpeedKTS\":10,\"windSpeedKPH\":19,\"windSpeedMPH\":12,\"windDirDEG\":110,\"windDir\":\"ESE\",\"windGustKTS\":null,\"windGustKPH\":null,\"windGustMPH\":null,\"flightRule\":\"MVFR\",\"visibilityKM\":16.0934,\"visibilityMI\":10,\"weather\":\"Cloudy with Light Rain\",\"weatherShort\":\"Light Rain\",\"weatherCoded\":\":L:R,::OV\",\"weatherPrimary\":\"Light Rain\",\"weatherPrimaryCoded\":\":L:R\",\"cloudsCoded\":\"OV\",\"icon\":\"rainn.png\",\"heatindexC\":10,\"heatindexF\":50,\"windchillC\":10,\"windchillF\":50,\"feelslikeC\":10,\"feelslikeF\":50,\"isDay\":false,\"sunrise\":1584011764,\"sunriseISO\":\"2020-03-12T07:16:04-04:00\",\"sunset\":1584054309,\"sunsetISO\":\"2020-03-12T19:05:09-04:00\",\"snowDepthCM\":null,\"snowDepthIN\":null,\"precipMM\":0,\"precipIN\":0,\"solradWM2\":0,\"solradMethod\":\"estimated\",\"ceilingFT\":2900,\"ceilingM\":883.92,\"light\":0,\"uvi\":null,\"QC\":\"O\",\"QCcode\":10,\"trustFactor\":100,\"sky\":100},\"raw\":\"METAR KLOM 122315Z AUTO 11010KT 10SM -RA SCT018 SCT023 OVC029 10\\/07 A3008 RMK AO2 T00990070\"}]}");
		endpointMap.put("/places/closest", "{\"success\":true,\"error\":{\"code\":\"warn_location\",\"description\":\"No state/province provided, using location based on population\"},\"response\":[{\"loc\":{\"lat\":33.755,\"long\":-84.39},\"place\":{\"name\":\"Atlanta\",\"state\":\"GA\",\"stateFull\":\"Georgia\",\"country\":\"US\",\"countryFull\":\"United States\",\"region\":\"usse\",\"regionFull\":\"Southeast\",\"continent\":\"na\",\"continentFull\":\"North America\"},\"profile\":{\"elevM\":320,\"elevFT\":1050,\"pop\":463878,\"tz\":\"America/New_York\",\"tzname\":\"EDT\",\"tzoffset\":-14400,\"isDST\":true,\"wxzone\":[\"GAZ033\"],\"firezone\":[\"GAZ033\"],\"fips\":[\"13121\"],\"countyid\":[\"GAC121\"]},\"relativeTo\":{\"lat\":33.755,\"long\":-84.39,\"bearing\":180,\"bearingENG\":\"S\",\"distanceKM\":0,\"distanceMI\":0}}]}");
		endpointMap.put("/normals/Atlanta,us", "{\"success\":true,\"error\":null,\"response\":{\"id\":\"usw00003888\",\"loc\":{\"long\":-84.521,\"lat\":33.7792},\"place\":{\"name\":\"atlanta fulton co ap\",\"state\":\"ga\",\"country\":\"us\"},\"periods\":[{\"type\":\"monthly\",\"mon\":1,\"day\":null,\"timestamp\":1577836800,\"dateTimeISO\":\"2020-01-01T00:00:00+00:00\",\"temp\":{\"maxF\":53.1,\"maxC\":11.72,\"minF\":33.1,\"minC\":0.61,\"avgF\":43.1,\"avgC\":6.17},\"prcp\":{\"ytdIN\":null,\"ytdMM\":null,\"mtdIN\":4.27,\"mtdMM\":108},\"snow\":null,\"hdd\":67.9,\"cdd\":0}],\"profile\":{\"tz\":\"America\\/New_York\"},\"relativeTo\":{\"lat\":33.755,\"long\":-84.39,\"bearing\":282,\"bearingENG\":\"WNW\",\"distanceKM\":12.405,\"distanceMI\":7.708}}}");
		endpointMap.put("/normals/upland,us", "{\"success\":true,\"error\":null,\"response\":{\"id\":\"usw00003102\",\"loc\":{\"long\":-117.6,\"lat\":34.0561},\"place\":{\"name\":\"ontario intl ap\",\"state\":\"ca\",\"country\":\"us\"},\"periods\":[{\"type\":\"monthly\",\"mon\":3,\"day\":null,\"timestamp\":1583020800,\"dateTimeISO\":\"2020-03-01T00:00:00+00:00\",\"temp\":{\"maxF\":69.9,\"maxC\":21.06,\"minF\":47.2,\"minC\":8.44,\"avgF\":58.5,\"avgC\":14.72},\"prcp\":{\"ytdIN\":null,\"ytdMM\":null,\"mtdIN\":2.32,\"mtdMM\":59},\"snow\":null,\"hdd\":21.2,\"cdd\":1.2}],\"profile\":{\"tz\":\"America\\/Los_Angeles\"},\"relativeTo\":{\"lat\":34.09751,\"long\":-117.64839,\"bearing\":136,\"bearingENG\":\"SE\",\"distanceKM\":6.408,\"distanceMI\":3.982}}}");
		endpointMap.put("/normals/frankfort,us", "{\"success\":true,\"error\":null,\"response\":{\"id\":\"usc00153028\",\"loc\":{\"long\":-84.882,\"lat\":38.2022},\"place\":{\"name\":\"frankfort downtown\",\"state\":\"ky\",\"country\":\"us\"},\"periods\":[{\"type\":\"monthly\",\"mon\":3,\"day\":null,\"timestamp\":1583020800,\"dateTimeISO\":\"2020-03-01T00:00:00+00:00\",\"temp\":{\"maxF\":55.8,\"maxC\":13.22,\"minF\":31.2,\"minC\":-0.44,\"avgF\":43.5,\"avgC\":6.39},\"prcp\":null,\"snow\":null,\"hdd\":66.8,\"cdd\":0.1}],\"profile\":{\"tz\":\"America\\/New_York\"},\"relativeTo\":{\"lat\":38.20091,\"long\":-84.87328,\"bearing\":281,\"bearingENG\":\"W\",\"distanceKM\":0.775,\"distanceMI\":0.482}}}");
		endpointMap.put("/normals/philly+wings+f,us", "{\"success\":true,\"error\":{\"code\":\"warn_location\",\"description\":\"No state\\/province provided, using location based on population\"},\"response\":{\"id\":\"usc00366886\",\"loc\":{\"long\":-75.172,\"lat\":39.9575},\"place\":{\"name\":\"philly franklin inst\",\"state\":\"pa\",\"country\":\"us\"},\"periods\":[{\"type\":\"monthly\",\"mon\":3,\"day\":null,\"timestamp\":1583020800,\"dateTimeISO\":\"2020-03-01T00:00:00+00:00\",\"temp\":{\"maxF\":53.2,\"maxC\":11.78,\"minF\":36.4,\"minC\":2.44,\"avgF\":44.8,\"avgC\":7.11},\"prcp\":{\"ytdIN\":null,\"ytdMM\":null,\"mtdIN\":4.6,\"mtdMM\":117},\"snow\":{\"ytdIN\":null,\"ytdCM\":null,\"mtdIN\":1.9,\"mtdCM\":null},\"hdd\":62.8,\"cdd\":0.2}],\"profile\":{\"tz\":\"America\\/New_York\"},\"relativeTo\":{\"lat\":39.95234,\"long\":-75.16379,\"bearing\":309,\"bearingENG\":\"NW\",\"distanceKM\":0.905,\"distanceMI\":0.562}}}");
		MockExternalAPI api = new MockExternalAPI(endpointMap);
		
		testSource = new AerisAdvancedWeatherSource(api);
	}

	@Test
	public void testGetLocations() {
		List<WeatherLocation> results = testSource.getLocations(new WeatherFilter("Atlanta,US", 50, 40, PrecipType.Rainy, 10), TempUnit.Fahrenheit);
		List<WeatherLocation> expected = new ArrayList<WeatherLocation>();
		expected.add(new WeatherLocation("upland", "us", "Cloudy with Heavy Rain", WeatherIcon.Raining.getStaticPath(), WeatherIcon.Raining.getAnimatePath(), 47, 69, 50, TempUnit.Fahrenheit, 1898, false));
		expected.add(new WeatherLocation("frankfort", "us", "Cloudy with Light Rain", WeatherIcon.Raining.getStaticPath(), WeatherIcon.Raining.getAnimatePath(), 31, 55, 47, TempUnit.Fahrenheit, 757, false));
		expected.add(new WeatherLocation("philly wings f", "us", "Cloudy with Light Rain", WeatherIcon.Raining.getStaticPath(), WeatherIcon.Raining.getAnimatePath(), 36, 53, 50, TempUnit.Fahrenheit, 668, false));
		
		assertEquals(expected, results);
	}

	@Test
	public void testGetHistoricalWeatherFahrenheit() {
		List<MonthTemp> results = testSource.getHistoricalWeather("Atlanta,us", TempUnit.Fahrenheit);
		List<MonthTemp> expected = new ArrayList<MonthTemp>();
		expected.add(new MonthTemp(Month.Jan, 33, 53));
		expected.add(new MonthTemp(Month.Feb, 33, 53));
		expected.add(new MonthTemp(Month.Mar, 33, 53));
		expected.add(new MonthTemp(Month.Apr, 33, 53));
		expected.add(new MonthTemp(Month.May, 33, 53));
		expected.add(new MonthTemp(Month.Jun, 33, 53));
		expected.add(new MonthTemp(Month.Jul, 33, 53));
		expected.add(new MonthTemp(Month.Aug, 33, 53));
		expected.add(new MonthTemp(Month.Sep, 33, 53));
		expected.add(new MonthTemp(Month.Oct, 33, 53));
		expected.add(new MonthTemp(Month.Nov, 33, 53));
		expected.add(new MonthTemp(Month.Dec, 33, 53));
		
		assertEquals(expected, results);
	}
	
	@Test
	public void testGetHistoricalWeatherCelsius() {
		List<MonthTemp> results = testSource.getHistoricalWeather("Atlanta,us", TempUnit.Celsius);
		List<MonthTemp> expected = new ArrayList<MonthTemp>();
		expected.add(new MonthTemp(Month.Jan, 0, 11));
		expected.add(new MonthTemp(Month.Feb, 0, 11));
		expected.add(new MonthTemp(Month.Mar, 0, 11));
		expected.add(new MonthTemp(Month.Apr, 0, 11));
		expected.add(new MonthTemp(Month.May, 0, 11));
		expected.add(new MonthTemp(Month.Jun, 0, 11));
		expected.add(new MonthTemp(Month.Jul, 0, 11));
		expected.add(new MonthTemp(Month.Aug, 0, 11));
		expected.add(new MonthTemp(Month.Sep, 0, 11));
		expected.add(new MonthTemp(Month.Oct, 0, 11));
		expected.add(new MonthTemp(Month.Nov, 0, 11));
		expected.add(new MonthTemp(Month.Dec, 0, 11));
		
		assertEquals(expected, results);
	}

}
