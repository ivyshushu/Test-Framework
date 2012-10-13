package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.Home;

public class LoginTest {

	TestSetup setup = new TestSetup();
	
	@After
	public void tearDown() throws Exception {
		this.setup.getDriver().quit();
	}

	@Test
	public void test() {
		Home homePage = new Home(setup.getDriver());
		homePage.getToUrl(setup.getBaseUrl());
		this.setup.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage.login("ivyshushu@gmail.com", "6314134223");
	}

}
