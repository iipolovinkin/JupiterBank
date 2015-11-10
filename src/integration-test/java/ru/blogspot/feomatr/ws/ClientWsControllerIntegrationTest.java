package ru.blogspot.feomatr.ws;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.blogspot.feomatr.TestUtil;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;
import ru.blogspot.feomatr.service.TransactionService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author iipolovinkin
 * @since 04.11.2015
 */
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:serviceMocks.xml"//})
		, "classpath:baseWebAppContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientWsControllerIntegrationTest {

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

	private Client clientNo1;
	private Client clientNo2;

	private Account accountNo1;
	private Account accountNo2;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		clientNo1 = new Client(10L, "John", "Address 100 1010", 20);
		clientNo2 = new Client(20L, "James", "Address 100 1010", 120);

		accountNo1 = new Account(1L, clientNo1, new BigDecimal(100));
		accountNo2 = new Account(2L, clientNo1, new BigDecimal(200));
		Account account1 = new Account(3L, clientNo2, new BigDecimal(100));
		Account account2 = new Account(4L, clientNo2, new BigDecimal(200));
	}

	@After
	public void tearDown() throws Exception {
		Mockito.reset(clientService);
	}

	/**
	 * @throws Exception
	 * @see org.springframework.test.web.servlet.ResultActions#andExpect(ResultMatcher)
	 */
	@Test
	public void testGetClientList() throws Exception {
//		when(accountService.getAllAccounts()).thenReturn(Arrays.asList(accountNo1, accountNo2));
		when(clientService.getAllClients()).thenReturn(Arrays.asList(clientNo1, clientNo2));

		mockMvc.perform(get("/ws/clients/index"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.clientList", hasSize(2)))
				.andExpect(jsonPath("$.clientList[0].id").value(is(10)))
				.andExpect(jsonPath("$.clientList[0].firstname").value(is("John")))
				.andExpect(jsonPath("$.clientList[0].age").value(is(20)))
				.andExpect(jsonPath("$.clientList[1].id").value(is(20)))
				.andExpect(jsonPath("$.clientList[1].firstname").value(is("James")))
				.andExpect(jsonPath("$.clientList[1].age").value(is(120)));

		verify(clientService).getAllClients();
		verifyNoMoreInteractions(clientService);
	}

	@Test
	public void testGetEmptyClientList() throws Exception {
		when(clientService.getAllClients()).thenReturn(Collections.<Client>emptyList());

		mockMvc.perform(get("/ws/clients/index"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.clientList").value(emptyCollectionOf(Client.class)));

		verify(clientService).getAllClients();
		verifyNoMoreInteractions(clientService);
	}

	@Test
	public void testClientIsFound() throws Exception {
		int clientId = clientNo2.getId().intValue();
		when(clientService.getClientById(clientNo2.getId())).thenReturn(clientNo2);

		mockMvc.perform(get("/ws/clients/show/" + clientId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.client.id").value(is(clientId)))
				.andExpect(jsonPath("$.client.firstname").value(is("James")))
				.andExpect(jsonPath("$.client.age").value(is(120)));

		verify(clientService).getClientById(anyLong());
		verifyNoMoreInteractions(clientService);
	}

	@Test
	@Ignore
	public void testClientIsNotFound() throws Exception {
		int clientId = 77777;
		when(clientService.getClientById((long) clientId)).thenThrow(new ServiceException("Client not found"));

		mockMvc.perform(get("/ws/clients/show/" + clientId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.client").value(isNull()));

		verify(clientService).getClientById(anyLong());
		verifyNoMoreInteractions(clientService);
	}

}