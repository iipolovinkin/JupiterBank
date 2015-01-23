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
import java.math.BigDecimal;

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
        boolean transfer = transferService.transferFrom(broker);
        LOGGER.info("transfer = {}, broker = {}", transfer, broker);

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
        boolean transfer = transferService.transferTo(broker);
        LOGGER.info("transfer = {}, broker = {}", transfer, broker);

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
        boolean transfer = transferService.transfer(broker);
        LOGGER.info("transfer = {}, broker = {}", transfer, broker);

        return "transfer";
    }
}
