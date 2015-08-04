package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO;

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
        return clientDAO.getAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientDAO.getById(id);
    }

}
