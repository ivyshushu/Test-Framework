package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Living extends Base {
	
	public Living(WebDriver d) {
		super(d);
		//url need to change !!!!
		this.driver.get(this.getBaseUrl());
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
