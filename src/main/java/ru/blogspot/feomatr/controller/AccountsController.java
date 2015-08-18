package ru.blogspot.feomatr.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.TransferService;

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

    @RequestMapping(method = RequestMethod.GET, params = "transferFrom")
    public String showTransferFrom(Broker broker, Model model) {
        log.info("GET TransferFrom");
        model.addAttribute("broker", new Broker());

        return "transferFrom";
    }

    @RequestMapping(method = RequestMethod.POST, params = "transferFrom")
    public String doTransferFromAccount(Broker broker, Model model) {
        log.info("POST TransferFrom");
        boolean transfer = transferService.transferFrom(broker);
        log.info("transfer = {}, broker = {}", transfer, broker);
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
        log.info("POST TransferTo");
        boolean transfer = transferService.transferTo(broker);
        log.info("transfer = {}, broker = {}", transfer, broker);
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
        log.info("POST Transfer");
        boolean transfer = transferService.transfer(broker);
        log.info("transfer = {}, broker = {}", transfer, broker);
        model.addAttribute("isTransfered", transfer);

        return "transfer";
    }

}
