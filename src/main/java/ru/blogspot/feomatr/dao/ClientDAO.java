/**
 *
 */
package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientDAO {
    List<Client> getAllClients();

    Client getById(Long id);

    Client create(Client cl);

    boolean delete(Client cl);

    int update(Client cl);

}
