package com.techelevator.np.jdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.np.dao.ParkDAO;
import com.techelevator.np.models.Park;

public class JDBCParkDAOTest extends DAOIntegrationTest {

	private ParkDAO parkDao;
	private JdbcTemplate jdbcTemplate;
	private static SingleConnectionDataSource datasource; 
	
	@Before
	public void setup() {
		datasource = (SingleConnectionDataSource) super.getDataSource();
		parkDao = new JDBCParkDAO(datasource);
		jdbcTemplate = new JdbcTemplate(datasource);
		truncateParkAndWeather();
	}
	
	@Test
	public void get_all_parks_with_empty_table_returns_empty_list() {
		Assert.assertEquals(0, parkDao.getAllParks().size());
	}
	
	@Test
	public void get_all_parks_returns_all_parks() {
		insertPark("DHF", "TestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		Assert.assertEquals(1, parkDao.getAllParks().size());

		insertPark("DDD", "TestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		insertPark("FFF", "TestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		insertPark("GGG", "TestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		
		Assert.assertEquals(4, parkDao.getAllParks().size());
	}
	
	@Test
	public void get_all_parks_returns_alphabetical() {
		insertPark("DDD", "BTestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		insertPark("FFF", "CTestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		insertPark("GGG", "ATestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		
		List<Park> parks = parkDao.getAllParks();
		Assert.assertEquals("ATestPark", parks.get(0).getName());
		Assert.assertEquals("CTestPark", parks.get(2).getName());
		
	}
	
	@Test
	public void get_all_parks_returns_proper_forecast() {
		insertPark("GGG", "ATestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		insertWeatherForPark("GGG");
		
		List<Park> parks = parkDao.getAllParks();
		
		Assert.assertEquals(5, parks.get(0).getFiveDayForecast().size());
	}
	
	@Test 
	public void get_park_by_code_returns_park_with_code() {
		insertPark("GGG", "ATestPark", "a", 3, 3, 3, 3, "a", 3, 3, "a", "a", "a", 3, 3);
		
		Assert.assertEquals("GGG", parkDao.getParkByParkCode("GGG").getCode());
	}
	
	@Test
	public void get_park_by_code_returns_null_if_no_park_exists() {
		Assert.assertNull(parkDao.getParkByParkCode("GGG"));
	}
	
	private void insertPark(String code, String parkName, String state, int acreage, 
			int elevation, int miles, int campsites, String climate, int yearFounded, 
			int visitorCount, String author, String quote, String description, int entryFee, int species ) {
		String sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, " + 
				"numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, " + 
				"parkdescription, entryfee, numberofanimalspecies ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, code, parkName, state, acreage, elevation, miles, campsites, climate, yearFounded,
				visitorCount, quote, author, description, entryFee, species);
	}
	
	private void insertWeatherForPark(String parkCode) {
		String sql = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) " +
					"VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, parkCode, 1, 55, 60, "cloudy");
		jdbcTemplate.update(sql, parkCode, 2, 55, 60, "cloudy");
		jdbcTemplate.update(sql, parkCode, 3, 55, 60, "cloudy");
		jdbcTemplate.update(sql, parkCode, 4, 55, 60, "cloudy");
		jdbcTemplate.update(sql, parkCode, 5, 55, 60, "cloudy");
				
	}
	
	private void truncateParkAndWeather() {
		String sql = "TRUNCATE park CASCADE";
		jdbcTemplate.update(sql);
		
		sql = "TRUNCATE weather CASCADE";
		jdbcTemplate.update(sql);
		
	}
	
	
}
