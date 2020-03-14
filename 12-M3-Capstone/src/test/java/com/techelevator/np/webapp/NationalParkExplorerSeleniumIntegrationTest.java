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
	
	
	
}
