package ru.blogspot.feomatr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
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

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }

    @Test
    public void testShowAllAccounts() throws Exception {
        String expectedViewName = "accounts";
        List<Account> expectedAccounts = asList(new Account(), new Account());
        when(accountService.getAllAccounts()).thenReturn(expectedAccounts);

        String viewName = accountsController.showAllAccounts(model, mock(HttpServletRequest.class));

        assertEquals(expectedViewName, viewName);
        assertEquals(expectedAccounts, model.asMap().get("accounts"));
    }

    @Test
    public void testShowTransferFrom() throws Exception {
        String expectedViewName = "transferFrom";

        String viewName = accountsController.showTransferFrom(new Broker(), model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }


    @Test
    public void testShowTransferTo() throws Exception {
        String expectedViewName = "transferTo";

        String viewName = accountsController.showTransferTo(new Broker(), model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }

    @Test
    public void testShowTransfer() throws Exception {
        String expectedViewName = "transfer";

        String viewName = accountsController.showTransfer(new Broker(), model);

        assertEquals(expectedViewName, viewName);
        assertEquals(new Broker().toString(), model.asMap().get("broker").toString());
    }

}
