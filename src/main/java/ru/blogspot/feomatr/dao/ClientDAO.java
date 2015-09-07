package ru.blogspot.feomatr.dao;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientDAO {

    List<Client> getAll() throws DAOException;

    Client getById(Long id) throws DAOException;

    Client create(Client client) throws DAOException;

    void delete(Client client) throws DAOException;

    void update(Client client) throws DAOException;

}
