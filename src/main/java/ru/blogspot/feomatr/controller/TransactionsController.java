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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TransactionsController.class);
	private TransactionService transactionService;

	@Inject
	public TransactionsController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping()
	public String showTransactions(Model model) {
		LOGGER.debug(" {}", "showTransactions");
		model.addAttribute("transactions", transactionService.getAll());
		model.addAttribute("formFilter", new FormFilter());
		return "transactions";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doFilter(FormFilter formFilter, Model model) {
		LOGGER.debug("doFilter formFilter: {}", formFilter);

		model.addAttribute("transactions", transactionService.getByFilter(
				formFilter.getIdFrom(), formFilter.getIdTo(),
				formFilter.getStartTime(), formFilter.getEndTime()));

		return "transactions";
	}
}
