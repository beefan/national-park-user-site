package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyPage clickSurveyNavButton() {
		WebElement surveyNav = webDriver.findElement(
				By.cssSelector("body > div.header > nav > a:nth-child(2)"));
		surveyNav.click();
		
		return new SurveyPage(webDriver);
	}
	
	public HomePage clickHomeNavButton() {
		WebElement homeNav = webDriver.findElement(
				By.cssSelector("body > div.header > nav > a:nth-child(1)"));
		homeNav.click();
		
		return this;
	}
	
	public ParkDetailPage clickParkImage() {
		WebElement img = webDriver.findElement(
				By.cssSelector("body > div.main-section > div > div:nth-child(3) > a > img"));
		img.click();
		
		return new ParkDetailPage(webDriver);
	}
	
	public String getHomePageIndicator() {
		return webDriver.findElement(By.id("homeSeleniumHelper")).getText();
	}
}
