/**
 * 
 */
package ru.blogspot.feomatr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import ru.blogspot.feomatr.dao.Test.ClientDaoTestImpl;
import ru.blogspot.feomatr.entity.Client;

/**
 * Handles requests for the application Client List page
 * 
 * @author iipolovinkin
 *
 */
@Controller
public class ClientListController {
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String withParam(Model model) {
		// return "Obtained 'foo' query parameter value '" + foo + "'";
		List<Client> clientList = new ClientDaoTestImpl().getAllClients();
		model.addAttribute("clientList", clientList);
		return "clients";
	}
}
