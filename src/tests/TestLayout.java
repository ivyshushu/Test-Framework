package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;

import pages.Base.HeaderRegion;
import pages.Home;
import regions.HeaderMenu;

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
		home.header().clickMenu("living");
		
		System.out.println(setup.getDriver().getCurrentUrl());
		System.out.println(setup.getDriver().getTitle());
		assertEquals("https://www.sneakpeeq.com/v/LIVING",setup.getDriver().getCurrentUrl());
	}
	
	@Test
	public void testHoverLivingMenuLoadsMenu() {
		Home home = new Home(setup.getDriver());
		home.getHomePage();
		if(!home.isLoggedIn()) {
			home.login("ivyshushu@gmail.com", "6314134223");
		}
		setup.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    HeaderRegion header = home.header();
		HeaderMenu menu = header.hoverMenu("living");
		assertEquals("living", menu.getMenuName());
		assertEquals(true, menu.isDropdownMenuVisible());

	}

}
