package ru.blogspot.feomatr.controller;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
import ru.blogspot.feomatr.formBean.ConverterUtils;
import ru.blogspot.feomatr.formBean.Paginator;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static ru.blogspot.feomatr.formBean.UIUtils.showErrorMessage;

/**
 * Handles requests for the application Client List page
 *
 * @author iipolovinkin
 */
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "clients")
public class ClientListController {
    private static final Logger log = LoggerFactory.getLogger(ClientListController.class);
    private final ClientService clientService;
    private final AccountService accountService;

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

    @RequestMapping(method = RequestMethod.GET)
    public String showClients(Model model, HttpServletRequest request) throws ServletRequestBindingException {
        log.info("showClients");

        List<Client> clients = null;
        int size = -1;
        try {
            clients = clientService.getAllClients();
            size = clients.size();
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }

        Integer pageNumber = ServletRequestUtils.getIntParameter(request, "page", 1);
        Integer count = Paginator.ROWS_COUNT_PER_PAGE;

        Paginator paginator = new Paginator(pageNumber, count, size);
        if (paginator.getLastIndex() == -1) {
            model.addAttribute("clientList", clients);
            return "clients";
        }
        List<Client> clientSublist = clients.subList(paginator.getFirstIndex(), paginator.getLastIndex());
        model.addAttribute("clientList", clientSublist);
        model.addAttribute("paginator", paginator);
        return "clients";
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

    @RequestMapping(value = "/{id}.xml", method = RequestMethod.GET, params = "output")
    public ModelAndView saveClient(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws ServletRequestBindingException {
        String output = ServletRequestUtils.getStringParameter(request, "output");
        Client client = null;
        try {
            client = clientService.getClientById(id);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            showErrorMessage("Operation failed", e);
        }
        Client client_ = ConverterUtils.getDetachedClient(client);
        if ("XML".equals(output.toUpperCase())) {
            //return xml view
            return new ModelAndView("XmlClientView", "data", client_);
        }

        //return xml view too
        return new ModelAndView("XmlClientView", "data", client_);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showClient(@PathVariable("id") Long id, Model model) {
        // todo implement pagination for accounts of one client
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
