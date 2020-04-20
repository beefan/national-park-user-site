package com.techelevator.np.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class DailyForecastTest {
	
	private static DailyForecast df;
	
	@BeforeClass
	public static void setup() {
		df = new DailyForecast();
	}
	
	@Test
	public void test_park_code_field() {
		df.setParkCode("TES");
		Assert.assertEquals("TES", df.getParkCode());
	}
	
	@Test
	public void test_day_field() {
		df.setDay(3);
		Assert.assertEquals(3, df.getDay());
	}
	
	@Test 
	public void test_daily_low_field() {
		df.setDailyLow(40);
		Assert.assertEquals(40, df.getDailyLow());
	}
	
	@Test
	public void test_daily_high_field() {
		df.setDailyHigh(60);
		Assert.assertEquals(60, df.getDailyHigh());
	}
	
	@Test
	public void test_forecast_field() {
		df.setForecast("cloudy");
		Assert.assertEquals("cloudy", df.getForecast());
	}
	
}
