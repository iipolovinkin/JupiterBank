package ru.blogspot.feomatr.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author iipolovinkin
 *
 */
public class HomeControllerTest {

	@Test
	public void testShowHome() throws Exception {
		HomeController controller = new HomeController();
		String showHome = controller.showHome(null);
		assertEquals("home", showHome);
	}

}
