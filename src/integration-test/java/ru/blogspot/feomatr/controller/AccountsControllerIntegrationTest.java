package ru.blogspot.feomatr.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.TransactionService;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author polovinkin.igor
 * @since 24.09.2015
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:AccountsControllerContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountsControllerIntegrationTest {

    /**
     * This field contains a reference to the used web application context.
     */
    @Resource
    private WebApplicationContext webApplicationContext;
    /**
     * This field contains a reference to the MockMvc object that is used in our integration tests.
     */
    private MockMvc mockMvc;

    @Resource
    private AccountService accountService;

    @Resource
    private ClientService clientService;

    @Resource
    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        long clientId = 10L;
        Client client = new Client(clientId, "firstName", "Address 100 1010", 20);
        clientService.saveClient(client);
        accountService.saveAccount(new Account(1L, client, new BigDecimal(100)));
        accountService.saveAccount(new Account(2L, client, new BigDecimal(200)));

        assertFalse(accountService.getAllAccounts().isEmpty());
    }

    @After
    public void tearDown() throws Exception {

        for (Transaction transaction : transactionService.getAll()) {
            transactionService.delete(transaction);
        }
        for (Client client : clientService.getAllClients()) {
            clientService.delete(client);
        }

        assertTrue(clientService.getAllClients().isEmpty());
        assertTrue(accountService.getAllAccounts().isEmpty());
        assertTrue(transactionService.getAll().isEmpty());
    }

    /**
     * static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*
     * <p/>
     * mockMvc.perform(get("/person/1"))
     * .andExpect(status.isOk())
     * .andExpect(content().mimeType(MediaType.APPLICATION_JSON))
     * .andExpect(jsonPath("$.person.name").equalTo("Jason"));
     * <p/>
     * mockMvc.perform(post("/form"))
     * .andExpect(status.isOk())
     * .andExpect(redirectedUrl("/person/1"))
     * .andExpect(model().size(1))
     * .andExpect(model().attributeExists("person"))
     * .andExpect(flash().attributeCount(1))
     * .andExpect(flash().attribute("message", "success!"));
     *
     * @throws Exception
     */
    @Test
    public void testShowAllAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("accounts"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.request().attribute("accounts", is(accountService.getAllAccounts())));
    }

    @Test
    public void testShowAllAccounts2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("page", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("accounts"))
                .andExpect(MockMvcResultMatchers.view().name("accounts"));
    }

    @Test
    public void shouldReturnExcelWhenSaveAllAccountsWithExcelFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("output", "excel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ExcelAccountsReportView"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("list"));
    }

    @Test
    public void shouldReturnExcelWhenSaveAllAccountsWithAnyFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("output", "anyformat"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ExcelAccountsReportView"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("list"));
    }

    @Test
    public void shouldShowTransferFrom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transferFrom", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transferFrom"));
    }


    @Test
    public void shouldShowTransferTo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transferTo", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transferTo"));
    }

    @Test
    public void shouldShowTransfer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transfer", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    @Test
    public void shouldDoTransferFromAccount() throws Exception {
        Long idFrom = accountService.getAllAccounts().get(0).getId();
        Long idTo = 0L;
	    BigDecimal amount = new BigDecimal(3);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/accounts")
		        .param("transferFrom", "")
                .param("accountFrom", idFrom.toString())
                .param("accountTo", idTo.toString())
                .param("amount", amount.toString())
                .param("dateTime", "");
        mockMvc.perform(request)
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", true))
		        .andExpect(MockMvcResultMatchers.view().name("transferFrom"));

	    int size = transactionService.getAll().size();
	    assertThat(size, is(1));

	    BigDecimal actualAmount = transactionService.getByFilter(idFrom, null, null, null).get(0).getAmount();
	    assertThat(actualAmount, is(closeTo(amount, BigDecimal.ZERO)));
    }

    @Test
    public void shouldDoOneTransferToAccount() throws Exception {
        Long idFrom = 0L;
        Long idTo = accountService.getAllAccounts().get(1).getId();
	    BigDecimal amount = new BigDecimal(2);
	    Broker broker = new Broker(idFrom, idTo, amount, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferTo", "")
		        .param("transferTo", "")
		        .param("accountFrom", idFrom.toString())
		        .param("accountTo", idTo.toString())
		        .param("amount", amount.toString())
		        .param("dateTime", ""))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", true))
		        .andExpect(MockMvcResultMatchers.view().name("transferTo"));

	    int size = transactionService.getAll().size();
	    assertThat(size, is(1));

	    BigDecimal actualAmount = transactionService.getByFilter(null, idTo, null, null).get(0).getAmount();
	    assertThat(actualAmount, is(closeTo(amount, BigDecimal.ZERO)));
    }

    @Test
    public void shouldDoTransferAccount() throws Exception {
        Long idFrom = accountService.getAllAccounts().get(0).getId();
        Long idTo = accountService.getAllAccounts().get(1).getId();
	    BigDecimal amount = new BigDecimal(1);
	    Broker broker = new Broker(idFrom, idTo, amount, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transfer", "")
		        .param("transfer", "")
		        .param("accountFrom", idFrom.toString())
		        .param("accountTo", idTo.toString())
		        .param("amount", amount.toString())
		        .param("dateTime", ""))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", true))
		        .andExpect(MockMvcResultMatchers.view().name("transfer"));

	    int size = transactionService.getAll().size();
	    assertThat(size, is(1));

	    BigDecimal actualAmount = transactionService.getByFilter(idFrom, idTo, null, null).get(0).getAmount();
	    assertThat(actualAmount, is(closeTo(amount, BigDecimal.ZERO)));
    }

    // todo fix and rename when will realised JB-50
    @Ignore
    @Test
    public void shouldDoTransferAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transfer", "").requestAttr("broker", broker))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
		        .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    // todo fix when will realised JB-50
    @Ignore
    @Test
    public void shouldDoTransferFromAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferFrom", "").requestAttr("broker", broker))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
		        .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    // todo fix when will realised JB-50
    @Ignore
    @Test
    public void shouldDoTransferToAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferTo", "").requestAttr("broker", broker))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
		        .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }
}