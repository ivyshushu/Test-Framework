package pages;

import org.openqa.selenium.WebDriver;

public class Home extends Base {
	
	public Home(WebDriver d) {
		super(d);
	}
	
	public void getHomePage() {
		this.driver.get(this.getBaseUrl());
	}
	
	public boolean isLoggedIn() {
		return this.header().isAccountSectionPresent(); 
	}

	
}
