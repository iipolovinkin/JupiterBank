package ru.blogspot.feomatr.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.exceptions.ClientNotFoundException;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author iipolovinkin
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientListControllerTest {
    private MockHttpServletRequest request;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private AccountService accountService;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientListController clientListController;
    private Model model;
    private Client expectedClient;
    private Long id = 1l;

    @Before
    public void setUp() {
        model = new ExtendedModelMap();
        request = new MockHttpServletRequest();
        expectedClient = new Client(id, "name", "address rnd", 20);
    }

    @Test
    public void testAddAccountFromForm() throws Exception {
        String expectedView = "redirect:/clients/" + id;
        when(clientService.getClientById(id)).thenReturn(expectedClient);

        String resultView = clientListController.addAccountFromForm(id, model);

        assertEquals(expectedView, resultView);
    }

    @Test
    public void testAddClientFromForm_WithoutErrors() throws Exception {
        String expectedView = "redirect:/clients/" + id;
        BindingResult bindingResult = mock(BindingResult.class);
        HttpServletRequest request = this.request;

        when(bindingResult.hasErrors()).thenReturn(false);
        when(clientService.saveClient(expectedClient)).thenReturn(expectedClient);

        String resultView = clientListController.addClientFromForm(expectedClient, bindingResult, model, request);

        assertEquals(expectedView, resultView);
    }

    @Test
    public void testAddClientFromForm_WithBindingResultError() throws ServiceException {
        String expectedView = "clients/edit";
        BindingResult bindingResult = mock(BindingResult.class);
        HttpServletRequest request = this.request;

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(clientService.saveClient(expectedClient)).thenReturn(expectedClient);

        String resultView = clientListController.addClientFromForm(expectedClient, bindingResult, model, request);

        assertEquals(expectedView, resultView);
    }

    @Test
    public void testCreateClientProfile() {
        String expectedView = "clients/edit";

        String resultView = clientListController.createClientProfile(model);

        assertEquals(expectedView, resultView);
        assertNotNull(model.asMap().get("client"));
    }

    @Test
    public void testShowClients() throws Exception {
        String expectedView = "clients";
        List<Client> expectedClients = newArrayList();
        when(clientService.getAllClients()).thenReturn(expectedClients);

        String view = clientListController.showClients(model, request);

        assertEquals(expectedView, view);
        assertSame(expectedClients, model.asMap().get("clientList"));
    }

    @Test
    public void testShowClientDetails() throws Exception {
        String expectedView = "clients/show";
        when(clientService.getClientById(id)).thenReturn(expectedClient);

        String resultView = clientListController.showClient(id, model);

        assertEquals(expectedView, resultView);
        assertSame(expectedClient, model.asMap().get("client"));
    }

    @Test
    public void testShowNonExistenceClientDetails() {
        String expectedView = "clients/show";
//        when(clientService.getClientById(10000l)).thenReturn(null);

        expectedException.expect(ClientNotFoundException.class);
        String resultView = clientListController.showClient(id, model);

        assertEquals(expectedView, resultView);
        assertSame(expectedClient, model.asMap().get("client"));
    }

    @Test
    public void testSaveAllClients() throws Exception {
        ModelAndView modelAndView = new ModelAndView("ExcelClientsReportView", "data", model);
        request.setParameter("output", "excel");

        ModelAndView actualModelAndView = clientListController.saveAllClients(model, request);

        assertThat(actualModelAndView.getViewName(), is(modelAndView.getViewName()));
        assertTrue(actualModelAndView.getModel().containsKey("data"));

    }
}
