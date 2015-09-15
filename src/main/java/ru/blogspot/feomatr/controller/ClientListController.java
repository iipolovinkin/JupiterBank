package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.exceptions.ClientNotFoundException;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static ru.blogspot.feomatr.controller.UIUtils.showErrorMessage;

/**
 * Handles requests for the application Client List page
 *
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Controller
@RequestMapping(value = "clients")
public class ClientListController {
    private static final Logger log = LoggerFactory.getLogger(ClientListController.class);
    private ClientService clientService;
    private AccountService accountService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addAccountFromForm(@PathVariable("id") Long id, Model model) {
        log.info("addAccountFromForm {}", id);

        Client client = null;
        try {
            client = clientService.getClientById(id);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        Account account = new Account(client);
        log.info("account:  {}", account);
        try {
            accountService.saveAccount(account);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        log.info("account saved:  {}", account);

        return "redirect:/clients/" + client.getId();
    }

    @RequestMapping(method = RequestMethod.POST, params = "new")
    public String addClientFromForm(@Valid Client client,
                                    BindingResult bindingResult, Model model, HttpServletRequest request) {
        log.info("addClientFromForm");
        log.info("bindingResult.hasErrors {}", bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            log.info("fieldErrors: {}", bindingResult.getFieldErrors());
            return "clients/edit";
        }

        try {
            clientService.saveClient(client);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        log.info(client.toString());

        return "redirect:/clients/" + client.getId();
    }

    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createClientProfile(Model model) {
        log.info("createClientProfile");
        Client client = new Client("John", "45 Green Park Street", 18);
        model.addAttribute(client);
        return "clients/edit";
    }

    @RequestMapping()
    public String showClients(Model model) {
        log.info("showClients");

        List<Client> clients = null;
        try {
            clients = clientService.getAllClients();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        if (clients != null) {
            model.addAttribute("clientList", clients);
        }
        return "clients";
    }


    @RequestMapping(method = RequestMethod.GET, params = {"page", "count"})
    public String showClientsPage(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        log.info("showClients");

        Integer page = Integer.valueOf(ServletRequestUtils.getStringParameter(request, "page"));
        Integer count = Integer.valueOf(ServletRequestUtils.getStringParameter(request, "count"));

        List<Client> clients = null;
        try {
            clients = clientService.getAllClients();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        if (count < 1 || page < 1) {
            model.addAttribute("clientList", clients);
            return "clients";
        }
        int pageCount = clients.size() / count;
        if (clients.size() % count > 0) {
            ++pageCount;
        }
        if (page > pageCount) {
            model.addAttribute("clientList", clients);
            return "clients";
        } else {
            int toIndex = page * count;
            toIndex = toIndex > clients.size() ? clients.size() : toIndex;
            List<Client> clientSublist = clients.subList((page - 1) * count, toIndex);
            model.addAttribute("clientList", clientSublist);
            return "clients";
        }
    }


    @RequestMapping(method = RequestMethod.GET, params = "output")
    public ModelAndView saveAllClients(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");
        List<Client> clients = Lists.newArrayList();
        try {
            clients = clientService.getAllClients();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        model.addAttribute("list", clients);

        if ("EXCEL".equals(output.toUpperCase())) {
            //return excel view
            ModelAndView modelAndView = new ModelAndView("ExcelClientsReportView", "data", model);
            return modelAndView;
        }

        //return excel view too
        return new ModelAndView("ExcelClientsReportView", "data", model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showClient(@PathVariable("id") Long id, Model model) {
        log.info("showClient");
        Client client = null;
        try {
            client = clientService.getClientById(id);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        model.addAttribute("client", client);
        if (client == null) {
            try {
                throw new ClientNotFoundException(id);
            } catch (ResourceNotFoundException e) {
                log.error("CLIENT NOT FOUND");
            }
            return "clients/show";
        }
        Set<Account> accounts = client.getAccounts();
        log.info("accounts.size: {}", accounts.size());
        model.addAttribute("accounts", accounts);
        return "clients/show";
    }

}
