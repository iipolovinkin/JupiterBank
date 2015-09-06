package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface ClientService {
    Client saveClient(Client client);

    void updateClient(Client client);

    List<Client> getAllClients();

    Client getClientById(Long id);

}
