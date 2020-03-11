package com.techelevator.np.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.np.dao.SurveyDAO;
import com.techelevator.np.models.SurveyEntry;

public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<SurveyEntry> getAllSurveys() {
		List<SurveyEntry> surveys = new ArrayList<SurveyEntry>();
		
		String sql = "SELECT s.surveyid, s.parkcode, s.emailaddress, s.state, s.activitylevel " + 
				"FROM survey_result s " + 
				"JOIN ( SELECT parkcode, COUNT(*) AS cnt " + 
				"       FROM survey_result " + 
				"       GROUP BY parkcode " + 
				"     ) s2 ON ( s2.parkcode = s.parkcode ) " + 
				"ORDER BY s2.cnt DESC";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while (results.next()) {
			surveys.add(mapRowToSurvey(results));
		}
		
		return surveys;
	}

	@Override
	public void save(SurveyEntry survey) {
		// TODO Auto-generated method stub
		
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
