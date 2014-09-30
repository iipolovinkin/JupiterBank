/**
 * 
 */
package ru.blogspot.feomatr.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransactionService;

/**
 * 
 * @author iipolovinkin
 *
 */

@Controller
@RequestMapping(value = "accounts")
public class AccountsController {
	private static final Logger logger = LoggerFactory
			.getLogger(AccountsController.class);
	private AccountService accountService;
	private TransactionService transactionService;

	@Inject
	public AccountsController(AccountService accountService,
			TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@RequestMapping()
	public String showAllAccounts(Model model) {
		logger.info(" {}", "showAccounts");
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "accounts";
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String showTransfer(Broker broker, Model model) {
		logger.info(" {}", "GET Transfer");
		Long id1 = 3L, id2 = null, amount = null;
		model.addAttribute("broker", new Broker());
		return "transfer";
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public String doTransfer(Broker broker, Model model) {
		logger.info("POST Transfer");
		Long amount = broker.getAmount();
		Account accountFrom, accountTo;
		accountFrom = accountService.getAccountById(broker.getAccountFrom());
		accountTo = accountService.getAccountById(broker.getAccountTo());
		logger.info("a1 {}", accountFrom);
		logger.info("a2 {}", accountTo);
		logger.info("amount: {}", amount);
		return "transfer";
	}
}
