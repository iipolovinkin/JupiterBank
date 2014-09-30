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

import ru.blogspot.feomatr.service.TransactionService;

/**
 * 
 * @author iipolovinkin
 *
 */

@Controller
@RequestMapping(value = "transactions")
public class TransactionsController {
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionsController.class);
	private TransactionService transactionService;

	@Inject
	public TransactionsController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping()
	public String showTransactions(Model model) {
		logger.info(" {}", "showTransactiosn");
		model.addAttribute("transactions",
				transactionService.getAllTransactions());
		return "transactions";
	}
}
