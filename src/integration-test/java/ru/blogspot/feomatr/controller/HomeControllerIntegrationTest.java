package ru.blogspot.feomatr.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.TransactionService;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author iipolovinkin
 * @since 24.09.2015
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:controllerITContext.xml"})
@ExtendWith(SpringExtension.class)
public class HomeControllerIntegrationTest {

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Resource
    private AccountService accountService;

    @Resource
    private ClientService clientService;

    @Resource
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
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

    @Test
    public void testShowHome() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testShowHomeHome() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/home");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testShowHomeLogin() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/login");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    public void testShowAdminPage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin_page");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("adminClass"))
                .andExpect(MockMvcResultMatchers.view().name("admin_page"));
    }

    @Test
    public void shouldGenerateData() throws Exception {
        String clientCount = "2";
        String threadsCount = "2";
	    String expectedClientCount = String.valueOf(Integer.valueOf(clientCount) * Integer.valueOf(threadsCount));
	    String accountsCount = "3";
        String transfersCount = "3";

	    // admin_page.jsp has 4 parameters: clientsCount, accountsCount, transfersCount, threadsCount.
	    // well, set them.
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/admin_page")
                .param("clientsCount", clientCount)
                .param("accountsCount", accountsCount)
                .param("transfersCount", transfersCount)
                .param("threadsCount", threadsCount);
	    
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.model().attributeExists("adminClass"))
		        .andExpect(MockMvcResultMatchers.view().name("admin_page"));

        String actualClientCount = String.valueOf(clientService.getAllClients().size());
        assertEquals(expectedClientCount, actualClientCount);
    }

}