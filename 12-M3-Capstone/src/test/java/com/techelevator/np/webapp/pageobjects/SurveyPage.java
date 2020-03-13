package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SurveyPage extends Page{
	
	private WebDriver webDriver;
	
	public SurveyPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
	}
	
	public String getSurveyHeader() {
		return webDriver.findElement(By.cssSelector("body > div.main-section > div > div > h2")).getText();
	}
	
	public SurveyPage selectAPark() {
		chooseOptionFromSelect("#parkCode", "Glacier National Park");
		return this;
	}
	
	public SurveyPage selectAState() {
		chooseOptionFromSelect("#state", "GU");
		return this;
	}
	
	public SurveyPage enterEmailAddress() {
		enterFieldValue("#emailAddress", "tester@nationalparkexplorer.com");
		return this;
	}
	
	public SurveyPage selectActivityLevel() {
		webDriver.findElement(By.id("activityLevel3")).click();
		return this;
	}
	
	public SurveyResultsPage submitSurvey() {
		webDriver.findElement(By.cssSelector("#survey > div.submit > input")).click();
		return new SurveyResultsPage(webDriver);
	}
}
