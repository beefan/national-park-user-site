package com.techelevator.np.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.np.dao.SurveyDAO;
import com.techelevator.np.models.SurveyEntry;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	// TODO delete?
	// is this even useful?
	@Override
	public List<SurveyEntry> getAllSurveys() {
		List<SurveyEntry> surveys = new ArrayList<SurveyEntry>();

		String sql = "SELECT s.surveyid, s.parkcode, s.emailaddress, s.state, s.activitylevel "
				+ "FROM survey_result s " + "JOIN ( SELECT parkcode, COUNT(*) AS cnt " + "       FROM survey_result "
				+ "       GROUP BY parkcode " + "     ) s2 ON ( s2.parkcode = s.parkcode ) " + "ORDER BY s2.cnt DESC";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			surveys.add(mapRowToSurvey(results));
		}

		return surveys;
	}

	@Override
	public Map<String, Integer> getSurveyCounts() {

		Map<String, Integer> surveyCounts = new LinkedHashMap<String, Integer>();

		String sql = "SELECT count(s.parkcode), s.parkcode " + "FROM survey_result AS s "
				+ "JOIN park ON park.parkcode = s.parkcode " + "GROUP BY s.parkcode, park.parkname "
				+ "ORDER BY count DESC, park.parkname ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

		while (results.next()) {
			surveyCounts.put(results.getString("parkcode"), results.getInt("count"));
		}

		return surveyCounts;
	}

	@Override
	public void save(SurveyEntry survey) {
		
		String sql = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) "+
		"VALUES (DEFAULT, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());

	}

	private SurveyEntry mapRowToSurvey(SqlRowSet row) {
		SurveyEntry s = new SurveyEntry();

		s.setId(row.getLong("surveyid"));
		s.setParkCode(row.getString("parkcode"));
		s.setEmailAddress(row.getString("emailaddress"));
		s.setState(row.getString("state"));
		s.setActivityLevel(row.getString("activitylevel"));

		return s;
	}

}
