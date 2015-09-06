package ru.blogspot.feomatr.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.formBean.FormFilter;
import ru.blogspot.feomatr.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Controller
@RequestMapping(value = "transactions")
public class TransactionsController {
    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);
    private TransactionService transactionService;

    @RequestMapping()
    public String showTransactions(Model model) {
        log.debug(" {}", "showTransactions");
        model.addAttribute("transactions", transactionService.getAll());
        model.addAttribute("formFilter", new FormFilter());
        return "transactions";
    }

    @RequestMapping(method = RequestMethod.GET, params = "output")
    public ModelAndView saveAllTransactions(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("list", transactions);

        if ("EXCEL".equals(output.toUpperCase())) {
            //return excel view
            ModelAndView modelAndView = new ModelAndView("ExcelTransactionsReportView", "data", model);
        }

        //return excel view too
        return new ModelAndView("ExcelTransactionsReportView", "data", model);
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

        if (!formFilter.getEndTime().isEmpty()) {
            endTime = DateTime.parse(formFilter.getEndTime(), formatter);
        }

        model.addAttribute("transactions", transactionService.getByFilter(formFilter.getIdFrom(), formFilter.getIdTo(), startTime, endTime));

        return "transactions";
    }
}
