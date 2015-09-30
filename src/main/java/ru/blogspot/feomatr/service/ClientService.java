package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientService {
    Client saveClient(Client client) throws ServiceException;

    void updateClient(Client client) throws ServiceException;

    List<Client> getAllClients() throws ServiceException;

    Client getClientById(Long id) throws ServiceException;

    void delete(Client client) throws ServiceException;
}
