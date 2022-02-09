package ru.blogspot.feomatr.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
@ExtendWith(SpringExtension.class)
public class AccountsWsControllerIntegrationTest {

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

	private Account accountNo1;
	private Account accountNo2;
	private Account accountNo14;

	@BeforeEach
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Client client = new Client(10L, "firstName", "Address 100 1010", 20);
		accountNo1 = new Account(1L, client, new BigDecimal(100));
		accountNo2 = new Account(2L, client, new BigDecimal(200));
		accountNo14 = new Account(14L, client, new BigDecimal(1400));

	}

	@AfterEach
	public void tearDown() throws Exception {
		Mockito.reset(accountService);
	}

	/**
	 * @throws Exception
	 * @see org.springframework.test.web.servlet.ResultActions#andExpect(ResultMatcher)
	 */
	@Test
	public void testGetAccountList() throws Exception {
		when(accountService.getAllAccounts()).thenReturn(Arrays.asList(accountNo1, accountNo2));

		mockMvc.perform(get("/ws/accounts/index"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.accountList", hasSize(2)))
				.andExpect(jsonPath("$.accountList[0].id").value(is(1)))
				.andExpect(jsonPath("$.accountList[0].balance").value(is(100)))
				.andExpect(jsonPath("$.accountList[1].id").value(is(2)))
				.andExpect(jsonPath("$.accountList[1].balance").value(is(200)));

		verify(accountService).getAllAccounts();
		verifyNoMoreInteractions(accountService);
	}

	@Test
	public void testGetEmptyAccountList() throws Exception {
		when(accountService.getAllAccounts()).thenReturn(Collections.<Account>emptyList());

		mockMvc.perform(get("/ws/accounts/index"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.accountList").value(emptyCollectionOf(Account.class)));

		verify(accountService).getAllAccounts();
		verifyNoMoreInteractions(accountService);
	}

	@Test
	public void testAccountIsFound() throws Exception {
		int accountId = accountNo2.getId().intValue();
		when(accountService.getAccountById(accountNo2.getId())).thenReturn(accountNo2);

		mockMvc.perform(get("/ws/accounts/show/" + accountId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.account.id").value(is(accountId)))
				.andExpect(jsonPath("$.account.balance").value(is(200)));

		verify(accountService).getAccountById(anyLong());
		verifyNoMoreInteractions(accountService);
	}

	@Disabled
	@Test
	public void testAccountIsNotFound() throws Exception {
		int accountId = 77777;
		Account acc = new Account();
		when(accountService.getAccountById((long) accountId)).thenThrow(new ServiceException("Account not found"));

		mockMvc.perform(get("/ws/accounts/show/" + accountId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.account").value(isNull()));

//		verify(accountService).getAccountById((long) accountId);
//		verifyNoMoreInteractions(accountService);
	}

	//todo how to set account's owner?
	@Disabled
	@Test
	public void testAddValidAccount() throws Exception {
		mockMvc.perform(post("/ws/accounts/add")
				.contentType(MediaType.TEXT_PLAIN)
				.content(new ObjectMapper().writeValueAsString(accountNo14)))
				//"{\"id\":14,\"balance\":12.00,\"accountNo\":\"40817810061010000015\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.account.id").value(is(14)))
				.andExpect(jsonPath("$.account.balance").value(is(1400)))
				.andExpect(jsonPath("$.status").value(is("всё хорошо")));
	}

	// todo repair test: validate account from Json;
	@Disabled
	@Test
	public void testAddInvalidAccount() throws Exception {
		String invalidJSON = "\"id\":124";
		mockMvc.perform(post("/ws/accounts/add")
				.contentType(MediaType.TEXT_PLAIN)
				.content(invalidJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.status").value(is("ошибка")));
	}

	@Disabled
	@Test
	public void testAddEmptyAccount() throws Exception {
		String content = "";

		mockMvc.perform(post("/ws/accounts/add")
				.contentType(MediaType.TEXT_PLAIN)
				.content(content))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.status").value(is("ошибка")));
	}

}