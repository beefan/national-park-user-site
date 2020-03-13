package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParkDetailPage {
	private WebDriver webDriver;

	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String getDetailPageIndicator() {
		return webDriver.findElement(By.id("detailSeleniumHelper")).getText();
	}
}
