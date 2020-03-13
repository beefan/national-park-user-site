package com.techelevator.np.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.np.NationalParkWorker;
import com.techelevator.np.models.Park;
import com.techelevator.np.models.SurveyEntry;

@Controller
@SessionAttributes({ "weatherUnit", "parks", "states", "surveyCount", "userSurvey"})
public class HomeController {

	@Autowired
	private NationalParkWorker npWorker;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String showHomePage(ModelMap map) {
		if (!map.containsAttribute("parks")) {
			map.addAttribute("parks", npWorker.getAllParks());
		}
		
		if (!map.containsAttribute("weatherUnit")) {
			map.addAttribute("weatherUnit", "F");
		}
		
		return "home";
	}
	
	@RequestMapping(path="/changeTemp", method = RequestMethod.GET)
	public String changeTemp(@RequestParam String unit, @RequestParam String code, ModelMap map) {
		map.replace("weatherUnit", unit);
		return "redirect:/parkdetailpage?code="+code;
	}

	@RequestMapping(path = "/parkdetailpage", method = RequestMethod.GET)
	public String showParkDetailPage(ModelMap map, @RequestParam String code) {
		Park park = npWorker.getParkByParkCode(code);
		Map<Integer, List<String>> weatherRecs = npWorker.getFiveDayWeatherRecommendations(park.getFiveDayForecast());
	
		map.addAttribute("dates", npWorker.getNextFiveDates());
		map.addAttribute("park", park);
		map.addAttribute("weatherRecs", weatherRecs);
		return "parkdetailpage";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String showSurveyPage(ModelMap map) {
		SurveyEntry survey = new SurveyEntry();
		if (!map.containsAttribute("survey")) {
			map.addAttribute("survey", survey);
		}
		if (!map.containsAttribute("states")) {
			map.addAttribute("states", npWorker.getStateAbbreviations());
		}
		return "survey";
	}

	@RequestMapping(path = "/submitSurvey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") SurveyEntry survey, BindingResult result,
			ModelMap map) {
		if (result.hasErrors()) {
			return "survey";
		} else {
			if (!map.containsAttribute("surveyCount")) {

				Map<Park, Integer> surveyCount = new LinkedHashMap<Park, Integer>();
				npWorker.saveSurvey(survey);
				for (Entry entry : npWorker.getSurveyCounts().entrySet()) {
					surveyCount.put(npWorker.getParkByParkCode((String) entry.getKey()), (Integer) entry.getValue());

				}
				map.addAttribute("surveyCount", surveyCount);
				survey.setParkName(npWorker.getParkByParkCode(survey.getParkCode()).getName());
				map.addAttribute("userSurvey", survey);
			}
			return "redirect:/survey";
		}
	}

}
