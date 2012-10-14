package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestChrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Users/ivyshushu/Documents/workspace/Test-Framework/bin/chromedriver");
		
		WebDriver d = new ChromeDriver();
		d.get("http://www.google.com");
	}

}
