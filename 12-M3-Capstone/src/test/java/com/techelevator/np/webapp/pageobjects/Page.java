package com.techelevator.np.webapp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class Page {
	
	private WebDriver webDriver;

	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	protected void enterFieldValue(String cssSelector, String value) {
		WebElement field = webDriver.findElement(By.cssSelector(cssSelector));
		field.sendKeys(value);
	}
	
	protected void chooseOptionFromSelect(String cssSelector, String optionText) {
		Select selectField = new Select(webDriver.findElement(By.cssSelector(cssSelector)));
		selectField.selectByVisibleText(optionText);
	}
}
