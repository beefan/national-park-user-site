package com.techelevator.np.jdbc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.np.dao.ParkDAO;
import com.techelevator.np.models.DailyForecast;
import com.techelevator.np.models.Park;

public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();

		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "parkdescription, entryfee, numberofanimalspecies " + "FROM park ORDER BY parkname";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			parks.add(mapRowToPark(results));
		}

		for (Park park : parks) {
			park.setFiveDayForecast(getFiveDayForecast(park.getCode()));
		}

		return parks;
	}

	@Override
	public Park getParkByParkCode(String code) {
		
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "parkdescription, entryfee, numberofanimalspecies " + "FROM park WHERE parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, code);
		
		if (!results.next()) {
			return null;
		}
		
		return mapRowToPark(results);
	}

	private List<DailyForecast> getFiveDayForecast(String parkCode) {
		List<DailyForecast> fiveDayForecast = new LinkedList<DailyForecast>();

		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast "
				+ "FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);

		while (results.next()) {
			fiveDayForecast.add(mapRowToForecast(results));
		}

		return fiveDayForecast;
	}

	private DailyForecast mapRowToForecast(SqlRowSet row) {
		DailyForecast forecast = new DailyForecast();

		forecast.setParkCode(row.getString("parkcode"));
		forecast.setDay(row.getInt("fivedayforecastvalue"));
		forecast.setDailyLow(row.getInt("low"));
		forecast.setDailyHigh(row.getInt("high"));
		forecast.setForecast(row.getString("forecast"));

		return forecast;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();

		park.setCode(row.getString("parkcode"));
		park.setName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevation(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getInt("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		park.setInspirationalQuote(row.getString("inspirationalquote"));
		park.setDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setNumberOfSpecies(row.getInt("numberofanimalspecies"));

		return park;
	}

}
