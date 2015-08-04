package ru.blogspot.feomatr.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class HomeControllerTest {

    @Test
    public void testShowHome() throws Exception {
        HomeController controller = new HomeController();
        String showHome = controller.showHome(null);
        assertEquals("home", showHome);
    }

}
