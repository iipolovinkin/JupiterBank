package ru.blogspot.feomatr.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author iipolovinkin
 */
@ExtendWith(MockitoExtension.class)
public class ClientListControllerTest {
    private MockHttpServletRequest request;
    @Mock
    private AccountService accountService;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientListController clientListController;
    private Model model;
    private Client expectedClient;
    private Long id = 1l;

    @BeforeEach
    void setUp() {
        model = new ExtendedModelMap();
        request = new MockHttpServletRequest();
        expectedClient = new Client(id, "name", "address rnd", 20);
    }

    @Test
    void testAddAccountFromForm() throws Exception {
        String expectedView = "redirect:/clients/" + id;
        when(clientService.getClientById(id)).thenReturn(expectedClient);

        String resultView = clientListController.addAccountFromForm(id, model);

        assertEquals(expectedView, resultView);
    }

    @Test
    void testAddClientFromForm_WithoutErrors() throws Exception {
        String expectedView = "redirect:/clients/" + id;
        BindingResult bindingResult = mock(BindingResult.class);
        HttpServletRequest request = this.request;

        when(bindingResult.hasErrors()).thenReturn(false);
        when(clientService.saveClient(expectedClient)).thenReturn(expectedClient);

        String resultView = clientListController.addClientFromForm(expectedClient, bindingResult, model, request);

        assertEquals(expectedView, resultView);
    }

    @Test
    void testAddClientFromForm_WithBindingResultError() throws ServiceException {
        String expectedView = "clients/edit";
        BindingResult bindingResult = mock(BindingResult.class);
        HttpServletRequest request = this.request;

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(clientService.saveClient(expectedClient)).thenReturn(expectedClient);

        String resultView = clientListController.addClientFromForm(expectedClient, bindingResult, model, request);

        assertEquals(expectedView, resultView);
    }

    @Test
    void testCreateClientProfile() {
        String expectedView = "clients/edit";

        String resultView = clientListController.createClientProfile(model);

        assertEquals(expectedView, resultView);
        assertNotNull(model.asMap().get("client"));
    }

    @Test
    void testShowClients() throws Exception {
        String expectedView = "clients";
        List<Client> expectedClients = newArrayList();
        when(clientService.getAllClients()).thenReturn(expectedClients);

        String view = clientListController.showClients(model, request);

        assertEquals(expectedView, view);
        assertSame(expectedClients, model.asMap().get("clientList"));
    }

    @Test
    void testShowClientDetails() throws Exception {
        String expectedView = "clients/show";
        when(clientService.getClientById(id)).thenReturn(expectedClient);

        String resultView = clientListController.showClient(id, model);

        assertEquals(expectedView, resultView);
        assertSame(expectedClient, model.asMap().get("client"));
    }

    @Test
    void testShowNonExistenceClientDetails() {
        assertThrows(ClientNotFoundException.class, () -> clientListController.showClient(id, model));
    }

    @Test
    void testSaveAllClients() throws Exception {
        ModelAndView modelAndView = new ModelAndView("ExcelClientsReportView", "data", model);
        request.setParameter("output", "excel");

        ModelAndView actualModelAndView = clientListController.saveAllClients(model, request);

        assertThat(actualModelAndView.getViewName(), is(modelAndView.getViewName()));
        assertTrue(actualModelAndView.getModel().containsKey("data"));

    }
}
