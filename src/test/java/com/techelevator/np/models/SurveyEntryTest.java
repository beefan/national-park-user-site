package com.techelevator.np.models;

import org.junit.*;

public class SurveyEntryTest {

	private static SurveyEntry se;
	
	@BeforeClass
	public static void setup() {
		se = new SurveyEntry();
	}
	
	@Test
	public void test_id_field() {
		se.setId(400L);
		Assert.assertEquals(400L, se.getId());
	}
	
	@Test 
	public void test_park_code_field() {
		se.setParkCode("GGG");
		Assert.assertEquals("GGG", se.getParkCode());
	}
	
	@Test
	public void test_email_address_field() {
		se.setEmailAddress("b@test.com");
		Assert.assertEquals("b@test.com", se.getEmailAddress());
	}
	
	@Test
	public void test_state_field() {
		se.setState("OH");
		Assert.assertEquals("OH", se.getState());
	}
	
	@Test
	public void test_activity_level_field() {
		se.setActivityLevel("active");
		Assert.assertEquals("active", se.getActivityLevel());
	}
	
	@Test
	public void test_park_name_field() {
		se.setParkName("Majestic Park");
		Assert.assertEquals("Majestic Park", se.getParkName());
	}
	
}
