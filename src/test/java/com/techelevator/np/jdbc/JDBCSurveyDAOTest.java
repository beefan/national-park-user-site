package com.techelevator.np.jdbc;

import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.np.dao.SurveyDAO;
import com.techelevator.np.models.SurveyEntry;

public class JDBCSurveyDAOTest extends DAOIntegrationTest {

	private SurveyDAO surveyDao;
	private JdbcTemplate jdbcTemplate;
	private static SingleConnectionDataSource datasource;

	@Before
	public void setup() {
		datasource = (SingleConnectionDataSource) super.getDataSource();
		surveyDao = new JDBCSurveyDAO(datasource);
		jdbcTemplate = new JdbcTemplate(datasource);
		truncateSurveyResult();
	}

	@Test
	public void get_survey_on_empty_table_returns_nothing() {
		Assert.assertEquals(0, surveyDao.getSurveyCounts().size());
	}

	@Test
	public void get_survey_counts_returns_accurate_counts() {
		insertSurvey("ENP");
		Assert.assertEquals(1, (int) surveyDao.getSurveyCounts().get("ENP"));

		insertSurvey("ENP");
		Assert.assertEquals(2, (int) surveyDao.getSurveyCounts().get("ENP"));

		insertSurvey("GNP");
		Assert.assertEquals(1, (int) surveyDao.getSurveyCounts().get("GNP"));

	}

	@Test
	public void duplicate_counts_are_sorted_alphabetically() {
		insertSurvey("GNP");
		insertSurvey("GNP");

		insertSurvey("ENP");
		insertSurvey("ENP");

		int position = 0;
		for (Entry entry : surveyDao.getSurveyCounts().entrySet()) {
			if (position == 0)
				Assert.assertEquals("ENP", entry.getKey());
			if (position == 1)
				Assert.assertEquals("GNP", entry.getKey());
			position++;
		}

	}

	@Test
	public void save_a_survey_inserts_new_survey() {
		
		SurveyEntry s = new SurveyEntry();
		s.setParkCode("GGG");
		s.setEmailAddress("b@b");
		s.setState("oh");
		s.setActivityLevel("fit");
		
		surveyDao.save(s);
		
		String sql = "SELECT count(*) FROM survey_result";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		int count = 0;
		
		if (result.next()) {
			count = result.getInt("count");
		}
		
		Assert.assertEquals(1, count);
	}

	private void insertSurvey(String parkCode) {
		String sql = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, parkCode, "a", "a", "a");

	}

	private void truncateSurveyResult() {
		String sql = "TRUNCATE survey_result CASCADE";
		jdbcTemplate.update(sql);
	}
}
