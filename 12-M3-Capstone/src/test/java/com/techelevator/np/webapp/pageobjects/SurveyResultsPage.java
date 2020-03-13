package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SurveyResultsPage {

	private WebDriver webDriver;

	public SurveyResultsPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String getSurveyResultsIndicator() {
		return webDriver.findElement(By.id("survey-title")).getText();
	}
}
