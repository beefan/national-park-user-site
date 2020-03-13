package com.techelevator.np.webapp;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.techelevator.np.webapp.pageobjects.HomePage;


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
	public void survey_can_be_filled_out_and_submitted() {
		String surveyResultsIndicator = homePage.clickSurveyNavButton()
												.selectAPark()
												.enterEmailAddress()
												.selectAState()
												.selectActivityLevel()
												.submitSurvey()
												.getSurveyResultsIndicator();
		
		Assert.assertEquals("And the Survey Says..." , surveyResultsIndicator);
	}
	
}
