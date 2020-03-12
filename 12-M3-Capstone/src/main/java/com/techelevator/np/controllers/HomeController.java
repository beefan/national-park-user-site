package com.techelevator.np.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.np.NationalParkWorker;
import com.techelevator.np.models.Park;
import com.techelevator.np.models.SurveyEntry;

@Controller
@SessionAttributes({ "weatherUnit" })
public class HomeController {

	@Autowired
	private NationalParkWorker npWorker;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String showHomePage(ModelMap map) {
		map.put("parks", npWorker.getAllParks());
		return "home";
	}

	@RequestMapping(path = "/parkdetailpage", method = RequestMethod.GET)
	public String showParkDetailPage(ModelMap map, HttpServletRequest request) {
		Park park = npWorker.getParkByParkCode(request.getParameter("code"));
		Map<Integer, List<String>> weatherRecs = npWorker.getFiveDayWeatherRecommendations(park.getFiveDayForecast());

		map.put("park", park);
		map.put("weatherRecs", weatherRecs);
		return "parkdetailpage";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String showSurveyPage(ModelMap map) {
		SurveyEntry survey = new SurveyEntry();
		if (!map.containsAttribute("survey")) {
			map.addAttribute("survey", survey);
		}
		map.put("parks", npWorker.getAllParks());
		return "survey";
	}

	@RequestMapping(path = "/submitSurvey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") SurveyEntry survey, BindingResult result,
			RedirectAttributes attr) {
		Map<Park, Integer> surveyCount = new HashMap<Park, Integer>();

		for (Entry entry : npWorker.getSurveyCounts().entrySet()) {
			surveyCount.put(npWorker.getParkByParkCode((String) entry.getKey()), (Integer) entry.getValue());

		}
//		map.put("surveyCount", surveyCount);

		return "redirect:/survey";
	}

}
