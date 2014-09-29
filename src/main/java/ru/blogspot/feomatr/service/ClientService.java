/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

/**
 * @author iipolovinkin
 *
 */
public interface ClientService {
	Client saveClient(Client client);

	List<Client> getAllClients();

	Client getClientById(Long id);

}
