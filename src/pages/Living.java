package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Living extends Base {
	private String url = "http://www.sneakpeeq.com/v/LIVING";
	
	public Living(WebDriver d) {
		super(d);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void getToLivingPage() {
		this.getDriver().get(getUrl());
	} 
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
