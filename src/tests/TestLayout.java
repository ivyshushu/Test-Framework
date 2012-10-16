package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;

import pages.Home;

public class TestLayout {

	TestSetup setup = new TestSetup();
	

	@After
	public void tearDown() throws Exception {
		setup.getDriver().quit();
	}

	@Test
	public void testClickLivingMenuLoadsPageLiving() {
		Home home = new Home(setup.getDriver());
		home.getHomePage();
		if(!home.isLoggedIn()) {
			home.login("ivyshushu@gmail.com", "6314134223");
		}
		setup.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		home.header().clickLivingMenu();
		
		System.out.println(setup.getDriver().getCurrentUrl());
		System.out.println(setup.getDriver().getTitle());
		assertEquals("https://www.sneakpeeq.com/v/LIVING",setup.getDriver().getCurrentUrl());
		
	}

}
