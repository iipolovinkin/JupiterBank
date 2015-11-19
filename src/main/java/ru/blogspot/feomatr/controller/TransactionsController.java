package ru.blogspot.feomatr.controller;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
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

import static ru.blogspot.feomatr.formBean.UIUtils.showErrorMessage;


/**
 * @author iipolovinkin
 */
@NoArgsConstructor
@Controller
@RequestMapping(value = "transactions")
public class TransactionsController {
    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);
	@Setter
    private TransactionService transactionService;
	private String senderAccountNo;
	private String receiverAccountNo;
	private DateTime startTime = null;
	private DateTime endTime = null;


    public String showTransactions(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info(" {}", "showTransactions, start:" + stopwatch);
        List<Transaction> transactions = null;
        int size = -1;
        try {
            transactions = transactionService.getAll();
            size=transactions.size();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }

        model.addAttribute("formFilter", new FormFilter());

        Integer pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
        Integer count = Paginator.ROWS_COUNT_PER_PAGE;

        Paginator paginator = new Paginator(pageNumber, count, size);
        if (paginator.getLastIndex() == -1) {
            model.addAttribute("transactions", transactions);
            return "transactions";
        }
        log.info(" {}", "showTransactions, middle:" + stopwatch);
        List<Transaction> transactionSublist = transactions.subList(paginator.getFirstIndex(), paginator.getLastIndex());
        model.addAttribute("transactions", transactionSublist);
        model.addAttribute("paginator", paginator);
        log.info(" {}", "showTransactions, end:" + stopwatch);
        stopwatch.stop();

        return "transactions";
    }

	@RequestMapping()
	public String doFilter(Model model, HttpServletRequest request) {
		int size=0;
		List<Transaction> transactionByFilter = Lists.newArrayList();
		try {
			transactionByFilter = transactionService.getByFilter(senderAccountNo, receiverAccountNo, startTime, endTime);
			size = transactionByFilter.size();
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
		}

		model.addAttribute("formFilter", new FormFilter(senderAccountNo, receiverAccountNo, startTime, endTime));

		Integer pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		Integer count = Paginator.ROWS_COUNT_PER_PAGE;

		Paginator paginator = new Paginator(pageNumber, count, size);
		if (paginator.getLastIndex() == -1) {
			model.addAttribute("transactions", transactionByFilter);
			return "transactions";
		}
		List<Transaction> transactionSublist = transactionByFilter.subList(paginator.getFirstIndex(), paginator.getLastIndex());
		model.addAttribute("transactions", transactionSublist);
		model.addAttribute("paginator", paginator);

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
	public String doFilter(FormFilter formFilter, Model model, HttpServletRequest request) {
		log.debug("doFilter formFilter: {}", formFilter);

		if (!formFilter.getStartTime().isEmpty()) {
			startTime = DateTime.parse(formFilter.getStartTime(), FormFilter.DATE_FORMAT);
		} else {
			startTime = null;
		}
		if (!formFilter.getEndTime().isEmpty()) {
			endTime = DateTime.parse(formFilter.getEndTime(), FormFilter.DATE_FORMAT);
		} else {
			endTime = null;
		}

		int size = 0;
		List<Transaction> transactionByFilter = Lists.newArrayList();
		try {
			senderAccountNo = formFilter.getSenderAccountNo();
			receiverAccountNo = formFilter.getReceiverAccountNo();

			transactionByFilter = transactionService.getByFilter(senderAccountNo, receiverAccountNo, startTime, endTime);
			size = transactionByFilter.size();
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
		}

		Integer pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
		Integer count = Paginator.ROWS_COUNT_PER_PAGE;

		Paginator paginator = new Paginator(pageNumber, count, size);
		if (paginator.getLastIndex() == -1) {
			model.addAttribute("transactions", transactionByFilter);
			return "transactions";
		}
		List<Transaction> transactionSublist = transactionByFilter.subList(paginator.getFirstIndex(), paginator.getLastIndex());
		model.addAttribute("transactions", transactionSublist);
		model.addAttribute("paginator", paginator);

		return "transactions";
	}
}
