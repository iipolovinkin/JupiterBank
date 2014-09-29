/**
 * 
 */
package ru.blogspot.feomatr.dao;

import java.util.List;

import ru.blogspot.feomatr.entity.Client;

/**
 * @author iipolovinkin
 *
 */
public interface ClientDAO {
	List<Client> getAllClients();
	
	Client getById(Long id);

	Client create(Client cl);

	boolean delete(Client cl);

	int update(Client cl);

}
