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

import ru.blogspot.feomatr.service.AccountService;

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

	@Inject
	public AccountsController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping()
	public String showAllAccounts(Model model) {
		logger.info(" {}", "showAccounts");
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "accounts";
	}
}
