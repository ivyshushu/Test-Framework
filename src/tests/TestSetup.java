package tests;

import org.openqa.selenium.WebDriver;

public class TestSetup {
	protected final WebDriver driver;
	private String baseUrl;
	
	public TestSetup(WebDriver d, String url){
		this.driver = d;
		this.setBaseUrl(url);
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
