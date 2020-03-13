package com.techelevator.np.models;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;

public class ParkTest {

	private static Park p;
	
	@BeforeClass
	public static void setup() {
		p = new Park();
	}
	
	@Test
	public void test_code_field() {
		p.setCode("TES");
		Assert.assertEquals("TES", p.getCode());
	}
	
	@Test
	public void test_name_field() {
		p.setName("TEST");
		Assert.assertEquals("TEST", p.getName());
	}
	
	@Test
	public void test_state_field() {
		p.setState("OH");
		Assert.assertEquals("OH", p.getState());
	}
	
	@Test 
	public void test_acreage_field() {
		p.setAcreage(34445);
		Assert.assertEquals(34445, p.getAcreage());
	}
	
	@Test
	public void test_elevation_field() {
		p.setElevation(4000);
		Assert.assertEquals(4000, p.getElevation());
	}
	
	@Test
	public void test_miles_of_trail_field() {
		p.setMilesOfTrail(455.04);
		Assert.assertEquals(455.04, p.getMilesOfTrail(), 2);
	}
	
	@Test 
	public void test_number_of_campsites_field() {
		p.setNumberOfCampsites(45);
		Assert.assertEquals(45, p.getNumberOfCampsites());
	}
	
	@Test
	public void test_climate_field() {
		p.setClimate("wet");
		Assert.assertEquals("wet", p.getClimate());
	}
	
	@Test
	public void test_year_founded_field() {
		p.setYearFounded(1970);
		Assert.assertEquals(1970, p.getYearFounded());
	}
	
	@Test
	public void test_annual_visitor_count_field() {
		p.setAnnualVisitorCount(345);
		Assert.assertEquals(345, p.getAnnualVisitorCount());
	}
	
	@Test 
	public void test_inspirational_quote_field() {
		p.setInspirationalQuote("testing is not the root of all evil; it addresses evil's root");
		Assert.assertEquals("testing is not the root of all evil; it addresses evil's root", p.getInspirationalQuote());
	}
	
	@Test
	public void test_quote_author_field() {
		p.setQuoteAuthor("Paul");
		Assert.assertEquals("Paul", p.getQuoteAuthor());
	}
	
	@Test
	public void test_entry_fee_field() {
		p.setEntryFee(5);
		Assert.assertEquals(5, p.getEntryFee());
	}
	
	@Test
	public void test_number_of_species_field() {
		p.setNumberOfSpecies(45);
		Assert.assertEquals(45, p.getNumberOfSpecies());
	}
	
	@Test
	public void test_weather_forecast_field() {
		List<DailyForecast> forecasts = new ArrayList<DailyForecast>();
		for (int i = 0; i < 5; i++) {
			forecasts.add(new DailyForecast());
		}
		
		p.setFiveDayForecast(forecasts);
		Assert.assertEquals(5, p.getFiveDayForecast().size());
	}
}
