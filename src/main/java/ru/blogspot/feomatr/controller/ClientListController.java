/**
 * 
 */
package ru.blogspot.feomatr.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;

/**
 * Handles requests for the application Client List page
 * 
 * @author iipolovinkin
 *
 */
@Controller
@RequestMapping(value = "clients")
public class ClientListController {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientListController.class);
	private ClientService clientService;
	private AccountService accountService;

	@Inject
	public ClientListController(ClientService clientService,
			AccountService accountService) {
		this.setClientService(clientService);
		this.accountService = accountService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String addAccountFromForm(@PathVariable("id") Long id, Model model) {
		// if (bindingResult.hasErrors()) {
		// return "ERROR!";
		// }
		logger.info("addAccountFromForm {}", id);

		Client client = clientService.getClientById(id);
		Account account = new Account(client);
		logger.info("account:  {}", account);
		accountService.saveAccount(account);
		logger.info("account saved:  {}", account);
		List<Account> accs = accountService.getAllAccounts();
		for (Account a : accs) {
			logger.info("a {}", a);
		}

		return "redirect:/clients/" + client.getId() + "/";
	}

	@RequestMapping(method = RequestMethod.POST, params = "new")
	public String addClientFromForm(@Valid Client client,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		logger.info("addClientFromForm");
		logger.info("bindingResult.hasErrors {}", bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			logger.info("fieldErrors: {}", bindingResult.getFieldErrors());
			return "clients/edit";
		}

		client = getClientService().saveClient(client);
		logger.info(client.toString());

		return "redirect:/clients/" + client.getId();
	}

	@RequestMapping(params = "new", method = RequestMethod.GET)
	public String createClientProfile(Model model) {
		logger.info("createClientProfile");
		model.addAttribute(new Client());
		return "clients/edit";
	}

	@RequestMapping()
	public String showClients(Model model) {
		logger.info("showClients");

		model.addAttribute("clientList", getClientService().getAllClients());
		return "clients";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showClient(@PathVariable("id") Long id, Model model) {
		logger.info("showClient");
		Client cl = getClientService().getClientById(id);
		model.addAttribute("client", cl);
		List<Account> accounts = accountService.getAccountsByOwner(cl);
		model.addAttribute("accounts", accounts);
		return "clients/show";
	}

	/**
	 * @return the clientService
	 */
	public ClientService getClientService() {
		return clientService;
	}

	/**
	 * @param clientService
	 *            the clientService to set
	 */
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

}
