package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestSetup {
	protected WebDriver driver;
	private final String baseUrl = "http://sneakpeeq.com";
	
	public TestSetup() {
		System.setProperty("webdriver.chrome.driver", "/Users/ivyshushu/Documents/workspace/Test-Framework/bin/chromedriver");
		this.driver = new ChromeDriver();
	}

	public String getBaseUrl() {
		return baseUrl;
	}
	
/***
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
***/
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
