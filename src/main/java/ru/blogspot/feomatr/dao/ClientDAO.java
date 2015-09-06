package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientDAO {

    List<Client> getAll();

    Client getById(Long id);

    Client create(Client client);

    void delete(Client client);

    void update(Client client);

}
