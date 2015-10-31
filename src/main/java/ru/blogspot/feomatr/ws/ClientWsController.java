package ru.blogspot.feomatr.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.blogspot.feomatr.formBean.UIUtils.showErrorMessage;

/**
 * @author iipolovinkin
 * @since 30.10.2015
 */
@Controller
@RequestMapping(value = "/ws/clients", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class ClientWsController {
	private static final Logger log = LoggerFactory.getLogger(ClientWsController.class);
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/index")
	@ResponseBody
	public Map<String, Object> showClients() throws ServletRequestBindingException {
		log.trace("show clients");
		Map<String, Object> map = new HashMap<String, Object>();

		List<Client> clients = null;
		try {
			clients = clientService.getAllClients();
			map.put("clientList", clients);
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
			return map;
		}
		return map;
	}

	@RequestMapping(value = "/show/{clientId}")
	@ResponseBody
	public Map<String, Object> showClient(@PathVariable("clientId") Long id) {
		log.trace("show client with id: {}", id);
		Map<String, Object> map = new HashMap<String, Object>();

		Client client = null;
		try {
			client = clientService.getClientById(id);
			map.put("client", client);
		} catch (ServiceException e) {
			log.error("Operation failed", e);
			showErrorMessage("Operation failed", e);
			return map;
		}
		return map;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public Map<String, Object> addClient(@RequestBody String json) throws ServletRequestBindingException {
		Client client = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			client = new ObjectMapper().readValue(json, Client.class);
			log.trace("add client: {}", client);
			clientService.saveClient(client);
			map.put("status", "всё хорошо");
			map.put("client", client);
			return map;
		} catch (IOException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		} catch (ServiceException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public Map<String, Object> updateClient(@RequestBody String json) throws ServletRequestBindingException {
		Client client = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			client = new ObjectMapper().readValue(json, Client.class);
			log.trace("update client: {}", client);
			clientService.updateClient(client);
			map.put("status", "всё хорошо");
			map.put("client", client);
			return map;
		} catch (IOException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		} catch (ServiceException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping("/delete/{clientId}")
	@ResponseBody
	public Map<String, Object> deleteClient(@PathVariable("clientId") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (true) {
			map.put("status", "error");
			map.put("message", "not supported");
			return map;
		}
		Client client = new Client();
		client.setId(id);
		try {
			log.trace("delete client with id: {}", id);
			clientService.delete(client);
			map.put("status", "всё хорошо");
			map.put("client", client);
		} catch (ServiceException e) {
			log.error("", e);
			map.put("status", "ошибка");
			map.put("message", e.getMessage());
		}

		return map;
	}


}
