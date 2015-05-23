package ru.blogspot.feomatr.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.blogspot.feomatr.formBean.FormFilter;
import ru.blogspot.feomatr.service.TransactionService;


/**
 * @author iipolovinkin
 */

@Controller
@RequestMapping(value = "transactions")
public class TransactionsController {
    private static final Logger log = LoggerFactory
            .getLogger(TransactionsController.class);
    private TransactionService transactionService;

    public TransactionsController() {
    }

    @RequestMapping()
    public String showTransactions(Model model) {
        log.debug(" {}", "showTransactions");
        model.addAttribute("transactions", transactionService.getAll());
        model.addAttribute("formFilter", new FormFilter());
        return "transactions";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doFilter(FormFilter formFilter, Model model) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        log.debug("doFilter formFilter: {}", formFilter);

        DateTime startTime = null;
        DateTime endTime = null;

        if (!formFilter.getStartTime().isEmpty()) {
            startTime = DateTime.parse(formFilter.getStartTime(), formatter);
        }
        ;
        if (!formFilter.getEndTime().isEmpty()) {
            endTime = DateTime.parse(formFilter.getEndTime(), formatter);
        }


        model.addAttribute("transactions", transactionService.getByFilter(formFilter.getIdFrom(), formFilter.getIdTo(), startTime, endTime));

        return "transactions";
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
