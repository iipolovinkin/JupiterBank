/**
 *
 */
package ru.blogspot.feomatr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransferService;

import javax.inject.Inject;

/**
 * @author iipolovinkin
 */

@Controller
@RequestMapping(value = "accounts")
public class AccountsController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AccountsController.class);
    private AccountService accountService;
    private TransferService transferService;

    @Inject
    public AccountsController(AccountService accountService,
                              TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @RequestMapping()
    public String showAllAccounts(Model model) {
        LOGGER.info(" {}", "showAccounts");
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferFrom")
    public String showTransferFrom(Broker broker, Model model) {
        LOGGER.info(" {}", "GET TransferFrom");
        model.addAttribute("broker", new Broker());
        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferFrom")
    public String doTransferFromAccount(Broker broker, Model model) {
        LOGGER.info("POST TransferFrom");
        Long amount = broker.getAmount();
        Account accountFrom;
        accountFrom = accountService.getAccountById(broker.getAccountFrom());
        LOGGER.info("a1 {}, a2 {}", accountFrom);
        boolean transfer = transferService.transferFrom(accountFrom, amount);
        LOGGER.info("transfer = {}, {}", transfer, accountFrom);

        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transferTo")
    public String showTransferTo(Broker broker, Model model) {
        LOGGER.info(" {}", "GET TransferTo");
        model.addAttribute("broker", new Broker());
        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferTo")
    public String doTransferToAccount(Broker broker, Model model) {
        LOGGER.info("POST TransferTo");
        Long amount = broker.getAmount();
        Account accountTo;
        Long aTo = broker.getAccountTo();
        LOGGER.info(String.valueOf(aTo));
        accountTo = accountService.getAccountById(aTo);
        LOGGER.info("a1 {}, a2 {}", accountTo, aTo);
        boolean transfer = transferService.transferTo(accountTo, amount);
        LOGGER.info("transfer = {}, {}", transfer, accountTo);
        return "transferTo";
    }

    @RequestMapping(method = RequestMethod.GET, params = "transfer")
    public String showTransfer(Broker broker, Model model) {
        LOGGER.info(" {}", "GET Transfer");
        model.addAttribute("broker", new Broker());
        return "transfer";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transfer")
    public String doTransfer(Broker broker, Model model) {
        LOGGER.info("POST Transfer");
        Long amount = broker.getAmount();
        Account accountFrom;
        Account accountTo;
        accountFrom = accountService.getAccountById(broker.getAccountFrom());
        accountTo = accountService.getAccountById(broker.getAccountTo());
        LOGGER.info("from: {}, to: {}", accountFrom, accountTo);
        boolean transfer = transferService.transfer(accountFrom, accountTo,
                amount);
        LOGGER.info("transfer = {}", transfer);
        return "transfer";
    }
}
