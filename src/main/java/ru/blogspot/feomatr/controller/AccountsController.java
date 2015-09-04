package ru.blogspot.feomatr.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransferService;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping()
    public String showAllAccounts(Model model) {
        log.info("showAccounts");
        model.addAttribute("accounts", accountService.getAllAccounts());

        return "accounts";
    }

    @RequestMapping(method = RequestMethod.GET, params = "output")
    public ModelAndView saveAllAccounts(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");

        model.addAttribute("list", accountService.getAllAccounts());

        if ("EXCEL".equals(output.toUpperCase())) {
            //return excel view
            ModelAndView modelAndView = new ModelAndView("ExcelAccountsReportView", "data", model);
        }

        //return excel view too
        return new ModelAndView("ExcelAccountsReportView", "data", model);
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferFrom")
    public String showTransferFrom(Broker broker, Model model) {
        log.info("GET TransferFrom");
        model.addAttribute("broker", new Broker());

        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferFrom")
    public String doTransferFromAccount(Broker broker, Model model) {
        boolean transfer = transferService.transferFrom(broker);
        log.info("POST TransferFrom transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferTo")
    public String showTransferTo(Broker broker, Model model) {
        log.info("GET TransferTo");
        model.addAttribute("broker", new Broker());

        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferTo")
    public String doTransferToAccount(Broker broker, Model model) {
        boolean transfer = transferService.transferTo(broker);
        log.info("POST TransferTo, transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transfer")
    public String showTransfer(Broker broker, Model model) {
        log.info("GET Transfer");
        model.addAttribute("broker", new Broker());

        return "transfer";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transfer")
    public String doTransfer(Broker broker, Model model) {
        boolean transfer = transferService.transfer(broker);
        log.info("POST Transfer transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transfer";
    }

}
