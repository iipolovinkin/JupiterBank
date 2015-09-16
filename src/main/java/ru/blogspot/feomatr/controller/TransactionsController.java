package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
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
import ru.blogspot.feomatr.formBean.Paginator;
import ru.blogspot.feomatr.service.ServiceException;
import ru.blogspot.feomatr.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ru.blogspot.feomatr.controller.UIUtils.showErrorMessage;


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
    public String showTransactions(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        log.info(" {}", "showTransactions");
        List<Transaction> transactions = null;
        int size = -1;
        try {
            transactions = transactionService.getAll();
            size=transactions.size();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }

        String page = ServletRequestUtils.getStringParameter(request, "page");
        Integer pageNumber = page == null ? 1 : Integer.valueOf(page);
        Integer count = Paginator.CLIENTS_COUNT_PER_PAGE;

        Paginator paginator = new Paginator(pageNumber, count, size);
        if (paginator.getLastIndex() == -1) {
            model.addAttribute("transactions", transactions);
            return "transactions";
        }

        List<Transaction> transactionSublist = transactions.subList(paginator.getFirstIndex(), paginator.getLastIndex());
        model.addAttribute("transactions", transactionSublist);
        model.addAttribute("paginator", paginator);

        model.addAttribute("formFilter", new FormFilter());

        return "transactions";
    }

    @RequestMapping(method = RequestMethod.GET, params = "output")
    public ModelAndView saveAllTransactions(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");
        List<Transaction> transactions = Lists.newArrayList();
        try {
            transactions = transactionService.getAll();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        model.addAttribute("list", transactions);

        if ("EXCEL".equals(output.toUpperCase())) {
            //return excel view
            ModelAndView modelAndView = new ModelAndView("ExcelTransactionsReportView", "data", model);
            return modelAndView;
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

        try {
            List<Transaction> transactionByFilter = transactionService.getByFilter(formFilter.getIdFrom(), formFilter.getIdTo(), startTime, endTime);
            model.addAttribute("transactions", transactionByFilter);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }

        return "transactions";
    }
}
