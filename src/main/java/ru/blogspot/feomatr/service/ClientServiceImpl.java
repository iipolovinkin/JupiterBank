/**
 *
 */
package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO;

    public ClientServiceImpl() { }

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

    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
}
