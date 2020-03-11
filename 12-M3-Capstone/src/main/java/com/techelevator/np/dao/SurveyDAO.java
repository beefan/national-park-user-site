package com.techelevator.np.dao;

import java.util.List;
import java.util.Map;

import com.techelevator.np.models.SurveyEntry;

public interface SurveyDAO {
	
	//TODO delete?
	List<SurveyEntry> getAllSurveys();
	
	Map<String, Integer> getSurveyCounts();
	void save(SurveyEntry survey);
	
}
