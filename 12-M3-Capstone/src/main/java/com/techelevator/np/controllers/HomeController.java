package com.techelevator.np.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.np.dao.ParkDAO;
import com.techelevator.np.dao.SurveyDAO;

@Controller
@SessionAttributes({"weatherUnit"})
public class HomeController {
	
	@Autowired
	private ParkDAO parkDao;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String redirectToHomePage() {
		return "home";
	}
	
	
	
	
}
