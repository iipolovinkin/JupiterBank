package ru.blogspot.feomatr.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.blogspot.feomatr.formBean.UIUtils.showErrorMessage;

/**
 * @author iipolovinkin
 * @since 30.10.2015
 */
@Controller
@RequestMapping(value = "/ws/accounts", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class AccountsWsController {
	private static final Logger log = LoggerFactory.getLogger(AccountsWsController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/index")
	@ResponseBody
	public Map<String, Object> getAccounts() throws ServletRequestBindingException {
		log.trace("get accounts");
		Map<String, Object> map = new HashMap<String, Object>();

		List<Account> accounts = null;
		try {
			accounts = accountService.getAllAccounts();
			map.put("accountList", accounts);
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
			return map;
		}
		return map;
	}

	@RequestMapping(value = "/show/{accountId}")
	@ResponseBody
	public Map<String, Object> getAccount(@PathVariable("accountId") Long id) {
		log.trace("get account with id: {}", id);
		Map<String, Object> map = new HashMap<String, Object>();

		Account account = null;
		try {
			account = accountService.getAccountById(id);
			map.put("account", account);
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
			return map;
		}
		return map;
	}

	//todo you should add account to a client, think about it
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public Map<String, Object> addAccount(@RequestBody String json) throws ServletRequestBindingException {
		Account account = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			account = new ObjectMapper().readValue(json, Account.class);
			log.trace("add account: {}", account);
			accountService.saveAccount(account);
			map.put("status", "всё хорошо");
			map.put("account", account);
			return map;
		} catch (IOException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		} catch (ServiceException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		}
		return map;
	}

	//accounts should not be updated trough this interface, use transactions
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public Map<String, Object> updateAccount(@RequestBody String json) throws ServletRequestBindingException {
		return new HashMap<>();
	}

	//accounts should not be deleted, they could be closed
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAccount(@PathVariable("accountId") Long id) {
		return new HashMap<>();
	}


}
