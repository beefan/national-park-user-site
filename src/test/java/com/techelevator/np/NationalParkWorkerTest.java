package com.techelevator.np;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.np.models.DailyForecast;

public class NationalParkWorkerTest {
	
	private NationalParkWorker np;
	
	@Before
	public void setup() {
		np = new NationalParkWorker();
	}
	
	@Test
	public void get_weather_recommendations_cloudy_test() {		
		Assert.assertEquals(0, 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("cloudy", 60, 60))
							.get(0)
							.size());
	}
	
	@Test
	public void get_weather_recommendations_rain_test() {
		Assert.assertEquals("pack rain gear and wear waterproof shoes", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("rain", 60, 60))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_snow_test() {
		Assert.assertEquals("pack snowshoes", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("snow", 60, 60))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_thunder_test() {
		Assert.assertEquals("seek shelter and avoid hiking on exposed ridges", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("thunderstorms", 60, 60))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_sunny_test() {
		Assert.assertEquals("pack sunblock", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("sunny", 60, 60))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_hot_test() {
		Assert.assertEquals("bring an extra gallon of water", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("cloudy", 86, 60))
							.get(0)
							.get(0));
	}
	
	@Test
	public void get_weather_recommendations_layers_test() {
		Assert.assertEquals("wear breathable layers", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("cloudy", 65, 43))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_frigid_test() {
		Assert.assertEquals("caution: prolonged exposure to frigid temperatures can be deadly", 
				np.getFiveDayWeatherRecommendations(
						makeTestForecastList("cloudy", 24, 19))
							.get(0)
							.get(0));	
	}
	
	@Test
	public void get_weather_recommendations_more_than_one_rec_test() {
		List<String> recs = np.getFiveDayWeatherRecommendations(
								makeTestForecastList("snow", 24, 18))
									.get(0);
		
		Assert.assertEquals("pack snowshoes", recs.get(0));
		Assert.assertEquals("caution: prolonged exposure to frigid temperatures can be deadly", recs.get(1));
	}
	
	@Test
	public void get_weather_recommendations_maps_days_properly() {
		List<DailyForecast> forecasts = makeTestForecastList("snow", 60, 60);
		DailyForecast df = new DailyForecast();
		df.setForecast("sunny");
		df.setDailyHigh(76);
		df.setDailyLow(40);
		df.setDay(1);
		forecasts.add(df);
		Map<Integer, List<String>> recs = np.getFiveDayWeatherRecommendations(forecasts);
		Assert.assertEquals("pack snowshoes", recs.get(0).get(0));
		Assert.assertEquals(3, recs.get(1).size());
	}
	
	@Test
	public void get_state_abbreviations_returns_56_strings() {
		/*US states and territories = 56*/
		Assert.assertEquals(56, np.getStateAbbreviations().size());
	}
	
	@Test
	public void get_state_abbreviations_returns_abbreviations() {
		Assert.assertEquals(2, np.getStateAbbreviations().get(0).length());
	}
	
	@Test
	public void get_state_abbreviations_returns_state_alphabetically() {
		List<String> states = np.getStateAbbreviations();
		
		Assert.assertEquals("AK", states.get(0));
		Assert.assertEquals("WY", states.get(states.size()-1));
	}
	
	@Test
	public void get_five_days_returns_five_dates() {
		Assert.assertEquals(5, np.getNextFiveDates().size());
	}
	
	@Test
	public void get_five_days_returns_next_five_days_in_order() {
		List<LocalDate> dates = np.getNextFiveDates();
		
		Assert.assertEquals(LocalDate.now().toString(), dates.get(0).toString());
		Assert.assertEquals(LocalDate.now().plusDays(1).toString(), dates.get(1).toString());
		Assert.assertEquals(LocalDate.now().plusDays(2).toString(), dates.get(2).toString());
		Assert.assertEquals(LocalDate.now().plusDays(3).toString(), dates.get(3).toString());
		Assert.assertEquals(LocalDate.now().plusDays(4).toString(), dates.get(4).toString());
	}
	
	private List<DailyForecast> makeTestForecastList(String forecast, int high, int low) {
		DailyForecast df = new DailyForecast();
		df.setForecast(forecast);
		df.setDailyHigh(high);
		df.setDailyLow(low);
		df.setDay(0);
		List<DailyForecast> dfl = new LinkedList<DailyForecast>();
		dfl.add(df);
		
		return dfl;
	}
	
}
