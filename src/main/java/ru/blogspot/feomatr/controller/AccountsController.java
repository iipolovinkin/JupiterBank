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
import ru.blogspot.feomatr.entity.Transaction;
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

	@RequestMapping(method = RequestMethod.GET, params = "transferTo")
	public String showTransferTo(Broker broker, Model model) {
		logger.info(" {}", "GET TransferTo");
		model.addAttribute("broker", new Broker());
		return "transferTo";
	}

	@RequestMapping(method = RequestMethod.POST, params = "transferTo")
	public String doTransferToAccount(Broker broker, Model model) {
		logger.info("POST TransferTo");
		Long amount = broker.getAmount();
		Account accountFrom = null, accountTo;
		accountTo = accountService.getAccountById(broker.getAccountTo());
		if (accountTo == null || amount == null) {
			logger.info(" {}", "fail transferTo");
			return "transferTo";
		}
		logger.info("a1 {}, a2 {}", accountFrom, accountTo);
		Broker.transferTo(accountTo, amount);
		transactionService.create(new Transaction(amount, null, accountTo));
		logger.info("a1' {}, a2' {}", accountFrom, accountTo);

		return "transferTo";
	}

	@RequestMapping(method = RequestMethod.GET, params = "transfer")
	public String showTransfer(Broker broker, Model model) {
		logger.info(" {}", "GET Transfer");
		model.addAttribute("broker", new Broker());
		return "transfer";
	}

	@RequestMapping(method = RequestMethod.POST, params = "transfer")
	public String doTransfer(Broker broker, Model model) {
		logger.info("POST Transfer");
		Long amount = broker.getAmount();
		Account accountFrom, accountTo;
		accountFrom = accountService.getAccountById(broker.getAccountFrom());
		accountTo = accountService.getAccountById(broker.getAccountTo());
		if (accountFrom == null || accountTo == null || amount == null) {
			logger.info(" {}", "fail");
			return "transfer";
		}
		logger.info("a1 {}, a2 {}", accountFrom, accountTo);
		Broker.transfer(accountFrom, accountTo, amount);
		transactionService.create(new Transaction(amount, accountFrom,
				accountTo));
		logger.info("a1' {}, a2' {}", accountFrom, accountTo);

		return "transfer";
	}
}
