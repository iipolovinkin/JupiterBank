package ru.blogspot.feomatr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author iipolovinkin
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountsControllerTest {
    @Mock
    private AccountService accountService;
    @Mock
    private TransferService transferService;
    @InjectMocks
    private AccountsController accountsController;

    private Model model;
    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
        request = new MockHttpServletRequest();
    }

    @Test
    public void testShowAllAccounts() throws Exception {
        String expectedViewName = "accounts";
        List<Account> expectedAccounts = asList(new Account(), new Account());
        when(accountService.getAllAccounts()).thenReturn(expectedAccounts);

        String viewName = accountsController.showAllAccounts(model, mock(HttpServletRequest.class));

        assertEquals(expectedViewName, viewName);
        assertEquals(expectedAccounts, (List<Account>) model.asMap().get("accounts"));
    }

    @Test
    public void testShowTransferFrom() throws Exception {
        String expectedViewName = "transferFrom";

        String viewName = accountsController.showTransferFrom(model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }


    @Test
    public void testShowTransferTo() throws Exception {
        String expectedViewName = "transferTo";

        String viewName = accountsController.showTransferTo(model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }

    @Test
    public void testShowTransfer() throws Exception {
        String expectedViewName = "transfer";

        String viewName = accountsController.showTransfer(model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }

    @Test
    public void testSaveAllAccounts() throws Exception {
        ModelAndView modelAndView = new ModelAndView("ExcelAccountsReportView", "data", model);
        request.setParameter("output", "excel");

        ModelAndView actualModelAndView = accountsController.saveAllAccounts(model, request);

        assertThat(actualModelAndView.getViewName(), is(modelAndView.getViewName()));
        assertTrue(actualModelAndView.getModel().containsKey("data"));

    }

	@Test
	public void shouldReturnExistsAccount() throws Exception {
		String expectedViewName = "accounts/showAccount";
		Long id = 0L;
		Client owner = new Client("James", "37 Red road", 15);
		Account expectedAccount = new Account(id, owner, 10L);
		when(accountService.getAccountById(0L)).thenReturn(expectedAccount);

		String viewName = accountsController.showAccount(id, model);

		assertEquals(expectedViewName, viewName);
		assertSame(expectedAccount, model.asMap().get("account"));
	}

	@Test
	public void shouldReturnNullWhenAccountDoesNotExist() throws Exception {
		String expectedViewName = "accounts/showAccount";
		Long id = 100000L;
		Account expectedAccount = null;
		when(accountService.getAccountById(id)).thenReturn(null);

		String viewName = accountsController.showAccount(id, model);

		assertEquals(expectedViewName, viewName);
		assertSame(expectedAccount, model.asMap().get("account"));
	}
}
