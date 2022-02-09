package ru.blogspot.feomatr.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.blogspot.feomatr.formBean.AdminClass;
import ru.blogspot.feomatr.formBean.ControllerHelper;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * todo use  human readable naming, use AAA, use assertThat
 *
 * @author iipolovinkin
 */
public class HomeControllerTest {
    private HomeController controller;
    private Model model;

    @BeforeEach
    void setUp() throws Exception {
        model = new ExtendedModelMap();
        controller = new HomeController();
    }

    @Test
    void testShowHome() throws Exception {
        String expectedView = "home";

        String actualView = controller.showHome(null);

        assertEquals(expectedView, actualView);
    }

    @Test
    void testShowHomeLogin() throws Exception {
        String expectedView = "login";

        String actualView = controller.showHomeLogin(null);

        assertEquals(expectedView, actualView);
    }

    @Test
    void testShowAdminPageReturnAdminPage() throws Exception {
        String expectedView = "admin_page";

        String actualView = controller.showAdminPage(model);

        assertEquals(expectedView, actualView);
    }

    @Test
    void testShowAdminPagePasteAdminObject() throws Exception {
        AdminClass adminObject = new AdminClass();

        controller.showAdminPage(model);

        assertNotNull(model.asMap().get("adminClass"));
    }

    @Test
    void testDoTransferToAccount() throws Exception {
        String expectedView = "admin_page";
        controller.setControllerHelper(mock(ControllerHelper.class));
        HttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("adminClass", new AdminClass());

        String actualView = controller.doGenerateData(mock(AdminClass.class), null);

        assertEquals(expectedView, actualView);
    }

}
