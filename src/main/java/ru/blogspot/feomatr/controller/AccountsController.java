package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.formBean.Paginator;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ServiceException;
import ru.blogspot.feomatr.service.TransferService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ru.blogspot.feomatr.formBean.UIUtils.showErrorMessage;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Controller
@RequestMapping(value = "accounts")
public class AccountsController {
    private static final Logger log = LoggerFactory.getLogger(AccountsController.class);

    private AccountService accountService;
    private TransferService transferService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAllAccounts(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        log.info("showAccounts");
        List<Account> accounts = null;
        int size = -1;
        try {
            accounts = accountService.getAllAccounts();
            size = accounts.size();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        Integer pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
        Integer count = Paginator.ROWS_COUNT_PER_PAGE;

        Paginator paginator = new Paginator(pageNumber, count, size);
        if (paginator.getLastIndex() == -1) {
            model.addAttribute("accounts", accounts);
            return "accounts";
        }
        List<Account> accountSublist = accounts.subList(paginator.getFirstIndex(), paginator.getLastIndex());
        model.addAttribute("accounts", accountSublist);
        model.addAttribute("paginator", paginator);

        return "accounts";
    }

    @RequestMapping(method = RequestMethod.GET, params = "output")
    public ModelAndView saveAllAccounts(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");

        List<Account> accounts = Lists.newArrayList();
        try {
            accounts = accountService.getAllAccounts();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        model.addAttribute("list", accounts);

        if ("EXCEL".equals(output.toUpperCase())) {
            //return excel view
            ModelAndView modelAndView = new ModelAndView("ExcelAccountsReportView", "data", model);
            return modelAndView;
        }

        //return excel view too
        return new ModelAndView("ExcelAccountsReportView", "data", model);
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferFrom")
    public String showTransferFrom(Model model) {
        log.info("GET TransferFrom");
        model.addAttribute("broker", new Broker());
        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferFrom")
    public String doTransferFromAccount(@ModelAttribute Broker broker, Model model) {
        boolean transfer = false;
        try {
            transfer = transferService.transferFrom(broker);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        log.info("POST TransferFrom transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);
        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferTo")
    public String showTransferTo(Model model) {
        log.info("GET TransferTo");
        model.addAttribute("broker", new Broker());

        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferTo")
    public String doTransferToAccount(@ModelAttribute Broker broker, Model model) {
        boolean transfer = false;
        try {
            transfer = transferService.transferTo(broker);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        log.info("POST TransferTo, transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transfer")
    public String showTransfer(Model model) {
        log.info("GET Transfer");
        model.addAttribute("broker", new Broker());

        return "transfer";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transfer")
    public String doTransfer(@ModelAttribute Broker broker, Model model) {
        boolean transfer = false;
        try {
            transfer = transferService.transfer(broker);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        log.info("POST Transfer transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transfer";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showAccount(@PathVariable("id") Long id, Model model) {
		Account account = null;
		try {
			account = accountService.getAccountById(id);
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
		}
		model.addAttribute("account", account);

		return "accounts/showAccount";
	}
}
