package com.techelevator.np.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.np.NationalParkWorker;
import com.techelevator.np.models.Park;

@Controller
@SessionAttributes({"weatherUnit"})
public class HomeController  {
	
	@Autowired
	private NationalParkWorker npWorker;
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String showHomePage(ModelMap map) {
		map.put("parks", npWorker.getAllParks());
		return "home";
	}
	
	@RequestMapping(path="/parkdetailpage", method=RequestMethod.GET)
	public String showParkDetailPage(ModelMap map, HttpServletRequest request) {
		Park park = npWorker.getParkByParkCode(request.getParameter("code"));
		Map<Integer, List<String>> weatherRecs= npWorker.getFiveDayWeatherRecommendations(park.getFiveDayForecast());
		
		map.put("park", park );
		map.put("weatherRecs", weatherRecs);
		return "parkdetailpage";
	}
	
	
}
