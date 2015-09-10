package ru.blogspot.feomatr.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.blogspot.feomatr.formBean.AdminClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class HomeControllerTest {
    private HomeController controller;
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        controller = new HomeController();
    }

    @Test
    public void testShowHome() throws Exception {
        String expectedView = "home";

        String actualView = controller.showHome(null);

        assertEquals(expectedView, actualView);
    }

    @Test
    public void testShowHomeLogin() throws Exception {
        String expectedView = "login";

        String actualView = controller.showHomeLogin(null);

        assertEquals(expectedView, actualView);
    }

    @Test
    public void testShowAdminPageReturnAdminPage() throws Exception {
        String expectedView = "admin_page";

        String actualView = controller.showAdminPage(model);

        assertEquals(expectedView, actualView);
    }

    @Test
    public void testShowAdminPagePasteAdminObject() throws Exception {
        AdminClass adminObject = new AdminClass();

        controller.showAdminPage(model);

        assertNotNull(model.asMap().get("adminClass"));
    }

    @Test
    public void testDoTransferToAccount() throws Exception {
        String expectedView = "admin_page";
        controller.setControllerHelper(mock(ControllerHelper.class));

        String actualView = controller.doGenerateData(mock(AdminClass.class), null, null, null);

        assertEquals(expectedView, actualView);
    }

}
