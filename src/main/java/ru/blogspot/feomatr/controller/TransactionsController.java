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
import ru.blogspot.feomatr.formBean.FormFilter;
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

	@RequestMapping(method = RequestMethod.POST, params = "clear")
	public String clearInput(Model model) {
		logger.info(" {}", "clearInput");
		model.addAttribute("transactions", transactionService.getAll());
		model.addAttribute("formFilter", new FormFilter());
		return "transactions";
	}

	@RequestMapping()
	public String showTransactions(Model model) {
		logger.info(" {}", "showTransactions");
		model.addAttribute("transactions", transactionService.getAll());
		model.addAttribute("formFilter", new FormFilter());
		return "transactions";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doFilter(FormFilter formFilter, Model model) {
		logger.info("do Filter");
		logger.info("FF: {}", formFilter);
		Account a1, a2;
		// TransactionFilter = new TransactionFilter();
		// model.addAttribute("transactionFilter", transactionFilter);
		// model.addAttribute("formFilter", new FormFilter());

		model.addAttribute("transactions", transactionService.getByFilter(
				formFilter.getIdFrom(), formFilter.getIdTo(),
				formFilter.getStartTime(), formFilter.getEndTime()));

		return "transactions";
	}
	// class FormFilter {

	// }
}
