package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Home extends Base {
	
	public Home(WebDriver d) {
		super(d);
		this.driver.get(this.getBaseUrl());
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	
}
