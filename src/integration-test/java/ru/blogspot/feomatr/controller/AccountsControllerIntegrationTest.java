package ru.blogspot.feomatr.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author polovinkin.igor
 *
 * @since 24.09.2015
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:controllerITContext.xml"})
@ExtendWith(SpringExtension.class)
public class AccountsControllerIntegrationTest {

    /**
     * This field contains a reference to the used web application context.
     */
    @Resource
    private WebApplicationContext webApplicationContext;
    /**
     * This field contains a reference to the MockMvc object that is used in our integration tests.
     */
    @Autowired
    private MockMvc mockMvc;

    @Resource
    @Autowired
    private AccountService accountService;

    @Resource
    @Autowired
    private ClientService clientService;

    @Resource
    private TransactionService transactionService;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        long clientId = 10L;
        Client client = new Client(clientId, "firstName", "Address 100 1010", 20);
        clientService.saveClient(client);
        accountService.saveAccount(new Account(1L, client, new BigDecimal(100)));
        accountService.saveAccount(new Account(2L, client, new BigDecimal(200)));

        assertFalse(accountService.getAllAccounts().isEmpty());
    }

    @AfterEach
    void tearDown() throws Exception {

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
    void testShowAllAccounts() throws Exception {

        List<Account> allAccounts = accountService.getAllAccounts();
        System.out.println("allAccounts = " + allAccounts);
//        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"));
//                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.view().name("accounts"))
//                .andExpect(MockMvcResultMatchers.model().size(2))
//                .andExpect(MockMvcResultMatchers.request().attribute("accounts", is(accountService.getAllAccounts())));
    }

    @Test
    void testShowAllAccounts2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("page", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("accounts"))
                .andExpect(MockMvcResultMatchers.view().name("accounts"));
    }

    @Test
    void shouldReturnExcelWhenSaveAllAccountsWithExcelFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("output", "excel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ExcelAccountsReportView"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("list"));
    }

    @Test
    void shouldReturnExcelWhenSaveAllAccountsWithAnyFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("output", "anyformat"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ExcelAccountsReportView"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("list"));
    }

    @Test
    void shouldShowTransferFrom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transferFrom", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transferFrom"));
    }


    @Test
    void shouldShowTransferTo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transferTo", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transferTo"));
    }

    @Test
    void shouldShowTransfer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts").param("transfer", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    @Test
    void shouldDoTransferFromAccount() throws Exception {
        String idFrom = accountService.getAllAccounts().get(0).getAccountNo();
        String idTo = "";
        BigDecimal amount = new BigDecimal(3);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/accounts")
                .param("transferFrom", "")
                .param("senderAccountNo", idFrom)
                .param("receiverAccountNo", idTo)
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
    void shouldDoOneTransferToAccount() throws Exception {
        String idFrom = "";
        String idTo = accountService.getAllAccounts().get(1).getAccountNo();
        BigDecimal amount = new BigDecimal(2);
        Broker broker = new Broker(idFrom, idTo, amount, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferTo", "")
                .param("transferTo", "")
                .param("senderAccountNo", idFrom)
                .param("receiverAccountNo", idTo)
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
    void shouldDoTransferAccount() throws Exception {
        String idFrom = accountService.getAllAccounts().get(0).getAccountNo();
        String idTo = accountService.getAllAccounts().get(1).getAccountNo();
        System.out.println("accountService.getAllAccounts().get(0) = " + accountService.getAllAccounts().get(0));
        System.out.println("accountService.getAllAccounts().get(1) = " + accountService.getAllAccounts().get(1));
        BigDecimal amount = new BigDecimal(1);
        System.out.println("idFrom = " + idFrom);
        System.out.println("idTo = " + idTo);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transfer", "")
                .param("transfer", "")
                .param("senderAccountNo", idFrom)
                .param("receiverAccountNo", idTo)
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
    @Disabled
    @Test
    void shouldDoTransferAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transfer", "").requestAttr("broker", broker))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
                .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    // todo fix when will realised JB-50
    @Disabled
    @Test
    void shouldDoTransferFromAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferFrom", "").requestAttr("broker", broker))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
                .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }

    // todo fix when will realised JB-50
    @Disabled
    @Test
    void shouldDoTransferToAccountExceptException() throws Exception {
        Broker broker = new Broker();

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts").param("transferTo", "").requestAttr("broker", broker))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("isTransfered", false))
                .andExpect(MockMvcResultMatchers.view().name("transfer"));
    }
}