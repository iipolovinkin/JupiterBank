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

	@Inject
	public ClientServiceImpl(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
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
		return clientDAO.create(client);
	}

	@Override
	public int updateClient(Client client) {
		return clientDAO.update(client);
	}

	@Override
	public List<Client> getAllClients() {
		return clientDAO.getAllClients();
	}

	@Override
	public Client getClientById(Long id) {
		return clientDAO.getById(id);
	}

}
