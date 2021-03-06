package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;

import pages.Base.HeaderRegion;
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
		this.setup.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HeaderRegion header = homePage.new HeaderRegion(setup.getDriver());
		assertEquals(true, header.isAccountSectionPresent());
	}

}
