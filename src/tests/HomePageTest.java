package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

		TestSetup setup = new TestSetup();
	

	@After
	public void tearDown() throws Exception {
		setup.getDriver().quit();
	}

	@Test
	public void testEachMenuLink() {
		fail("Not yet implemented");
	}

}
