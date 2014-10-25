/**
 * 
 */
package ru.blogspot.feomatr.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private static final Logger LOGGER = LoggerFactory
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
		LOGGER.info(" {}", "showAccounts");
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "accounts";
	}

	@RequestMapping(method = RequestMethod.GET, params = "transferFrom")
	public String showTransferFrom(Broker broker, Model model) {
		LOGGER.info(" {}", "GET TransferFrom");
		model.addAttribute("broker", new Broker());
		return "transferFrom";
	}

	@RequestMapping(method = RequestMethod.POST, params = "transferFrom")
	public String doTransferFromAccount(Broker broker, Model model) {
		LOGGER.info("POST TransferFrom");
		Long amount = broker.getAmount();
		Account accountFrom;
		accountFrom = accountService.getAccountById(broker.getAccountFrom());
		if (accountFrom == null || amount == null) {
			LOGGER.info(" {}", "fail transferFrom");
			return "transferFrom";
		}
		LOGGER.info("a1 {}, a2 {}", accountFrom);
		//@ TODO should I wire Broker with AccountService and TransactionService ?? 
		// These three operations looks like one logical operation
		if (Broker.transferFrom(accountFrom, amount)) {
			accountService.update(accountFrom);
			transactionService
					.create(new Transaction(amount, accountFrom, null));
			LOGGER.info("a1' {}, a2' {}", accountFrom);
		} else {
			LOGGER.info("transfer from: {} amount:{} failed",
					accountFrom.toString(), amount);
		}

		return "transferFrom";
	}

	@RequestMapping(method = RequestMethod.GET, params = "transferTo")
	public String showTransferTo(Broker broker, Model model) {
		LOGGER.info(" {}", "GET TransferTo");
		model.addAttribute("broker", new Broker());
		return "transferTo";
	}

	@RequestMapping(method = RequestMethod.POST, params = "transferTo")
	public String doTransferToAccount(Broker broker, Model model) {
		LOGGER.info("POST TransferTo");
		Long amount = broker.getAmount();
		Account accountTo;
		accountTo = accountService.getAccountById(broker.getAccountTo());
		if (accountTo == null || amount == null) {
			LOGGER.info(" {}", "fail transferTo");
			return "transferTo";
		}
		LOGGER.info("a1 {}, a2 {}", accountTo);
		//@ TODO should I wire Broker with AccountService and TransactionService ?? 
		// These three operations looks like one logical operation
		Broker.transferTo(accountTo, amount);
		accountService.update(accountTo);
		transactionService.create(new Transaction(amount, null, accountTo));
		
		LOGGER.info("a1' {}, a2' {}", accountTo);

		return "transferTo";
	}

	@RequestMapping(method = RequestMethod.GET, params = "transfer")
	public String showTransfer(Broker broker, Model model) {
		LOGGER.info(" {}", "GET Transfer");
		model.addAttribute("broker", new Broker());
		return "transfer";
	}

	@RequestMapping(method = RequestMethod.POST, params = "transfer")
	public String doTransfer(Broker broker, Model model) {
		LOGGER.info("POST Transfer");
		Long amount = broker.getAmount();
		Account accountFrom;
		Account accountTo;
		accountFrom = accountService.getAccountById(broker.getAccountFrom());
		accountTo = accountService.getAccountById(broker.getAccountTo());
		if (accountFrom == null || accountTo == null || amount == null) {
			LOGGER.info(" {}", "fail");
			return "transfer";
		}
		LOGGER.info("a1 {}, a2 {}", accountFrom, accountTo);
		
		//@ TODO should I wire Broker with AccountService and TransactionService ?? 
		// These operations looks like one logical operation
		Broker.transfer(accountFrom, accountTo, amount);
		accountService.update(accountFrom);
		accountService.update(accountTo);
		transactionService.create(new Transaction(amount, accountFrom,
				accountTo));

		LOGGER.info("a1' {}, a2' {}", accountFrom, accountTo);

		return "transfer";
	}
}
