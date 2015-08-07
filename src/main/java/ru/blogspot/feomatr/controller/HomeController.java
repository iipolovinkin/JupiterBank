package ru.blogspot.feomatr.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.blogspot.feomatr.formBean.AdminClass;

import javax.servlet.http.HttpServletRequest;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private ControllerHelper controllerHelper;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String showHome(Model model) {
        log.info("showHome");
        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String showHomeLogin(Model model) {
        log.info("login");
        return "login";
    }

    @RequestMapping(value = {"/admin_page"}, method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        log.info("admin_page");
        AdminClass adminClass = new AdminClass();
        adminClass.setClientsCount(50);
        adminClass.setAccountsCount(50);
        adminClass.setTransfersCount(50);
        adminClass.setThreadsCount(5);
        model.addAttribute(adminClass);
        return "admin_page";
    }

    @RequestMapping(value = {"/admin_page"}, method = RequestMethod.POST)
    public String doTransferToAccount(AdminClass adminClass, BindingResult bindingResult, Model model, HttpServletRequest request) {
        log.info("POST admin_page");
        log.info("adminClass = {}", adminClass);
        int clientsCount = adminClass.getClientsCount();
        int accountsCount = adminClass.getAccountsCount();
        int transfersCount = adminClass.getTransfersCount();
        int threadsCount = adminClass.getThreadsCount();
        controllerHelper.generateThreadCATs(clientsCount, accountsCount, transfersCount, threadsCount);

        return "admin_page";
    }
}
