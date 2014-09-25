/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import javax.inject.Inject;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

/**
 * @author iipolovinkin
 *
 */
public class ClientServiceImpl implements ClientService {
	private ClientDAO clientDAO;

	public ClientServiceImpl() {
	}

	@Inject
	public ClientServiceImpl(ClientDAO clientDAO) {
		this.setClientDAO(clientDAO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.service.ClientService#saveClient(ru.blogspot.feomatr
	 * .entity.Client)
	 */
	@Override
	public Client saveClient(Client client) {
		List<Client> clients = getClientDAO().getAllClients();
		client.setId(clients.get(clients.size() - 1).getId() + 1);
		client = getClientDAO().create(client);
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		return getClientDAO().getAllClients();
	}

	@Override
	public Client getClientById(Long id) {
		return getClientDAO().getById(id);
	}

	/**
	 * @return the clientDAO
	 */
	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	/**
	 * @param clientDAO the clientDAO to set
	 */
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

}
