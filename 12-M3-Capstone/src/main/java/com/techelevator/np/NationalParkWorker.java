package com.techelevator.np;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techelevator.np.dao.ParkDAO;
import com.techelevator.np.dao.SurveyDAO;
import com.techelevator.np.models.DailyForecast;
import com.techelevator.np.models.Park;
import com.techelevator.np.models.SurveyEntry;

@Component
public class NationalParkWorker {

	private static final String SNOW_REC = "pack snowshoes";
	private static final String RAIN_REC = "pack rain gear and wear waterproof shoes";
	private static final String SUN_REC = "pack sunblock";
	private static final String THUNDER_REC = "seek shelter and avoid hiking on exposed ridges";
	private static final String HOT_REC = "bring an extra gallon of water";
	private static final String LAYERS_REC = "wear breathable layers";
	private static final String FRIGID_REC = "caution: prolonged exposure to frigid temperatures can be deadly";
	private static final String[] STATES = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE",
			"FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH",
			"MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK",
			"OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI",
			"WV","WY"};
	
	@Autowired
	private SurveyDAO surveyDao;

	@Autowired
	private ParkDAO parkDao;

	public NationalParkWorker() {

	}

	public List<Park> getAllParks() {
		return parkDao.getAllParks();
	}

	public Park getParkByParkCode(String code) {
		return parkDao.getParkByParkCode(code);
	}

	public void saveSurvey(SurveyEntry survey) {
		surveyDao.save(survey);
	}

	public Map<String, Integer> getSurveyCounts() {
		return surveyDao.getSurveyCounts();
	}
	
	public Map<Integer, List<String> > getFiveDayWeatherRecommendations(List<DailyForecast> forecasts) {
		Map<Integer, List<String>> weatherRecs = new HashMap<Integer, List<String>>();
		
		for (DailyForecast forecast : forecasts) {
			weatherRecs.put(forecast.getDay(), getWeatherRecommendation(forecast));
		}
		
		return weatherRecs;
	}
	public List<String> getStateAbbreviations(){
		List<String> states = new LinkedList<String>();
		Collections.addAll(states, STATES);
		
		return states;
	}
	
	public List<Date> getNextFiveDates(){
		List<Date> dateList = new LinkedList<Date>();
		dateList.add(new Date());
		for(int i = 1; i < 5; i++) {
			
			dateList.add(new Date(LocalDate.now().plusDays(i).toEpochDay() * (1000 * 60 * 60 * 24)) );
		}
		return dateList;
	}
	
	private List<String> getWeatherRecommendation(DailyForecast forecast) {
		List<String> weatherRec = new ArrayList<String>();

		switch (forecast.getForecast()) {
		case "snow":
			weatherRec.add(SNOW_REC);
			break;
		case "rain":
			weatherRec.add(RAIN_REC);
			break;
		case "thunderstorms":
			weatherRec.add(THUNDER_REC);
			break;
		case "sunnny":
			weatherRec.add(SUN_REC);
			break;
		}

		if (forecast.getDailyHigh() > 75) {
			weatherRec.add(HOT_REC);
		}
		if (forecast.getDailyHigh() - forecast.getDailyLow() > 20) {
			weatherRec.add(LAYERS_REC);
		}
		if (forecast.getDailyLow() < 20) {
			weatherRec.add(FRIGID_REC);
		}

		return weatherRec;
	}
	

}
