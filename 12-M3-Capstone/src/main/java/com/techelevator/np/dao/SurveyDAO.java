package com.techelevator.np.dao;

import java.util.List;

import com.techelevator.np.models.SurveyEntry;

public interface SurveyDAO {
	
	List<SurveyEntry> getAllSurveys();
	void save(SurveyEntry survey);
	
}
