/**
 * 
 */
package ru.blogspot.feomatr.controller;

import java.util.List;

import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.dao.stub.ClientDaoStubImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

/**
 * Handles requests for the application Client List page
 * 
 * @author iipolovinkin
 *
 */
@Controller
public class ClientListController {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientListController.class);

	@RequestMapping(value = "clients")
	public String showCLients(Model model) {
		logger.info("showCLients");
		List<Client> clientList = new ClientDaoStubImpl().getAllClients();
		model.addAttribute("clientList", clientList);
		return "clients";
	}

	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public String showClient(@PathVariable("id") Long id, Model model) {
		logger.info("showClient");
		Client client = new ClientDaoStubImpl().getById(id);
		model.addAttribute("client", client);
		return "clients/show";
	}

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String showHome(Model model) {
		logger.info("showHome");
		return "home";
	}
}
