package com.techelevator.np.webapp;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.techelevator.np.webapp.pageobjects.HomePage;
import com.techelevator.np.webapp.pageobjects.ParkDetailPage;
import com.techelevator.np.webapp.pageobjects.SurveyPage;


public class NationalParkExplorerSeleniumIntegrationTest {
	private static WebDriver webDriver;
	private HomePage homePage;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void click_survey_page_nav_returns_survey_page() {
		String surveyHeader = homePage.clickSurveyNavButton()
									  .getSurveyHeader();
		
		Assert.assertEquals("What's your favorite park?", surveyHeader);
	}
	
	@Test
	public void click_home_page_nav_returns_home_page() {
		String homeIndicator = homePage.clickHomeNavButton()
									   .getHomePageIndicator();
		
		Assert.assertEquals("home", homeIndicator);
	}
	
	@Test
	public void click_park_image_opens_detail_page() {
		String detailIndicator = homePage.clickParkImage()
										 .getDetailPageIndicator();
		
		Assert.assertEquals("detail", detailIndicator);
	}
	
	@Test 
	public void survey_page_end_to_end_test() {
		// Test Submit Survey 
		SurveyPage surveyPage = homePage.clickSurveyNavButton()
												.selectAPark()
												.enterEmailAddress()
												.selectAState()
												.selectActivityLevel()
												.submitSurvey();
		
		Assert.assertEquals("And the Survey Says..." , surveyPage.getSurveyResultsIndicator());
		
		// Test Park Detail Image Links are Correct
		ParkDetailPage detailPage = surveyPage.clickParkImage();		   
		Assert.assertEquals("detail", detailPage.getDetailPageIndicator());	
		
		// Test for Favorite Park Link is Correctly Displayed
		surveyPage = detailPage.clickSurveyNavButton();
		Assert.assertEquals("Glacier National Park", surveyPage.getUserFavoriteParkText());
		
		// Test Favorite Park Link is Correct
		String parkName = surveyPage.clickUserFavoriteParkLink()
				  					.getParkName();
		Assert.assertEquals("Glacier National Park , Montana", parkName);
	}
	
	@Test
	public void click_celcius_changes_temperature_unit() {
	ParkDetailPage detailPage =	homePage.clickParkImage();
	double tempF = detailPage.getFirstTemperature();
	detailPage = detailPage.clickFocusTempUnit();
	double tempC = detailPage.getFirstTemperature();
	Assert.assertEquals((tempF - 32) * 5 / 9.0, tempC, 1);
	
	}
	
	@Test
	public void click_farenheit_changes_temperature_unit() {
		ParkDetailPage detailPage =	homePage.clickParkImage();
		double tempF = detailPage.getFirstTemperature();
		detailPage = detailPage.clickFocusTempUnit();
		detailPage = detailPage.clickFocusTempUnit();
		double tempTest = detailPage.getFirstTemperature();
		Assert.assertEquals(tempF, tempTest, 1);
	}
	
	@Test
	public void deatil_page_remembers_user_temp_unit() {
		ParkDetailPage detailPage =	homePage.clickParkImage();
		detailPage = detailPage.clickFocusTempUnit();
		double temp = detailPage.getFirstTemperature();
		double tempTest = detailPage.clickHomeNavButton()
									.clickParkImage()
									.getFirstTemperature();
		Assert.assertEquals(temp, tempTest, 1);
		
	}
	
	
	
}
