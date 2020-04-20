package com.techelevator.np.dao;

import java.util.List;
import java.util.Map;

import com.techelevator.np.models.SurveyEntry;

public interface SurveyDAO {
	
	Map<String, Integer> getSurveyCounts();
	void save(SurveyEntry survey);
	
}
