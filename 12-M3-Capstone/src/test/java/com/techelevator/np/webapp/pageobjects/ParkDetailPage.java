package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkDetailPage {
	private WebDriver webDriver;

	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String getDetailPageIndicator() {
		return webDriver.findElement(By.id("detailSeleniumHelper")).getText();
	}
	public SurveyPage clickSurveyNavButton() {
		WebElement surveyNav = webDriver.findElement(
				By.cssSelector("body > div.header > nav > a:nth-child(2)"));
		surveyNav.click();
		
		return new SurveyPage(webDriver);
	}
	public String getParkName() {
		return webDriver.findElement(By.cssSelector("body > div.main-section > div > h1")).getText();
	}
	public ParkDetailPage clickFocusTempUnit() {
		webDriver.findElement(By.cssSelector("body > div.main-section > div > div.five-day-forecast > div.unit > p > a > span")).click();
		return new ParkDetailPage(webDriver);
	}
	public double getFirstTemperature() {
		return Double.parseDouble(webDriver.findElement(By.cssSelector("#low")).getText().substring(5));
	}
	public HomePage clickHomeNavButton() {
		WebElement homeNav = webDriver.findElement(
				By.cssSelector("body > div.header > nav > a:nth-child(1)"));
		homeNav.click();
		
		return new HomePage(webDriver);
	}
	
}
