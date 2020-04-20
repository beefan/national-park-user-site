package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	public SurveyPage submitSurvey() {
		webDriver.findElement(By.cssSelector("#survey > div.submit > input")).click();
		return this;
	}
	public String getSurveyResultsIndicator() {
		return webDriver.findElement(By.id("survey-title")).getText();
	}
	public ParkDetailPage clickParkImage() {
		WebElement img = webDriver.findElement(By.cssSelector("#results > div:nth-child(7) > a > img"));
		img.click();
		
		return new ParkDetailPage(webDriver);
	}
	public String getUserFavoriteParkText() {
		return webDriver.findElement(By.cssSelector("body > div.main-section > div > div > div.user-results > p > a")).getText();
	}
	public ParkDetailPage clickUserFavoriteParkLink() {
		webDriver.findElement(By.cssSelector("body > div.main-section > div > div > div.user-results > p > a")).click();
		return new ParkDetailPage(webDriver);
	}
}
